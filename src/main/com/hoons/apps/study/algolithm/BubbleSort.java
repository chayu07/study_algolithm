package com.hoons.apps.study.algolithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class BubbleSort {

	public static void search(int[] arr) {
		int temp = 0;
		int compareIndex=0;
		boolean isAlreadyOrdered = true;
		for(int iterCount = arr.length -1; iterCount >= 0; iterCount--) {
			isAlreadyOrdered = true;
			for(int targetIndex = 0; targetIndex < iterCount; targetIndex++) {
				compareIndex = targetIndex + 1;
				if(arr[targetIndex] > arr[compareIndex]) {
					isAlreadyOrdered = false;
					temp = arr[targetIndex];
					arr[targetIndex] = arr[compareIndex];
					arr[compareIndex] = temp;
				}
			}
			print(arr);
			if(isAlreadyOrdered) break;

		}
	}

	private static void print(int[] arr) {
		for (int index = 0; index < arr.length; index++) {
			if(index <arr.length-1) {
				System.out.print(arr[index] + ",");
			}else {
				System.out.println(arr[index]);
			}
		}
	}

	public static void main(String[] argc) {
        int[] arr = {1,2,3,4,5,6,7,1000};

		BubbleSort.search(arr);
	}
}
