package Advance.p4CustomToggle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

public class toggleButton extends JPanel{
	public Color bgcolor=new Color(0xFFFFFF);
	public Color linecolor=new Color(0xDEDEDE);
	public Color darkcolor=new Color(0xE1E1E1);
	public Color lightcolor=new Color(0x33B4FF);
	public int padding=2;
	
	boolean selected=false;
	SatateListener stateListener;
	
	public toggleButton() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
//				toggleButton.this.selected=true;   两种写法都能控制 toggleButton里的属性
				toogle();
				
				//把自定义的监听器和鼠标监听器绑定。从外面看来，就好像自定义了鼠标监听器
				if(stateListener!=null) {
					StateEvent event=new StateEvent(this, 0);
					event.state=toggleButton.this.selected;
					stateListener.stateChanged(event);
				}
			}
		});
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean flag) {
		this.selected=flag;
		repaint();
	}
	
	public void toogle() {
		this.selected=!this.selected;
		repaint();
	}
	
	//内部接口  
	public  interface SatateListener{
		void stateChanged(StateEvent e);
	}
	
	//自定义监听器
	public void setStateListener(SatateListener s) {
		this.stateListener=s;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int width=getWidth();
		int height=getHeight();
		
		Graphics2D g2d=(Graphics2D)g;
		
		
//		根据面板的宽 高，得到一个 最大的 边长为2:1 的矩形
		int w=width;
		int h=width/2;
		if(h>height) {
			h=height;
			w=height*2;
		}
		Rectangle rectangle = new Rectangle((width-w)/2, (height-h)/2, w, h);
		//在原有矩形上均分出左右两个矩形
		Rectangle rectangle1 = new Rectangle((width-w)/2, (height-h)/2, w/2, h);
		Rectangle rectangle2 = new Rectangle((width-w)/2+w/2, (height-h)/2, w/2, h);
		
		//根据左右矩形确定对应的圆弧
		Shape arc1 = new Arc2D.Float(rectangle1,90,180,Arc2D.OPEN);
		Shape arc2 = new Arc2D.Float(rectangle2,270,180,Arc2D.OPEN);
		
		//连接圆弧
		Path2D line = new Path2D.Float();
		line.append(arc1, true);
		line.append(arc2, true);
		line.closePath();
		
		//绘制按钮主体
		g2d.setPaint(linecolor);
		g2d.draw(line);
		g2d.setPaint(bgcolor);
		g2d.fill(line);
		
//		绘制两个圆
		if(selected) {
//			选中 蓝色
			drawCircleInside(g2d,rectangle2,padding,linecolor,lightcolor);
		}else {
//			未选中 灰色
			drawCircleInside(g2d,rectangle1,padding,linecolor,darkcolor);
		}
	}


	public void drawCircleInside(Graphics2D g,Rectangle rec,int deflate,Color linecolor,Color fillColor) {
//		复制这个 矩形对象
		Rectangle rectangle = new Rectangle(rec);
/**
 * 	padding效果，即圆和button主体边缘不重叠，往里缩了点。需要先改变坐标。
 * 因为，圆是根据矩形绘制，矩形又是从它的左上角坐标（起点）绘制，单纯改变矩形的长 宽，
 * 还会重叠
 */
		rec.x+=deflate;
		rec.y+=deflate;
		rec.width-=(deflate*2);
		rec.height-=(deflate*2);
		
		Shape shape = new Ellipse2D.Double(rec.x,rec.y,rec.width,rec.height);
		g.setPaint(linecolor);
		g.draw(shape);
		
		g.setPaint(fillColor);
		g.fill(shape);
	}
}
