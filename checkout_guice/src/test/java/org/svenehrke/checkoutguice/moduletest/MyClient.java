package org.svenehrke.checkoutguice.moduletest;

public class MyClient implements IClient {
	private final IMyThing myThing;

	public MyClient(IMyThing aMyThing) {
		myThing = aMyThing;
	}

	public IMyThing getMyThing() {
		return myThing;
	}
}
