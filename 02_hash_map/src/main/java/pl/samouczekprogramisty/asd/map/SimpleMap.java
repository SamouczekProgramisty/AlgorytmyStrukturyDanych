package pl.samouczekprogramisty.asd.map;

public interface SimpleMap<K, V> {
    boolean isEmpty();
    int size();
    V get(K key);
    V put(K key, V value);
    void clear();
    V remove(K key);
    boolean containsKey(K key);
}
