package pl.samouczekprogramisty.asd.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestSimpleHashMap {

    private static class Dummy {
        private final int equals;
        private final int hashCode;

        public Dummy(int hashCode, int equals) {
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
    public void shouldReturnInsertedValue() {
        String value = map.put(d(10), "value");
        assertThat(value, is("value"));
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

}
