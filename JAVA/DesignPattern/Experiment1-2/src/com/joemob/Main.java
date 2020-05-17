package com.joemob;

public class Main {

    public static void main(String[] args) {
        Sort sort = new BubbleSort();
        sort.SortNumber();
        sort = new QuickSort();
        sort.SortNumber();
        sort = new MergeSort();
        sort.SortNumber();
        sort = new HeapSort();
        sort.SortNumber();
    }
}
