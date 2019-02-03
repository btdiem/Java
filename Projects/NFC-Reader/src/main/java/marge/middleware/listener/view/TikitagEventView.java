package marge.middleware.listener.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Reader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Observable;
import java.util.Observer;

import javax.management.Notification;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

//import com.tikitag.client.tagservice.ReaderEvent;
import com.tikitag.client.tagservice.ReaderEvent.ReaderEventType;
import com.tikitag.client.tagservice.impl.PollResponse.Tag;
import com.tikitag.ons.model.util.ReaderId;
import com.tikitag.ons.model.util.TagEvent.TagEventType;
import com.tikitag.ons.model.util.TagId;
import com.tikitag.util.HexFormatter;

import marge.middleware.listener.models.TikitagListener;
import marge.middleware.listener.specification.IListener;
import marge.middleware.listener.view.specification.IView;
import marge.middleware.shared.event.models.ReaderEvent;
import marge.middleware.shared.event.models.TagEvent;
import marge.middleware.shared.event.specification.IEvent;
/**
 * This class is an User Interface that demonstrates for Publish/Subscribe model </br>
 * This is an {@link JFrame} and an implementation of {@link IView} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TikitagEventView extends JFrame implements IView, Serializable{
	
	final String TITLE = "Tikitag View";
	final String EVENT_TAB_TITLE = "Tikitag Event Table";
	final String DATA_TAB_TITLE = "Reader Tag Data";
	private JTextArea txtDescription;
	private DefaultTableModel eventTableModel;
	private static final long serialVersionUID = 5006443065468795922L;
	private JTable eventTable;
	private JTree tree;
	DefaultMutableTreeNode root = null;
	/**
	 * The view is a {@link JTabbedPane} with 2 Tabs (2 {@link JComponent} </br>
	 * The first tab is event table that updated real time events </br>
	 * The second one is data table that is reported by system </br>
	 * At the end of {@link JTabbedPane} is a {@link JTextArea} that shows the information of event more detail </br>
	 * @param model - {@IListener} </br>
	 */
	public TikitagEventView(IListener model) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		((TikitagListener)model).addObserver(this);
		setTitle(TITLE);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(Default_WIDTH, Default_HEIGHT));
		//create event tab
		JComponent eventTab = makeEventTablePanel();//makeTextPanel(EVENT_TAB_TITLE);
		eventTab.setPreferredSize(new Dimension(Default_WIDTH, Default_HEIGHT));
		//create reader tag tab
		JComponent dataTab = makeReaderTagPanel();//makeTextPanel(DATA_TAB_TITLE);
		//set title for event tab
		tabbedPane.add(EVENT_TAB_TITLE,eventTab);
		//set title for reader tag tab
		tabbedPane.add(DATA_TAB_TITLE,dataTab);
		//add Scrollbar to tabbed pannel
		JScrollPane scrollPane = new JScrollPane(tabbedPane);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		

	}

	/**
	 * The event is automatically notified to View by using {@link Observer} model </br>
	 * The view will register itself with {@link IListener} as an {@link Observer} </b> 
	 * and wait a change notification from {@IListener}
	 * {@link IEvent} attaches the event and reader/tag data </br>
	 * So it depends on specific implementation View, which data will be displayed </br>
	 * @param arg0 - {@link IListener} </br>
	 * @param arg1 - {@link IEvent} </br>
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		IEvent event = (IEvent)arg1;
		updateEventTable(event);
		updateReaderTagData(event);
		this.repaint();
	}
	/**
	 * Receiving the {@link Notification} from {@link IListener} </br>
	 * Parsing {@link IEvent} and update {@link TableModel} </br>
	 * @param event - {@link IEvent} </br>
	 */
	public void updateEventTable(IEvent event){
		
		String dateTime = event.getTime();
		String eventSrc = event.getSource();
		String eventData = event.getData();
		Object[] data = new Object[]{dateTime, eventSrc, eventData};
		eventTableModel.fireTableDataChanged();
		eventTableModel.addRow(data);
		setTitle(TITLE + " (" +eventTableModel.getRowCount() + ")");
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.view.specification.IView#listen() </br>
	 */
	@Override
	public void listen() {
		// TODO Auto-generated method stub
		this.setVisible(true);

	}
	/**
	 * Create a {@link JPanel} that contains a {@link JTable} and a {@link JTextArea} </br>
	 * {@link JTable} displays events at the real time reported by {@link IListener}</br>
	 * Meanwhile {@link JTextArea} display the data of event of {@link Reader} and {@link Tag} in more detail</br>
	 * @return a {@link JComponent} </br>
	 */
	public JComponent makeEventTablePanel(){
		 	
		JPanel panel = new JPanel(false);
		Object columnNames[] = { "Date time", "Event Source", "Event Data"};
		eventTableModel = new DefaultTableModel(columnNames, 0);
		eventTable = new JTable(eventTableModel);
		eventTable.setPreferredSize(new Dimension(Default_WIDTH, Default_HEIGHT));
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(450);
		eventTable.getColumnModel().getColumn(2).setPreferredWidth(400);
		eventTable.setAutoCreateRowSorter(true);
		JScrollPane spTable = new JScrollPane(eventTable);
		panel.setLayout(new BorderLayout());
		panel.add(spTable, BorderLayout.CENTER);
		eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent event) {
				updateDescription(eventTable.getSelectedRow());
			}
			
		});

		txtDescription = new JTextArea();
		txtDescription.setPreferredSize(new Dimension(Default_WIDTH, 80));
		panel.add(txtDescription,BorderLayout.SOUTH);
		return panel;
	}
	/**
	 * create a {@link JPanel} that contains {@link JTree}</br>
	 * Root node is your computer name <br>
	 * Child node of root is {@link ReaderId} and its data such as {@link ReaderId#getSerialNr()}</br>
	 * Child nodes of reader node are {@link Tag} and its data </br>
	 * Data of Tikitag tag are an URL that is stored in Heximal format </br> 
	 * @return {@link JComponent} </br>
	 * @throws UnknownHostException 
	 */
	public JComponent makeReaderTagPanel(){
		
		JPanel panel = new JPanel(true);
		try{
			root = new DefaultMutableTreeNode(InetAddress.getLocalHost().getHostName());
		}catch(Exception e){
			
		}
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);

        //treeModel = (DefaultTreeModel) tree.getModel();
		JScrollPane spTree = new JScrollPane(tree);
		panel.setLayout(new BorderLayout());
		panel.add(spTree, BorderLayout.CENTER);
		return panel;
	}
	/**
	 * There are 2 cases: {@link IEvent} is realization of {@link marge.middleware.shared.event.models.ReaderEvent} </br>
	 * Or {@link IEvent} is realization of {@link TagEvent} </br>
	 * When there is {@link ReaderEventType#READER_ADDED}, an new reader is added to {@link JTree} </br>
	 * When there is a {@link ReaderEventType#READER_REMOVED}, remove it out {@link JTree} </br>
	 * When there is a {@link TagEventType#PUT}, a new Tag is added to {@link JTree} </br>
	 * Whene there is a {@link TagEventType#REMOVE}, remove it out {@link JTree} </br>
	 * @param event - {@link IEvent} </br>
	 */
	public void updateReaderTagData(IEvent event){
		
		if (event instanceof marge.middleware.shared.event.models.ReaderEvent){
		
			ReaderEvent readerEvent = (ReaderEvent)event; 
			if (readerEvent.getEventType().getType().equals(ReaderEventType.READER_ADDED.name())){
				
				addReaderNode(readerEvent);
				
			}else if (readerEvent.getEventType().getType().equals(ReaderEventType.READER_REMOVED.name())){
				
				removeReaderTagNode(root.getUserObject().toString(), getUID(readerEvent.getReaderId()));
				
			}
		}else if (event instanceof TagEvent){
			
			TagEvent tagEvent = (TagEvent)event;
			if (tagEvent.getEventType().getType().equals(TagEventType.PUT.name())){
				//find category
				addTagNode(tagEvent);
				//add data to category
			}else if (tagEvent.getEventType().getType().equals(TagEventType.REMOVE.name())){
				//find the category
				removeReaderTagNode(getUID(tagEvent.getReaderId()), getTagId(tagEvent.getTagId()));
				//remove it out the category
			}
		}
		tree.expandPath(tree.getPathForRow(0));
	}
   
   /**
    * When user selects one row on event {@link JTable}, the event information is displayed in {@link JTextArea} </br>
    * The number of events will updated on the title of {@link JTabbedPane} </br>
    * @param selectedRow - {@link Integer} </br>
    */
	public void updateDescription(int selectedRow) {

		if (selectedRow < 0 || selectedRow >= eventTableModel.getRowCount()){
			return;
		}
		TableColumnModel tabColumnModel = eventTable.getTableHeader().getColumnModel();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < eventTableModel.getColumnCount(); i++){
			sb.append(tabColumnModel.getColumn(i).getHeaderValue()).append(" : ")
			.append(eventTableModel.getValueAt(selectedRow, i)).append("\n");
		}
		txtDescription.setText(sb.toString());
	}
	/**
	 * Create a reader node with parent is root </br>
	 * If event of reader has extra information, add them as leaf node of reader node </br>
	 * @param event - {@link ReaderEvent} </br>
	 */
	public void addReaderNode(ReaderEvent event){
		
		ReaderId id = event.getReaderId();
		String uid = getUID(id);
		//create a child
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(uid);
		child.add(new DefaultMutableTreeNode(getSerialNumber(id)));
		root.add(child);
		tree.collapsePath(tree.getPathForRow(0));
	}
	/**
	 * Normalize the {@link ReaderId#getUid()} data of {@link ReaderId} </br> 
	 * @param readerId - {@link ReaderId} </br>
	 * @return {@link String} </br>
	 */
	public String getUID(ReaderId readerId){
		return "UID="+HexFormatter.toHexString(readerId.getUid());
	}
	/**
	 * Normalize the data of {@link ReaderId#getSerialNr()} of {@link ReaderId}</br>
	 * @param readerId - {@link ReaderId} </br>
	 * @return {@link String} </br>
	 */
	public String getSerialNumber(ReaderId readerId){
		return "Serial Number="+readerId.getSerialNr();
	}
	/**
	 * Normalize the data of {@link TagId#getIdentifier()}
	 * @param tagId - {@link TagId}
	 * @return {@link String}
	 */
	public String getTagId(TagId tagId){
		return "TagId="+tagId.getIdentifier();
	}
	/**
	 * Find its {@link ReaderId} parent node that has the same {@link ReaderId#getUid()} with {@link TagEvent#getClientId()#getUID(ReaderId)}</br>
	 * Add data of a {@link TagEvent} to {@link JTree} </br>
	 * Transform data from Hexcimal to String </br>
	 * @param event - {@link IEvent} </br>
	 */
	@SuppressWarnings("resource")
	public void addTagNode(TagEvent event){
		
		String uid = getUID(event.getReaderId());
		String tagId = getTagId(event.getTagId());
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(tagId);
		if (event.getActionTag() != null){
			if (event.getActionTag().getTagData() != null){
				StringBuffer sb = new StringBuffer(HexFormatter.toHexString(event.getActionTag().getTagData()));
				if (sb.length() > 0){
					sb = new StringBuffer();
					sb.append(HexFormatter.pageView(HexFormatter.toHexString(event.getActionTag().getTagData())));
						Formatter offsetFormat = new Formatter();
						String[] arrays = offsetFormat.format("%s",  sb.toString()).toString().split("\n");
						for (String page : arrays){
							page = page.trim();
							child.add(new DefaultMutableTreeNode(page));
						}
					}
			}//if
		}//addTag
		/**
		 * find a reader parent node that has the name is UID of TagEvent
		 */
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode parent = findChildNode(root, uid);
		if (parent != null){
			parent.add(child);
			model.nodeStructureChanged(parent);
		}
	}
	/**
	 * This method will remove a node out the Tree data</br>
	 * If parent node is root, remove all of child for root node </br>
	 * If parent node is not root, iterate the tree to find {@link DefaultMutableTreeNode} parent node,</br>
	 * Then, iterate the child list of parent node to find deleted node </br>
	 * Delete it and update {@link DefaultTreeModel} </br>
	 * @param id - is {@link TagId#getIdentifier()} or {@link ReaderId#getUid()} </br>
	 */
	public void removeReaderTagNode(String parent, String node){
		
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode pNode = null;
		if (root.getUserObject().equals(parent)){
			pNode = root;
		}else{
			pNode = findChildNode(root, parent);
			if (pNode != null){
				DefaultMutableTreeNode child = findChildNode(pNode, node);
				if (child != null){
					child.removeAllChildren();
					pNode.remove(child);
					model.nodeStructureChanged(pNode);
				}
			}
		}//else
		
	}//void
	
	/**
	 * This method will find a child node from a parent node </br>
	 * Iterate the child collection of parent node </br> 
	 * If one of child of this collection has {@link DefaultMutableTreeNode#getUserObject()} is name of node </br>
	 * Return that node, otherwise return null if could not find any </br> 
	 * @param parent - {@link DefaultMutableTreeNode}
	 * @param childName - {@link String}
	 * @return {@link DefaultMutableTreeNode} or null
	 */
	public DefaultMutableTreeNode findChildNode(DefaultMutableTreeNode parent, String childName){
		
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		int childCount = model.getChildCount(parent);
		for (int j=0; j <childCount; j++){
			DefaultMutableTreeNode found = (DefaultMutableTreeNode)model.getChild(parent, j);
			if (found.getUserObject().equals(childName)){
				return found;
			}
		}
		return null;
	}
	
}
