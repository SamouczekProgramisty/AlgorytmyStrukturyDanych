package pl.samouczekprogramisty.asd.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestSimpleHashMap {

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

    @Before
    public void setUp() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void shouldBeAbleToGetEmptyMapSize() {
        assertThat(map.size(), is(0));
    }

    @Test
    public void shouldBeAbleToTellThatEmptyMapIsEmpty() {
        assertThat(map.isEmpty(), is(true));
    }

    @Test
    public void shouldBeAbleToAddNewItemToMap() {
        map.put(d(10), "value");
        assertThat(map.isEmpty(), is(false));
    }

    @Test
    public void shouldReturnNullIfKeyIsMissing() {
        String value = map.put(d(10), "value");
        assertThat(value, is(nullValue()));
    }

    @Test
    public void shouldIncrementSizeWhenAddingElement() {
        map.put(d(10), "value");
        assertThat(map.size(), is(1));
    }

    @Test
    public void shouldCreateTableWithDefaultSize() {
        assertThat(map.table.length, is(4));
    }

    @Test
    public void shouldCreateTableWithDefaultThreshold() {
        assertThat(map.threshold, is(3));
    }

    @Test
    public void shouldBeAbleToSayThatKey() {
        assertThat(map.threshold, is(3));
    }

    @Test
    public void shouldReturnAlreadyInsertedValue() {
        Dummy key = d(10);
        map.put(key, "value");

        assertThat(map.get(key), is("value"));
    }

    @Test
    public void shouldReturnNullForMissingValue() {
        Dummy key = d(10);
        assertThat(map.get(key), is(nullValue()));
    }

    @Test
    public void shouldHandleNullKey() {
        Dummy key = null;
        map.put(key, "value");

        assertThat(map.get(key), is("value"));
    }

    @Test
    public void shouldHandleNullValue() {
        Dummy key = d(10);
        map.put(key, null);

        assertThat(map.get(key), is(nullValue()));
    }

    @Test
    public void shouldOverrideExistingValue() {
        Dummy key = d(10);
        map.put(key, "oldValue");
        map.put(key, "newValue");

        assertThat(map.get(key), is("newValue"));
    }

    @Test
    public void shouldReturnPreviousValueWhenAddingOneForExistingKey() {
        Dummy key = d(10);
        map.put(key, "oldValue");
        String oldValue = map.put(key, "newValue");

        assertThat(oldValue, is("oldValue"));
    }

    @Test
    public void shouldntIncrementSizeWhenReplacingOldValue() {
        Dummy key = d(10);
        map.put(key, "oldValue");
        map.put(key, "newValue");

        assertThat(map.size(), is(1));
    }

    @Test
    public void shouldBeAbleToAddNewValueToExistingBucket() {
        map.put(d(10, 100), "value1");
        map.put(d(10, 200), "value2");

        assertThat(map.size(), is(2));
        assertThat(map.table[10 % map.table.length].size(), is(2));
    }

    @Test
    public void shouldPutValueToValidBucket() {
        Dummy key = d(10);
        map.put(key, "value");

        assertThat(map.table[10 % map.table.length], is(notNullValue()));
    }

    @Test
    public void shouldIncrementSizeWhenAddingNewKey() {
        map.put(d(10), "value1");
        map.put(d(11), "value2");

        assertThat(map.size(), is(2));
    }

    @Test
    public void shouldResizeWhenHitThreshold() {
        map.put(d(10), "value1");
        map.put(d(11), "value2");
        assertThat(map.table.length, is(4));
        assertThat(map.threshold, is(3));
        map.put(d(12), "value3");

        assertThat(map.table.length, is(8));
        assertThat(map.threshold, is(6));
    }

    @Test
    public void shouldAssignToDifferentBucketsAfterResize() {
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

}
