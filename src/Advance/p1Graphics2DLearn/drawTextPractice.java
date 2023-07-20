package Advance.p1Graphics2DLearn;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
/**
 * 测量文本后，手动居中 右对齐 左对齐 等
 * @author ZW
 *
 */
class drawTextPractice extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		
		//绘制矩形用于对照文本位置
		Rectangle rectangle = new Rectangle((width-200)/2, (height-100)/2, 200, 100);
		g2d.setPaint(Color.blue);
		g2d.draw(rectangle);
		
		String text="我爱你鸭鸭";
		
		//测量文本   getFontMetrics()获取当前的字体
		FontMetrics fontMetrics = g2d.getFontMetrics();
		int stringWidth = fontMetrics.stringWidth(text);
		int leading = fontMetrics.getLeading();
		int ascent = fontMetrics.getAscent();
		int descent = fontMetrics.getDescent();
		//文本高度=leading+ascent+descent
		int stringHeight=leading+ascent+descent;
		
//		计算文本起始点的位置,先算出文本对应的外边框矩形。  效果--居中
//		int stringX=(width-stringWidth)/2;
//		int stringY=(height-stringHeight)/2+stringHeight-descent;
		
		//效果--靠矩形左下角
		int stringX=(width-200)/2;
		int stringY=(height-100)/2+100-descent;
		
		//绘制文本
		g2d.drawString(text, stringX, stringY);
	}
	

}
