package tests.marge.middleware.listener;

import static org.junit.Assert.*;

import marge.middleware.listener.factory.specification.IListenerFactory;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.specification.IPublisher;
import marge.middleware.listener.factory.specification.IListenerFactoryAdaptee;
import marge.middleware.listener.factory.ListenerFactoryAdapter;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * This test for {@link IListenerFactory}, {@link ListenerFactoryAdapter}, {@link IListenerFactoryAdaptee} class </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class TestListenerFactory {

	IListenerFactory listenerFactory;
	
	@After
	public void tearDown(){
		listenerFactory = null;
	}
	/**
	 * Verify constructor of {@link IListenerFactoryAdapter} </br>
	 * Expecting this method runs without any Exception </br>
	 */
	@Test
	public void testConstructor(){
		IListenerFactoryAdaptee adaptee = Mockito.mock(IListenerFactoryAdaptee.class);
		listenerFactory = new ListenerFactoryAdapter(adaptee);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception(){
		listenerFactory = new ListenerFactoryAdapter(null);
	}
	/**
	 * Verify method {@link ListenerFactoryAdapter#createListener()} </br>s
	 * Mock {@link IListenerFactoryAdaptee} and mock method {@link IListenerFactoryAdaptee#createListener()} return a {@link IListener} </br>
	 * Expecting method {@link ListenerFactoryAdapter#createListener()} returns an {@link IListener} </br>
	 * @throws Exception
	 */
	@Test
	public void testCreateListener1() throws Exception{
		IListenerFactoryAdaptee adaptee = Mockito.mock(IListenerFactoryAdaptee.class);
		IListener listener = Mockito.mock(IListener.class);
		Mockito.when(adaptee.createListener()).thenReturn(listener);
		listenerFactory = new ListenerFactoryAdapter(adaptee);
		assertEquals(listener, listenerFactory.createListener());
	}
	/**
	 * Verify method {@link ListenerFactoryAdapter#createListener()} </br>s
	 * Mock {@link IListenerFactoryAdaptee} and mock method {@link IListenerFactoryAdaptee#createListener()} return null </br>
	 * Expecting method {@link ListenerFactoryAdapter#createListener()} returns null </br>
	 * @throws Exception
	 */
	@Test
	public void testCreateListener2() throws Exception{
		IListenerFactoryAdaptee adaptee = Mockito.mock(IListenerFactoryAdaptee.class);
		Mockito.when(adaptee.createListener()).thenReturn(null);
		listenerFactory = new ListenerFactoryAdapter(adaptee);
		assertNull(listenerFactory.createListener());
	}
	/**
	 * Verify method {@link ListenerFactoryAdapter#createPublisher()()} </br>s
	 * Mock {@link IListenerFactoryAdaptee} and mock method {@link IListenerFactoryAdaptee#createPublisher()()} return {@link IPublisher} </br>
	 * Expecting method {@link ListenerFactoryAdapter#createPublisher()} returns a {@link IPublisher} </br>
	 * @throws Exception
	 */
	@Test
	public void testCreatePublisher1() throws Exception{
		IListenerFactoryAdaptee adaptee = Mockito.mock(IListenerFactoryAdaptee.class);
		IPublisher publisher = Mockito.mock(IPublisher.class);
		Mockito.when(adaptee.createPublisher()).thenReturn(publisher);
		listenerFactory = new ListenerFactoryAdapter(adaptee);
		assertEquals(publisher, listenerFactory.createPublisher());
	}	
	/**
	 * Verify method {@link ListenerFactoryAdapter#createPublisher()()} </br>s
	 * Mock {@link IListenerFactoryAdaptee} and mock method {@link IListenerFactoryAdaptee#createPublisher()()} return null </br>
	 * Expecting method {@link ListenerFactoryAdapter#createPublisher()} returns null </br>
	 * @throws Exception
	 */
	@Test
	public void testCreatePublisher2() throws Exception{
		IListenerFactoryAdaptee adaptee = Mockito.mock(IListenerFactoryAdaptee.class);
		Mockito.when(adaptee.createPublisher()).thenReturn(null);
		listenerFactory = new ListenerFactoryAdapter(adaptee);
		assertNull(listenerFactory.createPublisher());
	}		

	
//	TikitagListenerFactory listenerFactory;
//	@Before
//	public void setUp(){
//		listenerFactory = new TikitagListenerFactory();
//	}
//
//	@After
//	public void tearDown(){
//		listenerFactory = null;
//	}
//	/**
//	 * @see {@link IListenerFactory#createPublisher()} </br>
//	 */
//	@Test
//	public void testCreatePublisher(){
//		IPublisher publisher = listenerFactory.createPublisher();
//		assertNotNull(publisher);
//	}
//	/**
//	 * @see IListenerFactory#createListener() </br>
//	 * @throws Exception </br>
//	 */
//	@Test
//	public void testCreateListener() throws Exception{
//		IListener listener = listenerFactory.createListener();
//		assertNotNull(listener);
//	}

}
