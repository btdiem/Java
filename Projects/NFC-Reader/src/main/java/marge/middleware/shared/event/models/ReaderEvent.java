package marge.middleware.shared.event.models;

import com.tikitag.ons.model.util.ClientId;
import com.tikitag.ons.model.util.ReaderId;

import marge.middleware.shared.event.specification.*;

/**
 * This class is an implementation of {@link IEvent} and extends from {@link TikitagEvent} </br>
 * It is an instance of the events generated by Tikitag Reader </br> 
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public class ReaderEvent extends TikitagEvent implements IEvent{
		
	public ReaderEvent(ClientId clientId, ReaderId readerId,
			IEventType eventType, long timestamp) throws IllegalArgumentException{
		super(clientId, readerId, eventType, timestamp);
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.TikitagEvent#toString()
	 */
	@Override
	public String toString(){
		return "READER EVENT[" + super.toString() + "]";
	}
	
	
}
