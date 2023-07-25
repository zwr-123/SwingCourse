package Advance.p8UnCompressZIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.SwingUtilities;
/**
 * 解压缩任务
 * @author ZW
 *
 */
public class LongTaskImpl extends shortTask {
	File zip;
	File dest;
	myFrame frame;

	public LongTaskImpl(File zip, File dest, myFrame frame) {
		this.zip = zip;
		this.dest = dest;
		this.frame = frame;
	}

	@Override
	protected void doTask() {
		long fileCount = 0;
		long fileSize = 0;
		long fileTotalSize = zip.length();
		long lastTime = System.currentTimeMillis();
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zip, Charset.forName("GBK"));
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry nextElement = entries.nextElement();
//				如果是目录文件就不用管 为什么不用管？
//				因为entries.hasMoreElements() 得到的就是压缩文件下的每个文件（目录+文件），我们只需对文件解压，对应的目录后面会创建
				if (nextElement.isDirectory())
					continue;
				
//				这里的路径要用 /  如果用 \\ 一些特殊名称的文件会读取不到，譬如.classpath 
//				nextElement.getName() 返回的"名字"，包含其在压缩文件中的相对路径
				File dest2 = new File(dest.getAbsolutePath(), nextElement.getName());
//				创建父目录
				if(dest2.getParentFile()!=null) {
					dest2.getParentFile().mkdirs();
				}
				InputStream inputStream = zipFile.getInputStream(nextElement);
				FileOutputStream fileOutputStream = new FileOutputStream(dest2);
				int len;
				byte se[] = new byte[1024];
				while ((len = inputStream.read(se)) != -1) {
					fileOutputStream.write(se, 0, len);
				}
//				手动睡眠一会，不然程序太快，进度条一闪而过
				Thread.sleep(1000);

				fileCount = fileCount + 1;
				fileSize = fileSize + nextElement.getSize();

				if (System.currentTimeMillis() - lastTime > 500) {
					int value = (int) (100 * fileSize / fileTotalSize);
					SwingUtilities.invokeLater(() -> {
						frame.progress.setProgress(value);
					});
				}
			}
		} catch (IOException | InterruptedException e) {
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

	@Override
	protected void refreshUI() {
		SwingUtilities.invokeLater(() -> {
//			关闭进度弹窗
			if (frame.progress != null) {
				frame.progress.setVisible(false);
				frame.progress = null;
			}
		});
	}

}
