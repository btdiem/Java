package marge.middleware.listener.factory;

import java.lang.reflect.Constructor;

import marge.middleware.listener.factory.specification.IListenerFactoryAdaptee;
import marge.middleware.listener.specification.IListener;
import marge.middleware.publisher.specification.IPublisher;
/**
 * This abstract class extends from {@link IListenerFactoryAdaptee} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public abstract class AbstractListenerFactoryAdaptee implements
		IListenerFactoryAdaptee {
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.factory.specification.IListenerFactoryAdaptee#createListener()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IListener createListener() throws Exception {
		Constructor<IListener> constructor = (Constructor<IListener>)Class.forName(getListenerClassName())
				.getConstructor(IPublisher.class);
		return  constructor.newInstance(createPublisher());		

	}

}
