package pl.samouczekprogramisty.asd.set;

public interface SimpleSet<E> {
    int size();
    boolean add(E element);
    boolean remove(E element);
    boolean contains(E element);
}
