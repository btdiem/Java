package marge.middleware.publisher.models;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.tikitag.client.tagservice.ReaderEvent;
import com.tikitag.client.tagservice.ReaderMonitor;
import com.tikitag.client.tagservice.TagMonitor;
import com.tikitag.client.tagservice.TagService;
import com.tikitag.client.tagservice.TagServiceConfiguration;
import com.tikitag.client.tagservice.TagType;
import com.tikitag.client.tagservice.impl.TagServiceImpl;
import com.tikitag.ons.model.util.ClientId;
import com.tikitag.ons.model.util.ReaderId;
import com.tikitag.ons.model.util.TagEvent;
import com.tikitag.ons.model.util.TagId;
import com.tikitag.ons.model.util.TagInfo;

import marge.middleware.publisher.specification.*;
import marge.middleware.shared.event.specification.*;
import marge.middleware.shared.event.models.*;
/**
 * {@link TagServiceConfiguration} will read the configuration of tikitag readers and save them into table with key is {@link ClientId}</b>
 * {@link ClientId} is the ip address of device that connects to tikitag reader </br>
 * {@link TagService} will use {@link TagServiceConfiguration} to manage the configuration of all reader and tag reader </br>
 * Because the tikitag reader and tikitag tag are passive, so to know they are connected or changed their configuration, </br>
 * The good way is to send a polling command to query their status </br>
 * In a certain time, {@link TagService} will do 2 jobs: </br>
 * 1. Send polling command to detect reader and tag's data by calling {@link Acr122TagReader} and {@link MifareTag}</br>
 * 2. Set threshold value for generating the events </br>
 * The device's data is managed by {@link ReaderManager} and the events are managed by {@link ReaderManager} </br> 
 * 
 * @author btdiem
 * @version 1.0
 * @created 17-Jun-2013 1:32:01 PM
 */
public class TikitagPublisher extends AbstractPublisher 
											implements IPublisher {
	private Logger log = Logger.getLogger(this.getClass());
    private TagService tagService;
    private TagServiceConfiguration tagServiceConfiguration;
    private String propertyFile = "configuration.properties";
    private Properties properties = new Properties();
    private String pollIntervalsPropertiy = "tikitag.pollintervals";
    private String ThresholdtimePropertiy = "tikitag.thresholdtime";
    
	/**
	 * Create {@link TagServiceConfiguration} object and read the setting properties </br>
	 * When {@link TagService} created, {@link TagService} will start detecting all readers and tags that connected local address </br>
	 */
    public TikitagPublisher(){
        try{
        	properties.load(new FileInputStream(propertyFile));
        }catch(Exception e){
        	log.error("Can not read properties file"+e);
        }
    	tagServiceConfiguration = new TagServiceConfiguration();
    	if (!properties.contains(pollIntervalsPropertiy)){
    		tagServiceConfiguration.setPollInterval(Long.valueOf(properties.getProperty(pollIntervalsPropertiy)));
    	}else{
    		tagServiceConfiguration.setPollInterval(DEFAULT_PollInterval);
    	}
    	if (!properties.contains(ThresholdtimePropertiy)){
    		tagServiceConfiguration.setPutThresholdTime(Long.valueOf(properties.getProperty(ThresholdtimePropertiy)));
    	}else{
    		tagServiceConfiguration.setPutThresholdTime(DEFAULT_Threshold);
    	}
         
        TagType[] tagTypes=tagServiceConfiguration.getDetectTagTypes();
        for (int i = 0; i < tagTypes.length; i++) {
			TagType type = tagTypes[i];
	        log.info("Detected tag type:" + type.toString());		
		}
        tagService = new TagServiceImpl(tagServiceConfiguration);

    }
    /*
     * (non-Javadoc)
     * @see marge.middleware.publisher.specification.IPublisher#start()
     */
    public void start(){
    	startService();
    	publishEvent();
    }
    /*
     * (non-Javadoc)
     * @see marge.middleware.publisher.specification.IPublisher#startService()
     */
    public void startService(){
        
		tagService.addReaderMonitor(new ReaderMonitorImpl());
		tagService.addTagMonitor(new TagMonitorImpl());
		tagService.start();
    }
    /*
     * (non-Javadoc)
     * @see marge.middleware.publisher.specification.IPublisher#onEvent(marge.middleware.shared.event.specification.IEvent)
     */
    public  synchronized void onEvent(IEvent event){
		eventQueue.add(event);
	}

	/**
	 * This class implements {@link ReaderMonitor} interface and it will be managed by {@link TagService} </br>
	 * Listening to and catching {@link ReaderEvent} generated by Tikitag Reader and reported under form of {@link IEvent} object</br>
	 *
	 */
	private class ReaderMonitorImpl implements ReaderMonitor{
	
	    public void onReaderEvent(ReaderEvent readerEvent){
	    
	    	ClientId clientId = tagServiceConfiguration.getClientId();
	    	ReaderId readerId = readerEvent.getReaderId();
	    	IEventType eventType = new TikitagEventType(readerEvent.getEventType().name());
	    	long currentTime = System.currentTimeMillis();
	    	IEvent event = new  marge.middleware.shared.event.models.ReaderEvent(
	    			clientId, readerId, eventType, currentTime);
	    	onEvent(event);
	    }
	}
	/**
	 * This class implements {@link TagMonitor} interface and it will be managed by {@link TagService} </br>
	 * Listening to and catching {@link TagEvent} generated by Tikitag Tag and reported under form of {@link IEvent} object</br>
	 */
	private class TagMonitorImpl implements TagMonitor{

		@Override
		public void onTagEvent(TagEvent tagEvent) {
			
	    	ClientId clientId = tagServiceConfiguration.getClientId();
	    	ReaderId readerId = tagEvent.getReaderId();
	    	TagId tagId = tagEvent.getActionTag().getTagId();
	    	IEventType eventType = new TikitagEventType(tagEvent.getTagEventType().name());
	    	long currentTime = System.currentTimeMillis();
	    	TagInfo actionTag = null;
	    	TagInfo contextTag = null;
	    	if (tagEvent.getActionTag() != null){
	    		actionTag = tagEvent.getActionTag();
	    	}
	    	if (tagEvent.getContextTag() != null){
	    		contextTag = tagEvent.getContextTag();
	    	}
	    	IEvent event = new  marge.middleware.shared.event.models.TagEvent(
	    			clientId, readerId, eventType, currentTime, tagId, actionTag, contextTag);
	    	onEvent(event);

		}
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.publisher.specification.IPublisher#getEventQueue()
	 */
	public Queue<IEvent> getEventQueue(){
		return eventQueue;
	}

}