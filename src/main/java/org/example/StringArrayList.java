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

    private String[] grow() {
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
            array = grow();
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
            array = grow();
        }
        size++;
        for (int i = size - 1; i > index; i--) {
            String tmp = array[i];
            array[i] = array[i - 1];
            array[i - 1] = tmp;
        }
        array[index] = item;
        return item;
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
        return item;
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
        String item = array[index];
        array[index] = null;
        if (index < size - 1) {
            for (int j = index + 1, i = index; j < size; j++, i++) {
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
        String[] arr = Arrays.copyOf(array, size);
        quickSort(arr, 0, arr.length - 1);

        return isExist(arr, item);
    }

    public boolean isExist(String[] arr, String element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.compareTo(arr[mid]) == 0) {
                return true;
            }

            if (element.compareTo(arr[mid]) < 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void quickSort(String[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(String[] arr, int begin, int end) {
        String pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            int resultOfCompare = arr[j].compareTo(pivot);
            if (resultOfCompare <= 0) { // arr[j] <= pivot
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(String[] arr, int left, int right) {
        String temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
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
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if(otherList == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        String[] arrString = Arrays.copyOf(array, size);
        return Arrays.equals(arrString, otherList.toArray());
    }

    private void shrinkToSize() {
        capacity = size;
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
        return Arrays.copyOf(array, size);
    }

}
