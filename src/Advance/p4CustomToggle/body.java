package Advance.p4CustomToggle;

import java.awt.Dimension;

import javax.swing.JFrame;

import Advance.p4CustomToggle.toggleButton.SatateListener;


/**
 * 自定义切换控件：绘制 事件
 * @author ZW
 *
 */
class body {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("测试");
		toggleButton toggle = new toggleButton();
		myFrame.setContentPane(toggle);
		myFrame.setSize(new Dimension(700, 500));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		
//		内部接口的匿名实现类的匿名对象   自定义的监听器
		toggle.setStateListener(new SatateListener() {

			@Override
			public void stateChanged(StateEvent e) {
				System.out.println("你好啊");
			}});
	}

}
