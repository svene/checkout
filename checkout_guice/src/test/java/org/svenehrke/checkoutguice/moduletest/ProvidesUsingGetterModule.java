package org.svenehrke.checkoutguice.moduletest;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class ProvidesUsingGetterModule implements Module {
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
	public IClient getMyThingClient() {
		System.out.println("ProvidesModule.getMyThingClient");
		return new MyClient(getMyThing());  // <-- calling getter directly circumvents guice and e.g. @Singleton is not valid anymore => don't do it
	}

	@Singleton
	@Provides
	public IOtherClient getMyOtherClient() {
		return new MyOtherClient(getMyThing()); // <-- calling getter directly circumvents guice and e.g. @Singleton is not valid anymore => don't do it
	}
}
