package com.hoons.apps.study.algolithm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class BigOhNotationTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private int setIndex() {
		int n = 1000;
		return n;
	}

	@Test
	public void ex1() {
		int n = setIndex();
		long startTime = System.nanoTime();


		for(int i = 0 ; i < n; i++)  {
			int a = i;
		}

		long endTime = System.nanoTime();

		System.out.println("ex1() execute time : " + (endTime - startTime));
	}

	@Test
	public void ex2() {
		int n = setIndex();
		long startTime = System.nanoTime();


		for(int i = 0 ; i < n; i+=2)  {
			int a = i;
		}

		long endTime = System.nanoTime();

		System.out.println("ex2() execute time : " + (endTime - startTime));
	}



	@Test
	public void ex3() {
		int n = setIndex();
		long startTime = System.nanoTime();


		for(int i = 0 ; i < n * n ; i+=2)  {
			int a = i;
		}

		long endTime = System.nanoTime();

		System.out.println("ex3() execute time : " + (endTime - startTime));
	}

	@Test
	public void ex4() {
		int n = setIndex();
		long startTime = System.nanoTime();

		for(int i = 0 ; i < n; i++)  {
			for(int j = 0; j <= i; j++) {
				int a = i;
			}
		}

		long endTime = System.nanoTime();

		System.out.println("ex4() execute time : " + (endTime - startTime));
	}

	@Test
	public void ex5() {
		int n = setIndex();
		long startTime = System.nanoTime();


		for(int i = 0 ; i < n * n; i++)  {
			for (int j = 0; j <= i; j++) {
				int a = i;
			}
		}

		long endTime = System.nanoTime();

		System.out.println("ex5() execute time : " + (endTime - startTime));
	}

}
