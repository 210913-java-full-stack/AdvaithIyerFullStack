package utils.datastructures;

/**
 * @param <E> allows the use of a variety of data types, including classes and interfaces
 */
public interface MyListInterface<E> {
    int size();

    void add(E e);

    void add(E e, int index);

    E get(int index);

    void remove(int index);

    void clear();

    boolean contains(E e);

}

