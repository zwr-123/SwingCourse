package Advance.p9TimeTask.s3carouselPic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


class myPanel extends JPanel{
	List<Image> picList=new ArrayList<>();
	int index=0; 		//当前图片序号
	int recInterval=15; //方格的间距
	Timer timer;	//定时器
	
	public myPanel() throws IOException {
		super();
//		初始化定时器
		timer=new Timer(100,new picTask());
//		间隔时间2秒
		timer.setDelay(2000);
//		启动定时器
		timer.start();
	}
	
//	添加图片到picList  未做文件类型过滤
	public void addImage(String path) {
		File picFolder = new File(path);
		File[] listFiles = picFolder.listFiles();
		for (File item : listFiles) {
			Image currentPic=loadImageByFile(item);
			if(currentPic!=null) {
				picList.add(currentPic);
			}
		}
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
//		为picList为空则返回
		if(picList.isEmpty()) return;
//		索引超过，则定位到第一张图片	
		if(index>picList.size()-1) {
			index=0;
		}
		
		int panelW = this.getWidth();
		int panelH = this.getHeight();

		Image currentImage=picList.get(index);
		
		int picW = currentImage.getWidth(null);
		int picH = currentImage.getHeight(null);
	
//		保持图片原有比例显示
		picW=panelW;
		picH=picW*picH/picW;
		if(picH>panelH) {
			picH=panelH;
			picW=picH*picW/picH;
		}
		int picX=(panelW-picW)/2;
		int picY=(panelH-picH)/2;
		
//		绘制图片
		g.drawImage(currentImage,picX,picY,picW,picH,null);

//		绘制方格
		drawRec(g,panelW,panelH);
		
		index=index+1;
	}
	
	private void drawRec(Graphics g,int panelW,int panelH) {
		int len = picList.size();
//	   	方格起始坐标	
		int recX=panelW/4;
		int recY=4*panelH/5;
		for(int i=0;i<len;i++) {
//			如果此方格索引 等于当前显示的图片索引，方格就变红
//			整体效果就是，当前显示的是第几张图片，那么第几个方格就变红
			if(i==index) {
				g.setColor(Color.red);
				g.fillRect(recX, recY, 15, 15);
			}
			g.setColor(Color.GREEN);
			g.drawRect(recX, recY, 15, 15);
			recX=recX+recInterval+15;
		}
	}

	public Image loadImageByFile(File file)  {
		Image image=null;
		try {
			 image=ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	
//	timer 可以在此定时器中 操作index 索引
	private class picTask implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			myPanel.this.repaint();
		}
		
	} 
	
}
