package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.marge.middleware.event.TestEventType;
import tests.marge.middleware.event.TestReaderEvent;
import tests.marge.middleware.event.TestTagEvent;
import tests.marge.middleware.listener.TestListener;
import tests.marge.middleware.listener.TestListenerFactory;
import tests.marge.middleware.listener.TestListenerFactoryAdaptee;
import tests.marge.middleware.publisher.TestTikitagPublisher;
import tests.marge.middleware.view.TestTikitagEventView;

@RunWith(Suite.class)
@SuiteClasses({TestEventType.class,
			 TestReaderEvent.class,
			 TestTagEvent.class,
			 TestListener.class,
			 TestListenerFactory.class,
			 TestTikitagPublisher.class,
			 TestTikitagEventView.class,
			 TestListenerFactoryAdaptee.class})

public class TestAll {


}
