package test;

public class TestInner {
	public class Thread1 extends Thread{
		public void run(){
			
		}
	}
	public void sys(){
		System.out.println("my name is sys");
	}
	public static void main(String[] args) {
		//TestInner ti = new TestInner();
		//ti.sys();
		Thread1 t =  new TestInner().new Thread1();
	}
}
