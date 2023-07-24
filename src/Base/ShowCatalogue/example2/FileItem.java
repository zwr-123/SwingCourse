package Base.ShowCatalogue.example2;

import java.io.File;
import java.text.SimpleDateFormat;

class FileItem {
	public  File file;
	public String name="";
	public boolean isDir=false;
	public String time="";
	public FileItem(File file) {
		this.file=file;
		this.name=file.getName();
		this.isDir=file.isDirectory();
		if(!isDir) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			this.time=simpleDateFormat.format(file.lastModified());
		}
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}

}
