package marge.middleware.shared.event.specification;
/**
 * This is an interface of Event </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IEvent {
	/**
	 * Return the source information that generates event </br>
	 * @return {@link String}
	 */
	public String getSource();
	/**
	 * Return the data of Event </br>
	 * @return {@link String} </br>
	 */
	public String getData();
	/**
	 * Return the time when event is generated </br>
	 * The format of date time is decided by implementation class </br>
	 * @return {@link String} </br>
	 */
	public String getTime();
}
