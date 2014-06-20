package com.hoons.apps.study.algolithm;

import java.math.BigInteger;

public class Factorial {
	public static BigInteger fact(int num) {
		boolean isNegativeNum = false;

		if(num ==0) return BigInteger.ZERO;

		if(Integer.signum(num) == -1) isNegativeNum = true;

		if(isNegativeNum) {
			if(num >= -1) return BigInteger.ONE;
		}else {
			if(num <= 1) return BigInteger.ONE;
		}

		BigInteger total = BigInteger.ONE;
		BigInteger sumNumber = BigInteger.valueOf(num);
		total = total.multiply(sumNumber);

		int nextNum = 1;
		if(isNegativeNum) {
			nextNum = num + 1;
		}else {
			nextNum = num - 1;
		}

		return total.multiply(fact(nextNum));
	}

	public static void main(String[] argc) {
		System.out.println(Factorial.fact(1));
	}
}
