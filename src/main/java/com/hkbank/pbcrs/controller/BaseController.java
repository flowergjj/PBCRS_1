package com.hkbank.pbcrs.controller;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.hkbank.pbcrs.util.DateUtils;

public class BaseController {
	private static final Logger log = LogManager.getLogger(BaseController.class);
	public static List<Map<String, String>> generalFileUpload(List<FileItem> list) throws Throwable {

		Properties config = new Properties();
		String saveBase = null;
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		ClassPathResource classPathResource = new ClassPathResource("config/common/report.properties");
		config.load(new InputStreamReader(classPathResource.getInputStream(), "GBK"));
		saveBase = config.getProperty("batchDir");
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
}
