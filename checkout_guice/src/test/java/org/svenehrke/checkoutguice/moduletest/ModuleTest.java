package org.svenehrke.checkoutguice.moduletest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModuleTest {

	private static Injector providesInjector;
	private static Injector linkedBindingInjector;

	@BeforeClass
	public static void setUp() throws Exception {
		providesInjector = Guice.createInjector(new ProvidesModule());
		linkedBindingInjector = Guice.createInjector(new LinkedBindingModule());
	}

	@Test
	public void testLinkedBinding() throws Exception {
		IMyThing mt = linkedBindingInjector.getInstance(IMyThing.class);
		assertEquals("Sven", mt.getName());

		// Verify that although binding is provided as 'IMyThing' it is possible to get an instance using
		// the implementation class 'MyThing':
		linkedBindingInjector.getInstance(MyThing.class);
		assertEquals("Sven", mt.getName());
	}

	@Test
	public void testProvides1() throws Exception {
		IMyThing mt = providesInjector.getInstance(IMyThing.class);
		assertEquals("Sven", mt.getName());

		final IClient mtc = providesInjector.getInstance(IClient.class);
		assertEquals("Sven", mtc.getMyThing().getName());
	}

	@Test
	public void testProvides2() throws Exception {
		final IClient mtc = providesInjector.getInstance(IClient.class);
		assertEquals("Sven", mtc.getMyThing().getName());

		assertSame(mtc, providesInjector.getInstance(IClient.class));
		assertSame(mtc.getMyThing(), providesInjector.getInstance(IClient.class).getMyThing());
	}

	@Test
	public void testStandardProvides() throws Exception {
		Injector inj = Guice.createInjector(new StandardProvidesModule());
		final IClient mtc = inj.getInstance(IClient.class);
		final IOtherClient oc = inj.getInstance(IOtherClient.class);

		assertSame(mtc.getMyThing(), oc.getMyThing());
	}

	@Test
	public void testProvidesUsingGetter() throws Exception {
		Injector inj = Guice.createInjector(new ProvidesUsingGetterModule());
		final IClient mtc = inj.getInstance(IClient.class);
		final IOtherClient oc = inj.getInstance(IOtherClient.class);

		assertNotSame(mtc.getMyThing(), oc.getMyThing());
	}

}
