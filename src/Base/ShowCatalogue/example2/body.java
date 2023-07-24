package Base.ShowCatalogue.example2;

import javax.swing.JFrame;

import Base.ShowCatalogue.MyFrame;

class body {

	public static void main(String[] args) {
		JFrame frame = new myFrame2("Swing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(700, 400);
		
		// 显示窗口
		frame.setVisible(true);

	}

}
