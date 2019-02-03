package marge.middleware.listener.factory;

import marge.middleware.listener.factory.specification.IListenerFactory;
import marge.middleware.listener.factory.specification.IListenerFactoryAdaptee;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.specification.IPublisher;
/**
 * This is a class that produces {@link IListener} and {@link IPublisher} by a {@link IListenerFactoryAdaptee} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class ListenerFactoryAdapter implements IListenerFactory {

	IListenerFactoryAdaptee listenerFactoryAdaptee;

	public ListenerFactoryAdapter(IListenerFactoryAdaptee listenerFactoryAdaptee){
		if (listenerFactoryAdaptee == null){
			throw new IllegalArgumentException ("Input parameter is null.");
		}
		this.listenerFactoryAdaptee = listenerFactoryAdaptee;
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.factory.specification.IListenerFactory#createListener() </br>
	 * @see also {@link IListenerFactoryAdaptee#createPublisher()} </br>
	 */
	@Override
	public IListener createListener() throws Exception {
		return listenerFactoryAdaptee.createListener();
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.factory.specification.IListenerFactory#createPublisher() </br>
	 * @see also {@link IListenerFactoryAdaptee#createPublisher()} </br>
	 */
	@Override
	public IPublisher createPublisher() {
		return listenerFactoryAdaptee.createPublisher();
	}

}
