package Base.ShowCatalogue.example2;

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

import Base.Jlist.pictureView;

/**
 * 这个类只是个渲染器，绘制出控件的样子，但不添加任何或返回任何实质的有功能的控件
 * 譬如：可以再返回一个button，但button没有点击功能
 * @author ZW
 *
 */
class fileListRenderer  implements ListCellRenderer{
	JPanel root = new JPanel();
	JLabel name=new JLabel();
	JLabel lastEditTime=new JLabel();
	pictureView picV=new pictureView();
	pictureView picV2=new pictureView();
	private Image fileImage,dirImage;
	
	public fileListRenderer() {
		super();
		/**
		 * 先加载图片，不能写在getListCellRendererComponent()里，因为会多次执行，性能浪费
		 */
		fileImage=load("/Base/ShowCatalogue/example2/file.jpg");
		dirImage=load("/Base/ShowCatalogue/example2/dir.jpg");
		
		root.setLayout(new FlowLayout(FlowLayout.LEFT));
		root.setBackground(Color.white);
		//当JList 设置为 水平显示控件时（类似流布局），需要开启这项
//		root.setPreferredSize(new Dimension(100, 100));
		root.add(picV);
		root.add(name);
		root.add(lastEditTime);
		
		picV.setBackground(Color.white);
		picV.setPreferredSize(new Dimension(30, 20));
	
		
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
		FileItem item=(FileItem)value;
		
		System.out.println("当前索引："+ index);
		
		//显示名字
		name.setText(item.toString());
		//显示时间，无需放到下面的判断中。因为此类对象在构造时已经做了处理
		lastEditTime.setText(item.time);
		
		//根据文件或目录 显示图标
		if(item.isDir) {
			picV.setimage(dirImage);
		}else {
			picV.setimage(fileImage);
		}
		
		
		
		//jlabel文本居中
		name.setHorizontalAlignment(SwingConstants.CENTER);
		lastEditTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		//jlabel文本风格
		name.setFont(new Font("楷体", Font.BOLD, 20));
		lastEditTime.setFont(new Font("楷体", Font.BOLD, 20));
		
		//jlabel大小
		name.setPreferredSize(new Dimension(270, 20));
		lastEditTime.setPreferredSize(new Dimension(270, 20));
		
		//jlabel文本颜色
		name.setForeground(Color.GRAY);
		lastEditTime.setForeground(Color.GRAY);
		
		//透明度true，后面才能设置jlabel的背景颜色
		name.setOpaque(true);
		lastEditTime.setOpaque(true);
		
		//若被选中则高亮显示
		if(isSelected) {
			//显示list的默认高亮颜色
			name.setBackground(list.getSelectionBackground());
			name.setForeground(list.getSelectionForeground());
			lastEditTime.setBackground(list.getSelectionBackground());
			lastEditTime.setForeground(list.getSelectionForeground());
		}else {
			name.setBackground(list.getBackground());
			name.setForeground(list.getForeground());
			lastEditTime.setBackground(list.getBackground());
			lastEditTime.setForeground(list.getForeground());
		}
		return root;
	}
}
