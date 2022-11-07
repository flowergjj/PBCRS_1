package com.hkbank.pbcrs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.DateUtils;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.PbcrsReportFieldMappingMapper;
import com.hkbank.pbcrs.model.PbcrsReportFieldMapping;
import com.hkbank.pbcrs.service.BatchCorrectService;
import com.hkbank.pbcrs.tool.List2Excel;

/**
 * 批量更正
 * 
 * @author hs1112
 * 
 */
@Controller
@RequestMapping("/batchCorrect")
@SuppressWarnings("all")
public class BatchCorrectController extends BaseController {
	private static final Logger log = LogManager.getLogger(BatchCorrectController.class);

	@Autowired
	private BatchCorrectService batchCorrectService;
	@Autowired
	private PbcrsReportFieldMappingMapper pbcrsReportFieldMappingMapper;
	@RequestMapping(value="/getFirstCombox")
	@ResponseBody
	public List<Map<String,Object>> getFirstCombox(HttpServletRequest request){
		
		Map<String, Object> param = Util.parseWebParas(request);
		List<Map<String, Object>> list = batchCorrectService.getFirstCombox(param);
		return list;
	}
	
	@RequestMapping(value="/getSecondCombox")
	@ResponseBody
	public List<Map<String,Object>> getSecondCombox(HttpServletRequest request){
		Map<String, Object> param = Util.parseWebParas(request);
		List<Map<String, Object>> list = batchCorrectService.getSecondCombox(param);
		return list;
	}
	
	@RequestMapping(value="/download")
	@ResponseBody
	public Map<String,Object>  download(HttpServletRequest request,HttpServletResponse response){
	 	//Map<String,String> param =new HashMap<String, String>();
	 	Map<String, Object> param = Util.parseWebParas(request);
    	//param.put("reportType", "企业基本信息");
    	//param.put("methodName", "'updateEnBasInfBsSgmt','updateEnbasinffcssgmt'");
    	List<PbcrsReportFieldMapping> listField = pbcrsReportFieldMappingMapper.listField(param);
		//DecimalFormat df = new DecimalFormat("000");
    	String reportType = param.get("reportType").toString();
		String expDir = "excelFile\\"+reportType+
				 String.valueOf(System.currentTimeMillis())  + ".xls";
		String ctxDir = request.getSession().getServletContext().getRealPath(
				String.valueOf(File.separatorChar));

		String saveDirectory = ctxDir + expDir;
		File file = new File(saveDirectory);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
		List2Excel export = new List2Excel();
		Map<String, Object> data1 = new LinkedHashMap<String, Object>();
/*		data1.put("0", "标识号码");
		data1.put("1", "报送日期");
		data1.put("2", "系统类型");
		data1.put("3", "报文类型");*/
		if(listField != null && listField.size() > 0){
			data1.put("0", "标识号码");
			data1.put("1", "报送日期");
			data1.put("2", "系统类型");
			data1.put("3", "报文类型");
			//特殊处理报表
			String reportIdS = "610,620,630,640,650";
			if(reportIdS.indexOf(listField.get(0).getReportId()) >= 0){
				data1.put("4", "报表年份");
				data1.put("5", "报表类型");
				data1.put("6", "报表细分类型");
			}
	    	for (int i = 0; i < listField.size(); i++) {
	    		if(reportIdS.indexOf(listField.get(0).getReportId()) >= 0){
	    			data1.put(""+(i+7)+"", listField.get(i).getComments());
	    		}else{
	    			data1.put(""+(i+4)+"", listField.get(i).getComments());
	    		}
	    		
			}
	    	map.add(data1);
		}
		Map<String, Object> data = new LinkedHashMap<String, Object>();
/*		data.put("0", "MARKNO");
		data.put("1", "DATADATE");
		data.put("2", "SOURCESYS");
		data.put("3", "REPORTTYPE");*/
		if(listField != null && listField.size() > 0){
			data.put("0", "MARKNO");
			data.put("1", "DATADATE");
			data.put("2", "SOURCESYS");
			data.put("3", "REPORTTYPE");
			//特殊处理报表
			String reportIdS = "610,620,630,640,650";
			if(reportIdS.indexOf(listField.get(0).getReportId()) >= 0){
				data.put("4", "SHEETYEAR");
				data.put("5", "SHEETTYPE");
				data.put("6", "SHEETTYPEDIVIDE");
			}
	    	for (int i = 0; i < listField.size(); i++) {
	    		if(reportIdS.indexOf(listField.get(0).getReportId()) >= 0){
	    			data.put(""+(i+7)+"", listField.get(i).getFieldName());
	    		}else{
	    			data.put(""+(i+4)+"", listField.get(i).getFieldName());
	    		}
	    		
			}
	    	map.add(data);
	    	
	    	Map<String, Object> data2 = new LinkedHashMap<String, Object>();
	    	data2.put("3", listField.get(0).getReportId());
	    	map.add(data2);
		}

		try {
			String filepath=export.toExcel(map, saveDirectory);
			// url路径
			Properties pbcrsReportCfg = Resources
					.getResourceAsProperties("config/common/report.properties");
			String path = pbcrsReportCfg.getProperty("batchUrl");
			//String url="http://localhost:";
			String destPath = path + request.getServerPort()
					+ request.getContextPath() + "/"
					+ expDir.replaceAll("\\\\", "/");

			System.out.println(" 导出Excel文件[成功] ");
	
			
			
			
			
			
			return fileDownload(filepath,response);
		} catch (Exception e) {
			System.out.println(" 导出Excel文件[失败] ");
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public Map<String, Object> uploadFile(HttpServletRequest request) throws Throwable {
		Map<String, Object> rslt = new HashMap<String, Object>();
		List<FileItem> fileItemList = Util.resolveFileItem(request);
		Map<String, Object> params = Util.parseWebParas(request);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if (fileItemList != null && fileItemList.size() > 0) {
			/* 1.2. 将用户文件上传至临时目录 */
			List<Map<String, String>> fileList = generalFileUpload(fileItemList);
			/* 1.3. 判断用户上传是否成功 */
			if (fileList == null) {
				rslt.put("RET_CODE", "FAILED");
				rslt.put("RET_MSG", "文件上传出现异常, 请联系后台管理人员!");
				return rslt;
			}
			User user =	(User)request.getSession().getAttribute("user");
			params.put("userid", user.getUserId());
			params.put("filePath", fileList.get(0).get("UPLOAD_FILE_NAME"));
			try {
				batchCorrectService.analyzeFile(params);
				rslt.put("RET_CODE", "SUCCESS");
				rslt.put("RET_MSG", "上传成功");
				return rslt;
			} catch (ParseException e) {
				log.debug("批量更正上传失败={}",e.getMessage());
				rslt.put("RET_CODE", "FAILD");
				rslt.put("RET_MSG", "文件数据日期格式不正确，请检查文件!");
				return rslt;
			}
			
			catch (Exception e) {
				e.printStackTrace();
				log.debug("批量更正上传失败={}",e.getMessage());
				if(ContainStr(e.getMessage())){
					rslt.put("RET_MSG", "上传失败");
				}else{
					rslt.put("RET_MSG", e.getMessage());
				}
				rslt.put("RET_CODE", "FAILD");
				return rslt;
			}
		}
		return null;
	}
	
	
	public boolean ContainStr(String str){
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.matches();
	}
	
	public Map<String, Object> fileDownload(String filePath,HttpServletResponse response){

		BufferedInputStream ins = null ;
		
		try {
			File file = new File(filePath); 
			String filename =FilenameUtils.getName(filePath);
			filename = URLEncoder.encode(filename, "UTF-8");
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
	

	
	@RequestMapping(value = "/checkFileName")
	@ResponseBody
	public boolean checkFileName(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		 int count = batchCorrectService.checkFile(param);
		 if(count>0){
			 return true;
		 }
		return false;
	}
	
	
	//报文批量更正报送
	@ResponseBody
	@RequestMapping("/report")
	public Map<String, Object> report(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("报文修改报送时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			batchCorrectService.reportBatch(params);
			rslt.put("RET_CODE", "SUCCESS");
		
		} catch (Exception e) {
			log.error("报文批量报送异常!", e);
			rslt.put("RET_CODE", "FAILED");
			if(e.getMessage().contains("连接错误")){
				rslt.put("ERR_MSG", "报送程序未正常启动,请联系管理员！");
			}
			return rslt;
		}
		
		return rslt;
	}
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return batchCorrectService.listPage(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/listPageInfo")
	public Map<String, Object> listPageInfo(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return batchCorrectService.listPageInfo(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	/**
	 * 批量删除模板下载
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/downloadDel")
	@ResponseBody
	public Map<String,Object>  downloadDel(HttpServletRequest request,HttpServletResponse response){
	 	Map<String, Object> param = Util.parseWebParas(request);
    	String reportType = param.get("reportType").toString();
		String expDir = "excelFile\\"+reportType+
				 String.valueOf(System.currentTimeMillis())  + ".xls";
		String ctxDir = request.getSession().getServletContext().getRealPath(
				String.valueOf(File.separatorChar));

		String saveDirectory = ctxDir + expDir;
		File file = new File(saveDirectory);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
		List2Excel export = new List2Excel();
		Map<String, Object> data1 = new LinkedHashMap<String, Object>();
		data1.put("0", "主键");
		data1.put("1", "报文类型");
		map.add(data1);
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("0", "MARKNO");
		data.put("1", "REPORTTYPE");
    	map.add(data);
    	
    	Map<String, Object> data2 = new LinkedHashMap<String, Object>();
    	data2.put("1", reportType(param.get("reportType").toString()));
    	map.add(data2);


		try {
			String filepath=export.toExcel(map, saveDirectory);
			// url路径
			Properties pbcrsReportCfg = Resources
					.getResourceAsProperties("config/common/report.properties");
			String path = pbcrsReportCfg.getProperty("batchUrl");
			//String url="http://localhost:";
			String destPath = path + request.getServerPort()
					+ request.getContextPath() + "/"
					+ expDir.replaceAll("\\\\", "/");

			System.out.println(" 导出Excel文件[成功] ");
		
			return fileDownload(filepath,response);
		} catch (Exception e) {
			System.out.println(" 导出Excel文件[失败] ");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 批量删除上传
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@ResponseBody
	@RequestMapping("/uploadFileDel")
	public Map<String, Object> uploadFileDel(HttpServletRequest request) throws Throwable {
		Map<String, Object> rslt = new HashMap<String, Object>();
		List<FileItem> fileItemList = Util.resolveFileItem(request);
		Map<String, Object> params = Util.parseWebParas(request);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if (fileItemList != null && fileItemList.size() > 0) {
			/* 1.2. 将用户文件上传至临时目录 */
			List<Map<String, String>> fileList = generalFileUpload(fileItemList);
			/* 1.3. 判断用户上传是否成功 */
			if (fileList == null) {
				rslt.put("RET_CODE", "FAILED");
				rslt.put("RET_MSG", "文件上传出现异常, 请联系后台管理人员!");
				return rslt;
			}
			User user =	(User)request.getSession().getAttribute("user");
			params.put("userid", user.getUserId());
			params.put("filePath", fileList.get(0).get("UPLOAD_FILE_NAME"));
			try {
				batchCorrectService.analyzeFileDel(params);
				rslt.put("RET_CODE", "SUCCESS");
				rslt.put("RET_MSG", "上传成功");
				return rslt;
			} catch (ParseException e) {
				log.debug("批量更正上传失败={}",e.getMessage());
				rslt.put("RET_CODE", "FAILD");
				rslt.put("RET_MSG", "文件数据日期格式不正确，请检查文件!");
				return rslt;
			}
			
			catch (Exception e) {
				e.printStackTrace();
				log.debug("批量更正上传失败={}",e.getMessage());
				if(ContainStr(e.getMessage())){
					rslt.put("RET_MSG", "上传失败");
				}else{
					rslt.put("RET_MSG", e.getMessage());
				}
				rslt.put("RET_CODE", "FAILD");
				
				return rslt;
			}
		}
		return null;
	}
	/**
	 * 批量删除上传时校验上传文件是否正确
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkFileNameDel")
	@ResponseBody
	public boolean checkFileNameDel(HttpServletRequest request) {
		Map<String, Object> param = Util.parseWebParas(request);
		 int count = batchCorrectService.checkFileDel(param);
		 if(count>0){
			 return true;
		 }
		return false;
	}
	
	/**
	 * 批量删除查询
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listPageDel")
	public Map<String, Object> listPageDel(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return batchCorrectService.listPageDel(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	/**
	 * 批量删除详情查询
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listPageInfoDel")
	public Map<String, Object> listPageInfoDel(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return batchCorrectService.listPageInfoDel(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	/**
	 * 批量删除报送
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/reportDel")
	public Map<String, Object> reportDel(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("报文修改报送时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			batchCorrectService.reportBatchDel(params);
			rslt.put("RET_CODE", "SUCCESS");
		
		} catch (Exception e) {
			log.error("报文批量报送异常!", e);
			rslt.put("RET_CODE", "FAILED");
			if(e.getMessage().contains("连接错误")){
				rslt.put("ERR_MSG", "报送程序未正常启动,请联系管理员！");
			}
			return rslt;
		}
		
		return rslt;
	}
	
	/**
	 * 根据日期和报文类型批量删除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/DelRpt")
	public Map<String, Object> DelRpt(HttpServletRequest request) {
		Map<String,Object> rslt = new HashMap<String, Object>();
		Map<String, Object> params = Util.parseWebParas(request);
		try {
			User user =	(User)request.getSession().getAttribute("user");
			if(user == null){
				log.error("报文修改报送时session会话失效!");
				rslt.put("RET_CODE", "FAILED");
				return rslt;
			}
			params.put("createUserNo", user.getUserId());
			params.put("createUserName", user.getUserName());
			params.put("reportId", reportType(params.get("reportType").toString()));
			batchCorrectService.reportDel(params);
			rslt.put("RET_CODE", "SUCCESS");
		
		} catch (Exception e) {
			log.error("报文批量报送异常!", e);
			rslt.put("RET_CODE", "FAILED");
			if(e.getMessage().contains("连接错误")){
				rslt.put("ERR_MSG", "报送程序未正常启动,请联系管理员！");
			}
			return rslt;
		}
		
		return rslt;
	}
	
	/**
	 * 批量删除详情查询
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listPageDelRpt")
	public Map<String, Object> listPageDelRpt(HttpServletRequest request) {
				
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return batchCorrectService.listPageDelRpt(params);
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	public String reportType(String reportName){
		if(reportName.equals("事业单位收入支出表信息")){
			return "654";
		}else if(reportName.equals("个人授信协议信息")){
			return "224";
		}else if(reportName.equals("事业单位资产负债表信息")){
			return "644";
		}else if(reportName.equals("企业借贷交易账户信息")){
			return "414";
		}else if(reportName.equals("企业现金流量表信息")){
			return "634";
		}else if(reportName.equals("企业利润及利润分配信息")){
			return "624";
		}else if(reportName.equals("企业基本信息")){
			return "314";
		}else if(reportName.equals("企业担保账户信息")){
			return "444";
		}else if(reportName.equals("个人借贷交易账户信息")){
			return "214";
		}else if(reportName.equals("企业资产负债表信息")){
			return "614";
		}else if(reportName.equals("个人基本信息")){
			return "114";
		}else if(reportName.equals("抵质押物信息")){
			return "514";
		}else if(reportName.equals("企业授信协议信息")){
			return "424";
		}
		return null;
	}
	
}
