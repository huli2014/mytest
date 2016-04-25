package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.FileUtil;

public class TestMemory {

	/**
	 * @param args
	 */
	public static void testM(){
		System.gc();
        long total = Runtime.getRuntime().totalMemory(); // byte
        long m1 = Runtime.getRuntime().freeMemory();
        System.out.println("total:"+total);
        System.out.println("before:" + (total - m1));
         
//        Map<Object,Object> map = new HashMap<Object,Object>();
//        for(int i=0; i < 1000000; i++){
//            map.put(new Object(), new Object());
//        }
        try {
			String str = FileUtil.readFile("d:/tt.txt");
			System.out.println("byte"+str.getBytes().length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long total1 = Runtime.getRuntime().totalMemory();
        long m2 = Runtime.getRuntime().freeMemory();
        System.out.println("after:" + (total1 - m2));
        System.out.println(Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMemory.testM();
       // System.out.println(map.toString());
	}

}
