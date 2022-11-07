package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.OrgSrevice;
import com.hkbank.pbcrs.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orgManage")
public class OrgManageController {
	
	private static final Logger log = LogManager.getLogger(OrgManageController.class);
	
	@Autowired
	private OrgSrevice orgService;

	@RequestMapping(value = "/orgList")
	@ResponseBody
	public Map<String,Object> getRoleList(HttpServletRequest req,HttpServletResponse response){
		String orgID = req.getParameter("queryOrgID");
		String orgName = req.getParameter("queryOrgName");
		
		Map<String,Object> querydata = new HashMap<String,Object>();
		querydata.put("orgID", orgID);
		querydata.put("orgName", orgName);
		
		int pageNo = Integer.parseInt(req.getParameter("page"));
		int pageSize = Integer.parseInt(req.getParameter("rows"));
		
		return orgService.findAllOrg(querydata, pageNo, pageSize);
		
	}
	
	@RequestMapping(value="/saveOrUpdateOrg")
	@ResponseBody
	public Map<String,String> saveOrUpdateOrgInfo(HttpServletRequest req,HttpServletResponse response){
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		String updateUser = user.getUserName();
		
		String orgID = req.getParameter("ORGID");
		String orgName = req.getParameter("ORGNAME");
		String relativeOrgID = req.getParameter("RELATIVEORGID");
		String adminRegion = req.getParameter("ADMINREGION");
		String address = req.getParameter("ADDRESS");
		String postcode = req.getParameter("POSTCODE");
		String type = req.getParameter("UPDATETYPE");
		
		Map<String,String> map = new HashMap<String,String>();
		Map<String,Object> insertdata = new HashMap<String,Object>();
		insertdata.put("orgID", orgID);
		insertdata.put("orgName", orgName);
		insertdata.put("relativeOrgID", relativeOrgID);
		insertdata.put("adminRegion", adminRegion);
		insertdata.put("address", address);
		insertdata.put("postcode", postcode);
		insertdata.put("delFlag","0");
		insertdata.put("updateUser",updateUser);
		insertdata.put("updateDate", new Date(System.currentTimeMillis()));
		
		if("save".equals(type)){
			int orgcounts = orgService.getOrgCountsById(insertdata);
			if( 0 == orgcounts ){
				int insertNum = orgService.saveOrg(insertdata);
				if( 1 != insertNum ){
					map.put("success", "");
					map.put("error", "机构表信息插入失败，请联系科技人员！");
				}else{
					insertNum = orgService.saveOrgLog(insertdata);
					
					if(1 != insertNum){
						map.put("success", "");
						map.put("error", "机构日志表信息插入失败，请联系科技人员！");
					}else{
						map.put("success", "success");
						map.put("error", "");
					}
				}
			}else{
				map.put("success", "");
				map.put("error", "该机构已存在，请检查！");
			}
		}else if("update".equals(type)){
			int insertNum = orgService.updateOrgInfo(insertdata);
			if( 1 != insertNum ){
				map.put("success", "");
				map.put("error", "机构信息更新失败，请联系科技人员！");
			}else{
				insertNum = orgService.saveOrgLog(insertdata);
				if(1 != insertNum){
					map.put("success", "");
					map.put("error", "机构日志表信息插入失败，请联系科技人员！");
				}else{
					map.put("success", "success");
					map.put("error", "");
				}
			}
		}
		return map;
	}
	
	@RequestMapping(value="/getOrgInfoById")
	@ResponseBody
	public Map<String,Object> getOrgInfoById(HttpServletRequest req,HttpServletResponse response){
		String orgID = req.getParameter("orgID");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgID", orgID);
		return orgService.getOrgInfoById(map);
	}
	
	@RequestMapping(value="/delOrChangeOrg")
	@ResponseBody
	public Map<String,String> delOrChangeOrg(HttpServletRequest req,HttpServletResponse response){
		int n=0;
		Map<String,String> rt = new HashMap<String,String>();
		String type = req.getParameter("type");
		String a = req.getParameter("listdata");
		String[] listdata = a.split(",");
		
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		if("del".equals(type)){
			for(int i=0;i<listdata.length;i++){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("orgID",listdata[i]);
				map.put("updateUser",user.getUserName());
				
				n += orgService.deleteOrg(map);
			}
			if( n != listdata.length ){
				rt.put("success", "");
				rt.put("error", "删除机构失败，请联系科技人员！");
			}else{
				rt.put("success", "success");
				rt.put("error", "");
				
			}
		}
		return rt;
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrgTree")
	public void getOrgTree(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = Util.parseWebParas(request);
		log.debug("接收到获取组织树状请求! 请求参数列表={}", params);
		
		try {
			String resultJson = orgService.getOrgTree(params);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(resultJson);
		} catch (Exception e) {
			log.error(e);
		}
	}

}
