package tests.marge.middleware.listener;

import marge.middleware.listener.models.TikitagListener;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.specification.IPublisher;
import marge.middleware.shared.event.specification.IEvent;

import org.junit.After;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
/**
 * This test for {@link IListener} and {@link TikitagListener} by using {@link JUnit4} and {@link Mock} </br>
 * @author btdiem </br>
 *
 */
public class TestListener {

	TikitagListener listener;
	
	@After
	public void tearDown(){
		listener = null;
	}
	/**
	 * @see {@link TikitagListener#TikitagListener(IPublisher)} </br>
	 * @throws IllegalArgumentException </br>
	 * @throws IllegalAccessException </br>
	 */
	@Test
	public void testConstructor() throws IllegalArgumentException, IllegalAccessException{
		listener = new TikitagListener(createMockPublisher());
	}
	/**
	 * @see {@link TikitagListener#TikitagListener(IPublisher)} </br>
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructor_Exception(){
		listener = new TikitagListener(null);
	}
	/**
	 * @see {@link TikitagListener#notifiy(IEvent)} </br>
	 * @throws IllegalArgumentException </br>
	 * @throws IllegalAccessException </br>
	 */
	@Test
	public void testNotify() throws IllegalArgumentException, IllegalAccessException{
		listener = new TikitagListener(createMockPublisher());
		IEvent mockEvent = Mockito.mock(IEvent.class);
		listener.notifiy(mockEvent);
	}
	/**
	 * @see {@link TikitagListener#start()} </br>
	 * @see also {@link TestListener#createMockPublisher()} </br>
	 */
	@Test
	public void testStart(){
		IPublisher mockPublisher = createMockPublisher();
		Mockito.doNothing().when(mockPublisher).start();
		listener = new TikitagListener(mockPublisher);
		listener.start();
		Mockito.verify(mockPublisher).start();
	}
	/**
	 * Create a {@link IPublisher} by using {@link Mockito} </br>
	 * @return {@link IPublisher} </br>
	 */
	private IPublisher createMockPublisher(){
		return Mockito.mock(IPublisher.class);
	}
}
