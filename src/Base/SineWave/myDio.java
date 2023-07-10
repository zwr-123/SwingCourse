package Base.SineWave;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class myDio extends JDialog{
	JButton OKButton = new JButton("确定");
	JButton CancelButton = new JButton("取消");
	
	JSpinner grainSpin = new JSpinner(new SpinnerNumberModel(1,1,5,1));
	JSpinner radiusSpin = new JSpinner(new SpinnerNumberModel(50,20,80,1));
	JSpinner periodSpin = new JSpinner(new SpinnerNumberModel(100,50,150,1));
	
	private boolean accepted=false;
	
	public myDio(Window owner) {
		super(owner);
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		/*
		 * 直接写代码就好了，为什么还要用if(true)。
		 * 注意看 两个 if{} 里都有jPanel1 ，同名。直接写代码的话，
		 * 要为变量设置多个不同名字，乱
		 */
		if(true) {
			JPanel jPanel1 = new JPanel();
			//4个控件，但设置成5行2列，多出的控件网格为了占位，否则4个控件显示过大
			jPanel1.setLayout(new GridLayout(5, 2, 20, 10));
			root.add(jPanel1,BorderLayout.CENTER);
			
			JLabel jLabel1 = new JLabel("粒度");
			JLabel jLabel2 = new JLabel("半径");
			JLabel jLabel3 = new JLabel("周期");
			
			jPanel1.add(jLabel1);
			jPanel1.add(grainSpin);
			jPanel1.add(jLabel2);
			jPanel1.add(radiusSpin);
			jPanel1.add(jLabel3);
			jPanel1.add(periodSpin);
		}
		
		if(true) {
			JPanel jPanel1 = new JPanel();
			jPanel1.setPreferredSize(new Dimension(0, 150));
			root.add(jPanel1,BorderLayout.SOUTH);
			jPanel1.add(OKButton);
			jPanel1.add(CancelButton);
		}
		
		OKButton.addActionListener(e->{
			accepted=true;
			//关闭对话框
			setVisible(false);
		});
		
		
		CancelButton.addActionListener(e->{
			accepted=false;
			setVisible(false);
		});
		
	}
	
	public boolean exec() {
		this.setTitle("参数设置");
		//true 为模态窗口  即对话框弹出后，不能操作主窗口（阻塞），当对话框操作完成后才放行
		this.setModal(true);
		this.setSize(350,350);
		DioLocation();
		this.setVisible(true);
		return accepted;
	}

	/**
	 * 对话框显示在父窗口中间
	 */
	private void DioLocation() {
		Rectangle OwnerBounds = this.getOwner().getBounds();
		int width2 = this.getWidth();
		int height2 = this.getHeight();
		int x=OwnerBounds.x+(OwnerBounds.width-width2)/2;
		int y=OwnerBounds.y + (OwnerBounds.height-height2)/2;
		this.setBounds(x,y,width2,height2);
	}
}
