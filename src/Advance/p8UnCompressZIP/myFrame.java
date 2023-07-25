package Advance.p8UnCompressZIP;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Base.Jtable.Student;
import Utils.Location;

/**
 * 自定义窗口
 * 
 * @author ZW
 *
 */
class myFrame extends JFrame {
	JPanel root;
	JTable table = null;
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	myDio progress;
	File zipFile;
	File destDir;

	public myFrame(String title) {
		super(title);
		root = new JPanel(new BorderLayout());
		this.setContentPane(root);

		// 初始化工具栏
		initToolBar();

		// 表格初始化
		initTable();
	}

	private void initToolBar() {

		JButton button1 = new JButton("打开文件");
		JButton button2 = new JButton("解压缩");

		button1.addActionListener(e -> {
			openDir();
		});

		button2.addActionListener(e -> {
			openDecompression();
		});

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(button1);
		toolBar.add(button2);
		root.add(toolBar, BorderLayout.PAGE_START);

	}

	private void initTable() {

		table = new JTable(defaultTableModel) {
//		不允许编辑表格
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

		};
//		表格需要放到scrollPane中
		JScrollPane scrollPane = new JScrollPane(table);
		root.add(scrollPane, BorderLayout.CENTER);

		// 占满
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true); // 整行选择
		table.setRowHeight(30);

		// 列设置：添加5列
		defaultTableModel.addColumn("");
		defaultTableModel.addColumn("名称");
		defaultTableModel.addColumn("大小");
		defaultTableModel.addColumn("最后修改日期");

//		给第一列使用渲染器，达到自定义效果
		table.getColumnModel().getColumn(0).setCellRenderer(new picColumnRender());
	}

	private void openDir() {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("zip压缩文件", "zip");
		jFileChooser.setFileFilter(fileNameExtensionFilter);
		jFileChooser.setDialogTitle("打开文件");
		jFileChooser.setCurrentDirectory(new File("."));
		int ret = jFileChooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			zipFile = jFileChooser.getSelectedFile();
			new shortTaskImpl(zipFile, defaultTableModel).exec();
		}

	}

	private void openDecompression() {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser.setDialogTitle("解压文件");
		int ret = jFileChooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			destDir = jFileChooser.getSelectedFile();
			new LongTaskImpl(zipFile, destDir, myFrame.this).exec();
			progress = new myDio(this);
			progress.exec();
		}

	}

//	内部类，自定义进度对话框
	class myDio extends JDialog {
		Window owner;
		JProgressBar jProgressBar = new JProgressBar(0, 100);

		public myDio(Window owner) {
			super(owner);
			this.owner = owner;
			JPanel root = new JPanel();
			this.setContentPane(root);
			root.setLayout(new BorderLayout());
			root.add(jProgressBar, BorderLayout.CENTER);
		}

		public void exec() {
//			去掉标题栏
//			this.setUndecorated(true);
			this.setTitle("任务进度条");
			// true 为模态窗口 即对话框弹出后，不能操作主窗口（阻塞），当对话框操作完成后才放行
			this.setModal(true);
			this.setSize(210, 110);
			// 位置显示
			Location.centerInOwner(this, owner);
			this.setVisible(true);
		}

//		设置进度条value
		public void setProgress(int value) {
			jProgressBar.setValue(value);
		}

	}

}
