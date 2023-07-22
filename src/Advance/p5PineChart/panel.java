package Advance.p5PineChart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import javax.swing.JPanel;

 class panel extends JPanel{
//	 扇形对象集合
	ArrayList<pie> list = new ArrayList<>();
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
//		计算，也即是补全每个扇形对象的属性值
		calculate();
		
//		循环绘制
		for (pie item : list) {
			if(item.shape!=null) {
				g2d.setPaint(item.color);
				g2d.fill(item.shape);
			}
		}
	}
	
	public void addPie(pie p) {
		list.add(p);
	}
	
//	计算
	public void calculate() {
		if(list.size()==0) {
			return;
		}
		
		double totalAmount=0;
		for (pie item : list) {
			totalAmount=item.amount+totalAmount;
		}
		
		double totalDegree=0;
		for(int i=0;i<=list.size()-1;i++) {
			pie p = list.get(i);
			p.degree=(p.amount)/totalAmount*360;
//			如果太小，此部分扇形就看不见，所以给个能看见的最小值
			if(p.degree<3) {
				p.degree=3;
			}
//			确保加起来总共360度，若直接正向计算，总和不精确
			if(i==list.size()-1) {
				p.degree=360-totalDegree;
			}
			
			totalDegree=p.degree + totalDegree;
		}
		
//		取最大的正方形，用于确定圆
		int width = getWidth();
		int height = getHeight();
		int w=width;
		int h=width;
		if(h>height) {
			w=height;
			h=height;
		}
		Rectangle rectangle = new Rectangle((width-w)/2, (height-h)/2, w, h);
		
//		缩小矩形，使其对应圆边不覆盖面板边框
		rectangle.grow(-4, -4);
//		赋值扇形对象的形状属性
		double start=90;
		for (pie item : list) {
			item.shape = new Arc2D.Double(rectangle, start, item.degree, Arc2D.PIE);
			start=start+item.degree;
		}
	}
}
