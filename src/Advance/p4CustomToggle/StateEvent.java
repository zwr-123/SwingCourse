package Advance.p4CustomToggle;

import java.awt.AWTEvent;
/**
 * 自定义的事件 ActionEvent
 * @author ZW
 *
 */
class StateEvent extends AWTEvent{
	public boolean state;

	public StateEvent(Object source, int id) {
		super(source, id);
		// TODO Auto-generated constructor stub
	}
}
