package Base.Jlist;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 * 自定义JList：替换默认的监听器
 * @author ZW
 *
 * @param <E>
 */

public class MyJList<E> extends JList<E>{
	
	public MyJList() {
		super();
		ListenersControl();
	}
	
	public void ListenersControl() {

		// 删除JList 所有鼠标监听器
		MouseListener[] mouseListeners = this.getMouseListeners();
		MouseMotionListener[] mouseMotionListeners = this.getMouseMotionListeners();
		for (MouseListener item : mouseListeners) {
			this.removeMouseListener(item);
		}
		for (MouseMotionListener item : mouseMotionListeners) {
			this.removeMouseMotionListener(item);
		}

		

		// 给JList 添加自定义鼠标监听器--点击后选中此行
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				//左键点击
				if (e.getButton() == MouseEvent.BUTTON1) {
					int index = MyJList.this.locationToIndex(e.getPoint());

					// 获取当前选中的列表项的范围
					Rectangle cellBounds = MyJList.this.getCellBounds(index, index);
					// 若此范围不包括点击位置(空白位置)，则清空所有选中
					if (!cellBounds.contains(e.getPoint())) {
						MyJList.this.clearSelection();
						return;
					}

					// 单选
//							this.clearSelection();
//							this.setSelectedIndex(index);

					// 多选 且 点击已选项，会取消此项选中
					if (MyJList.this.isSelectedIndex(index)) {
						MyJList.this.removeSelectionInterval(index, index);
					} else {
						MyJList.this.addSelectionInterval(index, index);
					}

				}

				//右键点击
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (LocationToIndex(MyJList.this, e.getPoint()) >= 0) {
						int index = MyJList.this.locationToIndex(e.getPoint());
						//清空所选
						MyJList.this.clearSelection();
						MyJList.this.setSelectedIndex(index);
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
