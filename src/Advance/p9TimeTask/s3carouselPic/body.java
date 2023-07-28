package Advance.p9TimeTask.s3carouselPic;

import java.io.IOException;

import javax.swing.JFrame;
/**
 * 循环展示图片
 * 定时器，绘制图片
 * @author ZW
 *
 */
class body {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JFrame jFrame = new JFrame();
		myPanel myPanel = new myPanel();
		myPanel.addImage("C:\\Users\\ZW\\Documents\\workspace-spring-tool-suite-4-4.17.2\\SwingCourse\\src\\Advance\\p9TimeTask\\s3carouselPic\\pic");
		jFrame.setContentPane(myPanel);
		jFrame.setSize(500, 400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

}
