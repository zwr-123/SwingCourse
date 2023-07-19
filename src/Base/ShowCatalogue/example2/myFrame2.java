package Base.ShowCatalogue.example2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Base.Jlist.MyJList;


public class myFrame2 extends JFrame{
	
	DefaultListModel<FileItem> model=new DefaultListModel<>();
	MyJList2<FileItem> mylist=new MyJList2<>();
	JTextField jTextField;
	File currentFile = new File(".");
	public myFrame2(String title)
	{
		super(title);
		
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout(3,10));
		// int top, int left, int bottom, int right
		root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.setContentPane(root);
		
		
		root.add(initTop(),BorderLayout.NORTH);
		root.add(initCenter(),BorderLayout.CENTER);
		
		
		loadDir(currentFile);
	}
	
	private JComponent initTop() {
		JPanel jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(0, 27));
		jPanel.setLayout(new BorderLayout(5,5));
		
		jTextField = new JTextField(40);
		JButton jButton = new JButton("打开");
		jButton.setPreferredSize(new Dimension(60, HEIGHT));
		jButton.addActionListener(e->{
			openDir();
		});
		
		jPanel.add(jTextField, BorderLayout.CENTER);
		jPanel.add(jButton, BorderLayout.EAST);
		return jPanel;
		
	}
	

	private JComponent initCenter() {
		
//		设置JList为水平显示控件（类似流布局），需要在 fileListRenderer  中设置 返回的 Jpanel 的大小，用于计算然后排列
//		mylist.setVisibleRowCount(-1);
//		mylist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
		/*
		 * 如果list中的model元素是String，则显示这个字符串
		 * 如果list中的model元素是对象，且没有设置渲染器，
		 * 则默认显示这个对象类中toString方法的返回的字符串。若设置了渲染器，则显示渲染器里的设置
		 */
		mylist.setCellRenderer(new fileListRenderer());
		mylist.setModel(model);
		mylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mylist.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2) {
					onDoubleLeft(e);
				}
			}

		});
		
		JScrollPane jScrollPane = new JScrollPane(mylist);
		return jScrollPane;
	}	
	
	
	private void loadDir(File file)  {
		//加载前先清空
		model.removeAllElements();
		
		
		String canonicalPath;
		try {
			/**
			 * 将当前路径（相对），转为绝对路径
			 * 因为currentFile根据相对路径声明的，会使下面的 file.getParentFile() 返回值总为null 
			 * 会导致 程序刚运行时，不显示  (上层目录) 一行
			 */
			canonicalPath = file.getCanonicalPath();
			this.currentFile = new File(canonicalPath);
			
			//回显所选目录的路径到jTextField
			jTextField.setText(canonicalPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		File[] listFiles = currentFile.listFiles();
		if(listFiles==null) {
			return;
		}
		
		//添加 上层目录 行
		if(true) {
			File parentFile = currentFile.getParentFile();
			//可能没有父目录
			if(parentFile!=null) {
				FileItem fileItem0 = new FileItem(parentFile);
				fileItem0.name="(上层目录)";
				fileItem0.time="";
				model.addElement(fileItem0);
			}
		}
		
		for (File file2 : listFiles) {
			FileItem fileItem = new FileItem(file2);
			System.out.println(fileItem.toString());
			model.addElement(fileItem);
		}
	}
	
	private void openDir() {
		// TODO Auto-generated method stub
		JFileChooser Chooser = new JFileChooser();
		//只显示目录
		Chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ret = Chooser.showOpenDialog(this);
		if(ret==JFileChooser.APPROVE_OPTION) {
			File file=Chooser.getSelectedFile();
			if(file!=null) {
				loadDir(file);
			}
		}
	}

	/**
	 * 双击打开目录
	 * @param e
	 */
	public void onDoubleLeft(MouseEvent e) {
		Point point = e.getPoint();
		//返回点中列的索引，若点中空白处，返回最近列的索引。
		int index = mylist.locationToIndex(point);
		Rectangle cellBounds = mylist.getCellBounds(index, index);
		//若啥也没点中就返回
		if(!cellBounds.contains(point)) {
			return;
		}
		//根据索引找到element，然后显示
		FileItem element = model.getElementAt(index);
		//双击目录才显示
		if(element.isDir) {
			loadDir(element.file);
		}
	}

}
