package com.hkbank.pbcrs.service;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportModMapper;
import com.hkbank.pbcrs.mapper.PbcrsSysReportconfigMapper;

@Service
public class PbcrsReportModService {
	@Autowired
	PbcrsReportModMapper pbcrsReportModMapper;
	@Autowired
	private ReportCmdService service;
	
	//列表
	public Map<String,Object> listPage(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportModMapper.selectAllbyContCount(params);

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
				list = pbcrsReportModMapper.selectAllbyContPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		}

	public Map<String, Object> InlistPage(Map<String, Object> params) {
		/* String path;
		try {
			path = this.getClass().getResource("/").toURI().getPath();
			 path=path.substring(0,path.indexOf("WEB-INF"));
			 System.out.println(path);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		   
		   
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportModMapper.selectInListCount(params);

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
			list = pbcrsReportModMapper.selectInListPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
	} 
	public Map<String, Object> InDeletelistPage(Map<String, Object> params) {
	   
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportModMapper.selectDeleteInListCount(params);

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
			list = pbcrsReportModMapper.selectDeleteInListPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
	} 
	
	//企业整笔删除列表
	public Map<String,Object> listPageDel(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportModMapper.selectAllbyContCountDel(params);

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
				list = pbcrsReportModMapper.selectAllbyContPageDel( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		}
	
	
	//企业更正列表
	public Map<String,Object> listPageUpdSub(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportModMapper.selectAllbyContCountUpdSub(params);

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
				list = pbcrsReportModMapper.selectAllbyContPageUpdSub( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		}
	public Map<String, Object> updSublistPage(Map<String, Object> params) {
		/* String path;
		try {
			path = this.getClass().getResource("/").toURI().getPath();
			 path=path.substring(0,path.indexOf("WEB-INF"));
			 System.out.println(path);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		   
		   
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportModMapper.selectUpdSubListCount(params);

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
			list = pbcrsReportModMapper.selectUpdSubListPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
	} 
	
	//列表
		public Map<String,Object> getLogInfo(Map<String,Object> params){
				
				int pageNo = MapUtils.getIntValue(params, "page");
				int pageSize = MapUtils.getIntValue(params, "rows");
				
				int skip = (pageNo - 1) * pageSize;

				String countKey;
				/* 2. 计算总记录数 */
				int total = pbcrsReportModMapper.selectLogInfoCount(params);

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
					list = pbcrsReportModMapper.selectLogInfoPage( params);
				}
				Map<String, Object> rslt = new HashMap<String, Object>();
				rslt.put("total", total);
				rslt.put("rows", list);

				return rslt;
				
			}
	
	public Map<String, Object> checkMsg(Map<String,Object> params){
			   
			 return pbcrsReportModMapper.checkMsg(params);
			
		}

	
	public Map<String, Object> byInParagraphDeletePage(Map<String, Object> params) {

		   
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportModMapper.byInParagraphDeleteCount(params);

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
			list = pbcrsReportModMapper.byInParagraphDeleteListPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
	
	}
	
	public Map<String, Object> byEnParagraphDeletePage(Map<String, Object> params) {

		   
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportModMapper.byEnParagraphDeleteCount(params);

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
			list = pbcrsReportModMapper.byEnParagraphDeleteListPage( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
	
	}
}
