package marge.middleware.shared.event.models;

import marge.middleware.shared.event.specification.*;
/**
 * This abstract class extends from {@link IEventType} interface </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public abstract class AbstractEventType implements IEventType{

	private String type;
	
	public AbstractEventType(String type) throws IllegalArgumentException{
		if (type == null){
			throw new IllegalArgumentException("Type parameter is NULL.");
		}
		this.type = type;
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.IEventType#getType()
	 */
	@Override
	public String getType() {
		return this.type;
	}
	@Override
	public String toString(){
		return "EventType="+type;
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.shared.event.specification.IEventType#normalizedData()
	 */
	@Override
	public String normalizedData() {
		return parseEvent(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IEventType other = (IEventType) obj;
		return type == other.getType();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result +  type.hashCode();
		return result;
	}
}
