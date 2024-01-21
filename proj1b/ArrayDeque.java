public class ArrayDeque<T>  implements Deque<T> {
    private T[] array;
    private int size;
    private int length;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        nextFirst = 4;
        nextLast = 4;
    }
    private void expand() {
        T[] array1 = (T[]) new Object[length * 2];
        int ptr1 = nextFirst;
        int ptr2 = length;
        while (ptr1 != nextLast) {
            array1[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        nextFirst = length;
        nextLast = ptr2;
        array = array1;
        length *= 2;
    }
    private void shrink() {
        T[] array1 = (T[]) new Object[length / 2];
        int ptr1 = nextFirst;
        int ptr2 = length / 4;
        while (ptr1 != nextLast) {
            array1[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        nextFirst = length / 4;
        nextLast = ptr2;
        array = array1;
        length /= 2;
    }
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }
    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }
    @Override
    public void addFirst(T item) {
        if (size == length - 1) {
            expand();
        }
        nextFirst = minusOne((nextFirst));
        array[nextFirst] = item;
        size++;
    }
    @Override
    public void addLast(T item) {
        if (size == length - 1) {
            expand();
        }
        array[nextLast] = item;
        nextLast = plusOne(nextLast, length);
        size++;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        int ptr = nextFirst;
        while (ptr != nextLast) {
            System.out.println(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
    @Override
    public T removeFirst() {
        if (length / size >= 4 && length >= 16) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T ans = array[nextFirst];
        nextFirst = plusOne(nextFirst, length);
        size--;
        return ans;
    }
    @Override
    public T removeLast() {
        if (length / size >= 4 && length >= 16) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T ans = array[nextLast];
        size--;
        return ans;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = nextFirst;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }
}
