package com.hoons.term.mongodb;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import term.mongodb.MongodbConnector;


public class MongodbTest {
	private MongodbConnector connector;
	public static int availableConut;

	@Before
	public void setUp() throws Exception {
		connector = new MongodbConnector("192.168.0.16");
		connector.makeClient();
	}

	@After
	public void tearDown() throws Exception {
		connector.shutdownClient();
		availableConut = 0;
	}

	@Test
	public final void 조회_Thread1() throws Throwable {
		//given
		int callPerThread = 1;

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread10() throws Throwable {
		//given
		int callPerThread = 10;

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread50() throws Throwable {
		//given
		int callPerThread = 50;

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread100() throws Throwable {
		//given
		int callPerThread = 100;

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread500() throws Throwable {
		//given
		int callPerThread = 500;

		//when
		readData(callPerThread);
	}


	@Test
	public final void 조회_Thread1000() throws Throwable {
		//given
		int callPerThread = 1000;

		//when
		readData(callPerThread);
	}

	private void readData(int callPerThread) throws Throwable {
		//given
		TestRunnable[] testRunnables = new TestRunnable[callPerThread];
		for(int index = 0; index < callPerThread; index++) {
			testRunnables[index] = new ReadThreadTester(connector, index);
		}

		//when
       	MultiThreadedTestRunner multiThreadTestRunner = new MultiThreadedTestRunner(testRunnables);
       	long startedTime = System.nanoTime();
       	multiThreadTestRunner.runTestRunnables();
       	long endedTime = System.nanoTime();


		//then
       	double seconds = (double)(endedTime - startedTime) / 1000000000.0;
       	System.out.println("쓰레드 " + callPerThread + ":" +seconds);
	}


	@Test
	public final void 입력_Thread1() throws Throwable {
		//given
		int callPerThread = 1;

		//when
		insertData(callPerThread);
	}

	@Test
	public final void 입력_Thread10() throws Throwable {
		//given
		int callPerThread = 10;

		//when
		insertData(callPerThread);
	}

	@Test
	public final void 입력_Thread50() throws Throwable {
		//given
		int callPerThread = 50;

		//when
		insertData(callPerThread);
	}

	@Test
	public final void 입력_Thread100() throws Throwable {
		//given
		int callPerThread = 100;

		//when
		insertData(callPerThread);
	}

	@Test
	public final void 입력_Thread500() throws Throwable {
		//given
		int callPerThread = 500;

		//when
		insertData(callPerThread);
	}

	@Test
	public final void 입력_Thread1000() throws Throwable {
		//given
		int callPerThread = 1000;
		availableConut = callPerThread;

		//when
		insertData(callPerThread);
	}

	private void insertData(int userCount) throws Throwable {
		TestRunnable[] testRunnables = new TestRunnable[userCount];
		for(int index = 0; index < userCount; index++) {
			testRunnables[index] = new WriteThreadTester(connector, 10000, index);
		}

		//when
       	MultiThreadedTestRunner multiThreadTestRunner = new MultiThreadedTestRunner(testRunnables);
       	long startedTime = System.nanoTime();
       	multiThreadTestRunner.runTestRunnables();
       	long endedTime = System.nanoTime();


		//then
       	double seconds = (double)(endedTime - startedTime) / 1000000000.0;
       	System.out.println();
//       	connector.remove();
	}

	class WriteThreadTester extends TestRunnable {
		private MongodbConnector pos;
		private int writeCount;
		private int threadNo;

		public WriteThreadTester(MongodbConnector pos, int writeCount, int threadNo) {
			this.pos = pos;
			this.writeCount = writeCount;
			this.threadNo = threadNo;
		}

		@Override
		public void runTest() throws Throwable {
			try {
				System.out.println("Thread No " + threadNo + " Started.");
				pos.writeTest(writeCount, threadNo);
			} catch (Exception e) {
				System.out.println("Thread No " + threadNo + " Exception. Msg[" + e.getMessage() + "] AvailableThreadCount[" + --availableConut +"]" );
				//e.printStackTrace();
			} finally {
				System.out.println("Thread No " + threadNo + " Finished.");
			}
		}
	}

	class ReadThreadTester extends TestRunnable {
		private MongodbConnector pos;
		private int threadNo;

		public ReadThreadTester(MongodbConnector pos, int threadNo) {
			this.pos = pos;
			this.threadNo = threadNo;
		}

		@Override
		public void runTest() throws Throwable {
			try {
				System.out.println("Thread No " + threadNo + " Started.");
				pos.readTest(threadNo);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Thread No " + threadNo + " Exception. Msg[" + e.getMessage() + "] AvailableThreadCount[" + --availableConut +"]" );
			} finally{
				System.out.println("Thread No " + threadNo + " Finished.");
			}
		}
	}
}
