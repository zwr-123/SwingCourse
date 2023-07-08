package Base.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class myFrame extends JFrame {

	myItemListener jMenuItemListener = new myItemListener();

	public myFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		// 使用边界布局
		root.setLayout(new BorderLayout());

		myPanel myPanel = new myPanel();
		// 添加自定义JPanel到root
		root.add(myPanel, BorderLayout.CENTER);

		// 给myPanel添加鼠标监听器
		myPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// 如果点击鼠标右键，出现菜单
				if (e.getButton() == e.BUTTON3) {
					// 创建菜单
					JPopupMenu jPopupMenu = new JPopupMenu();

					// 创建菜单项
					JMenuItem jMenuItem1 = new JMenuItem("另存为");
					// 为菜单项绑定一个命令标识，在监听器中使用
					jMenuItem1.setActionCommand("save as");
					// 点击菜单项后有相应操作，即绑定监听器
					jMenuItem1.addActionListener(jMenuItemListener);

					JMenuItem jMenuItem2 = new JMenuItem("退出");
					jMenuItem2.setActionCommand("exit");
					jMenuItem2.addActionListener(jMenuItemListener);

					// 把菜单项放进菜单中
					jPopupMenu.add(jMenuItem1);
					jPopupMenu.add(jMenuItem2);

					// 根据组件和点击位置（相对位置）生成菜单
					jPopupMenu.show(e.getComponent(), e.getX(), e.getY());

				}
			}

		});
	}

	// 内部类,自定义JPanel
	private class myPanel extends JPanel {
		public myPanel() {
			this.setOpaque(true);
			this.setBackground(Color.gray);
		}
	}

	// 内部类监听器
	private class myItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("save as")) {
				System.out.println("另存为");
			} else if (actionCommand.equals("exit")) {
				System.out.println("退出");
			}

		}
	}
}
