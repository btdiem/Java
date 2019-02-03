package marge.middleware.publisher.specification;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.tikitag.client.tagservice.ReaderMonitor;
import com.tikitag.client.tagservice.TagMonitor;

import marge.middleware.listener.specification.*;
import marge.middleware.shared.event.specification.*;

/**
 * This class is an interface to define the methods to add subscriber </br>
 * connect and retrieve data of reader and smart card </br>
 * The data and events will be collected and are publisher to all subscriber via {@link EventBus} </b>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IPublisher {

	public final int DEFAULT_PollInterval = 100;
	public final int DEFAULT_Threshold = 1000;
	/**
	 * This method is called when {@link IListener} wants to register himself and receive events from {@link IPublisher} </br>
	 * {@link IListener} now takes a role as a subscriber </br>
	 * The {@link IEvent} will be notified automatically from {@link IListener} to {@link IListener} via a {@link EventBus} system </br>
	 * Add a {@link Subscribe} to list </br>
	 * @param listener - {@link IListener}
	 */
	public void addListener(IListener listener);
	/**
	 * This method is called when {@link ReaderMonitor} or {@link TagMonitor} received an event </br>
	 * After receiving, the event will be left in a Queue and then publish to {@link IListener} </br> 
	 * @param event - {@link IEvent} </br>
	 */
	public void onEvent(IEvent event);
	/**
	 * There is a {@link Timer} to execute {@link TimerTask} that pop events from a {@link Queue} </br>
	 * and publishes them to all subscribers </br>
	 */
	public void publishEvent();
	/**
	 * Start running service and publishing the received events </br>
	 */
	public void start();
	/**
	 * Starting detecting the events coming from tikitag reader and tag reader </br> 
	 * Starting retrieving the configuration and checking the connection between the system and readers/tags </br>
	 */
	public void startService();
	/**
	 * Return a list of {@link IEvent} </br>
	 * @return {@link Queue} </br>
	 */
	public Queue<IEvent> getEventQueue();
}
