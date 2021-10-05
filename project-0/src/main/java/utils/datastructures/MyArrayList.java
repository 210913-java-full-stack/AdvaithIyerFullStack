package utils.datastructures;

import java.util.Scanner;
import java.util.Arrays;


/**
 * Custom arrayList class
 */
public class MyArrayList<T> implements MyListInterface<T>{
    int size;
    int maxSize;
    Object[] array;


    public MyArrayList() {
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T t) {
        if (size == array.length - 1) {
            growArray();
        }
        array[size] = t;
        size++;
    }

    @Override
    public void add(T t, int index) {

        if (size == array.length - 1) {
            growArray();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid: Too small");
        }
        if (index > array.length - 1) {
            throw new IndexOutOfBoundsException("Invalid: Too big");
        }

        array[size] = t;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid: Out of array's range");
        }
        return (T) array[index];
    }

    @Override
    public void remove(int index) {
        // if index is negative or greater than size of size, we throw
        // Exception.
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Invalid: Out of array's range");
        }
        Object removed = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--; // this is used to reduce size of ArrayListCustom after the removal of an element.
        System.out.println("The element removed was " + removed);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size ;i++) {
            array[i] = null;
            size = 0;
        }
    }

    @Override
    public boolean contains(T t) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            if (sc.equals(t)) {
                System.out.println("The array contains");
                return true;
            }
        }
        return false;
    }


    private void growArray() {
        maxSize *= 2;
        array = Arrays.copyOf(array, maxSize);

    }
}