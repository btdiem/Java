package tests.marge.middleware.listener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import marge.middleware.listener.factory.AbstractListenerFactoryAdaptee;
import marge.middleware.listener.factory.TikitagListenerFactoryAdaptee;
import marge.middleware.listener.factory.specification.IListenerFactoryAdaptee;
import marge.middleware.listener.models.TikitagListener;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.models.TikitagPublisher;
import marge.middleware.publisher.specification.IPublisher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IListenerFactoryAdaptee}, {@link AbstractListenerFactoryAdaptee} and {@link TikitagListenerFactoryAdaptee} </br>
 * Using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TestListenerFactoryAdaptee {
	
	IListenerFactoryAdaptee adaptee;
	@Before
	public void setUp(){
		adaptee = new TikitagListenerFactoryAdaptee();
	}
	@After
	public void tearDown(){
		adaptee = null;
	}
	/**
	 * Verify method {@link IListenerFactoryAdaptee#getListenerClassName()} </br>
	 * Expecting this method returns a String </br>
	 */
	@Test
	public void testGetListenerClassName(){
		String clazzName = "marge.middleware.listener.models.TikitagListener";
		assertEquals(clazzName, adaptee.getListenerClassName());
	}
	/**
	 * Verify method {@link IListenerFactoryAdaptee#createListener()} </br>
	 * After calling, expecting this method returns a {@link IListener} and this {@link IListener} is instance of {@link TikitagListener} </br>
	 * @throws Exception </br>
	 */
	@Test
	public void testCreateListener() throws Exception{
		IListener listener = adaptee.createListener();
		assertNotNull(listener);
		assertTrue(listener instanceof TikitagListener);
	}
	/**
	 * Verify method {@link IListenerFactoryAdaptee#createPublisher()} <br>
	 * After calling, expecting it returns a {@link IPublisher} and this object is instance of {@link TikitagPublisher} </br>
	 */
	@Test
	public void testCreatePublisher(){
		IPublisher publisher = adaptee.createPublisher();
		assertNotNull(publisher);
		assertTrue(publisher instanceof TikitagPublisher);
	}
	
}
