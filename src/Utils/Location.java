package Utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * 工具类
 * @author ZW
 *
 */
public class Location {
	
	
	/**
	 * 设置组件在屏幕中间显示
	 * @param win
	 */
	public static void  centerInScreen(Window win) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x=(screenSize.width-win.getWidth())/2;
		int y=(screenSize.height-win.getHeight())/2;
		win.setLocation(x, y);
	}
	
	
	/**
	 * 设置组件显示在其父组件的中间
	 * @param win
	 * @param owner
	 */
	public static void  centerInOwner(Window win,Window owner) {
		//获取父组件的位置
		Rectangle ownerBounds = owner.getBounds();
		
		//计算并设置win组件的位置
		int width=  win.getWidth();
		int height = win.getHeight();
		int x=ownerBounds.x + (ownerBounds.width-width)/2;
		int y=ownerBounds.y+ (ownerBounds.height-height)/2;
		win.setBounds(x, y, width, height);
	}

}
