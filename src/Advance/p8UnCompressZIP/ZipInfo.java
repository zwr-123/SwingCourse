package Advance.p8UnCompressZIP;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * 存储压缩文件信息，使程序结构更加合理。但此次练习，未使用
 * @author ZW
 *
 */
class ZipInfo {
	public int fileCount;
	public long totalSize;
	
	List<ZipEntry>  entries=new ArrayList<>();
	
//	排序
	public void sort() {}

}
