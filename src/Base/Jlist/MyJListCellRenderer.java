package Base.Jlist;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class MyJListCellRenderer  implements ListCellRenderer{
	JPanel root = new JPanel();
	JLabel jlabel=new JLabel();
	pictureView picV=new pictureView();
	private Image iconMale,iconFemale;
	
	public MyJListCellRenderer() {
		super();
		/**
		 * 先加载图片，不能写在getListCellRendererComponent()里，因为会多次执行
		 */
		iconMale=load("/Base/Jlist/男.jpg");
		iconFemale=load("/Base/Jlist/女.png");
		
		root.setLayout(new FlowLayout(FlowLayout.LEFT));
		root.add(picV);
		picV.setPreferredSize(new Dimension(30, 20));
		root.add(jlabel);
		//使这俩组件的颜色与Jlist一致
		root.setBackground(Color.white);
		picV.setBackground(Color.white);
	}

	//加载图片。此处的路径  /表示从类路径下获取，不带斜杠表示从当前类的路径下获取
	public Image load(String resourcePath)  {
		InputStream in = this.getClass().getResourceAsStream(resourcePath);
		Image image=null;
		try {
			image=ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	
	
	//列表项每次被点击时，都会执行这个方法
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		//设置数据项类型
		StudentInfo stu=(StudentInfo)value;
		
		jlabel.setText("姓名："+stu.getName()+" 性别："+stu.getSex());
		
		//jlabel文本居中
		jlabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//jlabel文本风格
		jlabel.setFont(new Font("楷体", Font.BOLD, 20));
		
		//jlabel大小
		jlabel.setPreferredSize(new Dimension(270, 20));
		
		//jlabel文本颜色
		jlabel.setForeground(Color.GRAY);
		
		//透明度true，后面才能设置jlabel的背景颜色
		jlabel.setOpaque(true);
		
		//若被选中则高亮显示
		if(isSelected) {
			//显示list的默认高亮颜色
			jlabel.setBackground(list.getSelectionBackground());
			jlabel.setForeground(list.getSelectionForeground());
		}else {
			jlabel.setBackground(list.getBackground());
			jlabel.setForeground(list.getForeground());
		}
		
		//根据性别显示图片   此处的picV.setimage 写在构造方法里没用
		if(stu.getSex().equals("男")) {
			picV.setimage(iconMale);

		}else {
			picV.setimage(iconFemale);
		}
		
		return root;
	}
}
