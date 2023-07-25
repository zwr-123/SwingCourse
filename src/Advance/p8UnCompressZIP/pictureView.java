package Advance.p8UnCompressZIP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 自定义面板，加载显示图片
 * @author ZW
 *
 */
class pictureView  extends JPanel{
	private Image image;
	private Color backGroundColor;
	
	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
		this.repaint();//重新绘制，为什么要有这个
	}
	
	
	public void setimage(Image image) {
		this.image = image;
		this.repaint();
	}
	
	
	public void setimage(File file) {
		try {
			this.image=ImageIO.read(file);
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setimage(String resourcePath) {
		InputStream in = this.getClass().getResourceAsStream(resourcePath);
		try {
			this.image=ImageIO.read(in);
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int panelW=this.getWidth();
		int panelH = this.getHeight();
		if(backGroundColor!=null) {
			g.setColor(backGroundColor);
			g.fillRect(0, 0, panelW, panelH);
		}
		
		
		if(image!=null) {
			int imgW=image.getWidth(null);
			int imgH=image.getHeight(null);
			//这句有什么用？
			Rectangle rectangle = new Rectangle(0, 0, panelW, panelH);
			
			rectangle.grow(-2, -2);
			rectangle =fitPicture(rectangle,imgW,imgH);
			g.drawImage(image, rectangle.x, rectangle.y, rectangle.width,rectangle.height,null);
			
			//在图片周围绘制边框
			g.setColor(new Color(255, 215, 0));
			g.drawRect(rectangle.x, rectangle.y, rectangle.width-1, rectangle.height-1);
		}
	}
	
	
	
	//保持比例展示图片，并且让图片不遮盖其边框
	private Rectangle fitPicture(Rectangle rec, int imgW,int imgH) {
		int realW=rec.width;
		int realH=rec.width*imgH/imgW;
		if(realH>rec.height) {
			realH=rec.height;
			realW=rec.height*imgW/imgH;
		}
		int x=rec.x+(rec.width-realW)/2;
		int y=rec.y+(rec.height -realH)/2;
		return new Rectangle(x,y,realW,realH);
	
	}
	

}
