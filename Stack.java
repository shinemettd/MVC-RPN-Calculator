/**
 * Stack<T> is a generic stack data structure implementation in Java.
 * It provides a last-in, first-out (LIFO) collection of items.
 *
 * @param <T> The type of elements stored in the stack.
 */
public class Stack<T> {
    private Object[] items;
    private int top;

    /**
     * Initializes a new instance of the CustomStack class with a specified initial size.
     *
     * @param size The initial size of the stack.
     */
    public Stack(int size) {
        items = new Object[size];
        top = -1;
    }

    /**
     * Initializes a new instance of the CustomStack class with a default initial size of 10.
     */
    public Stack() {
        this(32);
    }

    /**
     * Pushes an item onto the stack.
     *
     * @param item The item to be pushed onto the stack.
     * @return The item that was pushed.
     */
    public T push(T item) {
        if (top == items.length - 1) {
            resize();
        }
        items[++top] = item;
        return item;
    }

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @return The item at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = (T) items[top];
        items[top--] = null;
        return item;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @return The item at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) items[top];
    }

    /**
     * Doubles the size of the internal array when the stack is full.
     */
    private void resize() {
        Object[] newItems = new Object[items.length * 2];
        System.arraycopy(items, 0, newItems, 0, newItems.length);
        items = newItems;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return top + 1;
    }
}
