package pl.samouczekprogramisty.asd.set;

import pl.samouczekprogramisty.asd.map.SimpleHashMap;


public class SimpleHashSet<T> implements SimpleSet<T> {
    private static final Object PRESENT = new Object();

    private final SimpleHashMap<T, Object> map = new SimpleHashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(T item) {
        return map.put(item, PRESENT) == null;
    }

    @Override
    public boolean remove(T item) {
        return map.remove(item) == PRESENT;
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }
}
