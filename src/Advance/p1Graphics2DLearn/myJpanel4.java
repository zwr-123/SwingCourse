package Advance.p1Graphics2DLearn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.JPanel;

/**
 * 绘制彩色边框
 * 
 * @author ZW
 *
 */
class myJpanel4 extends JPanel {

	@Override
	protected void paintChildren(Graphics g) {
		// TODO Auto-generated method stub
		super.paintChildren(g);

		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;

		// 设置画笔线形， 画的是虚线 实线 还是其他自定义线形
		BasicStroke basicStroke = new BasicStroke(10); // 宽度为10的实线
		g2d.setStroke(basicStroke);

		Color c1 = Color.BLACK;
		Color c2 = Color.GREEN;

		// 绘制
		drawLine(g2d, 0, 0, width, 0, c1, c2);
		drawLine(g2d, width, 0, width, height, c1, c2);
		drawLine(g2d, 0, 0, 0, height, c1, c2);
		drawLine(g2d, 0, height, width, height, c1, c2);
	}

	public void drawLine(Graphics2D g, int x1, int y1, int x2, int y2, Color c1, Color c2) {
		// 起点
		Double start = new Point2D.Double(x1, y1);
		// 终点
		Double end = new Point2D.Double(x2, y2);

		// 颜色相关
		float[] dis = { 0.0f, 1.f };
		Color[] colors = { c1, c2 };
		Paint linearGradientPaint = new LinearGradientPaint(start, end, dis, colors);
		g.setPaint(linearGradientPaint);

		// 形状相关 画条线
		Shape shape = new Line2D.Double(start, end);
		// 此处不再用 g.drawLine()，指定shape 有更多的绘制选择
		g.draw(shape);
	}
}
