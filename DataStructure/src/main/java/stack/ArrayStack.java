package stack;

import array.Array;

public class ArrayStack<E> implements Stack<E> {
    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayStack(){
        array = new Array<E>();
    }

    public int getsize() {
        return array.getSize();
    }

    public Boolean isEmpty() {
        return array.isEmpty();
    }

    public void push(E e) {
       array.addLast(e);
    }

    public E pop() {
        return array.removeLast();
    }

    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append('[');
        for(int i=0;i<array.getSize();i++){
            stringBuilder.append(array.get(i));
            if(i!=array.getSize()-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] top");
        return stringBuilder.toString();
    }
}
