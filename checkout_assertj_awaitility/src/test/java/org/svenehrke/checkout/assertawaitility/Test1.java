package org.svenehrke.checkout.assertawaitility;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

import static com.jayway.awaitility.Awaitility.await;

public class Test1 {

	int counter = 0;

	@Test
	public void test1() throws Exception {
		//when
//		asynchronousMessageQueue.sendPing();
		//then
		await().until( () -> getNumberOfReceivedPackets() == 3 );
	}



	private int getNumberOfReceivedPackets() {
		System.out.println("before wait");
		// see https://twitter.com/quosco/status/454355031442333696
		int oneSecond = 1000 * 1000 * 1000;
		LockSupport.parkNanos(oneSecond);
		System.out.println("after wait");
		counter++;
		System.out.println("counter = " + counter);
		return counter;
	}
}
