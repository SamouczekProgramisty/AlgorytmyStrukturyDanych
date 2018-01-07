package pl.samouczekprogramisty.asd.map;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SimpleHashMap<K, V> {

    private static final float LOAD_FACTOR = 0.75F;

    private static final int INITIAL_CAPACITY = 4;

    private int size;

    // just for test access
    int threshold;

    // just for test access
    List<Entry<K, V>>[] table;

    private static class Entry<K, V> {

        private final K key;

        private final V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(getKey(), entry.getKey()) &&
                    Objects.equals(getValue(), entry.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
    }

    public SimpleHashMap() {
        this(INITIAL_CAPACITY);
    }

    public SimpleHashMap(int initialCapacity) {
        table = new List[initialCapacity];
        threshold = (int) (initialCapacity * LOAD_FACTOR);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int hash = hash(key);
        List<Entry<K, V>> bucket = table[hash];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if ((key == entry.key) || (key != null && key.equals(entry.key))) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public V put(K key, V value) {
        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }
        table[hash].add(new Entry<>(key, value));
        size++;
        return value;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() % table.length;
    }

    // V remove(Object key);

    // void clear();

}