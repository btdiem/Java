/**
 * 
 */
package tests.marge.middleware.event;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runners.JUnit4;

import marge.middleware.shared.event.models.AbstractEventType;
import marge.middleware.shared.event.models.TikitagEventType;
import marge.middleware.shared.event.specification.IEventType;

import com.tikitag.client.tagservice.ReaderEvent.ReaderEventType;
import com.tikitag.ons.model.util.TagEvent.TagEventType;

/**
 * The test for {@link IEventType}, {@link TikitagEventType}, {@link AbstractEventType} class by using {@link JUnit4} </br>
 * @author btdiem </br>
 *
 */
public class TestEventType {

	IEventType eventType;
	/**
	 * @see TikitagEventType#TikitagEventType(String) </br>
	 */
	@Test
	public void testConstructor(){
		eventType = new TikitagEventType("READER_ADDED");
		assertEquals("READER_ADDED", eventType.getType());
	}
	/**
	 * @see TikitagEventType#TikitagEventType(String) </br>
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor_Exception(){
		eventType = new TikitagEventType(null);
	}
	/**
	 * @see TikitagEventType#toString() </br>
	 */
	@Test
	public void testToString(){
		eventType = new TikitagEventType("READER_ADDED");
		assertEquals("EventType=READER_ADDED", eventType.toString());
	}
	/**
	 * @see {@link TikitagEventType#equals(Object)} </br>
	 */
	@Test
	public void testEquals(){
		eventType = new TikitagEventType("READER_ADDED");
		assertFalse(eventType.equals(null));
		assertTrue(eventType.equals(eventType));
		assertFalse(eventType.equals(new String()));
		IEventType thesame = new TikitagEventType("READER_ADDED");
		IEventType thediff = new TikitagEventType("TAG_ADDED");
		assertFalse(eventType.equals(thediff));
		assertTrue(eventType.equals(thesame));
	}
	/**
	 * @see {@link TikitagEventType#hashCode()} </br>
	 */
	@Test
	public void testHashcode(){
		eventType = new TikitagEventType("READER_ADDED");
		IEventType thesame = new TikitagEventType("READER_ADDED");
		IEventType thediff = new TikitagEventType("TAG_ADDED");
		assertNotSame(eventType.hashCode(), thediff.hashCode());
		assertEquals(eventType.hashCode(), thesame.hashCode());
	}
	/**
	 * @see {@link TikitagEventType#parseEvent(String)} </br>
	 */
	@Test
	public void testParseEvent(){
		eventType = new TikitagEventType("READER_ADDED");
		assertNull(eventType.parseEvent(null));
		assertEquals("Card is inserted", eventType.parseEvent(ReaderEventType.READER_ADDED.name()));
		assertEquals("Card is unreadable", eventType.parseEvent(ReaderEventType.READER_FAILURE.name()));
		assertEquals("Card is removed", eventType.parseEvent(ReaderEventType.READER_REMOVED.name()));
		assertEquals("Tag is put on the reader", eventType.parseEvent(TagEventType.PUT.name()));
		assertEquals("Tag is removed out the reader", eventType.parseEvent(TagEventType.REMOVE.name()));
		assertEquals("Tag is touch", eventType.parseEvent(TagEventType.TOUCH.name()));
		assertEquals("ANYTHING", eventType.parseEvent("ANYTHING"));
	}
	/**
	 * @see {@link TikitagEventType#normalizedData()} </br>
	 */
	@Test
	public void testNormalizeData(){
	
		eventType = new TikitagEventType("READER_ADDED");
		assertEquals("Card is inserted", eventType.normalizedData());
	}
}
