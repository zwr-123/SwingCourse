package Advance.p6Concurrency;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.FontUIResource;
/**
 * 倒计时
 * 事件循环时的线程安全
 * @author ZW
 *
 */
public class body {
	static JButton jButton = new JButton("开始");
	static JLabel jLabel = new JLabel("新年快乐！");
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("倒计时");
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		jFrame.setContentPane(root);
		
		
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setFont(new FontUIResource("楷体", Font.BOLD, 60));
		
		root.add(jButton,BorderLayout.NORTH);
		root.add(jLabel,BorderLayout.CENTER);
		
		jFrame.setSize(700, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
		jButton.addActionListener(e->{
			onclick();
		});
	}

	private static void onclick() {
		jButton.setEnabled(false);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
//				这里的for循环不能放到回调函数中  为什么？
				for(int i=10;i>=0;i--) {
					//final 只能被赋值一次，但这个值可以是动态的
					final String text = String.valueOf(i);
//					回调函数，确保操作共享数据时（界面线程，新线程共享）线程是安全的
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
//					匿名实现类中可以使用外边方法的局部变量text。匿名类的构造器会把外部text的值传给此类的属性，所以二者值相同，但不是同一个对象
							jLabel.setText(text);
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				结束后复原界面
				SwingUtilities.invokeLater(()->{
					jLabel.setText("新年快乐");
					jButton.setEnabled(true);
				});
			}
		}).start();
		
//		不能写在这里，否则直接就运行到了，没效果
//		jButton.setEnabled(true);
	}

}
