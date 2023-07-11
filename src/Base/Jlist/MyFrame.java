package Base.Jlist;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MyFrame extends JFrame {
	JButton jButton1 = new JButton("添加");
	JButton jButton2 = new JButton("删除");

	
	JList<StudentInfo> list = new JList<>();
	//列表项数据模型
	DefaultListModel<StudentInfo> Model = new DefaultListModel<>();

	public MyFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		this.setContentPane(root);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//滚动栏界面
		JScrollPane jScrollPane = new JScrollPane(list);

		JPanel jPanel = new JPanel();
		jPanel.add(jButton1);
		jPanel.add(jButton2);

		root.add(jScrollPane, BorderLayout.CENTER);
		root.add(jPanel, BorderLayout.NORTH);

		//设置列表项风格
		list.setCellRenderer(new MyJListCellRenderer());

		// 设置JList每项数据
		Model.addElement(new StudentInfo(1, "萧炎", "男", "1908820"));
		Model.addElement(new StudentInfo(2, "林动", "男", "1908821"));
		list.setModel(Model);
		
		// 设置JList 多选模式
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		//各种事件
		ListenersControl();
	}

	/**
	 * 各种事件
	 */
	public void ListenersControl() {

		// 删除JList 所有鼠标监听器
		MouseListener[] mouseListeners = list.getMouseListeners();
		MouseMotionListener[] mouseMotionListeners = list.getMouseMotionListeners();
		for (MouseListener item : mouseListeners) {
			list.removeMouseListener(item);
		}
		for (MouseMotionListener item : mouseMotionListeners) {
			list.removeMouseMotionListener(item);
		}

		// 添加按钮 绑定监听器
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Model.addElement(new StudentInfo(2, "月牙儿", "女", "0098"));
			}
		});

		// 删除按钮，绑定监听器。从后往前删。默认是从前往后，但删除一个后，坐标打乱，后面的就失败
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedIndices = list.getSelectedIndices();
				for (int i = selectedIndices.length - 1; i >= 0; i--) {
					Model.remove(selectedIndices[i]);
				}
			}
		});

		// 给JList 添加自定义鼠标监听器
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				//左键点击
				if (e.getButton() == MouseEvent.BUTTON1) {
					int index = list.locationToIndex(e.getPoint());

					// 获取当前选中的列表项的范围
					Rectangle cellBounds = list.getCellBounds(index, index);
					// 若此范围不包括点击位置(空白位置)，则清空所有选中
					if (!cellBounds.contains(e.getPoint())) {
						list.clearSelection();
						return;
					}

					// 单选
//							list.clearSelection();
//							list.setSelectedIndex(index);

					// 多选 且 点击已选项，会取消此项选中
					if (list.isSelectedIndex(index)) {
						list.removeSelectionInterval(index, index);
					} else {
						list.addSelectionInterval(index, index);
					}

				}

				//右键点击
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (LocationToIndex(list, e.getPoint()) >= 0) {
						int index = list.locationToIndex(e.getPoint());
						//清空所选
						list.clearSelection();
						list.setSelectedIndex(index);
						JPopupMenu jPopupMenu = new JPopupMenu();
						jPopupMenu.add(new JMenuItem("待完成的功能"));
						jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}

		});

	}

	// 确定点击位置是否在JList的选项区域
	public static int LocationToIndex(JList list, Point p) {
		int index = list.locationToIndex(p);
		if (index < 0) {
			// 右键点JList空白区域 index 为-1
			return -1;
		}

		Rectangle cellBounds = list.getCellBounds(index, index);
		if (cellBounds.contains(p)) {
			return index;
		}
		return -1;
	}

}
