package Base.JToolBar;

import javax.swing.JFrame;

public class body {

	public static void main(String[] args) {
		JFrame frame = new MyFrame("JToolBar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(800, 600);
		
		// 显示窗口
		frame.setVisible(true);
	}

}
