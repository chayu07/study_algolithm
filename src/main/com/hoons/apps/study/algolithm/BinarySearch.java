package com.hoons.apps.study.algolithm;


public class BinarySearch {

    private static int search(int[] arr, int findValue) {
        int findIndex = -1;
        if(arr.length <= 0) return findIndex;

        int low = 0;
        int high = arr.length - 1;
        findIndex = findIndex(arr, findValue ,low, high);

        return findIndex;
    }

    private static int findIndex(int[] arr, int findValue, int low, int high) {


        int mid = (low + high) / 2;
        System.out.println("low:" + low + " high:" + high + " mid:" + mid);
        if(low > high) return -1;
        if(arr[mid] == findValue) return mid;

        if(arr[mid] > findValue)  {
            return findIndex(arr, findValue, low, mid-1);
        }else if( arr[mid] < findValue ){
            return findIndex(arr, findValue, mid+1, high);
        }

        return mid;
    }

    public static void main(String[] argc) {
        int[] arr = { 5, 8, 11, 15, 20, 25, 31,40, 41,100,220, 300};
        int findValue = 100;

        System.out.println(BinarySearch.search(arr, findValue));
    }
}
