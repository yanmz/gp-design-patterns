package stack;

public interface Stack<E> {
    int getsize();

    Boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
