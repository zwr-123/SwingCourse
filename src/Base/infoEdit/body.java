package Base.infoEdit;

import javax.swing.JFrame;

import Utils.Location;

/**
 * 简单的学生信息编辑界面
 * 实现功能————
 * 1：界面ui
 * 2：以json格式保存/读取文件
 * @author ZW
 *
 */
public class body {

	public static void main(String[] args) {
		myFrame myFrame = new myFrame("学生信息编辑");
		myFrame.setSize(700, 450);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Location.centerInScreen(myFrame);
		myFrame.setVisible(true);
	}

}
