package Base.infoEdit;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 自定义提示对话框
 * @author ZW
 *
 */
public class myDio extends JDialog{
	JLabel message = new JLabel();
	
	private boolean accepted=false;
	
	public myDio(Window owner) {
		super(owner);
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		root.add(message,BorderLayout.CENTER);
	}
	
	/**
	 * 生成一个提示对话框
	 * @param parent  父窗口
	 * @param text   提示信息
	 */
	public static void createMyDio(Window parent ,String text) {
		myDio dio=new myDio(parent);
		dio.setMessageText(text);
		dio.exec();
	}
	
	public void setMessageText(String text) {
		message.setText(text);
		message.setFont(new Font("楷体", Font.BOLD, 18));
		//标签文本居中
		message.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public boolean exec() {
		this.setTitle("友情提示");
		//true 为模态窗口  即对话框弹出后，不能操作主窗口（阻塞），当对话框操作完成后才放行
		this.setModal(true);
		this.setSize(210,110);
		//位置显示
		DioLocation();
		this.setVisible(true);
		return accepted;
	}

	/**
	 * 对话框显示在父窗口中间
	 */
	private void DioLocation() {
		//获取父窗口的位置
		Rectangle OwnerBounds = this.getOwner().getBounds();
		int width2 = this.getWidth();
		int height2 = this.getHeight();
		int x=OwnerBounds.x+(OwnerBounds.width-width2)/2;
		int y=OwnerBounds.y + (OwnerBounds.height-height2)/2;
		this.setBounds(x,y,width2,height2);
	}
	
	
}
