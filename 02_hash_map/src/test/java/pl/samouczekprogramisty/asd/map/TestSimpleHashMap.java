package pl.samouczekprogramisty.asd.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TestSimpleHashMap {

    private static class Dummy {
        private final int equals;
        private final int hashCode;

        Dummy(int hashCode, int equals) {
            this.equals = equals;
            this.hashCode = hashCode;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Dummy) obj).equals == equals;
        }
    }

    private SimpleHashMap<Dummy, String> map;

    private Dummy d(int hashAndEquals) {
        return d(hashAndEquals, hashAndEquals);
    }

    private Dummy d(int hash, int equals) {
        return new Dummy(hash, equals);
    }

    @BeforeEach
    void setUp() {
        map = new SimpleHashMap<>();
    }

    @Test
    void shouldBeAbleToGetEmptyMapSize() {
        assertThat(map.size(), is(0));
    }

    @Test
    void shouldBeAbleToTellThatEmptyMapIsEmpty() {
        assertThat(map.isEmpty(), is(true));
    }

    @Test
    void shouldBeAbleToAddNewItemToMap() {
        map.put(d(10), "value");
        assertThat(map.isEmpty(), is(false));
    }

    @Test
    void shouldReturnNullIfKeyIsMissing() {
        String value = map.put(d(10), "value");
        assertThat(value, is(nullValue()));
    }

    @Test
    void shouldIncrementSizeWhenAddingElement() {
        map.put(d(10), "value");
        assertThat(map.size(), is(1));
    }

    @Test
    void shouldCreateTableWithDefaultSize() {
        assertThat(map.table.length, is(4));
    }

    @Test
    void shouldCreateTableWithDefaultThreshold() {
        assertThat(map.threshold, is(3));
    }

    @Test
    void shouldBeAbleToSayThatKey() {
        assertThat(map.threshold, is(3));
    }

    @Test
    void shouldReturnAlreadyInsertedValue() {
        Dummy key = d(10);
        map.put(key, "value");

        assertThat(map.get(key), is("value"));
    }

    @Test
    void shouldReturnNullForMissingValue() {
        Dummy key = d(10);
        assertThat(map.get(key), is(nullValue()));
    }

    @Test
    void shouldHandleNullKey() {
        Dummy key = null;
        map.put(key, "value");

        assertThat(map.get(key), is("value"));
    }

    @Test
    void shouldHandleNullValue() {
        Dummy key = d(10);
        map.put(key, null);

        assertThat(map.get(key), is(nullValue()));
    }

    @Test
    void shouldOverrideExistingValue() {
        Dummy key = d(10);
        map.put(key, "oldValue");
        map.put(key, "newValue");

        assertThat(map.get(key), is("newValue"));
    }

    @Test
    void shouldReturnPreviousValueWhenAddingOneForExistingKey() {
        Dummy key = d(10);
        map.put(key, "oldValue");
        String oldValue = map.put(key, "newValue");

        assertThat(oldValue, is("oldValue"));
    }

    @Test
    void shouldntIncrementSizeWhenReplacingOldValue() {
        Dummy key = d(10);
        map.put(key, "oldValue");
        map.put(key, "newValue");

        assertThat(map.size(), is(1));
    }

    @Test
    void shouldBeAbleToAddNewValueToExistingBucket() {
        map.put(d(10, 100), "value1");
        map.put(d(10, 200), "value2");

        assertThat(map.size(), is(2));
        assertThat(map.table[10 % map.table.length].size(), is(2));
    }

    @Test
    void shouldPutValueToValidBucket() {
        Dummy key = d(10);
        map.put(key, "value");

        assertThat(map.table[10 % map.table.length], is(notNullValue()));
    }

    @Test
    void shouldIncrementSizeWhenAddingNewKey() {
        map.put(d(10), "value1");
        map.put(d(11), "value2");

        assertThat(map.size(), is(2));
    }

    @Test
    void shouldResizeWhenHitThreshold() {
        map.put(d(10), "value1");
        map.put(d(11), "value2");
        assertThat(map.table.length, is(4));
        assertThat(map.threshold, is(3));
        map.put(d(12), "value3");

        assertThat(map.table.length, is(8));
        assertThat(map.threshold, is(6));
    }

    @Test
    void shouldAssignToDifferentBucketsAfterResize() {
        map.put(d(4), "value1");
        map.put(d(3), "value2");
        assertThat(map.table[0].size(), is(1)); // 4 % 4
        assertThat(map.table[3].size(), is(1)); // 3 % 4
        map.put(d(2), "value3");

        assertThat(map.table[4].size(), is(1)); // 4 % 8
        assertThat(map.table[3].size(), is(1)); // 3 % 8
        assertThat(map.table[2].size(), is(1)); // 2 % 8
        assertThat(map.table[0], is(nullValue()));
    }

    @Test
    void shouldBeAbleToClearMap() {
        map.put(d(1), "value");
        map.clear();

        assertThat(map.size(), is(0));
    }

    @Test
    void shouldReturnNullWhenRemovingNonExistingKey() {
        String removedValue = map.remove(d(10));
        assertThat(removedValue, is(nullValue()));
    }

    @Test
    void shouldReturnRemovedValue() {
        Dummy key = d(10);
        map.put(key, "value");
        String removedValue = map.remove(key);

        assertThat(removedValue, is("value"));
    }

    @Test
    void shouldntDecreaseSizeWhenValueWasntRemoved() {
        map.put(d(10), "value");
        map.put(d(10, 20), "value");
        map.put(d(15), "value");
        map.remove(d(20));

        assertThat(map.size(), is(3));
    }

    @Test
    void shouldDecreaseSizeWhenRemovingExistingKey() {
        map.put(d(10), "value");
        map.put(d(10, 20), "value");
        map.put(d(15), "value");
        map.remove(d(15));

        assertThat(map.size(), is(2));
    }
}
