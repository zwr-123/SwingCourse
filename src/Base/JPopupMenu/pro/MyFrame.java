package Base.JPopupMenu.pro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicMenuItemUI;

public class MyFrame extends JFrame
{
	JPopupMenu popup = new JPopupMenu();
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());

		// 右键菜单 
		popup.add(createMenuItem("打开", "fileOpen", "ic_open.png"));
		popup.add(createMenuItem("保存", "fileSave", "ic_save.png"));
		popup.add(createMenuItem("另存为", "fileSaveAs", null ));

		// 定制右键菜单的边框
		Border bd1 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border bd2 = BorderFactory.createEmptyBorder(1,1,1,1);
		popup.setBorder(BorderFactory.createCompoundBorder(bd1, bd2));
		
		// 添加右键事件响应，点击右键时，弹出菜单
		root.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton()==MouseEvent.BUTTON3) //
				{
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}			
		});
	}

	// text : 菜单项文字 
	// action: 菜单项关联的命令
	// iconName: 图标文件名
	private JMenuItem createMenuItem(String text, String action, String iconName)
	{		
		JMenuItem item = new JMenuItem(text);
		item.setActionCommand(action);
		item.addActionListener(actionListener );
		if(iconName != null)
		{
			String imagePath = "/Base/JPopupMenu/pro/" + iconName;
			URL imageURL = getClass().getResource(imagePath);
			item.setIcon(new ImageIcon(imageURL));
		}
		
		// 菜单项外观设置
		item.setUI(new MyMenuItemUI());
		item.setFont(new Font("宋体",0,12));
		item.setBorder(BorderFactory.createEmptyBorder(4,2,4,6));;
		return item;
	}

	// 创建一个菜单项的 UI类，设定选中时的前景色和背景色
	private static class MyMenuItemUI extends BasicMenuItemUI
	{
		public MyMenuItemUI()
		{
			this.selectionBackground = new Color(0x91C9F7);
			this.selectionForeground = new Color(0x666666);
		}
	}
	
	// 创建一个事件监听器
	// 注意： actionListener是类的属性
	private ActionListener actionListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();
			System.out.println("执行命令: " + action);
			
			if(action.equals("fileOpen"))
			{
				JOptionPane.showMessageDialog(MyFrame.this, action);
			}			
		}		
	};

}
