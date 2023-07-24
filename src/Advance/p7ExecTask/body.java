package Advance.p7ExecTask;

import javax.swing.JFrame;
/**
 * 任务执行  抽象类的继承  模仿简化理解SwingWorker类
 * JProgressBar的使用
 * @author ZW
 *
 */
public class body {

	public static void main(String[] args) {
		myFrame myFrame = new myFrame("测试窗口");
		myFrame.setSize(600, 400);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
