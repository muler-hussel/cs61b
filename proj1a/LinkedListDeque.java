public class LinkedListDeque<T> {
    public class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
        private Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        Node ll = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = ll;
        sentinel.next = ll;
        size++;
    }
    public void addLast(T item) {
        Node ll = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = ll;
        sentinel.prev = ll;
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ans = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return ans;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ans = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return ans;
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(Node ptr, int index) {
        if (index == 0) {
            return ptr.item;
        } else {
            return getRecursiveHelper(ptr.next, index - 1);
        }
    }
}
