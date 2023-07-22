package Advance.p5PineChart;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.util.Random;
/**
 * 扇形对象  
 * swing的绘制就是，构造出绘制图形对象，再绘制。所以图形 属性的定义很重要。
 * 譬如：我怎么知道要 定义 Arc2D类型的成员变量。因为本身就是扇形图，最后一定是
 * fill(),里面传入Shape对象。
 * @author ZW
 *
 */
public class pie {
	double amount;
	double degree;
	Object tag;
	Color color;
	Arc2D  shape;
	public pie() {
		super();
	}
	public pie(double amount, Object tag, Color color) {
		super();
		if(amount<=0) {
			System.out.println("count不能小于0！");
			return;
		}
		this.amount = amount;
		this.tag = tag;
		if(color==null) {
			int rgb = new Random().nextInt(0xFFFFFF);
			this.color=new Color(rgb);
		}else {
			this.color = color;
		}
	}
}
