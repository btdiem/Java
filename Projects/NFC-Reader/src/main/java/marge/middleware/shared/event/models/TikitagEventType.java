package marge.middleware.shared.event.models;

import com.tikitag.client.tagservice.ReaderEvent.ReaderEventType;
import com.tikitag.ons.model.util.TagEvent.TagEventType;

import marge.middleware.shared.event.specification.*;
/**
 * This class is an implementation of {@link IEventType} and extends from {@link AbstractEventType} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class TikitagEventType extends AbstractEventType implements IEventType{

	public TikitagEventType(String type) throws IllegalArgumentException {
		super(type);
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.IEventType#parseEvent(java.lang.String) </br>
	 * The event is translated to make sense with user's view </br>
	 */
	@Override
	public String parseEvent(String eventType) {
		if (eventType == null){
			return null;
		}
		if (eventType.equals(ReaderEventType.READER_ADDED.name())){
			return "Card is inserted";
		}else if (eventType.equals(ReaderEventType.READER_FAILURE.name())){
			return "Card is unreadable";
		}else if (eventType.equals(ReaderEventType.READER_REMOVED.name())){
			return "Card is removed";
		}else if(eventType.equals(TagEventType.PUT.name())){
			return "Tag is put on the reader";
		}else if (eventType.equals(TagEventType.REMOVE.name())){
			return "Tag is removed out the reader";
		}else if (eventType.equals(TagEventType.TOUCH.name())){
			return "Tag is touch";
		}else{
			return eventType;
		}
	}
	

	
}
