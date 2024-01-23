package org.example;

import java.util.Arrays;

public class StringArrayList implements StringList {
    private String[] array;
    private int size;
    private int capacity;

    public StringArrayList() {
        size = 0;
        capacity = 10;
        array = new String[capacity];
    }

    private String[] addCapacity() {
        capacity = ((capacity * 3) / 2) + 1;
        String[] newArray = new String[capacity];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    @Override
    public String add(String str) {
        if(str == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        if (size == capacity) {
            array = addCapacity();
            size++;
            array[size - 1] = str;
            return str;
        } else {
            size++;
            array[size - 1] = str;
            return str;
        }
    }

    @Override
    public String add(int index, String item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(size == capacity){
            array = addCapacity();
        }
        size++;
        for (int i = size - 1; i > index; i--) {
            String tmp = array[i];
            array[i] = array[i - 1];
            array[i - 1] = tmp;
        }
        array[index] = item;
        return new String(item);
    }

    @Override
    public String set(int index, String item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
        return new String(item);
    }

    @Override
    public String remove(String item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        int index = indexOf(item);
        if (index != -1) {
            return remove(index);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        String item = new String(array[index]);
        array[index] = null;
        if (index < size - 1) {
            for (int j = index + 1, i = index; j < size; j++, i++) {
                String tmp = array[i];
                array[i] = array[j];
                array[j] = null;
            }
        }
        size--;
        if (size > 0) {
            if (capacity / size > 2) {
                shrinkToSize();
            }
        }

        return item;
    }

    @Override
    public boolean contains(String item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        for (int i = 0; i < size; i++) {
            if(array[i].equals(item)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return new String(array[index]);
    }

    @Override
    public boolean equals(StringList otherList) {
        if(otherList == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        return Arrays.equals(array, otherList.toArray());
    }

    private void shrinkToSize() {
        capacity = size;
        String[] newArray = new String[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 10;
        array = new String[capacity];
    }

    @Override
    public String[] toArray() {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

}
