package org.svenehrke.checkoutguice.moduletest;

import com.google.inject.Binder;
import com.google.inject.Module;

public class LinkedBindingModule implements Module {
	public void configure(Binder aBinder) {
		aBinder.bind(IMyThing.class).to(MyThing.class);
	}
}
