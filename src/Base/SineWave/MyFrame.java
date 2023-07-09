package Base.SineWave;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class MyFrame extends JFrame{
	MyMenuItemListener menuItemListener=new MyMenuItemListener();
	SineWave sineWave = new SineWave();
	public MyFrame(String title) {
		super(title);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		this.setContentPane(jPanel);
		
		sineWave.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				//鼠标右键显示菜单
				if(e.getButton()==e.BUTTON3) {
					showMenu(e);
				}
			}
			
		});
		jPanel.add(sineWave,BorderLayout.CENTER);
	}
/**
 * 创建菜单
 * @param e
 */
	protected void showMenu(MouseEvent e) {
		JPopupMenu jPopupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("参数设置");
		menuItem.setActionCommand("setArgs");
		menuItem.addActionListener(menuItemListener);
		jPopupMenu.add(menuItem);
		//根据父组件和点击位置展现固定位置的菜单
		jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	/**
	 * 内部类监听器，绑定到菜单项上
	 * @author ZW
	 *
	 */
	private class MyMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("setArgs")) {
				
				//MyFrame.this指当前对象MyFrame，即myDio的父窗口
				myDio myDio = new myDio(MyFrame.this);
				
				//在JSpinner中展示曲线当前的参数值
				myDio.grainSpin.setValue(sineWave.grain);
				myDio.radiusSpin.setValue(sineWave.radius);
				myDio.periodSpin.setValue(sineWave.period);
				
				//根据返回值，设置曲线参数
				boolean result = myDio.exec();
				if(result){
					sineWave.grain=(int)myDio.grainSpin.getValue();
					sineWave.radius=(int)myDio.radiusSpin.getValue();
					sineWave.period=(int)myDio.periodSpin.getValue();
					
					//立即生效参数（重新绘制曲线）
					sineWave.repaint();
				}
			}
		}
		
	}
}
