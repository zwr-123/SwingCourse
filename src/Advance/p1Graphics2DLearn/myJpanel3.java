package Advance.p1Graphics2DLearn;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Utils.AfImageScaler;

/**
 * 学习 clip(Shape s)  绘制图片的一部分
 * @author ZW
 *
 */
class myJpanel3 extends JPanel {
	BufferedImage image;
	public myJpanel3()  {
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
		
		//图片按比例居中显示在窗口 不是此次重点
		int imageW=image.getWidth();
		int imageH=image.getHeight();
		AfImageScaler fit = new AfImageScaler(imageW,imageH,width,height);
		Rectangle rec = fit.fitCenterInside();
		
		//截取圆形区域显示，注意 根据矩形去画圆
		Shape region = new Ellipse2D.Double((width-100)/2, (height-100)/2, 100, 100);
		g2d.clip(region);
		g2d.drawImage(image, rec.x, rec.y, rec.width,rec.height,null);
		
	}
	

}
