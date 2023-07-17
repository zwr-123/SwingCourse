package Base.ShowCatalogue;

import javax.swing.JFrame;


public class body {

	public static void main(String[] args) {
		JFrame frame = new MyFrame("Swing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(600, 400);
		
		// 显示窗口
		frame.setVisible(true);

	}

}
