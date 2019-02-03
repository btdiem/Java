package tests.marge.middleware.event;

import static org.junit.Assert.*;
import marge.middleware.shared.event.specification.IEvent;
import marge.middleware.shared.event.specification.IEventType;
import marge.middleware.shared.event.specification.TikitagEvent;
import marge.middleware.shared.event.models.TagEvent;

import org.easymock.classextension.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tikitag.ons.model.util.ClientId;
import com.tikitag.ons.model.util.ReaderId;
import com.tikitag.ons.model.util.TagId;
import com.tikitag.ons.model.util.TagInfo;
/**
 * This test for {@link IEvent}, {@link TagEvent} and {@link TikitagEvent} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ReaderId.class, TagId.class})
public class TestTagEvent extends TestTikitagEvent{
	
	TagEvent tagEvent;
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception5(){
		defaultEvent = getConstructor_TagIdNull();
	}
	@Test
	@Override
	public void testEquals() {
		super.testEquals();
		assertFalse(defaultEvent.equals(getConstructorDiffTagId()));
	}
	@Test
	@Override
	public void testHashcode() {
		
		super.testHashcode();
		assertNotSame(defaultEvent.hashCode(), getConstructorDiffTagId().hashCode());
	}

	@Override
	public IEvent getDefaultConstructor() {
		
		tagEvent = new TagEvent(createMockClientId(),
				createMockReaderId(),
				createMockEventType(),
				TIMESTAMP,
				createMockTagId(),
				createMockTagInfo(),
				createMockTagInfo());
		return tagEvent;
	}

	@Override
	public IEvent getConstructor_ClientIdNull() {
		
		return new TagEvent(null, 
				createMockReaderId(), 
				createMockEventType(), 
				TIMESTAMP, 
				createMockTagId(), 
				createMockTagInfo(), 
				createMockTagInfo());
	}

	@Override
	public IEvent getConstructor_ReaderIdNull() {
		
		return new TagEvent(createMockClientId(), 
				null, 
				createMockEventType(), 
				TIMESTAMP, 
				createMockTagId(), 
				createMockTagInfo(), 
				createMockTagInfo());
	}

	@Override
	public IEvent getConstructor_EventTypeNull() {
		
		return new TagEvent(createMockClientId(),
				createMockReaderId(),
				null,
				TIMESTAMP,
				createMockTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}

	@Override
	public IEvent getConstructor_TimestampInvalid() {
		
		return new TagEvent(createMockClientId(),
				createMockReaderId(),
				createMockEventType(),
				0,
				createMockTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}

	private IEvent getConstructor_TagIdNull() {
		
		return new TagEvent(createMockClientId(),
				createMockReaderId(),
				createMockEventType(),
				TIMESTAMP,
				null,
				createMockTagInfo(),
				createMockTagInfo());
	}

	@Override
	public IEvent getSameConstructor() {
		return tagEvent;
	}

	@Override
	public IEvent getConstructorDiffClientId() {
		
		return new TagEvent(createMockClientId(),
				tagEvent.getReaderId(),
				tagEvent.getEventType(),
				TIMESTAMP,
				tagEvent.getTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}

	@Override
	public IEvent getConstructorDiffReaderId() {
		
		return new TagEvent(tagEvent.getClientId(),
				createMockReaderId(),
				tagEvent.getEventType(),
				TIMESTAMP,
				tagEvent.getTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}

	@Override
	public IEvent getConstructorDiffEventTypeId() {
		
		return new TagEvent(tagEvent.getClientId(),
				tagEvent.getReaderId(),
				createMockEventType(),
				TIMESTAMP,
				tagEvent.getTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}

	@Override
	public IEvent getConstructorDiffTimestamp() {
		
		return new TagEvent(createMockClientId(),
				tagEvent.getReaderId(),
				tagEvent.getEventType(),
				100,
				tagEvent.getTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}

	private IEvent getConstructorDiffTagId(){
		
		return new TagEvent(tagEvent.getClientId(),
				tagEvent.getReaderId(),
				tagEvent.getEventType(),
				TIMESTAMP,
				createMockTagId(),
				createMockTagInfo(),
				createMockTagInfo());
	}
	private TagId createMockTagId(){
		return PowerMock.createMock(TagId.class);
	}
	private TagInfo createMockTagInfo(){
		return Mockito.mock(TagInfo.class);
	}
	@Test
	public void testNormalizeData(){
		
		IEventType mockEventType = createMockEventType();
		Mockito.when(mockEventType.normalizedData()).thenReturn(TAG_EVENT_TYPE);
		defaultEvent = new TagEvent(createMockClientId(), 
									createMockReaderId(), 
									mockEventType, 
									TIMESTAMP, 
									createMockTagId(), 
									createMockTagInfo(), 
									createMockTagInfo());
		
		assertEquals(TAG_EVENT_TYPE, defaultEvent.getData());
		Mockito.verify(mockEventType).normalizedData();
	}
	@Test
	public void testGetSource(){
		
		ReaderId mockReaderId = createMockReaderId();
		//EasyMock.expect(mockReaderId.getSerialNr()).andReturn(READER_ID_TOSTRING);
		//PowerMock.replay(mockReaderId);
		ClientId mockClientId = createMockClientId();
		Mockito.when(mockClientId.getName()).thenReturn(CLIENT_ID_TOSTRING);
		TagId mockTagId = createMockTagId();
		EasyMock.expect(mockTagId.getIdentifier()).andReturn(TAG_IDENTIFIER);
		PowerMock.replay(mockTagId);
		String s = "ClientId=" + CLIENT_ID_TOSTRING + ",ReaderId=" + READER_ID_TOSTRING+",TagId="+TAG_IDENTIFIER;
		
		defaultEvent = new TagEvent(mockClientId, 
									mockReaderId, 
									createMockEventType(), 
									TIMESTAMP, 
									mockTagId, 
									createMockTagInfo(), 
									createMockTagInfo());
		
		assertEquals(s, defaultEvent.getSource());
		
		Mockito.verify(mockClientId).getName();
	}
	@Test
	public void testToString(){
		
		ReaderId mockReaderId = createMockReaderId();
		//EasyMock.expect(mockReaderId.getSerialNr()).andReturn(READER_ID_TOSTRING);
		//PowerMock.replay(mockReaderId);
		
		ClientId mockClientId = createMockClientId();
		Mockito.when(mockClientId.getName()).thenReturn(CLIENT_ID_TOSTRING);
		
		TagId mockTagId = createMockTagId();
		EasyMock.expect(mockTagId.getIdentifier()).andReturn(TAG_IDENTIFIER);
		PowerMock.replay(mockTagId);
		
		TagInfo mockActionTag = createMockTagInfo();
		Mockito.when(mockActionTag.getTagData()).thenReturn(null);
		
		TagInfo mockContextTag = createMockTagInfo();
		Mockito.when(mockContextTag.getTagData()).thenReturn(null);
		
		IEventType mockEventType = createMockEventType();
		Mockito.when(mockEventType.normalizedData()).thenReturn(TAG_EVENT_TYPE);
		
		defaultEvent = new TagEvent(mockClientId, 
									mockReaderId, 
									mockEventType, 
									TIMESTAMP, 
									mockTagId, 
									mockActionTag, 
									mockContextTag);
		
		assertEquals("TAG EVENT[DateTime=01/00/1970 01:00:01,"
				+ "EventSource="+"ClientId=" + CLIENT_ID_TOSTRING + ",ReaderId=" + READER_ID_TOSTRING+",TagId="+TAG_IDENTIFIER
				+",EventData="+TAG_EVENT_TYPE+"]", defaultEvent.toString());
		
		Mockito.verify(mockActionTag).getTagData();
		Mockito.verify(mockContextTag).getTagData();
	}
	@Test
	public void testGetTagInfo(){
		
		TagInfo mockActionTag = createMockTagInfo();
		TagInfo mockContextTag = createMockTagInfo();
		defaultEvent = new TagEvent(createMockClientId(), 
							createMockReaderId(), 
							createMockEventType(), 
							TIMESTAMP, 
							createMockTagId(), 
							mockActionTag, 
							mockContextTag);
		assertEquals(mockActionTag, ((TagEvent)defaultEvent).getActionTag());
		assertEquals(mockContextTag, ((TagEvent)defaultEvent).getContextTag());
	}
}
