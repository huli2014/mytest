package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import pojo.Father;
import pojo.Son;
import util.FileUtil;
import util.XmlUtil2;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
//			JAXBContext context = JAXBContext.newInstance(pojo.getClass());
//			Marshaller marshaller = context.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			marshaller.marshal(pojo, new File(filePath));
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Father.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "s");
			//marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "w");
			Father father = new Father();
			father.setAge(45);
			father.setName("tom");
			Son son = new Son();
			son.setName("jack");
			son.setAge(12);
			son.setGrade(3);
			father.setSon(son);
			String xmlString = XmlUtil2.jaxbWriteXml(father);
			Father father2 = XmlUtil2.jaxbReadXml(xmlString, Father.class);
			
			//对象序列化
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\mvtech\\objectFile.obj"));
			out.writeObject(father);
			out.close();
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\mvtech\\objectFile.obj"));
			father = (Father) in.readObject();
			in.close();
			
			//写入文件
			FileUtil.witeFile("d:\\mvtech\\w\\s\\g\\writefile22.txt", xmlString);
			
			//读文件
			String str = FileUtil.readFile("d:\\mvtech\\w\\s\\g\\writefile22.txt");
			
			//写入ZIP
			FileUtil.writeZip(new File("d:\\mvtech\\w\\s\\g\\writefile22.txt"), "d:\\mvtech\\w\\s\\g\\writezip.zip");
			
			System.out.println(xmlString);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
