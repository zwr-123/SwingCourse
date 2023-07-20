package Advance.p1Graphics2DLearn;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 四种填充类型 具体原理可见jdk文档 Paint 接口的实现类
 * setPaint(Paint p)
 * @author ZW
 *
 */
class myJpanel2 extends JPanel {
	BufferedImage image;
	public myJpanel2()  {
		URL resource = getClass().getResource("/Advance/advacePaint/纹理.jpeg");
		try {
			image = ImageIO.read(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;

		// 纯色填充
		g2d.setPaint(Color.GRAY); // 设置画笔颜色
		g2d.fillRect(0, 0, width / 2, height / 2);

		// 渐变填充
		Point2D start = new Point2D.Float(width / 2, 0); // 起点
		Point2D end = new Point2D.Float(width, 0); // 终点
		float[] dist = { 0.0f, 0.5f, 1.0f }; // 关键点，每个关键点都应设置一个颜色
		Color[] colors = { Color.red, Color.BLACK, Color.BLUE }; // 对应上面每处的颜色
		LinearGradientPaint p = new LinearGradientPaint(start, end, dist, colors);
		g2d.setPaint(p);
		g2d.fillRect(width / 2, 0, width / 2, height / 2);

		// 辐射填充
		Point2D center = new Point2D.Float(width / 4, height/4*3);
		float radius = width/4;
		float[] dist2 = { 0.0f, 0.4f, 1.0f };
		Color[] colors2 = { Color.YELLOW, Color.GREEN, Color.black };
		RadialGradientPaint p2 = new RadialGradientPaint(center, radius, dist2, colors2);
		g2d.setPaint(p2);
		g2d.fillRect(0, height / 2, width / 2, height / 2);
		
		
		//纹理填充  若图片小于填充范围，则循环用此图片填满区域
		Rectangle anchor = new Rectangle(0, 0, image.getWidth(), image.getHeight()); 
		//anchor 用于选取图片部分区域
		TexturePaint texturePaint = new TexturePaint(image, anchor);
		g2d.setPaint(texturePaint);
		g2d.fillRect(width / 2, height / 2, width / 2, height / 2);
		

	}

}
