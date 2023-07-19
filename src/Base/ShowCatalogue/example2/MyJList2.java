package Base.ShowCatalogue.example2;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JList;

import Base.Jlist.MyJList;

public class MyJList2<E> extends JList<E>{
	public MyJList2() {
		super();
		ListenersControl();
	}

	private void ListenersControl() {
		// 删除JList 所有鼠标监听器
				MouseListener[] mouseListeners = this.getMouseListeners();
				MouseMotionListener[] mouseMotionListeners = this.getMouseMotionListeners();
				for (MouseListener item : mouseListeners) {
					this.removeMouseListener(item);
				}
				for (MouseMotionListener item : mouseMotionListeners) {
					this.removeMouseMotionListener(item);
				}
				
				
				// 给JList 添加自定义鼠标监听器--点击后单选此行
				this.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						int index = MyJList2.this.locationToIndex(e.getPoint());

						// 获取当前选中的列表项的范围
						Rectangle cellBounds = MyJList2.this.getCellBounds(index, index);
						// 若此范围不包括点击位置(空白位置)，则清空所有选中
						if (!cellBounds.contains(e.getPoint())) {
							MyJList2.this.clearSelection();
							return;
						}
						

						// 单选
						MyJList2.this.clearSelection();
						MyJList2.this.setSelectedIndex(index);

					}
				});

	}

}
