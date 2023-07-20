package Advance.p1Graphics2DLearn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * 画出不同类型线条   setStroke(Stroke s)
 * @author ZW
 *
 */
class myJpanel extends JPanel{


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		int width=getWidth();
		int height=getHeight();
		
		Graphics2D g2d=(Graphics2D)g;
		
		//开启抗锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BasicStroke Stroke = new BasicStroke(2 //线宽
				, BasicStroke.CAP_BUTT
				, BasicStroke.CAP_ROUND			//圆角连接  两线相交时
				, 1
				, new float[] {20,4} 	// 设置线型  线条 空白 线条 空白  也就是虚线
//20 表示 线条 长度 4表示 线条间的空格长度
				, 0);
		g2d.setStroke(Stroke);
		g2d.setPaint(Color.RED);     //设置画笔颜色
		g2d.drawLine(0, 0, width, height);
		g2d.drawLine(0, height, width, 0);
		
	}
	

}
