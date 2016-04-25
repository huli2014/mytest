package test;

import java.util.Arrays;

import pojo.Father;
import pojo.Son;
import util.XmlUtil2;

public class TestJaxb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Father f = new Father();
		f.setName("jak");
		f.setAge(42);
		Son s = new Son();
		s.setAge(12);
		s.setName("tom");
		f.setSon(s);
		
		String xml = XmlUtil2.jaxbWriteXml(f);
		
		Father obj = (Father)XmlUtil2.jaxbReadXml(xml, Father.class);
		System.out.println(obj.getSon().toString());
	}

}
