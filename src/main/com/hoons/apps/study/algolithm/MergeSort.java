package com.hoons.apps.study.algolithm;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {5,3,7,6,2,1,4,10,200,23,100,1};

		MergeSort sort = new MergeSort();
		sort.sort(arr);

		for (int index = 0; index < arr.length; index++) {
			if(index <arr.length-1) {
				System.out.print(arr[index] + ",");
			}else {
				System.out.print(arr[index]);
			}
		}
	}

	private int[] sort(int[] arr) {

		if( arr.length > 1) {
			int left = 0;
			int right = arr.length - 1;
			int[] tempSpace = new int[arr.length];
			internalSort(arr, tempSpace, left, right);
		}

		return arr;
	}

	private void internalSort(int[] arr, int[] tempSpace, int left, int right) {
		if(right > left) {
			int middle = (left + right) / 2;
			internalSort(arr, tempSpace, left, middle);
			internalSort(arr, tempSpace, middle + 1, right);
			merge(arr, left, right, middle, tempSpace);
		}
	}

	private void merge(int[] arr, int left, int right, int middle, int[] temp) {
		int left_cur = left;
		int right_cur = middle + 1;
		int temp_cur = 0;

		int left_end = middle;
		int right_end = right;

		int numberOfElement = right - left + 1;

		while((left_cur <= left_end) && (right_cur <= right_end)) {

			int left_value = arr[left_cur];
			int right_value = arr[right_cur];

			if(left_value >= right_value) {
				temp[temp_cur] = right_value;
				right_cur++;
				temp_cur++;
			}else {
				temp[temp_cur] = left_value;
				left_cur++;
				temp_cur++;
			}
		}

		while(left_cur <= left_end) {
			temp[temp_cur++] = arr[left_cur++];
		}

		while(right_cur <= right_end) {
			temp[temp_cur++] = arr[right_cur++];
		}

		for(int index=0; index < numberOfElement; index++) {
			arr[left] = temp[index];
			left++;
		}
	}
}
