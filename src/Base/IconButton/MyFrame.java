package Base.IconButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Utils.AfPanel;
import Utils.MyBorder;
import Utils.MyRowLayout;



public class MyFrame extends JFrame
{

	public MyFrame(String title)
	{
		super(title);

		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		// 工具栏 
		// AfPanel: 参考7.5的讲解
		AfPanel toolbar = new AfPanel();
		toolbar.setLayout(new MyRowLayout(5));
		toolbar.padding(5).preferredHeight(36);
		root.add(toolbar, BorderLayout.PAGE_START);
		
		// 添加按钮
		JButton openButton = createButton("/Base/IconBox/ic_open.png");
		JButton saveButton = createButton("/Base/IconBox/ic_save.png");
		JButton printButton = createButton("/Base/IconBox/ic_print.png");
		toolbar.add(openButton);
		toolbar.add(saveButton);
		toolbar.add(printButton);
		
		JTextArea content = new JTextArea();
		root.add(content, BorderLayout.CENTER);
		content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}
	
	public JButton createButton(String iconPath)
	{
		// 加载资源文件
		URL url = getClass().getResource(iconPath);	
		Icon icon = new ImageIcon(url);
		
		// 设置图标
		JButton button = new JButton();
		button.setIcon(icon);
		
		// 按钮的透明属性  因为按钮要使用图片为内容，所以此处设为false，让其原本内容透明
		button.setContentAreaFilled(false);  
		//鼠标移动到按钮上，按钮上的文字周围会出现隐约的方框，表示聚焦。此处false关闭聚焦
		button.setFocusPainted(false); 
		
		// 设置默认border为灰色
		button.setBorder(BorderFactory.createLineBorder(Color.gray));
		MyBorder.addPadding(button, 4);

		// 创建鼠标事件监听：鼠标移入时，更换border为蓝色
		button.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseEntered(MouseEvent e)
			{
				button.setBorder(BorderFactory.createLineBorder(Color.blue));
				MyBorder.addPadding(button, 4);
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{		
				button.setBorder(BorderFactory.createLineBorder(Color.gray));
				MyBorder.addPadding(button, 4);
			}
		});
		
		return button;
	}
	
	
}
