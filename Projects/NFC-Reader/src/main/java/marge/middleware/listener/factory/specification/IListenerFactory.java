package marge.middleware.listener.factory.specification;

import marge.middleware.listener.specification.*;
import marge.middleware.publisher.specification.*;

/**
 * This is an interface that defines methods to create {@link IListener} and {@link IPublisher} </br>
 * @author btdiem
 * @version 1.0
 * @created 17-Jun-2013 1:32:01 PM
 */
public interface IListenerFactory {
	/**
	 * Create {@link IListener} </br>
	 * @return {@link IListener} </br>
	 */
	public IListener createListener() throws Exception;
	/**
	 * Create a {@link IPublisher}
	 * @return {@link IPublisher}
	 */
	public IPublisher createPublisher();

}