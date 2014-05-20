package com.hoons.apps.study.algolithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BitArray {
	private BitSet bitSet;

	public void sort(List<Integer> intDatas) {
		bitSet = new BitSet(intDatas.size());

		for (int curInt : intDatas) {
			bitSet.set(curInt, true);
		}
	}

	public void printSortData() {
		for(int bitIndex=0; bitIndex<bitSet.size(); bitIndex++ ){
			boolean isExistBit = bitSet.get(bitIndex);
			if(isExistBit) {
				System.out.println(bitIndex);
			}
		}
	}

	public static void main(String[] argc) {

		List<Integer> values = new ArrayList<Integer>();
		for(int index=10000000; index > 0; index--) {
			values.add(index);
		}

		long startTime = System.currentTimeMillis();
		System.out.println("startTime[" + startTime +"]");

		BitArray bitArray = new BitArray();
		bitArray.sort(values);

		long endTime = System.currentTimeMillis();
		System.out.println("endTime[" + endTime +"]");
		System.out.println("execTime[" + (endTime - startTime) + "]");

//		bitArray.printSortData();
	}
}
