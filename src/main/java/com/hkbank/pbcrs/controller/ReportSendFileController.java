package com.hkbank.pbcrs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.regex.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hkbank.pbcrs.util.DateUtils;
import com.hkbank.pbcrs.util.StringUtil;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.model.ErrorInfo;
import com.hkbank.pbcrs.service.PbcrsReportSendfileService;
import com.hkbank.pbcrs.tool.FtpLoadFileUtil;


@Controller
@RequestMapping("/sendFile")
public class ReportSendFileController {
	private static final Logger log = LogManager.getLogger(ReportSendFileController.class);

@Autowired
private PbcrsReportSendfileService service;
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			return service.listPage(params);
		} catch (Exception e) {
			log.error("文件列表查询出现异常!", e);
		}
		
		return null;
	}
	@ResponseBody
	@RequestMapping("/download")
	public Map<String, Object> download(HttpServletRequest request,HttpServletResponse response) {
		BufferedInputStream ins = null ;
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			String filePath = params.get("filePath").toString();// 要下载的文件绝对路径
			String queryFilepath = params.get("queryFilepath").toString();
			if(filePath.indexOf(queryFilepath) > -1 && !queryFilepath.equals("resultdir") && !StringUtil.isEmpty(queryFilepath)){
				String path = filePath.substring(0, filePath.indexOf(queryFilepath)+queryFilepath.length());
				File sourceFile = new File(path);
				if(sourceFile.exists()){
					ZipOutputStream zos = null;
					try {
						File zipPath = new File(path+queryFilepath+".zip");
						FileOutputStream fos = new FileOutputStream(zipPath);
						zos = new ZipOutputStream(fos);
						compress(sourceFile, zos, queryFilepath, true);	
						response.reset();
						response.addHeader("Content-Disposition", "attachment;filename="
								+ new Date().getTime()+".zip");
						FileInputStream input = new FileInputStream(zipPath);
						OutputStream ous = new BufferedOutputStream(
								response.getOutputStream());
						response.setContentType("application/zip");
						byte[] buff = new byte[input.available()];
						int len = 0;
			            while ((len = input.read(buff)) != -1){

			            	ous.write(buff, 0, len);

			            }

			            input.close();
			  			ous.flush();
						ous.close();
						
					} catch (Exception e) {
						Map<String, Object> rslt = new HashMap<String, Object>();
						rslt.put("失败", "文件下载出错");
						return rslt;
					}
					
				}
			}else{						
				File file = new File(filePath); 
				String filename =FilenameUtils.getName(filePath);
				filename = URLEncoder.encode(filename, "UTF-8");
				filename = filename.replace("bad", "txt");
				ins = new BufferedInputStream(new FileInputStream(filePath));
				byte[] buffer = new byte[ins.available()];
				ins.read(buffer);
				ins.close();
	
				response.reset();
				response.addHeader("Content-Disposition", "attachment;filename="
						+ filename);
				response.addHeader("Content-Length", "" + file.length());
				OutputStream ous = new BufferedOutputStream(
						response.getOutputStream());
				response.setContentType("application/octet-stream");
				ous.write(buffer);
				ous.flush();
				ous.close();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("失败", "文件下载出错");
			return rslt;
		}
/*      finally {
			try {
				ins.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return null;

	}
	
	@ResponseBody
	@RequestMapping("/downloadAll")
	public Map<String, Object> downloadAll(HttpServletRequest request,HttpServletResponse response) {
		ZipOutputStream zos = null ;
		OutputStream ous = null;
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(new Date());
			String Path = params.get("path").toString();// 要下载的文件文件名外的路径
			int num = Integer.valueOf(params.get("num").toString()).intValue(); //需要打包的文件个数
			List<File> srcFiles = new ArrayList<File>();
			if(num > 0){
				
				for (int i = 1; i <= num; i++) {
					String filePath = Path+params.get("enc"+i).toString();
					File file = new File(filePath);
					srcFiles.add(file);
				}
			}
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new Date().getTime()+".zip");
			 ous = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/zip");
			zos = new ZipOutputStream(ous);
	       for (File srcFile : srcFiles) {

	              

	              zos.putNextEntry(new ZipEntry(srcFile.getName()));

	              int len;

	              FileInputStream in = new FileInputStream(srcFile);
	              byte[] buf = new byte[in.available()];

	              while ((len = in.read(buf)) != -1){

	                  zos.write(buf, 0, len);

	              }

	              zos.closeEntry();

	              in.close();

	          }
					
			ous.flush();
			//ous.close();

		} catch (Exception e) {
			// e.printStackTrace();
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("失败", "文件下载出错");
			return rslt;
		}
      finally {
			try {
				
				zos.close();
				ous.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}
	
	
	@ResponseBody
	@RequestMapping("/listPageRptType")
	public Map<String, Object> listPageRptType(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listPageRptType(params);
		} catch (Exception e) {
			log.error("报文类型查询出现异常!", e);
		}
		
		return null;
	}
	
	public void compress(File sourceFile,ZipOutputStream zos,String name,boolean flag) throws Exception{
		byte[] buf = new byte[2*1024];
		if(sourceFile.isFile()){
			zos.putNextEntry(new ZipEntry(name));
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while((len = in.read(buf)) != -1){
				zos.write(buf,0,len);
			}
			zos.closeEntry();
			in.close();
		}else{
			File[] listFiles = sourceFile.listFiles();
			if(listFiles == null || listFiles.length == 0){
				if(flag){
					zos.putNextEntry(new ZipEntry(name+"/"));
					zos.closeEntry();
				}
			}else{
				for (File file : listFiles) {
					if(flag){
						compress(file, zos, name+"/"+file.getName(), flag);
					}else{
						compress(file, zos, file.getName(), flag);
					}
				}
			}
		}
	}
	
	/*-------------------------银联---------------------------*/
	@ResponseBody
	@RequestMapping("/YLlistPage")
	public Map<String, Object> YLlistPage(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			if(params.get("YLqueryRptDate") == null || params.get("YLqueryRptDate").toString().equals("")) {
				 Calendar c = Calendar.getInstance();
				 c.add(Calendar.DAY_OF_MONTH, -1);
				 String YLqueryRptDate = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd");
				 params.put("YLqueryRptDate", YLqueryRptDate);
			}
			return service.YLlistPage(params);
		} catch (Exception e) {
			log.error("文件列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/uploadYlData")
	public Map<String, Object> uploadYlData(HttpServletRequest request) {
		Map<String,Object>	result = new HashMap<String, Object>();	
		Map<String, Object> params = Util.parseWebParas(request);
		String date = params.get("date").toString();
		date=date.replace("-", "");
		try {
			
			Map<String, Object> fileMap = FtpLoadFileUtil.uploadFile(date);
			if(fileMap  == null) {
				result.put("RPT_CODE", "FAILED");
				result.put("MSG", "远程服务器中指定日期目录下不存在文件！");
				return result;
			}
			List<String> list = (List<String>) fileMap.get("encList");
			service.insertYl(list,date);
			result.put("RPT_CODE", "SUCCESS");
			return result;
		} catch (Exception e) {
			log.error("银联数据文件获取异常", e);
			if(e.getMessage().contains("远程服务器中不存在")) {
				result.put("RPT_CODE", "FAILED");
				result.put("MSG", "请检查远程服务器中是否存在所选日期对应数据目录！");
				return result;
			}
		}
		result.put("RPT_CODE", "FAILED");
		result.put("MSG", "获取远程服务器文件失败!");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, Object> querySendFile(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			Map<String, Object> sendFileRel = service.getSendFileRel(params);
			return sendFileRel;
		} catch (Exception e) {
			log.error("文件列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/queryErrorInfo")
	public Map<String, Object> queryErrorInfo(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			Map<String, Object> sendFileRel = service.queryErrorInfo(params);
			return sendFileRel;
		} catch (Exception e) {
			log.error("文件列表查询出现异常!", e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/addFilter")
	public Map<String, Object> addFilter(@RequestBody List<ErrorInfo> errorInfo,HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
	
		try {
			String jsonString = JSON.toJSONString(errorInfo);
			List<ErrorInfo> parseArray = JSON.parseArray(jsonString, ErrorInfo.class);
			
			int count = service.addFilter(parseArray);
			Map<String,Object> result = new HashMap<>(16);
			result.put("RPT_CODE", "SUCCESS");
			result.put("MSG", "添加成功!");
			return result;
		} catch (Exception e) {
			log.error("添加失败!", e);
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/removeFilter")
	public Map<String, Object> removeFilter(@RequestBody List<ErrorInfo> errorInfo,HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);

		try {
			String jsonString = JSON.toJSONString(errorInfo);
			List<ErrorInfo> parseArray = JSON.parseArray(jsonString, ErrorInfo.class);

			int count = service.removeFilter(parseArray);
			Map<String,Object> result = new HashMap<>(16);
			result.put("RPT_CODE", "SUCCESS");
			result.put("MSG", "移除成功!");
			return result;
		} catch (Exception e) {
			log.error("文件列表查询出现异常!", e);
			e.printStackTrace();
		}

		return null;
	}
	
}
