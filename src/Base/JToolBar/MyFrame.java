package Base.JToolBar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MyFrame extends JFrame
{

	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());

		// 创建工具栏
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false); // 让工具栏固定、不允许浮动
		root.add(toolBar, BorderLayout.PAGE_START);
		
		// 向工具栏上添加按钮
		toolBar.add( toolButton("ic_open.png", "fileOpen","打开"));
		toolBar.add( toolButton("ic_save.png", "fileSave","保存"));
		toolBar.add( toolButton("ic_saveas.png","fileSaveAs","另存为"));
		toolBar.addSeparator();
		toolBar.add( toolButton("ic_help.png", "fileHelp","帮助"));
	}

	// 创建工具栏上的按钮
	protected JButton toolButton(String imageName, String action, String tooltip)
	{
		// 图标
		String imagePath = "/Base/JToolBar/" + imageName;
		URL imageURL = getClass().getResource(imagePath);

		// 创建按钮
		JButton button = new JButton();
		button.setActionCommand(action);
		button.setToolTipText(tooltip);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		
		button.addActionListener( actionListener );
		return button;
	}
	
	// 创建一个事件监听器
	// 注意： actionListener是类的属性
	private ActionListener actionListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();
			System.out.println("执行命令: " + action);
			
//			if(action.equals("fileOpen"))
//			{
//				JOptionPane.showMessageDialog(MyFrame.this, action);
//			}			
		}		
	};
}
