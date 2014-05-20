package com.hoons.term.handlersocket;

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

import com.hoons.apps.study.term.handlersocket.HandlersocketConnector;


public class HandlersocketTest {
	Logger logger = Logger.getLogger("executeTime");
	HandlersocketConnector connector;

	@Before
	public void setUp() throws Exception {
		connector = new HandlersocketConnector("192.168.0.15");
	}

	@After
	public void tearDown() throws Exception {
		connector.shutdownClient();
	}

	@Test
	public final void 조회_Thread1() throws Throwable {
		//given
		int callPerThread = 1;
		connector.makeReadClient();

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread10() throws Throwable {
		//given
		int callPerThread = 10;
		connector.makeReadClient();

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread50() throws Throwable {
		//given
		int callPerThread = 50;
		connector.makeReadClient();

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread100() throws Throwable {
		//given
		int callPerThread = 100;
		connector.makeReadClient();

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread500() throws Throwable {
		//given
		int callPerThread = 500;
		connector.makeReadClient();

		//when
		readData(callPerThread);
	}

	@Test
	public final void 조회_Thread1000() throws Throwable {
		//given
		int callPerThread = 1000;
		connector.makeReadClient();

		//when
		readData(callPerThread);
	}

	private void readData(int callPerThread) throws Throwable {
		// given
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
		int userCount = 1;
		connector.makeWriteClient();

		//when
		insertData(userCount);
	}


	@Test
	public final void 입력_Thread10() throws Throwable {
		//given
		int userCount = 10;
		connector.makeWriteClient();

		//when
		insertData(userCount);
	}

	@Test
	public final void 입력_Thread50() throws Throwable {
		//given
		int userCount = 50;
		connector.makeWriteClient();

		//when
		insertData(userCount);
	}

	@Test
	public final void 입력_Thread100() throws Throwable {
		//given
		int userCount = 100;
		connector.makeWriteClient();

		//when
		insertData(userCount);
	}

	@Test
	public final void 입력_Thread500() throws Throwable {
		//given
		int userCount = 500;
		connector.makeWriteClient();

		//when
		insertData(userCount);
	}

	@Test
	public final void 입력_Thread1000() throws Throwable {
		//given
		int userCount = 1000;
		connector.makeWriteClient();

		//when
		insertData(userCount);
	}

	private void insertData(int userCount) throws Throwable {
		//when
    	TestRunnable[] testRunnables = new TestRunnable[userCount];
        for(int index = 0; index < userCount; index++) {
            testRunnables[index] = new WriteThreadTester(connector, 1000000, index);
        }

       	MultiThreadedTestRunner multiThreadTestRunner = new MultiThreadedTestRunner(testRunnables);
       	long startedTime = System.nanoTime();
       	multiThreadTestRunner.runTestRunnables();
       	long endedTime = System.nanoTime();

		//then
       	double seconds = (double)(endedTime - startedTime) / 1000000000.0;
       	System.out.println("쓰레드 " + userCount + ":" +seconds);
	}

	class WriteThreadTester extends TestRunnable {
		private HandlersocketConnector pos;
		private int writeCount;
		private int threadNo;

		public WriteThreadTester(HandlersocketConnector pos, int writeCount, int threadNo) {
			this.pos = pos;
			this.writeCount = writeCount;
			this.threadNo = threadNo;
		}

		@Override
		public void runTest() throws Throwable {
			try {
				System.out.println("Thread No " + threadNo + " Start.");
				pos.writeTest(writeCount, threadNo);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Thread No " + threadNo + " Finished.");
			}
		}
	}

	class ReadThreadTester extends TestRunnable {
		private HandlersocketConnector pos;
		private int threadNo;

		public ReadThreadTester(HandlersocketConnector pos, int threadNo) {
			this.pos = pos;
			this.threadNo = threadNo;
		}

		@Override
		public void runTest() throws Throwable {
			try {
				System.out.println("Thread No " + threadNo + " Start.");
				pos.readTest(threadNo);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Thread No " + threadNo + " Finished.");
			}
		}
	}
}
