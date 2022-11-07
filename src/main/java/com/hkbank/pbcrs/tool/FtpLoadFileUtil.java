package com.hkbank.pbcrs.tool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.SocketException;
import java.util.*;


public class FtpLoadFileUtil {
	private static final Logger log = LogManager.getLogger(FtpLoadFileUtil.class);
	private static Map<String,String> config ;
	private static FTPClient ftpClient;
	/**
	 * 缓冲区大小参数
	 */
	private static final int BUFFER_SIZE = 1024 * 1024 * 4;

	
	public  static void initConfig(){
		if(config == null) {
			config = new HashMap<String, String>();
			loadProperties();
		}
		
	}
	private static void loadProperties() {
		InputStream is = FtpLoadFileUtil.class.getResourceAsStream("/config/ftp/ftp.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			String host = (String) p.get("host");
			String port =  p.get("port").toString();
			String username = (String) p.get("username");
			String password = (String) p.get("password");
			String reomteDirectory =  (String) p.get("reomteDirectory");
			String localDirectory = (String) p.get("localDirectory");
			config.put("host",host);
			config.put("port",port);
			config.put("username",username);
			config.put("password",password);
			config.put("reomteDirectory",reomteDirectory);
			config.put("localDirectory",localDirectory);
			log.info("ftp连接配置:{}",config);
		} catch (IOException e) {
			log.info("/config/ftp.properties配置文件不存在!",e);
			//System.out.println("/config/ftp.properties" + "配置文件不存在!");
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 创建ftp连接
	 * @throws IOException 
	 * @throws SocketException 
	 * @throws NumberFormatException 
	 *
	 */
	public static void connect()throws NumberFormatException, SocketException, IOException  {
		ftpClient = new FTPClient();
		ftpClient.connect(config.get("host"), Integer.parseInt(config.get("port")));
		ftpClient.login(config.get("username"), config.get("password"));
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		// 限制缓冲区大小
		ftpClient.setBufferSize(BUFFER_SIZE);
		int reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			closeConnect();
		}
	}
	
	/**
	 * 关闭ftp连接
	 */
	public static void closeConnect() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				log.error(config.get("host")+":"+config.get("port")+" ftp关闭失败",e);
			}
		}
	}
	
	public static Map<String,Object> uploadFile(String uploadDate) throws IOException  {
		initConfig();
		log.error("开始连接远程服务器："+config.get("host")+" "+config.get("port"));
		try {
			connect();
			
		} catch (Exception e) {
			log.error("连接远程服务器失败",e);
			throw new IOException("连接远程服务器失败");
		} 
		log.info("远程服务器连接成功！");
		log.info("开始下载文件!");
		Map<String, Object> downloadDirFile = downloadDirFile(config.get("reomteDirectory")+"/"+uploadDate,config.get("localDirectory")+File.separator+uploadDate);
		if(downloadDirFile == null) {
			log.info("远程目录"+config.get("reomteDirectory")+File.separator+uploadDate+"不存在文件！");
		}else {
			log.info("文件下载成功!");
		}
		closeConnect();
		log.info("ftp文件列表:{}",downloadDirFile);
		return downloadDirFile;
	}
	
	
	public static Map<String, Object> downloadDirFile(String reomateDirectory, String saveDirectory) throws IOException  {
		if (ftpClient != null) {
			if (!ftpClient.changeWorkingDirectory(reomateDirectory)) {
				throw new FileNotFoundException("远程服务器中不存在"+reomateDirectory+"目录");
			}
			ftpClient.enterLocalPassiveMode(); // 设置被动模式，开通一个端口来传输数据
			FTPFile[] listFiles = ftpClient.listFiles();
			List<String> encList = new ArrayList<String>();
			for (FTPFile ftpFile : listFiles) {
				if(ftpFile.isDirectory()){
					createParentPath(saveDirectory+File.separator+ftpFile.getName());
					downloadDirFile(reomateDirectory+"/"+ftpFile.getName(),saveDirectory+File.separator+ftpFile.getName());
				}else{
					String fileName = ftpFile.getName();
					createParentPath(saveDirectory);
					File file = new File(saveDirectory, fileName);
					OutputStream os = new FileOutputStream(file);
					ftpClient.retrieveFile(fileName, os);
					if (fileName.endsWith("enc")) {
						encList.add(file.getPath());
					}	
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			if(encList.size() == 0 ) {
				return null;
			}
			resultMap.put("encList", encList);
			return resultMap;
		}else {
			throw new IOException("未获取到ftp连接!");
		}	
	}
	
	/**
	 * 判断文件夹是否存在，不存在则创建
	 * 
	 * @param parentPath
	 */
	static void createParentPath(String parentPath) {
		File file = new File(parentPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	public static void main(String[] args) {
		try {
			// v ("20210907");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
