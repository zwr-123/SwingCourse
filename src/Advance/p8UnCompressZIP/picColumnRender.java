package Advance.p8UnCompressZIP;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 * 自定义列显示（第一列）图片
 * @author ZW
 *
 */
class picColumnRender extends pictureView implements TableCellRenderer{
	Image dirImage;
	Image fileImage;
	public picColumnRender() {
		dirImage=load("/Advance/p8UnCompressZIP/dir.jpg");
		fileImage=load("/Advance/p8UnCompressZIP/file.jpg");
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		boolean f=(boolean)value;
//		文件或目录展示不同的图片
		if(f) {
			this.setimage(dirImage);
		}else {
			this.setimage(fileImage);
		}
//		行被选中时，颜色和table保持一致	
		if(isSelected) {
			this.setBackground(table.getSelectionBackground());
		}else {
			this.setBackground(table.getBackground());
		}
		return this;
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
	

}
