package Advance.p9TimeTask.s2emulateClock;

import javax.swing.JFrame;

class body1 {

	public static void main(String[] args) {
		JFrame jFrame = new JFrame("模拟时钟");
		myPanel clock = new myPanel();
		jFrame.setContentPane(clock);

		jFrame.setSize(600, 400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

}
