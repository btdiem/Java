package marge.middleware.listener.factory.specification;

import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.specification.IPublisher;
/**
 * This is an interface that adapts between {@link IListenerFactory} and its implementation </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IListenerFactoryAdaptee {
	/**
	 * 
	 * @return {@link IListener} </br>
	 * @throws Exception </br>
	 */
	public IListener createListener() throws Exception;
	/**
	 * Return an {@link IPublisher}
	 * @return {@link IPublisher} </br>
	 */
	public IPublisher createPublisher();
	/**
	 * Return a full class name that implements {@link IListener} </br>
	 * and match with a implementation {@link IPublisher} </br>
	 * @return {@link String}
	 */
	public String getListenerClassName();
}
