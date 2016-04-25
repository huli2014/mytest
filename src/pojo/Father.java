package pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Father")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"name", "age","son"})
public class Father implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="age")
	private int age;
	@XmlElement(name="son")
	private Son son;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public Son getSon() {
		return son;
	}
	public void setSon(Son son) {
		this.son = son;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Œ“ «∞÷∞÷:"+name;
	}
	
}
