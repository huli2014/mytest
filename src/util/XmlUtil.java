package util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class XmlUtil {
	/**
	 * xml×Ö·û´® ×ª pojo
	 * 
	 * @param t
	 * @param xmlStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public static Object jaxbReadXml(Class cls, String xmlStr) {
		ByteArrayInputStream stream = null;
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			stream = new ByteArrayInputStream(xmlStr.getBytes("utf-8"));
			Unmarshaller um = context.createUnmarshaller();
			return um.unmarshal(stream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Object jaxbReadXml(String xmlStr, String xsdPath, Class cls, String[] errorMsg, boolean flag) {
		ByteArrayInputStream stream = null;
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			stream = new ByteArrayInputStream(xmlStr.getBytes("utf-8"));
			Unmarshaller um = context.createUnmarshaller();
			if(flag){
				String schemaLanguage = XMLConstants.W3C_XML_SCHEMA_NS_URI;
				SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);
				Schema schema = schemaFactory.newSchema(new File(xsdPath));
				um.setSchema(schema);
			}
			return um.unmarshal(stream);
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg[0] = e.getCause().getMessage();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public static Object jaxbReadXml(Class cls, byte[] bs) {
		if(bs == null)
			return null;
		return jaxbReadXml(cls, new ByteArrayInputStream(bs));
	}
	
	@SuppressWarnings("rawtypes")
	public static Object jaxbReadXml(Class cls, InputStream in) {
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			Unmarshaller um = context.createUnmarshaller();
			return um.unmarshal(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * pojo ×ª xml×Ö·û´®
	 * 
	 * @param pojo
	 * @return
	 */
	public static <T> String jaxbWriteXml(T pojo) {
		StringWriter out = null;
		String xmlStr = null;
		try {
			JAXBContext context = JAXBContext.newInstance(pojo.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			out = new StringWriter();
			marshaller.marshal(pojo, out);
			xmlStr = out.toString();
			// System.out.println(xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return xmlStr;
	}

	/**
	 * pojo ×ª xml×Ö·û´®
	 * 
	 * @param pojo
	 * @param ecoding ×Ö·û±àÂë Èç£ºutf-8¡¢gbk
	 * @return
	 */
	public static <T> String jaxbWriteXmlByEcoding(T pojo,String ecoding) {
		StringWriter out = null;
		String xmlStr = null;
		try {
			JAXBContext context = JAXBContext.newInstance(pojo.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, ecoding);
			out = new StringWriter();
			marshaller.marshal(pojo, out);
			xmlStr = out.toString();
			// System.out.println(xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return xmlStr;
	}
	public static <T> void jaxbWriteXmlFile(T pojo, String filePath) {

		try {
			JAXBContext context = JAXBContext.newInstance(pojo.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(pojo, new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * create policy xml
	 * 
	 * @param cqmsPolicyFilePath
	 *            file dir
	 * @param xmlName
	 *            file name
	 * @param xmlContent
	 *            file content
	 * @return true:success false:fail
	 */
	public static boolean createPolicyXml(String cqmsPolicyFilePath,
			String xmlName, String xmlContent) {
		boolean flag = false;
		if (cqmsPolicyFilePath == null || xmlName == null || xmlContent == null) {
			return flag;
		}
		File policyFile = new File(cqmsPolicyFilePath);
		if (!policyFile.exists()) {
			policyFile.mkdirs();
		}
		File[] policyFiles = policyFile.listFiles();
		if (policyFiles != null && policyFiles.length > 0) {
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			for (int i = 0; i < policyFiles.length; i++) {
				File subpolicyFile = policyFiles[i];
				if (subpolicyFile.isDirectory()) {
					try {
						File newFile = new File(subpolicyFile + "/" + xmlName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}
						fos = new FileOutputStream(newFile);
						bos = new BufferedOutputStream(fos);
						bos.write(xmlContent.getBytes("utf-8"));
						flag = true;
					} catch (Exception e) {
						flag = false;
						e.printStackTrace();
						break;
					} finally {
						if (bos != null) {
							try {
								bos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * xml×Ö·û´® ×ª pojo
	 * 
	 * @param cls
	 * @param xmlStr
	 * @param format ±àÂë¸ñÊ½
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object jaxbReadXml(Class cls, String xmlStr,String format) {
		ByteArrayInputStream stream = null;
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			stream = new ByteArrayInputStream(xmlStr.getBytes(format));
			Unmarshaller um = context.createUnmarshaller();
			return um.unmarshal(stream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<UDRNotifyRequest>").append("<commandID>2014-02-22-22-20-21-222</commandID>")
		.append("<provID>200</provID>").append("<typeID>11</typeID>")
		.append("<SearchResult>00</SearchResult>").append("<RecordNumber>10</RecordNumber>")
		.append("<FileNumber>1</FileNumber>").append("<FTPPath>FTP://27.17.8.182:6666/IF_UPLOAD/Access_Log/IDCLOG_2014-02-22-22-20-21-222_200_11</FTPPath>")
		.append("</UDRNotifyRequest>");
	}
}
