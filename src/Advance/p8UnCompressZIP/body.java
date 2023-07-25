package Advance.p8UnCompressZIP;

import javax.swing.JFrame;
/**
 * 查看压缩文件，选择解压缩目录，解压
 * @author ZW
 *
 */
class body {

	public static void main(String[] args) {
		myFrame myFrame = new myFrame("解压缩界面");
		myFrame.setSize(600, 500);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
