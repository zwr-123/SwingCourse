package Base.Jlist;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MyFrame extends JFrame {
	JButton jButton1 = new JButton("添加");
	JButton jButton2 = new JButton("删除");

	
	MyJList<StudentInfo> list = new MyJList<>();
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

		//为两个按钮绑定事件
		addButtonListeners();
	}

	private void addButtonListeners() {
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
		
	}

	/**
	 * 各种事件
	 */
	


}
