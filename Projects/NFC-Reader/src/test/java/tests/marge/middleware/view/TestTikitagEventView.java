package tests.marge.middleware.view;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.tikitag.client.tagservice.ReaderEvent.ReaderEventType;
import com.tikitag.ons.model.util.ReaderId;
import com.tikitag.ons.model.util.TagEvent.TagEventType;
import com.tikitag.ons.model.util.TagId;
import com.tikitag.ons.model.util.TagInfo;

import marge.middleware.listener.view.*;
import marge.middleware.listener.view.specification.IView;
import marge.middleware.listener.models.*;
import marge.middleware.shared.event.models.ReaderEvent;
import marge.middleware.shared.event.models.TagEvent;
import marge.middleware.shared.event.specification.IEvent;
import marge.middleware.shared.event.specification.IEventType;
/**
 * This test for {@link IView} and {@link TikitagView} by using {@link JUnit4} and {@link Mockito} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class TestTikitagEventView {
	
	IView tikitagEventView;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		tikitagEventView = null;
	}
	/**
	 * @see TikitagEventView#TikitagEventView(marge.middleware.listener.specification.IListener) </br>
	 */
	@Test
	public void testConstructor() {
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
	}
	/**
	 * Verify method {@link TikitagEventView#listen()} </br>
	 * Expecting {@link IView} is visible </br>
	 */
	@Test
	public void testListen(){
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		tikitagEventView.listen();
		assertTrue(((JFrame)tikitagEventView).isVisible());
	}
	/**
	 * Verify method {@link TikitagView#updateDescption} </br>
	 * Expecting this method runs without any exception </br>
	 * Using {@link Whitebox#invokeMethod(Object, Class, String, Class[], Object...)} to verify the private method </br>
	 * Object[] data = new Object[]{dateTime, eventSrc, eventData}; </br>
	 * @throws Exception </br>
	 */
	@Test
	public void testUpdateDescription1() throws Exception{
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		Whitebox.invokeMethod(tikitagEventView, "updateDescription", 1);
	}
	/**
	 * @see TikitagEventView#updateDescription(int)
	 * @throws Exception
	 */
	@Test
	public void testUpdateDescription2() throws Exception{
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		Field ftableModel = Whitebox.getField(tikitagEventView.getClass(), "eventTableModel");
		DefaultTableModel tableModel = (DefaultTableModel)ftableModel.get(tikitagEventView);
		Object[] data = new Object[]{"dateTime", "eventSrc", "eventData"};
		tableModel.addRow(data);
		tableModel.addRow(data);
		tableModel.addRow(data);
		Whitebox.invokeMethod(tikitagEventView, "updateDescription", 2);
	}	
	/**
	 * @see TikitagEventView#updateDescription(int)
	 * @throws Exception
	 */
	@Test
	public void testUpdateDescription4() throws Exception{
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		Whitebox.invokeMethod(tikitagEventView, "updateDescription", -1);
	}
	/**
	 * Mock a {@link IEvent} and call {@link TikitagEventView#update(java.util.Observable, Object)}  </br>
	 * Expecting method {@link TikitagEventView#update(java.util.Observable, Object)} runs without exception</br>
	 */
	@Test
	public void testUpdate(){
		
		IEvent mockEvent = Mockito.mock(IEvent.class);
		Mockito.when(mockEvent.getSource()).thenReturn("EVENT_SOURCE");
		Mockito.when(mockEvent.getTime()).thenReturn("01/00/1970 01:00:01");
		Mockito.when(mockEvent.getData()).thenReturn("EVENT_DATA");
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		tikitagEventView.update(listener, mockEvent);
		Mockito.verify(mockEvent).getData();
		Mockito.verify(mockEvent).getSource();
		Mockito.verify(mockEvent).getTime();
	}
	/**
	 * Create a {@link ReaderId} and call {@link TikitagEventView#addReaderNode(ReaderEvent)} </br>
	 * Expecting this method runs without any exception </br>
	 * @see TikitagEventView#addReaderNode(ReaderEvent) </br>
	 */
	@Test
	public void testAddReaderNode(){
		
		ReaderEvent readerEvent = Mockito.mock(ReaderEvent.class);
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		Mockito.when(readerEvent.getReaderId()).thenReturn(readerId);
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		((TikitagEventView)tikitagEventView).addReaderNode(readerEvent);
	}
	/**
	 * Verify method {@link TikitagEventView#addTagNode(TagEvent)} </br>
	 * Case 1: Mock a @ {@link TagEvent} and mock method {@link TagEvent#getActionTag()} returns null </br>
	 * Expecting {@link TikitagEventView#addTagNode(TagEvent)} runs without any exception </br>
	 */
	@Test
	public void testAddTagNode1(){
		
		TagEvent tagEvent = Mockito.mock(TagEvent.class);
		TagId tagId = new TagId("TagId");
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		Mockito.when(tagEvent.getTagId()).thenReturn(tagId);
		Mockito.when(tagEvent.getReaderId()).thenReturn(readerId);
		Mockito.when(tagEvent.getActionTag()).thenReturn(null);
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		((TikitagEventView)tikitagEventView).addTagNode(tagEvent);
	}
	/**
	 * Verify method {@link TikitagEventView#addTagNode(TagEvent)} </br>
	 * Case 2: Mock a @ {@link TagEvent} and mock method {@link TagEvent#getActionTag()} returns not null </br>
	 * Mock method {@link TagInfo#getTagData()} returns NULL </br>
	 * Expecting {@link TikitagEventView#addTagNode(TagEvent)} runs without any exception </br>
	 */
	@Test
	public void testAddTagNode2(){
		TagEvent tagEvent = Mockito.mock(TagEvent.class);
		TagId tagId = new TagId("TagId");
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		TagInfo tagInfo = Mockito.mock(TagInfo.class);
		Mockito.when(tagInfo.getTagData()).thenReturn(null);
		Mockito.when(tagEvent.getTagId()).thenReturn(tagId);
		Mockito.when(tagEvent.getReaderId()).thenReturn(readerId);
		Mockito.when(tagEvent.getActionTag()).thenReturn(tagInfo);
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		((TikitagEventView)tikitagEventView).addTagNode(tagEvent);
	}
	/**
	 * Verify method {@link TikitagEventView#addTagNode(TagEvent)} </br>
	 * Case 3:  Mock a @ {@link TagEvent} and mock method {@link TagEvent#getActionTag()} returns a {@link TagInfo} </br>
	 * Mock method  {@link TagInfo#getTagData()} returns byte array </br>
	 * Expecting {@link TikitagEventView#addTagNode(TagEvent)} runs without any exception </br>
	 */
	@Test
	public void testAddTagNode3(){
		
		TagEvent tagEvent = Mockito.mock(TagEvent.class);
		TagId tagId = new TagId("TagId");
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		TagInfo tagInfo = Mockito.mock(TagInfo.class);
		Mockito.when(tagInfo.getTagData()).thenReturn(new byte[]{123});
		Mockito.when(tagEvent.getTagId()).thenReturn(tagId);
		Mockito.when(tagEvent.getReaderId()).thenReturn(readerId);
		Mockito.when(tagEvent.getActionTag()).thenReturn(tagInfo);
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		((TikitagEventView)tikitagEventView).addTagNode(tagEvent);
	}
	/**
	 * Verify method {@link TikitagEventView#addTagNode(TagEvent)} </br>
	 * Case 4: Mock @{@link TagEvent} object and mock method {@link TagEvent#getActionTag()} returns {@link TagInfo} </br>
	 * Mock method {@link TagInfo#getTagData()} returns byte array </br>
	 * But {@link TagEvent} has no reader parent node that has a {@link ReaderId#getUid()} is the same with {@link TagEvent#getReaderId()} </br>
	 * Expecting {@link TikitagEventView#addTagNode(TagEvent)} runs without any exception </br>
	 *  
	 */
	@Test
	public void testAddTagNode4(){
		
		TagEvent tagEvent = Mockito.mock(TagEvent.class);
		TagId tagId = new TagId("TagId");
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		TagInfo tagInfo = Mockito.mock(TagInfo.class);
		Mockito.when(tagInfo.getTagData()).thenReturn(new byte[]{});
		Mockito.when(tagEvent.getTagId()).thenReturn(tagId);
		Mockito.when(tagEvent.getReaderId()).thenReturn(readerId);
		Mockito.when(tagEvent.getActionTag()).thenReturn(tagInfo);
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		((TikitagEventView)tikitagEventView).addTagNode(tagEvent);
		
	}	
	/**
	 * 
	 * Verify method {@link TikitagEventView#addTagNode(TagEvent)} </br>
	 * Case 5: Mock {@link TagEvent} and mock method {@link TagEvent#getActionTag()} returns TagInfo </br>
	 * Mock method {@link TagEvent#getData()} returns array of byte </br>
	 * Mock method {@link TagEvent#getTagId()} returns a {@link TagId} </br>
	 * Mock method {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} return a {@link DefaultMutableTreeNode} </br>
	 * Expecting {@link TikitagEventView#addTagNode(TagEvent)} runs without any exception </br>
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testAddTagNode5() throws IllegalArgumentException, IllegalAccessException{
		
		TagEvent tagEvent = Mockito.mock(TagEvent.class);
		TagId tagId = new TagId("TagId");
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		TagInfo tagInfo = Mockito.mock(TagInfo.class);
		Mockito.when(tagInfo.getTagData()).thenReturn(new byte[]{});
		Mockito.when(tagEvent.getTagId()).thenReturn(tagId);
		Mockito.when(tagEvent.getReaderId()).thenReturn(readerId);
		Mockito.when(tagEvent.getActionTag()).thenReturn(tagInfo);
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		//create a root not
		DefaultMutableTreeNode parent = Mockito.mock(DefaultMutableTreeNode.class);
		String uid = ((TikitagEventView)tikitagEventView).getUID(readerId);
		Field froot = Whitebox.getField(TikitagEventView.class, "root");
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) froot.get(tikitagEventView);
		TikitagEventView spy = (TikitagEventView)Mockito.spy(tikitagEventView);
		Mockito.when(spy.findChildNode(root, uid)).thenReturn(parent);
		spy.addTagNode(tagEvent);
		Mockito.verify(spy).findChildNode(root, uid);
		
	}
	/**
	 * Verify method {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} returns a {@link DefaultMutableTreeNode} </br>
	 * Call method {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} </br>
	 * Expecting that this method returns null </br>
	 */
	@Test
	public void testFindChildNode1(){
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		DefaultMutableTreeNode mockNode = Mockito.mock(DefaultMutableTreeNode.class);
		assertNull(((TikitagEventView)tikitagEventView).findChildNode(mockNode, "childNode"));
	}
	/**
	 * Mock method {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} returns {@link DefaultMutableTreeNode} node </br>
	 * Mock root node is  {@link DefaultMutableTreeNode} </br>
	 * Insert node to root </br>
	 * Expecting that {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)}  will return {@link DefaultMutableTreeNode} </br>
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testFindChildNode2() throws IllegalArgumentException, IllegalAccessException{
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		DefaultMutableTreeNode mockNode = Mockito.mock(DefaultMutableTreeNode.class);
		Mockito.when(mockNode.getUserObject()).thenReturn("childNode");
		Field froot = Whitebox.getField(TikitagEventView.class, "root");
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)froot.get(tikitagEventView);
		root.add(mockNode);
		assertEquals(mockNode, ((TikitagEventView)tikitagEventView).findChildNode(root, "childNode"));
	}	
	/**
	 * Verify method {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} </br>
	 * Mock {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} returns a {@link DefaultMutableTreeNode} node </br>
	 * Mock root node to {@link DefaultMutableTreeNode} </br>
	 * Add a node to root </br>
	 * Expecting that {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)}  returns  null </br>
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testFindChildNode3() throws IllegalArgumentException, IllegalAccessException{
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		DefaultMutableTreeNode mockNode = Mockito.mock(DefaultMutableTreeNode.class);
		Mockito.when(mockNode.getUserObject()).thenReturn("any");
		Field froot = Whitebox.getField(TikitagEventView.class, "root");
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)froot.get(tikitagEventView);
		root.add(mockNode);
		assertNotSame(mockNode, ((TikitagEventView)tikitagEventView).findChildNode(root, "childNode"));
	}
	/**
	 * Verify method TikitagEventView#removeReaderTagNode(String, String) </br>
	 * Case 1: Parent node of removed node is not root node </br>
	 * Expecting method TikitagEventView#removeReaderTagNode(String, String) runs without any exception </br>
	 * @throws UnknownHostException 
	 */
	@Test
	public void testRemoveReaderTagNode1() throws UnknownHostException{
		String parentNodeName = InetAddress.getLocalHost().getHostName();
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		((TikitagEventView)tikitagEventView).removeReaderTagNode(parentNodeName, "childNode");
	}
	/**
	 * Verify method TikitagEventView#removeReaderTagNode(String, String) </br> 
	 * Case 2: Parent node of removed node is not root node </br>
	 * Mock method {@link TikitagEventView#findChildNode(DefaultMutableTreeNode, String)} return null </br>
	 * Expecting method TikitagEventView#removeReaderTagNode(String, String) runs without any exception </br>
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testRemoveReaderTagNode2() throws IllegalArgumentException, IllegalAccessException{
		String parentNodeName = "parentNode";
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		TikitagEventView spy = (TikitagEventView)Mockito.spy(tikitagEventView);
		//get root not
		Field froot = Whitebox.getField(TikitagEventView.class, "root");
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)froot.get(tikitagEventView);
		Mockito.when(spy.findChildNode(root, parentNodeName)).thenReturn(null);
		spy.removeReaderTagNode(parentNodeName, "childNode");
		Mockito.verify(spy).findChildNode(root, parentNodeName);
	}
	/**
	 * Verify method {@link TikitagEventView#updateReaderTagData(IEvent)} </br>
	 * Case 1: Create an {@link IEvent} is instance of {@link ReaderEvent} </br>
	 * Mock an Event type with value {@link ReaderEventType#READER_ADDED} </br>
	 * Expecting method {@link TikitagEventView#updateReaderTagData(IEvent)} runs without any exception </br>
	 */
	@Test 
	public void testUpdateReaderTagData1(){
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		
		ReaderEvent event = Mockito.mock(ReaderEvent.class);
		IEventType eventType = Mockito.mock(IEventType.class);
		Mockito.when(eventType.getType()).thenReturn(ReaderEventType.READER_ADDED.name());
		Mockito.when(event.getEventType()).thenReturn(eventType);
		TikitagEventView spy = (TikitagEventView)Mockito.spy(tikitagEventView);
		Mockito.doNothing().when(spy).addReaderNode(event);
		spy.updateReaderTagData(event);
	}
	/**
	 * Verify method {@link TikitagEventView#updateReaderTagData(IEvent)} </br>
	 * Case 2: Create an {@link IEvent} is instance of {@link ReaderEvent} </br>
	 * Mock an Event type with value {@link ReaderEventType#READER_REMOVED} </br>
	 * Expecting method {@link TikitagEventView#updateReaderTagData(IEvent)} runs without any exception </br>
	 * @throws UnknownHostException 
	 */
	@Test 
	public void testUpdateReaderTagData2() throws UnknownHostException{
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		
		ReaderEvent event = Mockito.mock(ReaderEvent.class);
		IEventType eventType = Mockito.mock(IEventType.class);
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		String uid = ((TikitagEventView)tikitagEventView).getUID(readerId);
		String rootName = InetAddress.getLocalHost().getHostAddress();
		
		Mockito.when(eventType.getType()).thenReturn(ReaderEventType.READER_REMOVED.name());
		Mockito.when(event.getEventType()).thenReturn(eventType);
		Mockito.when(event.getReaderId()).thenReturn(readerId);
		
		TikitagEventView spy = (TikitagEventView)Mockito.spy(tikitagEventView);
		Mockito.doNothing().when(spy).removeReaderTagNode(rootName, uid);
		spy.updateReaderTagData(event);
	}
	/**
	 * Verify method {@link TikitagEventView#updateReaderTagData(IEvent)} </br>
	 * Case 3: Create an {@link IEvent} is instance of {@link TagEvent} </br>
	 * Mock Event type with value {@link TagEventType#PUT} </br>
	 * Expecting method {@link TikitagEventView#updateReaderTagData(IEvent)} runs without any exception </br>
	 * @throws UnknownHostException 
	 */
	@Test 
	public void testUpdateReaderTagData3() throws UnknownHostException{
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		
		TagEvent event = Mockito.mock(TagEvent.class);
		IEventType eventType = Mockito.mock(IEventType.class);
	
		
		Mockito.when(eventType.getType()).thenReturn(TagEventType.PUT.name());
		Mockito.when(event.getEventType()).thenReturn(eventType);
		
		TikitagEventView spy = (TikitagEventView)Mockito.spy(tikitagEventView);
		Mockito.doNothing().when(spy).addTagNode(event);
		spy.updateReaderTagData(event);
	}	
	/**
	 * Verify method {@link TikitagEventView#updateReaderTagData(IEvent)} </br>
	 * Case 4: Create an {@link IEvent} is instance of {@link TagEvent} </br>
	 * Mock Event type with value {@link TagEventType#PUT} </br>
	 * Expecting method {@link TikitagEventView#updateReaderTagData(IEvent)} runs without any exception </br>
	 * @throws UnknownHostException 
	 */
	@Test 
	public void testUpdateReaderTagData4() throws UnknownHostException{
		
		TikitagListener listener = Mockito.mock(TikitagListener.class);
		tikitagEventView = new TikitagEventView(listener);
		
		TagEvent event = Mockito.mock(TagEvent.class);
		IEventType eventType = Mockito.mock(IEventType.class);
		ReaderId readerId = new ReaderId("BCAEC508582D", "003907");
		String uid = ((TikitagEventView)tikitagEventView).getUID(readerId);
		TagId tagId = new TagId("TagId");
		String strTagId = ((TikitagEventView)tikitagEventView).getTagId(tagId);
		
		Mockito.when(eventType.getType()).thenReturn(TagEventType.REMOVE.name());
		Mockito.when(event.getEventType()).thenReturn(eventType);
		Mockito.when(event.getReaderId()).thenReturn(readerId);
		Mockito.when(event.getTagId()).thenReturn(tagId);
		
		TikitagEventView spy = (TikitagEventView)Mockito.spy(tikitagEventView);
		Mockito.doNothing().when(spy).removeReaderTagNode(uid, strTagId);
		spy.updateReaderTagData(event);
	}	

	
}
