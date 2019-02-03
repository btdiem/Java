package marge.middleware.shared.event.specification;
/**
 * This class is an interface of EventType </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IEventType {
	/**
	 * Return the type of {@link IEvent} </br>
	 * @return {@link String} </br>
	 */
	public String getType();
	/**
	 * Return a String that makes sense to user's view </br>
	 * @return {@link String} </br>
	 */
	public String normalizedData();
	/**
	 * Covert data from {@link IEventType#getType()} to {@link IEventType#normalizedData()} </br>
	 * @param eventType </br>
	 * @return {@link String} </br>
	 */
	public String parseEvent(String eventType);
}
