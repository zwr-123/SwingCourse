package Base.SineWave;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 绘制正弦曲线
 * @author ZW
 *
 */

public class SineWave extends JPanel {
	public int grain=3;//点的密度
	public int radius=50;//高度 振幅
	public int period=100;//x轴，每100像素表示一个周期（2Π）


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		//获取当前面板的长 宽
		int width=this.getWidth();
		int height=this.getHeight();
		
		//绘制一个白色矩形背景，大小=当前面板
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		//绘制x轴
		int center=height/2;
		g.setColor(Color.BLUE);
		g.drawLine(0, center, width, center);
		
		int x1=0; int y1=0;
		for(int i=0;i<width;i+=grain) {
			int x2=i;
			//把x换算成角度（弧度值）
			double angle=2*Math.PI*x2/period;
			int y2=(int)(radius*Math.sin(angle));
			g.drawLine(x1, center+y1, x2, center+y2);
			x1=x2;
			y1=y2;
		}
	}

}
