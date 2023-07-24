package Advance.p7ExecTask;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Utils.Location;

class myFrame extends JFrame{
	myDio dio;
	JButton button = new JButton("执行任务");
	public myFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		this.setContentPane(root);
		
		button.setSize(0, 60);
		root.add(button,BorderLayout.NORTH);
		
		button.addActionListener(e->{
//			先在新线程中执行任务，再弹出对话框。否则，对话框是阻塞的，会导致任务不执行
			button.setEnabled(false);
			new myTask().exec();
//			每次点击都要重新赋值，因为最后刷新窗口时，执行了dio=null;
			dio=new myDio(myFrame.this);
			dio.exec();
		});
	}
	
	private class myDio extends JDialog{
		Window owner;
		JProgressBar jProgressBar = new JProgressBar(0, 100);
		public myDio(Window owner) {
			super(owner);
			this.owner=owner;
			JPanel root = new JPanel();
			this.setContentPane(root);
			root.setLayout(new BorderLayout());
			root.add(jProgressBar,BorderLayout.CENTER);
		}
		
		public void exec() {
//			去掉标题栏
//			this.setUndecorated(true);
			this.setTitle("任务进度条");
			//true 为模态窗口  即对话框弹出后，不能操作主窗口（阻塞），当对话框操作完成后才放行
			this.setModal(true);
			this.setSize(210,110);
			//位置显示 
			Location.centerInOwner(this,owner);
			this.setVisible(true);
		}
		
//		设置进度条value
		public void setProgress(int value) {
			jProgressBar.setValue(value);
		}
		
	}

//继承类
	private class myTask extends shortTask{
//		执行任务
		@Override
		protected void doTask() {
			// TODO Auto-generated method stub
			String sz[]=new String[] {"1","2","3","4","5","6","7","8","9","10"};
			long lastTime = System.currentTimeMillis();
			for(int i=0;i<sz.length;i++) {
				System.out.println("当前数组元素是："+sz[i]);
				long now=System.currentTimeMillis();
				if(now-lastTime>500) {
					lastTime=now;
//					int value=new BigDecimal(i+1)
//					.divide(new BigDecimal(sz.length))
//					.multiply(new BigDecimal(100)).intValue();
					
//					先前用下面这个式子，value总是为0，因为先除，结果有小数，四舍五入=0，再乘以100还是0
//					可以用上面的精确计算，不过太麻烦
//					int value=(bd+1)/(sz.length)*100;
					
//					这样也可以，比较简单，先乘以，这样没有小数
					int value=100*(i+1)/sz.length;
					SwingUtilities.invokeLater(()->{
						dio.setProgress(value);
					});
				}
				try {
//					必须要睡会，不能让循环一直占用cpu,针对实际中用的是while(true)
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		
//		界面刷新
		@Override
		protected void refreshUI() {
			// TODO Auto-generated method stub
			dio.setVisible(false);
			SwingUtilities.invokeLater(()->{
				if(dio!=null) {
					dio.setVisible(false);
					dio=null;
				}
				button.setEnabled(true);
			});
		}
		
	}
}
