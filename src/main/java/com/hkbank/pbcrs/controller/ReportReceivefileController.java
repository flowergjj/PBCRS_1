package com.hkbank.pbcrs.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.cache.CacheManager;
import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.DateUtils;
import com.hkbank.pbcrs.util.RandomUtils;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.ReportReceivefileService;
import com.hkbank.pbcrs.tool.DownLoadFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/Receivefile")
public class ReportReceivefileController {

	private static final Logger log = LogManager.getLogger(ReportReceivefileController.class);

	@Autowired
	private ReportReceivefileService service;

	// 需要添加上传方法
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {

		Map<String, Object> params = Util.parseWebParas(request);
		params.put("sourceSys", "'PLN','ILN','SLN'");
		try {
			return service.listPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	@ResponseBody
	@RequestMapping("/listPageEn")
	public Map<String, Object> listPageEn(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		params.put("sourceSys", "'MIS','CB','SLNENT'");
		try {
			return service.listPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}

		return null;
	}
	@ResponseBody
	@RequestMapping("/listPageAll")
	public Map<String, Object> listPageAll(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
	
		try {
			return service.listPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}

		return null;
	}
	
	@ResponseBody
	@RequestMapping("/getReveiceFileList")
	public Map<String, Object> getReveiceFileList(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			return service.getReveiceFileList(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}

		return null;
	}

	@ResponseBody
	@RequestMapping("/uploadFile")
	public Map<String, Object> uploadFile(HttpServletRequest request) throws Throwable {
		Map<String, Object> rslt = new HashMap<String, Object>();
		List<FileItem> fileItemList = Util.resolveFileItem(request);
		Map<String, Object> params = Util.parseWebParas(request);
		//SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if (fileItemList != null && fileItemList.size() > 0) {
			/* 1.2. 将用户文件上传至临时目录 */
			List<Map<String, String>> fileList = generalFileUpload(fileItemList);
			/* 1.3. 判断用户上传是否成功 */
			if (fileList == null) {
				rslt.put("RET_CODE", "FAILED");
				rslt.put("RET_MSG", "文件上传出现异常, 请联系后台管理人员!");
				return rslt;
			}
			params.put("filePath", fileList.get(0).get("UPLOAD_FILE_NAME"));
			try {
				service.uploadFile(params);
			} catch (Throwable e) {
				rslt.put("RET_CODE", "FAILED");
				if(e.getMessage().contains("连接错误")){
					rslt.put("RET_MSG", "验证程序未正常启动,请联系管理员！");
					
				}else{
					rslt.put("RET_MSG", "上传失败,请检查文件!");
				}
				
				return rslt;

			}

			rslt.put("RET_CODE", "SUCCESS");
			rslt.put("RET_MSG", "上传成功");
			return rslt;

		}

		return rslt;
	}

	public static List<Map<String, String>> generalFileUpload(List<FileItem> list) throws Throwable {

		Properties config = new Properties();
		String saveBase = null;
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		ClassPathResource classPathResource = new ClassPathResource("config/common/report.properties");
		config.load(new InputStreamReader(classPathResource.getInputStream(), "GBK"));
		saveBase = config.getProperty("receivedir");
		if (StringUtils.isEmpty(saveBase))
			throw new Exception("Base path should not be null!");

		Iterator<FileItem> it = list.iterator();
		while (it.hasNext()) {
			FileItem item = it.next();

			/* 获取到文件域的情况 */
			if (!item.isFormField() && !"".equals(item.getName().trim())) {
				log.debug("获取到文件域, 准备进行上传! fieldName=[{}], fieldValue=[{}]", item.getFieldName(), item.getName());

				/* 存储的地址 */
				// String fileName = new File(item.getName()).getName();
				String fileName = FilenameUtils.getName(item.getName());
				log.debug("获取上传文件名为=[{}]", fileName);
				String savePath = "";
				synchronized (savePath) {
					savePath = saveBase + File.separator + DateUtils.FormatDate(new Date(), "yyyyMMdd");
				}
				File saveDir = new File(savePath);
				File saveFile = new File(savePath + File.separator + fileName);

				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}

				/* 如果没有文件存在则会出现FileNotFound异常, 因此先进行创建 */
				if (!saveFile.exists()) {
					saveFile.createNewFile();
				}

				Map<String, String> fileMap = new HashMap<String, String>();
				log.debug("上传文件路径为=[{}]", saveFile.getAbsolutePath());
				fileMap.put("UPLOAD_FILE_NAME", saveFile.getAbsolutePath());
				item.write(saveFile);
				item.delete();
				fileList.add(fileMap);
				log.debug("文件[{}]上传成功!", saveFile.getAbsolutePath());
			}
		}

		return fileList;
	}
	@RequestMapping("/loadFile")
	@ResponseBody
	public Map<String, Object> loadFile(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(request);
		DownLoadFile.download(params.get("filePath").toString(), response);
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		Properties config = new Properties();
		String saveBase = null;
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		ClassPathResource classPathResource = new ClassPathResource("config/common/report.properties");
		config.load(new InputStreamReader(classPathResource.getInputStream(), "GBK"));
		saveBase = config.getProperty("receivedir");
		System.out.println(saveBase);
	}

}
