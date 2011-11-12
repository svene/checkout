package org.svenehrke.checkoutguice.moduletest;

public class MyOtherClient implements IOtherClient {
	private final IMyThing myThing;

	public MyOtherClient(IMyThing aMyThing) {
		myThing = aMyThing;
	}

	public IMyThing getMyThing() {
		return myThing;
	}
}
