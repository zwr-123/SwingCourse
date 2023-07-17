package Base.ShowCatalogue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Utils.AfPanel;
import test.AfRowLayout;


public class MyFrame extends JFrame
{
	JList<ListItem> fileList = new JList<>();
	DefaultListModel<ListItem> listModel = new DefaultListModel<>();
	
	JTextField dirField = new JTextField(20);
	
	public MyFrame(String title)
	{
		super(title);

		// Content Pane
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());

		// 初始化列表控件
		JComponent listPane = initListView();
		root.add(listPane, BorderLayout.CENTER);
		
		//
		AfPanel locationBar = new AfPanel();
		root.add(locationBar, BorderLayout.PAGE_START);
		locationBar.setLayout(new AfRowLayout(10));
		locationBar.padding(5).preferredHeight(36);
		locationBar.add(dirField, "1w");
		
		JButton browseButton = new JButton("浏览");
		locationBar.add(browseButton, "auto");
		browseButton.addActionListener( (e)->{
			selectFolderDialog();
		});
		
		// 初始化显示 C:盘目录
		loadDir(new File("C:/"));
	}

	private JComponent initListView()
	{
		fileList.setModel(listModel);
		fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fileList.setVisibleRowCount(-1); // 不可缺少
		fileList.setLayoutOrientation(JList.VERTICAL);
		
		// 右键支持
		fileList.addMouseListener(new FileListMouseListener());
		
		JScrollPane listScrollPane = new JScrollPane(fileList);
		return listScrollPane;
	}
	
	private void selectFolderDialog()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("选择文件夹");
		int ret = chooser.showOpenDialog(this);
		if(ret == JFileChooser.APPROVE_OPTION)
		{
			File dir = chooser.getSelectedFile();
			loadDir(dir);
		}
	}
	
	// 显示目录 下的文件
	private void loadDir (File dir)
	{
		System.out.println("loadDir ...");
		// 显示当前路径
		dirField.setText( dir.getAbsolutePath());
		listModel.clear(); // 清空原有的item
		
		// 添加一个“返回上级目录”
		File parentDir = dir.getParentFile();
		if(parentDir != null) // 到达顶层目录之后，getParentFile()返回null
		{			
			ListItem item = new ListItem(parentDir);
			item.name = "返回上级目录..";
			item.isDir = true;
			listModel.addElement(item);
		}
		
		// 列出所有的子文件夹或文件
		// 当目录为空时，返回值files为 File[0],即长度为0的数组
		// 当目录不可访问时，返回值files为 null 
		File[] files = dir.listFiles();
		if(files != null) // 注意看视频演示：什么时候会返回null
		{
			for(File f :files)
			{
				ListItem item = new ListItem(f);
				listModel.addElement(item);
			}
		}		
	}
	
	// 显示右键菜单
	private void showContextMenu(MouseEvent e)
	{
		//TODO: 自行完成右键的操作 ..
	}
	
	// 双击目录则打开，双击文件则无反应
	private void fileDoubleClicked(MouseEvent e)
	{
		int index = fileList.locationToIndex(e.getPoint());
		if(index <0) return;
		
		//System.out.println("fileDoubleClicked ...");
		ListItem item = fileList.getModel().getElementAt(index);
		if(item.isDir)
		{
			// 加载这个目录
			loadDir (item.file);
		}
	}
	///////////////////////////////
	
	// 自定义的列表项数据
	private static class ListItem
	{
		public String name;
		public File file;
		public boolean isDir;
		
		public ListItem(File f)
		{
			this.file = f;
			this.name = f.getName();
			this.isDir = f.isDirectory();
		}
		
		// toString() 就是列表项默认的显示文字 
		@Override
		public String toString()
		{
			if(isDir)
				return "+ " + name;
			else
				return "  " + name;			
		}		
	}
	
	// File List 列表控件的鼠标事件支持
	private class FileListMouseListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				showContextMenu( e);
			}
			if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount()==2)
			{
				//System.out.println("双击 ...");
				fileDoubleClicked(e);
			}
		}			
	}
	
}
