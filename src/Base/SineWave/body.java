package Base.SineWave;

import java.awt.Dimension;

import javax.swing.JFrame;


/**
 * 绘制正弦曲线，并通过右键菜单修改其参数!
 * @author ZW
 *
 */
public class body {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame myFrame = new MyFrame("正弦曲线");
		myFrame.setSize(new Dimension(700, 500));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
