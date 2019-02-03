package marge.middleware.listener.models;

import java.util.Observable;

import com.google.common.eventbus.Subscribe;
import marge.middleware.shared.event.specification.*;
import marge.middleware.publisher.specification.*;
import marge.middleware.listener.specification.*;

/**
 * This is an implementation of {@link IListener} interface </br>
 * @author btdiem
 * @version 1.0
 * @created 17-Jun-2013 1:32:00 PM
 */
public class TikitagListener extends Observable implements IListener {
	
	IPublisher publisher;
	/**
	 * {@link IListener} registers itself with {@link IPublisher} to receive {@link IEvent} </br> 
	 * @param {@link {@link IPublisher}} </br>
	 */
	public TikitagListener(IPublisher publisher) throws IllegalArgumentException{
		if (publisher == null){
			throw new IllegalArgumentException("Input parameter is NULL.");
		}
		publisher.addListener(this);
		this.publisher = publisher;		
	}

	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.specification.IListener#notifiy(marge.middleware.shared.event.specification.IEvent)
	 */
	@Subscribe
	public void notifiy(IEvent event){
		notifyObservers(event);
	}

	
	@Override
	public void notifyObservers(Object arg) {
		this.setChanged();
		super.notifyObservers(arg);
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.listener.specification.IListener#start()
	 */
	@Override
	public void start() {
		publisher.start();
	}

}