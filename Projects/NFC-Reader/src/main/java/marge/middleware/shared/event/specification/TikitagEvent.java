package marge.middleware.shared.event.specification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tikitag.ons.model.util.ClientId;
import com.tikitag.ons.model.util.ReaderId;
/**
 * This abstract class extends from {@link IEvent} interface </br>
 * It is a middle class between Tikitag event data and {@link IEvent} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public abstract class TikitagEvent implements IEvent {

	protected ClientId clientId;
	protected ReaderId readerId;
	protected IEventType eventType;
	protected long timestamp;
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.IEvent#getSource() </br>
	 * The return value is a combination between ClientId#getName() and ReaderId#getSerialNr() </br>
	 */
	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return "ClientId=" + clientId.getName() + ",ReaderId=" + readerId.getSerialNr();
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.IEvent#getTime() </br>
	 * The format of output string is dd/mm/yyyy hh:mm:ss </br>
	 */
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return df.format(new Date(timestamp));
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.IEvent#getData()
	 */
	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return eventType.normalizedData();
	}

	public TikitagEvent(ClientId clientId, 
						ReaderId readerId, 
						IEventType eventType, 
						long timestamp) throws IllegalArgumentException{
		if (clientId == null){
			throw new IllegalArgumentException("ClientId is NULL.");
		}
		if (readerId == null){
			throw new IllegalArgumentException("ReaderId is NULL.");
		}
		if (eventType == null){
			throw new IllegalArgumentException("EventType is NULL.");
		}
		if (timestamp <= 0){
			throw new IllegalArgumentException("Timestamp is negative.");
		}
		this.clientId = clientId;
		this.readerId = readerId;
		this.eventType = eventType;
		this.timestamp = timestamp;
	}

	public ClientId getClientId() {
		return clientId;
	}

	public ReaderId getReaderId() {
		return readerId;
	}

	public IEventType getEventType() {
		return eventType;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+  clientId.hashCode();
		result = prime * result + eventType.hashCode();
		result = prime * result + readerId.hashCode();
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TikitagEvent other = (TikitagEvent) obj;
		return clientId==other.clientId 
				&& eventType == other.eventType
				&& readerId == other.readerId
				&& timestamp == other.timestamp;
		
	}

	@Override
	public String toString() {
		return "DateTime="+getTime() + ",EventSource=" + getSource() + ",EventData=" + getData();
	}
	
	

}
