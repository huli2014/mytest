package util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	public static void witeFile(String path,String content){
		File file = new File(path);
		String parent = file.getParent();
		if(!new File(parent).exists()){
			new File(parent).mkdirs();
		}
		FileOutputStream out = null;
		BufferedOutputStream buff = null;
		try {
			out = new FileOutputStream(file);
			buff = new BufferedOutputStream(out);
			try {
				buff.write(content.getBytes());
				buff.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
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
			if(buff!=null){
				try {
					buff.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static String readFile(String path) throws IOException{
		File file = new File(path);
		if(!file.exists()){
			return null;
		}
		//BufferedInputStream ins = new BufferedInputStream(new FileInputStream(new File(path)));
		BufferedReader in = new BufferedReader(new FileReader(new File(path)));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while((line=in.readLine())!=null){
			sb.append(line);
		}
		if(in!=null){
			in.close();
		}
		return sb.toString();
	}
	
	public static void writeZip(File file,String zipfile){
		ZipOutputStream out = null;
		try {
			 out = new ZipOutputStream(new FileOutputStream(new File(zipfile)));
			 try {
				 ZipEntry entry = new ZipEntry(file.getName());
				out.putNextEntry(entry);
				out.write(readFile(file.getPath()).getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
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
	}
	public static void main(String[] args) {
		File file = new File("d:/d/log1/log2/log3/log4");
		if(!file.exists()){
			file.mkdirs();
		}
		System.out.println(File.separator);
	}
}
