package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TestFtp {
	private static String hostname="192.168.1.87";
	private static String ftpUsername="test";
	private static String ftpPwd="123456";
	private static int port=21;
	private static FTPClient ftpClient = null;
	private static Logger log = LoggerFactory.getLogger(TestFtp.class);
	/**
	 * @param args
	 */
	public boolean createConnection(){
		if(ftpClient!=null){
			log.info("已有连接");
			return true;
		}
		ftpClient = new FTPClient();
		ftpClient.setDataTimeout(60*1000);
		ftpClient.setDefaultTimeout(60*1000);
		ftpClient.setConnectTimeout(60*1000);
		try {
			ftpClient.connect(hostname, port);
			if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
				if(ftpClient.login(ftpUsername, ftpPwd));{
					log.info("与FTP服务器成功连接....."+ftpClient.getReplyString());
					ftpClient.setBufferSize(100000);
					return true;
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return false;
	}
	public static void disconnect() {
		if (ftpClient != null) {
			try {
				ftpClient.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (log.isInfoEnabled()) {
				log.info("退出FTP远程主机![" + hostname + "]" + ftpClient.getReplyString());
			}
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public String[] readFileFromFtp(){
		String[] files = null;
		if(createConnection()){
			try {
				files = ftpClient.listNames("/IF_UPLOAD/Access_Log/");
				
				if(files!=null&&files.length>0){
					InputStream in = null;
					BufferedReader br = null;
					for(String currFileName:files){
						 in= ftpClient.retrieveFileStream(currFileName);
						//BufferedInputStream bin = new BufferedInputStream(in);
						 br = new BufferedReader(new InputStreamReader(in));
						String line=null;
						while((line=(br.readLine()))!=null){
							System.out.println(line);
						}
						ftpClient.completePendingCommand();
					}
					in.close();
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return files;
	}
	
	public static void main(String[] args) {
		while(true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//disconnect();
		// TODO Auto-generated method stub

	}
	public FTPClient getFTPClient(){
		return ftpClient;
	}

}
