package Advance.p1Graphics2DLearn;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * 学习文字的字体设置
 * @author ZW
 *
 */
class drawText extends JPanel{

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setPaint(Color.BLUE);
		//设置楷体，既是粗体又是斜体 大小20
		Font font = new Font("楷体", Font.BOLD| Font.ITALIC, 20);
//		基于原有的字体来改变字体，这里只改变了大小
//		Font deriveFont = font.deriveFont(40);
		g2d.setFont(font);
	
		//定位绘制文本 
		g2d.drawString("你好啊", 50, 50);
		
//		另一种全面设置字体的方式  大小 下划线  等
//		HashMap<TextAttribute, Object> fontMap = new HashMap<>();
//		fontMap.put(TextAttribute.FAMILY, "宋体");
//		fontMap.put(TextAttribute.SIZE, 40);
//		Font font2 = new Font(fontMap);
		
//		文本测量 通过fontMetrics获得文本的各种参数，用于精确定位 绘制 文本 
//		FontMetrics fontMetrics = g2d.getFontMetrics(font);
//		int stringWidth = fontMetrics.stringWidth("你好啊");
//		int height2 = fontMetrics.getHeight();
//		int leading = fontMetrics.getLeading();
//		int ascent = fontMetrics.getAscent();
//		int descent = fontMetrics.getDescent();
	}

}
