package com.hkbank.pbcrs.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.hkbank.pbcrs.mapper.PbcrsReportSendfileMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hkbank.pbcrs.mapper.PbcrsReportReceivefileMapper;
import com.hkbank.pbcrs.model.PbcrsReportReceivefile;


@Service
public class ReportReceivefileService {
	
	@Autowired
	PbcrsReportReceivefileMapper pbcrsReportReceivefileMapper;//主表

	@Autowired
	PbcrsReportSendfileMapper pbcrsReportSendfileMapper;
	
	private static final Logger log = LogManager
			.getLogger(ReportCmdService.class);
	
	public Map<String,Object> listPage(Map<String,Object> params){
		/*List<PbcrsReportInbasinfo> resultList=mapper.selectAll();
		for (PbcrsReportInbasinfo pbcrsReportInbasinfo : resultList) {
			String sgmtSeqNo=pbcrsReportInbasinfo.getBsSgmtSeqNo();
			PbcrsReportBssgmt bssgmt=bssgmtMapper.selectByPrimaryKey(sgmtSeqNo);
			pbcrsReportInbasinfo.setBsSgmt(bssgmt);
		}
		return resultList;*/
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		
		
		int total = pbcrsReportReceivefileMapper.selectPageCount(params);
		
		

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportReceivefileMapper.selectPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}
	
	public Map<String,Object> getReveiceFileList(Map<String,Object> params){
		/*List<PbcrsReportInbasinfo> resultList=mapper.selectAll();
		for (PbcrsReportInbasinfo pbcrsReportInbasinfo : resultList) {
			String sgmtSeqNo=pbcrsReportInbasinfo.getBsSgmtSeqNo();
			PbcrsReportBssgmt bssgmt=bssgmtMapper.selectByPrimaryKey(sgmtSeqNo);
			pbcrsReportInbasinfo.setBsSgmt(bssgmt);
		}
		return resultList;*/
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		
		
		int total = pbcrsReportReceivefileMapper.selectReveiceCount(params);
		
		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportReceivefileMapper.selectReveicePage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;

	}

	//上传方法
	public int uploadFile(Map<String,Object> params) throws Exception{
		int id = pbcrsReportReceivefileMapper.getKey();
		PbcrsReportReceivefile receviceFile = new PbcrsReportReceivefile();
		receviceFile.setSeqno((short)id);
		receviceFile.setReceivefilepath(FilenameUtils.getFullPath(params.get("filePath").toString()));
		receviceFile.setReceivefilename(params.get("filePath").toString());
		receviceFile.setImportDate(new Date());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		receviceFile.setImportTime(simpleDateFormat.format(new Date()));
		String fileName = FilenameUtils.getName(params.get("filePath").toString());
		fileName = fileName.substring(0,fileName.length()-5);
		pbcrsReportSendfileMapper.updateIsUpload(fileName);
		pbcrsReportReceivefileMapper.insert(receviceFile);
		log.info("ReportCmdService socket：");
		try {
			socket(id+"REPORT_RECEIVE");
		} catch (Throwable e) {
			throw new Exception("连接错误");
			
		}
		return 1;
	}

	public void socket(String reportTypeAndReport) throws Throwable {
		Properties pbcrsReportCfg = Resources
				.getResourceAsProperties("config/common/report.properties");
		String url = pbcrsReportCfg.getProperty("socket.url");
		int port = Integer.parseInt(pbcrsReportCfg.getProperty("socket.port"));
		log.info("ReportReceivefileService socket url：" + url + " port:" + port);
		Socket s = new Socket(url, port);
		OutputStream out = s.getOutputStream();
		out.write(reportTypeAndReport.getBytes("UTF-8"));
		out.flush();
		s.shutdownOutput();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));
		String line =null;
		while((line=br.readLine())!=null){
			log.info(line);
			System.out.println(line);
		}
		br.close();
		out.close();
	
		
		s.close();
	}
	
	public static void main(String[] args) {
	
	}

}
