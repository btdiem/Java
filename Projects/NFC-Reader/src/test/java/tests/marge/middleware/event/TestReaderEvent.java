package tests.marge.middleware.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tikitag.ons.model.util.ClientId;
import com.tikitag.ons.model.util.ReaderId;

import marge.middleware.shared.event.specification.IEvent;
import marge.middleware.shared.event.specification.IEventType;
import marge.middleware.shared.event.models.ReaderEvent;

/**
 * This test for {@link ReaderEvent} class by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ReaderId.class})
public class TestReaderEvent extends TestTikitagEvent {
	ReaderEvent readerEvent;
	
	/**
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 */
	@Override
	public IEvent getDefaultConstructor() {
		readerEvent = new ReaderEvent(createMockClientId(), 
				createMockReaderId(), 
				createMockEventType(), 
				1000);
		return readerEvent;
	}
	/**
	 * Create {@link ReaderEvent} object with {@link ClientId} is null </br>
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 */
	@Override
	public IEvent getConstructor_ClientIdNull() {
		return new ReaderEvent(null, 
				createMockReaderId(), 
				createMockEventType(), 
				1000);

	}
	/**
	 * Create {@link ReaderEvent} object with {@link ReaderId} is null </br>
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 */
	@Override
	public IEvent getConstructor_ReaderIdNull() {
		return new ReaderEvent(createMockClientId(), 
				null, 
				createMockEventType(), 
				1000);

	}
	/**
	 * Create {@link ReaderEvent} object with {@link IEventType} is null </br>
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 */
	@Override
	public IEvent getConstructor_EventTypeNull() {
		return new ReaderEvent(createMockClientId(), 
				createMockReaderId(), 
				null, 
				1000);

	}
	/**
	 * Create {@link ReaderEvent} object with timestamp is invalid </br>
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 */
	@Override
	public IEvent getConstructor_TimestampInvalid() {
		
		return new ReaderEvent(createMockClientId(), 
				createMockReaderId(), 
				createMockEventType(), 
				0);

	}
	/**
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 */	@Override
	public IEvent getSameConstructor() {
		return readerEvent;
	}
	/**
	 * @see ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long) </br>
	 * @see TestReaderEvent#createMockClientId() </br>
	 */
	 @Override
	public IEvent getConstructorDiffClientId() {
		return new ReaderEvent(createMockClientId(), 
					readerEvent.getReaderId(), 
					readerEvent.getEventType(), 
					readerEvent.getTimestamp());
	}
	/**
	 * @see {@link ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long)} </br>
	 * @see {@link ReaderEvent#getClientId()} </br>
	 * @see {@link ReaderEvent#getEventType()} </br>
	 * @see {@link ReaderEvent#getTimestamp()} </br>
	 * @see {@link TestReaderEvent#createMockReaderId()} </br>
	 */	 
	@Override
	public IEvent getConstructorDiffReaderId() {
		
		return new ReaderEvent(readerEvent.getClientId(), 
								createMockReaderId(), 
								readerEvent.getEventType(), 
								readerEvent.getTimestamp());
	}
	/**
	 * @see {@link ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long)} </br>
	 * @see {@link ReaderEvent#getClientId()} </br>
	 * @see {@link ReaderEvent#getReaderId()} </br>
	 * @see {@link TestTikitagEvent#createMockEventType()} </br>
	 */
	@Override
	public IEvent getConstructorDiffEventTypeId() {
		
		return new ReaderEvent(readerEvent.getClientId(), 
								readerEvent.getReaderId(), 
								createMockEventType(), 
								readerEvent.getTimestamp());

	}
	/**
	 * @see {@link ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long)} </br>
	 * @see {@link ReaderEvent#getClientId()} </br>
	 * @see {@link ReaderEvent#getReaderId()} </br>
	 * @see {@link TestTikitagEvent#createMockEventType()} </br>
	 */
	@Override
	public IEvent getConstructorDiffTimestamp() {
		
		return new ReaderEvent(readerEvent.getClientId(), 
				readerEvent.getReaderId(), 
				readerEvent.getEventType(), 
				100);

	}
	/**
	 * @see {@link ReaderEvent#getSource()} </br>
	 * @see also {@link TestTikitagEvent#createMockReaderId()} </br>
	 * @see also {@link TestTikitagEvent#createMockClientId()} </br>
	 * @see also {@link ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long)} </br>
	 */
	@Test
	public void testGetSource(){
		
		ReaderId mockReaderId = createMockReaderId();
		//EasyMock.expect(mockReaderId.getSerialNr()).andReturn(READER_ID_TOSTRING);
		//PowerMock.replay(mockReaderId);
		ClientId mockClientId = createMockClientId();
		Mockito.when(mockClientId.getName()).thenReturn(CLIENT_ID_TOSTRING);
		String s = "ClientId=" + CLIENT_ID_TOSTRING + ",ReaderId=" + READER_ID_TOSTRING;
		defaultEvent = new ReaderEvent(mockClientId, mockReaderId, createMockEventType(), TIMESTAMP);
		assertEquals(s, defaultEvent.getSource());
		Mockito.verify(mockClientId).getName();
	}
	/**
	 * @see {@link ReaderEvent#getData()} </br>
	 * @see also {@link TestTikitagEvent#createMockEventType()} </br>
	 * @see also {@link ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long)} </br>
	 */
	@Test
	public void testGetData(){
		IEventType mockEventType = createMockEventType();
		Mockito.when(mockEventType.normalizedData()).thenReturn(TAG_EVENT_TYPE);
		defaultEvent = new ReaderEvent(createMockClientId(), createMockReaderId(), mockEventType, TIMESTAMP);
		assertEquals(TAG_EVENT_TYPE, defaultEvent.getData());
	}
	/**
	 * @see {@link ReaderEvent#toString()} </br>
	 * @see also {@link TestTikitagEvent#createMockReaderId()} </br>
	 * @see also {@link TestTikitagEvent#createMockClientId()} </br>
	 * @see also {@link TestTikitagEvent#createMockEventType()} </br>
	 * @see also {@link ReaderEvent#ReaderEvent(ClientId, ReaderId, IEventType, long)} </br> 
	 */
	@Test
	public void testToString(){
		
		ReaderId mockReaderId = createMockReaderId();
		//EasyMock.expect(mockReaderId.getSerialNr()).andReturn(READER_ID_TOSTRING);
		//PowerMock.replay(mockReaderId);
		
		ClientId mockClientId = createMockClientId();
		Mockito.when(mockClientId.getName()).thenReturn(CLIENT_ID_TOSTRING);
		
		IEventType mockEventType = createMockEventType();
		Mockito.when(mockEventType.normalizedData()).thenReturn(TAG_EVENT_TYPE);
		
		defaultEvent = new ReaderEvent(mockClientId, 
									mockReaderId, 
									mockEventType, 
									TIMESTAMP) ;
		
		assertEquals("READER EVENT[DateTime=01/00/1970 01:00:01,"
				+ "EventSource="+"ClientId=" + CLIENT_ID_TOSTRING + ",ReaderId=" + READER_ID_TOSTRING
				+",EventData="+TAG_EVENT_TYPE+"]", defaultEvent.toString());	}

}
