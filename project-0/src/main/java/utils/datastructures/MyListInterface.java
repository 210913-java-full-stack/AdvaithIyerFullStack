package utils.datastructures;

public interface MyListInterface<E> {
    int size();

    void add(E e);

    void add(E e, int index);

    E get(int index);

    void remove(int index);

    void clear();

    boolean contains(E e);

}

