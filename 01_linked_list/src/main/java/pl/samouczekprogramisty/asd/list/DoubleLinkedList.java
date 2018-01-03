package pl.samouczekprogramisty.asd.list;

public class DoubleLinkedList<E> {

    // accessible from unit tests
    Node<E> first;
    Node<E> last;

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        Node(E element) {
            this.element = element;
        }
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

    public boolean add(E element) {
        if (last == null) {
            first = last = new Node<>(element);
            return true;
        }
        Node<E> newNode = new Node<>(element);
        newNode.previous = last;
        last.next = newNode;
        last = newNode;
        return true;
    }

    public void clear() {
        first = null;
        last = null;
    }

    public E get(int index) {
        return getNode(index).element;
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

    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    public boolean add(int index, E element) {
        if (first == null && index == 0) {
            first = new Node<>(element);
            last = first;
            return true;
        }

        Node<E> nodeAtIndex = getNode(index);

        // adding at the end of the list
        if (nodeAtIndex == null) {
            Node<E> previousLast = last;
            last = new Node<>(element);
            last.previous = previousLast;
            previousLast.next = last;
            return true;
        }

        // adding at the beginning of the list
        if (nodeAtIndex.previous == null) {
            Node<E> previousFirst = first;
            first = new Node<>(element);
            first.next = previousFirst;
            previousFirst.previous = first;
            return true;
        }

        Node<E> newNode = new Node<>(element);
        Node<E> previous = nodeAtIndex.previous;
        previous.next = newNode;
        newNode.previous = previous;

        newNode.next = nodeAtIndex;
        nodeAtIndex.previous = newNode;
        return true;
    }

    public E remove(int index) {
        Node<E> nodeToRemove = getNode(index);
        Node<E> previousNode = nodeToRemove.previous;
        Node<E> nextNode = nodeToRemove.next;
        E removedElement = nodeToRemove.element;

        // removing first node
        if (previousNode == null) {
            if (nextNode == null) {
                first = null;
                last = null;
            }
            else {
                first = nextNode;
                nextNode.previous = null;
            }
            return removedElement;
        }

        // removing last node
        if (nextNode == null) {
            last = previousNode;
            previousNode.next = null;
            return removedElement;
        }

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        return removedElement;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> node = first;
        while (node != null) {
            builder.append(node.element);
            builder.append(", ");
            node = node.next;
        }
        return builder.toString();
    }
}
