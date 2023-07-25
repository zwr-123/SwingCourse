package Advance.p8UnCompressZIP;

import javax.swing.SwingUtilities;
/**
 * 抽象任务类，模仿的是SwingWorker类
 * @author ZW
 *
 */
abstract class shortTask extends Thread{
	private Object args[];
	private Exception error; 
	
	public void exec(Object ... args) {
		this.args=args;
		start();
	}
	
	public Object[] getArgs() {
		return args;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
//			执行任务
			doTask();
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
			error=e;
		}
		
		
//		结束后刷新界面
		SwingUtilities.invokeLater(()->{
			refreshUI();
		});
		
	}
	
//	抽象方法
	protected  abstract void doTask();
	
	protected  abstract void refreshUI();

}
