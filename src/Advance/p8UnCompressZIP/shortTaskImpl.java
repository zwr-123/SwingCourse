package Advance.p8UnCompressZIP;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.table.DefaultTableModel;
/**
 * 解压任务
 * @author ZW
 *
 */
class shortTaskImpl extends shortTask {
	private File file;
	private DefaultTableModel defaultTableModel;
	List<Vector> list = new ArrayList<>();

	shortTaskImpl(File file, DefaultTableModel defaultTableModel) {
		this.file = file;
		this.defaultTableModel = defaultTableModel;
	}

	@Override
	protected void doTask() {
		// TODO Auto-generated method stub
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file, Charset.forName("GBK"));

			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry nextElement = entries.nextElement();

				loadfile(nextElement);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				zipFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

//	这个方法命名为done更好。此抽象类是对SwingWorker的模仿
	@Override
	protected void refreshUI() {
		// TODO Auto-generated method stub
//		加载前先清空
		defaultTableModel.setRowCount(0);
		for (Vector vector : list) {
			defaultTableModel.addRow(vector);
		}
	}

	private void loadfile(ZipEntry item) {

		Vector<Object> vector = new Vector<>();
		vector.add(item.isDirectory());
		vector.add(item.getName());
		vector.add(item.getSize());
		if (!item.isDirectory()) {
			vector.add(item.getLastModifiedTime());
		}

		list.add(vector);
	}
}
