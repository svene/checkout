package org.svenehrke.checkoutguice.moduletest;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class ProvidesModule implements Module {
	public void configure(Binder aBinder) {
	}

	@Singleton
	@Provides
	public IMyThing getMyThing() {
		System.out.println("ProvidesModule.getMyThing");
		return new MyThing();
	}

	@Singleton
	@Provides
	public IClient getMyThingClient(IMyThing mt) {
		System.out.println("ProvidesModule.getMyThingClient");
		return new MyClient(mt);
	}

	@Singleton
	@Provides
	public IOtherClient getMyOtherClient(IMyThing mt) {
		return new MyOtherClient(mt);
	}
/*
	@Singleton
	@Provides
	public IClient getMyThingClient() {
		System.out.println("ProvidesModule.getMyThingClient");
		return new MyClient(getMyThing());
	}

	@Singleton
	@Provides
	public IOtherClient getMyOtherClient() {
		return new MyOtherClient(getMyThing());
	}
*/
}
