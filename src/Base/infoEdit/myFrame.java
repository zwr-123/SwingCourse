package Base.infoEdit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
/**
 * 自定义窗口
 * @author ZW
 *
 */
class myFrame extends JFrame {
	JButton jButton1 = new JButton("打开");
	JButton jButton2 = new JButton("保存");

	JTextField jTextField1 = new JTextField(20);
	JTextField jTextField2 = new JTextField(20);
	JTextField jTextField4 = new JTextField(20);
	JComboBox<String> jComboBox = new JComboBox<>();
	//当前目录文件对象
	File lastDir = new File(".");

	public myFrame(String title) {
		super(title);
		setUI();

		jButton1.addActionListener(e -> {
			String filePath = openMenu();
			//当关闭/取消 打开窗口时，路径为null
			if (filePath != null) {
				openInfo(filePath);
			}

			// TODO Auto-generated catch block
		});

		jButton2.addActionListener(e -> {
			//提示信息，只写了学号的提示
			if (jTextField1.getText().equals("")) {
				myDio.createMyDio(this, "学号不能为空!");
				return;
			}

			String filePath = saveMenu();
			if (filePath != null) {
				saveInfo(filePath);
			}

		});

	}

	private String openMenu() {
		String path = null;
		//文件选择器
		JFileChooser jFileChooser = new JFileChooser();
		//设置过滤条件，即展示的文件类型
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("记事本", "txt");
		//添加过滤条件到选择器
		jFileChooser.setFileFilter(fileNameExtensionFilter);
		//选择窗口的默认显示目录
		jFileChooser.setCurrentDirectory(lastDir);
		//通过选择器打开选择窗口，this为当前窗口对象（父对象）
		int result = jFileChooser.showOpenDialog(this);
		//JFileChooser.APPROVE_OPTION 为通过，即点击了ok/保存/打开
		if (result == JFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getAbsolutePath();
			lastDir = jFileChooser.getSelectedFile().getParentFile();
		}
		return path;
	}

	private String saveMenu() {
		String path = null;
		JFileChooser jFileChooser = new JFileChooser();
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("记事本", "txt");
		jFileChooser.setFileFilter(fileNameExtensionFilter);
		jFileChooser.setCurrentDirectory(lastDir);
		int result = jFileChooser.showSaveDialog(this);
		if (result == jFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();
			path = file.getAbsolutePath();
			lastDir = file.getParentFile();
		}
		return path;
	}

	/**
	 * 读取txt中的json字符串，再用gson转换成java对象，最后回显到界面
	 * @param path
	 */
	private void openInfo(String path) {
		Gson gson = new Gson();
		String gsonInfo = null;
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(new File(path)));
			gsonInfo = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		StudentInfo stuInfo = gson.fromJson(gsonInfo, StudentInfo.class);
		// 回显 学号 姓名 性别 手机
		jTextField1.setText(Integer.toString(stuInfo.getId()));
		jTextField2.setText(stuInfo.getName());
		jComboBox.setSelectedItem(stuInfo.getSex());
		jTextField4.setText(stuInfo.getPhoneNum());

	}

	private void saveInfo(String path) {
		Gson gson = new Gson();
		StudentInfo studentInfo = new StudentInfo(Integer.parseInt(jTextField1.getText()), jTextField2.getText(),
				jComboBox.getSelectedItem().toString(), jTextField4.getText());
		String jsonInfo = gson.toJson(studentInfo);
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(new File(path)));
			out.writeUTF(jsonInfo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setUI() {
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout(0, 70));
		this.setContentPane(root);

		JPanel jPanel1 = new JPanel();
		JPanel jPanel2 = new JPanel();
		//网格布局器，实际行列（网格）大于 实际展示控件数目
		jPanel1.setLayout(new GridLayout(2, 4, 20, 10));
		jPanel1.setPreferredSize(new Dimension(0, 100));

		jPanel2.setLayout(new GridLayout(4, 4, 20, 20));
		root.add(jPanel1, BorderLayout.NORTH);
		root.add(jPanel2, BorderLayout.CENTER);

		JLabel jLabel1 = new JLabel("学号");
		JLabel jLabel2 = new JLabel("姓名");
		JLabel jLabel3 = new JLabel("性别");
		JLabel jLabel4 = new JLabel("手机");

		jComboBox.addItem("男");
		jComboBox.addItem("女");
		jComboBox.setSelectedIndex(0);

		/**
		 * 我用的是网格布局，所以
		 * 下面所有的 jPanel1.add(new JLabel())都是用来占位的
		 */
		jPanel1.add(new JLabel());
		jPanel1.add(new JLabel());
		jPanel1.add(new JLabel());
		jPanel1.add(new JLabel());
		jPanel1.add(new JLabel());
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		jPanel1.add(new JLabel());

		jPanel2.add(new JLabel());
		jPanel2.add(jLabel1);
		jPanel2.add(jTextField1);
		jPanel2.add(new JLabel());
		jPanel2.add(new JLabel());
		jPanel2.add(jLabel2);
		jPanel2.add(jTextField2);
		jPanel2.add(new JLabel());
		jPanel2.add(new JLabel());
		jPanel2.add(jLabel3);
		jPanel2.add(jComboBox);
		jPanel2.add(new JLabel());
		jPanel2.add(new JLabel());
		jPanel2.add(jLabel4);
		jPanel2.add(jTextField4);
		jPanel2.add(new JLabel());
	}

}
