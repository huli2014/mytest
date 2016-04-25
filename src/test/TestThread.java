package test;

public class TestThread extends Thread{
	private static TestFtp testFtp = new TestFtp();
	/**
	 * @param args
	 */
	public void run (){
		while(true){
			try {
				Thread.sleep(5000);
				String[] files=testFtp.readFileFromFtp();
				if(files!=null&&files.length>0){
					System.out.println("我还有文件");
				}else{
					System.out.println("我没文件了");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		TestThread t1 = new TestThread();
		TestThread t2 = new TestThread();
		t1.start();
		//t2.start();
		
//		for(int i=0;i<2;i++){
//			new TestThread().start();
//		}
		// TODO Auto-generated method stub

	}

}
