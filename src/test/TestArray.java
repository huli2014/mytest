package test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestArray {
	private static Object[] elementData = {};
	private static int size;
	/**
	 * @param args
	 */
	public static void testArray(){
		elementData = Arrays.copyOf(elementData, 10);
		elementData[size++]=1;
		AbstractList<Object> l = new ArrayList<Object>();
		l.add("1111");
		List<Object> l2 = new ArrayList<Object>(l);
		System.out.println(elementData.length);
		System.out.println(Object[].class);
	}
	
	public static void testLinkedList(){
		System.gc();
		long start = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		LinkedList<Object> linkedList = new LinkedList<Object>();
		for(int i=0;i<10000000;i++){
			linkedList.add(i);
		}
		long end = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		System.out.println(end-start);
	}
	public static void testArrayList(){
		//400608
		System.gc();
		long start = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		ArrayList<Object> arrayList = new ArrayList<Object>();
		for(int i=0;i<10000000;i++){
			arrayList.add(i);
		}
		long end = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		System.out.println(end-start);
	}
	public static void testMap(){
		Map map = new HashMap<String, Object>();
		map.put(null, null);
		map.put(null, null);
		System.out.println(0.75F);
	}
	public static void testSet(){
		Set set = new HashSet<Object>();
		set.add("111");
		set.remove("aa");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testLinkedList();
		//testArrayList();
		testMap();
		//System.out.println(1&1);
	}

}
