package util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XmlUtil2 {
	
	public  static <T>String jaxbWriteXml(T pojo) {
		StringWriter out = null;
		JAXBContext jaxbContext;
		try {
			out = new StringWriter();
			jaxbContext = JAXBContext.newInstance(pojo.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(pojo, out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return out.toString();
	}
	@SuppressWarnings("unchecked")
	public static <T> T jaxbReadXml(String xmlString ,Class<T> types){
		StringReader reader = new StringReader(xmlString);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(types);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
