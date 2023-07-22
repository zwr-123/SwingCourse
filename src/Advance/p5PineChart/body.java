package Advance.p5PineChart;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 饼状图的绘制
 * @author ZW
 *
 */
public class body {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("饼状图");
		panel panel = new panel();
		myFrame.setContentPane(panel);
		myFrame.setSize(new Dimension(900, 900));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		
		
		panel.addPie(new pie(100,"儿童",null));
		panel.addPie(new pie(500,"大人",null));
		panel.addPie(new pie(400,"老人",null));
	}	
}
