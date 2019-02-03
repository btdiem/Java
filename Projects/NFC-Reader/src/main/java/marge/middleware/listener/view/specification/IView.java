package marge.middleware.listener.view.specification;

import java.util.Observer;

import marge.middleware.listener.specification.IListener;
/**
 * This class is an interface View to display the data sent {@link IListener} </br>
 * @author btdiem </br>
 * @version 1.0 </br>
 *
 */
public interface IView extends Observer{

	public static final int Default_WIDTH=900;
	public static final int Default_HEIGHT=500;
	/**
	 * This method will display the {@link IView} </br>
	 * {@link IView} start listening to {@link IListener} system and update the received event and data of Tikitag reader and Tkitag tah </br>
	 */
	public void listen();
	
}
