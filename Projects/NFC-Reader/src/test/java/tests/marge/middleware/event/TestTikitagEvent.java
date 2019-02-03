package tests.marge.middleware.event;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import com.tikitag.ons.model.util.ClientId;
import com.tikitag.ons.model.util.ReaderId;

import marge.middleware.shared.event.specification.IEvent;
import marge.middleware.shared.event.specification.IEventType;
/**
 * This abstract class for {@link IEvent} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 *
 */
public abstract class TestTikitagEvent {

	IEvent defaultEvent;
	protected String CLIENT_ID_TOSTRING = "CLIENT_ID";
	protected String READER_ID_TOSTRING = "READER_ID";
	protected String READER_EVENT_TYPE = "READER_ADDED";
	protected String TAG_EVENT_TYPE = "PUT";
	protected String TAG_IDENTIFIER = "TAG_IDENTIFIER";
	protected long TIMESTAMP = 1000;//01/00/1970 01:00:01
	
	public abstract IEvent getDefaultConstructor();
	public abstract IEvent getConstructor_ClientIdNull();
	public abstract IEvent getConstructor_ReaderIdNull();
	public abstract IEvent getConstructor_EventTypeNull();
	public abstract IEvent getConstructor_TimestampInvalid();
	public abstract IEvent getSameConstructor();
	public abstract IEvent getConstructorDiffClientId();
	public abstract IEvent getConstructorDiffReaderId();
	public abstract IEvent getConstructorDiffEventTypeId();
	public abstract IEvent getConstructorDiffTimestamp();
	@Before
	public void setUp(){
		defaultEvent = getDefaultConstructor();
	}
	@After
	public void tearDown(){
		defaultEvent = null;
	}
	/**
	 * @see TestTikitagEvent#getConstructor_ClientIdNull() </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception1(){
		defaultEvent = getConstructor_ClientIdNull();
	}
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception2(){
		defaultEvent = getConstructor_EventTypeNull();
	}
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception3(){
		defaultEvent = getConstructor_ReaderIdNull();
	}
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception4(){
		defaultEvent = getConstructor_TimestampInvalid();
	}
	protected ClientId createMockClientId(){
		return Mockito.mock(ClientId.class);
	}
	protected ReaderId createMockReaderId(){
		//return PowerMock.createMock(ReaderId.class);
		return new ReaderId("BCAEC508582D", READER_ID_TOSTRING);
	}public TestTikitagEvent() {
		// TODO Auto-generated constructor stub
	}
	protected IEventType createMockEventType(){
		return Mockito.mock(IEventType.class);
	}
	@Test
	public void testEquals(){
		assertFalse(defaultEvent.equals(null));
		assertTrue(defaultEvent.equals(defaultEvent));
		assertFalse(defaultEvent.equals(new String()));
		assertTrue(defaultEvent.equals(getSameConstructor()));
		assertFalse(defaultEvent.equals(getConstructorDiffClientId()));
		assertFalse(defaultEvent.equals(getConstructorDiffEventTypeId()));
		assertFalse(defaultEvent.equals(getConstructorDiffReaderId()));
		assertFalse(defaultEvent.equals(getConstructorDiffTimestamp()));
	}
	@Test
	public void testHashcode(){
		
		assertEquals(defaultEvent.hashCode(), getSameConstructor().hashCode());
		assertNotSame(defaultEvent.hashCode(), getConstructorDiffClientId().hashCode());
		assertNotSame(defaultEvent.hashCode(), getConstructorDiffEventTypeId().hashCode());
		assertNotSame(defaultEvent.hashCode(), getConstructorDiffReaderId().hashCode());
		assertNotSame(defaultEvent.hashCode(), getConstructorDiffTimestamp().hashCode());
	}
	@Test
	public void testGetTime(){
		assertEquals("01/00/1970 01:00:01", defaultEvent.getTime());
	}

}
