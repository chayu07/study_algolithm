package com.hoons.apps.study.algolithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuickSort {


	public static int[] sort(int[] list) {

		int left = 0;
		int right = list.length - 1;
		int pivot = (left + right) / 2;

		interalSort(left, right, pivot, list);

		return list;
	}

	private static int[] interalSort(int left, int right, int pivot, int[] list) {
		if (left < right) {
			int newPivot = partition(left, right, pivot, list);
			interalSort(left, newPivot - 1, newPivot - 2, list);
			interalSort(newPivot + 1, right, newPivot + 2, list);
		}
		return list;
	}

	private static int partition(int left, int right, int pivot, int[] list) {
		int pivotValue = list[pivot];
		int newPivot = left;
		swapArr(right, pivot, list);
		for (int index = left; index < right; index++) {
			int compareValue = list[index];
			if (compareValue <= pivotValue) {
				swapArr(index, newPivot, list);
				newPivot++;
			}
		}
		swapArr(right, newPivot, list);
		return newPivot;
	}

	private static void swapArr(int a, int b, int[] list) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	public static void print(int[] list) {
		for (int index = 0; index < list.length; index++) {
			if (index < list.length - 1) {
				System.out.println(list[index] + ",");
			} else {
				System.out.println(list[index]);
			}
		}
	}

	public static void main(String[] argc) {
		int size = 10000000;
		int[] arr = new int[size];

		Random r = new Random(size);
		for(int i = 0; i < size; i++) {
			arr[i] = r.nextInt();
		}

		QuickSort.sort(arr);
	}
}
