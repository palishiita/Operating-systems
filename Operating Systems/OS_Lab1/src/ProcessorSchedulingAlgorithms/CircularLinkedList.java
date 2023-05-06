package ProcessorSchedulingAlgorithms;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements List<T> {

    //nested Node class
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T d, Node<T> n) {
            setData(d);
            setNext(n);
        }

        public T getData() {
            return data;
        }
        public void setData(T d) {
            data = d;
        }
        public Node<T> getNext() {
            return next;
        }
        public void setNext(Node<T> n) {
            next = n;
        }
    } //end nested Node class

    private int theSize;
    private int modCount;
    private Node<T> tail;

    //constructor
    public CircularLinkedList() {
        tail = new Node<>(null, null);
        modCount = 0;
        theSize = 0;
    }

    //clear list by getting rid of data in tail and setting pointer of tail to itself
    @Override
    public void clear() {
        tail.setData(null);
        tail.setNext(tail);
        theSize = 0;
    }

    //return size of the list
    @Override
    public int size() {
        return theSize;
    }

    //return if list is empty or not
    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    //get data from node at a given index
    @Override
    public T get(int index) {
        Node<T> theNode = getNode(index);
        return theNode.getData();
    }

    //set data of a node at a given index to something new
    @Override
    public T set(int index, T newValue) {
        Node<T> nodeSet = getNode(index);
        T old = nodeSet.getData();
        nodeSet.setData(newValue);
        return old;
    }

    /* To add new node to list, there are 4 case:
       if the list is empty then you are adding the first node
       if you want to add the node at index 0 with other nodes in the list
       if you want to add the node at the end of the list i.e. index = list.size
       if you want to add the node in the middle of the list;
     */
    @Override
    public void add(int index, T newValue) {
        if (index == 0 && size() == 0) addFirst(newValue); // if the list is empty then you are adding the first node
        else if (index == 0) addBeginning(newValue); // add the node at index 0 with other nodes in the list
        else if (index == size()) addLast(newValue); // add the node at the end of the list
        else {
            Node<T> nextNode = getNode(index);
            Node<T> prevNode = getNode(index - 1);
            Node<T> newNode = new Node<T>(newValue, nextNode);
            prevNode.setNext(newNode);
        }
        theSize++;
        modCount++;
    }

    //return true if new node was added to the end of the listlist
    @Override
    public void add(T newValue) {
        add(size(), newValue);
    }

    //remove a node at a given index, there are 3 cases
    //if you want to remove the first node of a list
    //if you want to remove the last node of the list
    //if you want to remove a node from the middle of the list
    @Override
    public void remove(int index) {
        T old = null;
        if (index == 0) old = removeFirst(getNode(index));
        else if (index == size() - 1) old = removeLast(getNode(index));
        else old = removeNode(getNode(index), getNode(index - 1));
        theSize--;
        modCount++;
    }

    //rotate the head of the list to the tail of the list, a counter-clock wise rotate
    //set the new tail to the head, pointers remain the name
    public void rotate() {
        Node<T> currHeadNode = getNode(0);
        tail = currHeadNode;
    }

    //added this method for CPU scheduler to get the index of a Process when running PSJF
    public int indexOf(T value) {
        for (int i = 0; i < theSize; i++) {
            if (value.equals(getNode(i).getData())) return i;
        }
        return -1;
    }

    //returns a new linked list iterator object to iterate through list
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    //get node at a given index
    private Node<T> getNode(int index) {
        return (getNode(index, 0, size() - 1));
    }

    //will return node at a given index and check the bounds of the index
    private Node<T> getNode(int index, int lower, int upper) {
        Node<T> currNode;
        if (index < lower || index > upper)
            throw new IndexOutOfBoundsException();
        currNode = tail.getNext();
        for (int i = 0; i < index; i++) currNode = currNode.getNext();
        return currNode;
    }

    //adding the first node of a list
    private void addFirst(T newString) {
        tail.setData(newString);
        tail.setNext(tail);
    }

    //adding a node at the front of the list
    private void addBeginning(T newString) {
        Node<T> nextNode = getNode(0);
        Node<T> newNode = new Node<T>(newString, nextNode);
        tail.setNext(newNode);
    }

    //adding a node at the end of the list
    private void addLast(T newString) {
        Node<T> prevNode = getNode(size() - 1);
        Node<T> newNode = new Node<T>(newString, getNode(0));
        tail = newNode;
        prevNode.setNext(newNode);
    }

    //removing a node at the middle of the list
    private T removeNode(Node<T> currNode, Node<T> prevNode) {
        T old = currNode.getData();
        prevNode.setNext(currNode.getNext());
        return old;
    }

    //removing a node at the front of the list
    private T removeFirst(Node<T> firstNode) {
        T old = firstNode.getData();
        tail.setNext(firstNode.getNext());
        return old;
    }

    //removing a node at the end of the list
    private T removeLast(Node<T> lastNode) {
        T old = lastNode.getData();
        Node<T> secondToLastNode = getNode(size() - 2);
        tail = secondToLastNode;
        secondToLastNode.setNext(lastNode.getNext());
        return old;
    }


    //LLI class
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> previous;
        private Node<T> current;
        private int expectedModCount;
        private boolean okToRemove;

        //constructor of linked list iterator, will set the current node to the first node of the list and the previous will
        //be the node before the head which is the tail node
        LinkedListIterator() {
            current = getNode(0);
            previous = tail;
            expectedModCount = modCount;
            okToRemove = false;
        }

        //list will always have a next node since the tail node points to the head node
        public boolean hasNext() {
            return true;
        }

        //will return the data of the node after the current node in the list
        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            T nextVal = current.getData();
            previous = current;
            current = current.getNext();
            okToRemove = true;
            return nextVal;
        }

        //remove a node from the list
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            CircularLinkedList.this.removeNode(current, previous);
            expectedModCount++;
            okToRemove = false;
        }
    }// end LLI class
}

