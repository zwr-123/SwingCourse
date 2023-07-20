package Advance.p1Graphics2DLearn;

import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * 实际运行时，myFrame.setContentPane() 传入不同的Jpanel对象试一试
 * @author ZW
 *
 */
class body {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("测试");
//		myFrame.setContentPane(new myJpanel());     画线
//		myFrame.setContentPane(new myJpanel2());	四种填充
//		myFrame.setContentPane(new myJpanel3());	截取部分图片显示	
//		myFrame.setContentPane(new myJpanel4());    绘制彩色边框
//		myFrame.setContentPane(new drawText());		绘制文本--定位，字体
		myFrame.setContentPane(new drawTextPractice());  //手动精确定位文本 如：居中 靠右等	
		myFrame.setSize(new Dimension(700, 500));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}
	

}
