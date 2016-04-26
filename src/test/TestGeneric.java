package test;

public class TestGeneric {
	public class Inner<T>{
		public void print(T t){
			System.out.println(t);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inner<Object> inner =  new TestGeneric(). new Inner<Object>();
		inner.print("ssss");
	}

}
