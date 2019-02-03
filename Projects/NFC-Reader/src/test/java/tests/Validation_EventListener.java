package tests;

import marge.middleware.listener.factory.*;
import marge.middleware.listener.factory.specification.*;
import marge.middleware.listener.models.TikitagListener;
import marge.middleware.listener.specification.IListener;
import marge.middleware.listener.view.*;

public class Validation_EventListener {

	public static void main(String[] args) {
		
		try {
			
			IListenerFactory factory = new ListenerFactoryAdapter(new TikitagListenerFactoryAdaptee());
			IListener listener = factory.createListener();
			listener.start();
			TikitagEventView view = new TikitagEventView((TikitagListener)listener);
			view.listen(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
