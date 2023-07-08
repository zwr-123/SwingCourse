package Base.JPopupMenu;

import javax.swing.JFrame;


/**
 * 菜单选项功能
 * @author ZW
 *
 */
public class body {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame myFrame = new myFrame("我的窗口");
		myFrame.setSize(700,300);
		//点击x 自动关闭程序（窗口）
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//使窗口可见
		myFrame.setVisible(true);
	}

}
