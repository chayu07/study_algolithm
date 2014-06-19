package com.hoons.apps.study.algolithm;

public class Fibonachi {

	public static long fib(long n) {
		return fibo_iter(n, 0, 1);
	}

	private static long fibo_iter(long x, long a, long b) {
		if (x == 0)
			return a;
		else
			return fibo_iter(x - 1, b, a + b);
	}

	public static void main(String[] argc) {
		System.out.println(Fibonachi.fib(5));
	}
}
