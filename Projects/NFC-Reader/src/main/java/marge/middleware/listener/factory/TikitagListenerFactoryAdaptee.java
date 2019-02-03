package marge.middleware.listener.factory;

import marge.middleware.listener.factory.specification.IListenerFactoryAdaptee;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.models.TikitagPublisher;
import marge.middleware.publisher.specification.IPublisher;
import marge.middleware.shared.event.specification.IEvent;
/**
 * This is an implementation of {@link IListenerFactoryAdaptee} </br>
 * and extends from {@link AbstractListenerFactoryAdaptee} </br>
 * {@link IListener} is created in this class will be received {@link IEvent} and data by {@link TikitagPublisher} </br> 
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public class TikitagListenerFactoryAdaptee extends AbstractListenerFactoryAdaptee
												implements IListenerFactoryAdaptee {
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.factory.specification.IListenerFactoryAdaptee#createPublisher() </br>
	 * Return a implementation of {@link IPublisher} </br>
	 * 
	 */
	@Override
	public IPublisher createPublisher() {
		return new TikitagPublisher();
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.factory.specification.IListenerFactoryAdaptee#getListenerClassName()
	 */
	@Override
	public String getListenerClassName() {
		return "marge.middleware.listener.models.TikitagListener";
	}

}
