package pl.samouczekprogramisty.asd.list;

public class SingleLinkedList<E> {

    private Node<E> first;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }

    private Node<E> getLast() {
        Node<E> currentNode = first;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private Node<E> getNode(int index) {
        if (isEmpty() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index);
        }

        Node<E> currentNode = first;
        int currentIndex = index;
        while (currentIndex > 0) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException("Index " + index);
            }
            currentNode = currentNode.next;
            currentIndex--;
        }

        return currentNode;
    }

    public int size() {
        int size = 0;
        Node<E> currentNode = first;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        if (first == null) {
            first = newNode;
            return true;
        }
        Node<E> lastNode = getLast();
        lastNode.next = newNode;
        return true;
    }

    public void clear() {
        first = null;
    }

    public E get(int index) {
        return getNode(index).element;
    }

    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    public void add(int index, E element) {

    }

    public E remove(int index) {
        int currentIndex = 0;
        Node<E> previousNode = null;
        Node<E> currentNode = first;
        while (currentIndex < index) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException("Index " + index);
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }



        return null;
    }
}
