package Advance.p9TimeTask.s2emulateClock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.Timer;

class myPanel extends JPanel {
	SimpleDateFormat dateFormat;
	int ovalX;
	int ovalY;
	int newRecW;
	Timer timer;
	myPanel() {
		new Timer(50, new timeTask()).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
//      抗锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//		获取当前时间的时，分，秒
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);

//      绘制表盘
		drawClockFace(g2d);
//		360/60=6
		int len=newRecW/2;
//		时针
        drawHand(g2d, (int)(hour + minute / 60.0) * 30, len/5, Color.BLACK, 6);
//      分针
        drawHand(g2d, minute * 6, 4*len/7, Color.BLUE, 4);
//      秒针
        drawHand(g2d, second * 6, 8*len/10, Color.RED, 2);
        

		
	}

	private void drawClockFace(Graphics2D g2d) {
//		设置画笔粗细
		g2d.setStroke(new BasicStroke(2));
		
//		绘制一个最大的圆
		
//		获取面板长款
		int panelW=getWidth();
		int panelH=getHeight();
		
//		确定最大的正方形长 宽
		int recW=panelW;
		int recH=recW;
		if(recH>panelH) {
			recW=panelH;
			recH=recW;
		}
//		获取最大正方形
		Rectangle rectangle = new Rectangle((panelW-recW)/2, (panelH-recH)/2, recW, recH);
//		正方形缩小点，不然和面板边框重叠。实际还是重叠，无奈。
		rectangle.grow(-5, -5);
		
		newRecW=(int)rectangle.getWidth();
		
//		绘制最大正方形对应的圆。此处的x y 依旧是正方形的左上角，而非正方形中心或圆心
		g2d.drawOval((panelW-recW)/2, (panelH-recH)/2, newRecW, newRecW);
		
//		圆心（正方形中心）
		ovalX=(int)rectangle.getCenterX();
		ovalY=(int)rectangle.getCenterY();
		
//		绘制刻度和数字
		for (int i = 1; i <= 60; i++) {
			// 将角度制转化为弧度制
			double angle = i * 6 * Math.PI / 180;
			// 如果是5的倍数则说明是小时数字，刻度线需要更长一些
			int length = i % 5 == 0 ? 10 : 5;
			// 计算刻度线的坐标。
			// 得到角度的三角函数值，再乘以半径，得到刻度线的起点和终点坐标
			int x1 = (int) (ovalX + Math.sin(angle) * (newRecW/2));
			int y1 = (int) (ovalY - Math.cos(angle) * (newRecW/2));
			int x2 = (int) (ovalX + Math.sin(angle) * ((newRecW/2) - length));
			int y2 = (int) (ovalY - Math.cos(angle) * ((newRecW/2) - length));
			if (i % 5 == 0) {
				String str = Integer.toString(i / 5);
				// 获取字符串的高度和宽度
				int strWidth = g2d.getFontMetrics().stringWidth(str);
				int strHeight = g2d.getFontMetrics().getAscent();
				// 将字符串画在表盘上
				g2d.drawString(str, x2 - strWidth/2, y2 + strHeight/2);
			}
			// 将刻度线的始末坐标用线连起来
			g2d.draw(new Line2D.Double(x1, y1, x2, y2));
		}

	}



	private void drawHand(Graphics2D g2d, int angle, int length, Color color, int width) {
		g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        //将角度转换为弧度，并计算出指针末尾的坐标
        int x2 = (int) (ovalX + Math.sin(angle*Math.PI / 180) * length);
        int y2 = (int) (ovalY - Math.cos(angle*Math.PI / 180) * length);
        //绘制从钟表中心到指针末尾的直线
        g2d.drawLine(ovalX, ovalY, x2, y2);
	}

//	内部类 配合Timer使用
	class timeTask implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			myPanel.this.repaint();
		}
	}

}
