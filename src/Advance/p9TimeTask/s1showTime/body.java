package Advance.p9TimeTask.s1showTime;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * 定时执行任务---显示当前时间
 * 本次使用javax.swing.Timer，是线程，别导错包。不能直接更新UI（安全问题）。
 * 类似的有 javax.swing.Timer ，它是轻量级的，用于执行短小的任务。创建多个Timer对象实际只对应1个线程，
 * 
 * 定时有误差，很难精确到10ms.
 * 为什么sleep()不能精确？线程睡醒后，会被放到线程队列中，等待执行。它前面可能还有其他线程
 * 
 * 
 * @author ZW
 *
 */
class body {
	static Timer timer;
	static JLabel jLabel = new JLabel("--:--:--");
	public static void main(String[] args) {
		JFrame frame = new JFrame("当前时间");
		JPanel root = new JPanel(new BorderLayout());
		root.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));  
		frame.setContentPane(root);
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jPanel = new JPanel(new FlowLayout());
		root.add(jPanel,BorderLayout.NORTH);
		JButton button1 = new JButton("开始");
		JButton button2 = new JButton("结束");
		jPanel.add(button1);
		jPanel.add(button2);
		
		
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setFont(new Font("楷体", Font.BOLD, 50));
		root.add(jLabel,BorderLayout.CENTER);
		
		frame.setVisible(true);
		
		
		button1.addActionListener(e->{
			if(timer!=null) {
				return;
			}
			timer=new Timer();
//			50：任务第一次执行时延迟50
//			new body.updateTime()：静态内部类实例化
//			1000：每隔1秒
//			整体功能：每隔1秒循环执行updateTime类中的run()
			timer.schedule(new body.updateTime(), 50, 1000);
		});
		
		
		button2.addActionListener(e->{
			if(timer!=null) {
				timer.cancel();
				timer=null;
			}
			jLabel.setText("--:--:--");
		});
		
	}
	
//	就是Runnable接口实现类
	static class updateTime extends TimerTask{

		@Override
		public void run() {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			String time = simpleDateFormat.format(System.currentTimeMillis());
			SwingUtilities.invokeLater(()->{
				jLabel.setText(time);
			});
		}
		
	}

}
