package tests.marge.middleware.publisher;

import static org.junit.Assert.*;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.models.TikitagPublisher;
import marge.middleware.shared.event.specification.IEvent;
import marge.middleware.publisher.specification.IPublisher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
/**
 * This test for {@link IPublisher} and {@link TikitagPublisher} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class TestTikitagPublisher {


	IPublisher publisher;
	@Before
	public void setUp(){
		publisher = new TikitagPublisher();
	}
	@After
	public void tearDown(){
		publisher = null;
	}
	/**
	 * When initializing {@link IPublisher}, there is no {@link IEvent} in the {@link IPublisher}
	 * Expecting method {@link IPublisher#getEventQueue()} is empty </br>
	 */
	@Test
	public void testConstructor(){
		assertTrue(publisher.getEventQueue().isEmpty());
	}
	/**
	 * Verify method {@link IPublisher#startService()} </br>
	 * Expecting this method runs without any exception </br>
	 */
	@Test
	public void testStartService(){
		IPublisher spy = Mockito.spy(publisher);
		spy.startService();
		Mockito.verify(spy).startService();
	}
	/**
	 * Verify method {@link IPublisher#publishEvent()} by adding an {@link IEvent} to {@link Queue} of {@link IPublisher} </br>
	 * Call {@link IPublisher#publishEvent()} and wait 5000ms </br>
	 * Expecting the added {@link IEvent} is removed and the {@link Queue} is empty </br>
	 * @see also {@link IPublisher#onEvent(IEvent)} </br>
	 * @see also {@link IPublisher#getEventQueue()} </br>
	 * @throws InterruptedException </br>
	 */
	@Test
	public void testPublishEvent() throws InterruptedException{
		IEvent mockEvent = Mockito.mock(IEvent.class);
		publisher.onEvent(mockEvent);
		assertEquals(1, publisher.getEventQueue().size());
		publisher.publishEvent();
		Thread.sleep(5000);//wait to publish event
		assertEquals(0, publisher.getEventQueue().size());
	}
	/**
	 * Verify method {@link IPublisher#start()} </br>
	 * Expecting this method runs without error </br>
	 * @see also {@link IPublisher#startService()} </br>
	 * @see also {@link IPublisher#publishEvent()} </br>
	 */
	@Test
	public void testStart(){
		IPublisher spy = Mockito.spy(publisher);
		Mockito.doNothing().when(spy).startService();
		Mockito.doNothing().when(spy).publishEvent();
		spy.start();
		Mockito.verify(spy).startService();
		Mockito.verify(spy).publishEvent();
	}
	/**
	 * Verify method {@link IPublisher#addListener(IListener)} </br>
	 * Expecting this method runs without exception </br>
	 */
	@Test
	public void testAddListener(){
		IListener mockListener = Mockito.mock(IListener.class);
		IPublisher spy = Mockito.spy(publisher);
		spy.addListener(mockListener);
		Mockito.verify(spy).addListener(mockListener);
	}
}
