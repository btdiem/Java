package marge.middleware.listener.specification;

import java.util.Observer;

import marge.middleware.publisher.specification.IPublisher;
import marge.middleware.shared.event.specification.*;
/**
 * This is an interface that takes a role as subscriber</br>
 * It will receive {@link IEvent} sent by {@link IPublisher} and then forward these {@link IEvent} to {@link Observer} list </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IListener {
	/**
	 * This method will receive the event sent by {@link IPublisher} </br>
	 * Mean while, it will notify received events to {@link Observer} </br>
	 * @param event - {@link IEvent} </br>
	 */
	public void notifiy(IEvent event);
	/**
	 * Start receiving the {@link IEvent} from {@link IPublisher } </br>
	 */
	public void start();
}
