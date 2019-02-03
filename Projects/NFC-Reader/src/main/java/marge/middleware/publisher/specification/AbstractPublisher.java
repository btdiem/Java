package marge.middleware.publisher.specification;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.eventbus.EventBus;
import marge.middleware.shared.event.specification.*;
import marge.middleware.listener.specification.*;
/**
 * This is an an abstract class that extends from {@link IPublisher} and implements some method of parent class </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 */
public abstract class AbstractPublisher implements IPublisher {

	protected EventBus eventBus;
	protected Queue<IEvent> eventQueue;
	Timer timer;
	long intervals = 3*1000;
	
	public AbstractPublisher(){
		eventBus = new EventBus();
		eventQueue = new LinkedList<IEvent>();
		timer = new Timer();
	}

	/*
	 * (non-Javadoc)
	 * @see marge.middleware.publisher.specification.IPublisher#addListener(marge.middleware.listener.specification.IListener)
	 */
	public void addListener(IListener listener){
		eventBus.register(listener);
	}
	/*
	 * (non-Javadoc)
	 * @see marge.middleware.publisher.specification.IPublisher#publishEvent()
	 */
	public void publishEvent(){
		timer.schedule(new PublishEventTask(), 0 , intervals);
	}
	
	class PublishEventTask extends TimerTask {

		@Override
		public void run() {
			while(!eventQueue.isEmpty()){
				eventBus.post(eventQueue.poll());
			}
		}//run
		
	}//class

}