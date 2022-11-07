package com.hkbank.pbcrs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.DataIntegrityTestService;

@Controller
@RequestMapping("/DataInteger")
public class DataIntegrityTestController {
	
	private static final Logger log = LogManager.getLogger(DataIntegrityTestController.class);
	@Autowired
	private DataIntegrityTestService service;
	
	@ResponseBody
	@RequestMapping("/listPage")
	public Map<String, Object> listPage(HttpServletRequest request) {
		
		

		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.listPage(params);		
		} catch (Exception e) {
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	

	@ResponseBody
	@RequestMapping("/infoManager")
	public Map<String, Object> infoManager(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.infoManager(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/PLNinfoManager")
	public Map<String, Object> PLNinfoManager(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.PLNinfoManager(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/ILNinfoManager")
	public Map<String, Object> ILNinfoManager(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.ILNinfoManager(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/SLNinfoManager")
	public Map<String, Object> SLNinfoManager(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.SLNinfoManager(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/indBaseAddCheck")
	public Map<String, Object> indBaseAddCheck(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.indBaseAddCheck(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/d1FirstCheckResult")
	public Map<String, Object> d1FirstCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.d1FirstCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/d1SecondCheckResult")
	public Map<String, Object> d1SecondCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.d1SecondCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/d1ThirdCheckResult")
	public Map<String, Object> d1ThirdCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.d1ThirdCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/r4FirstCheckResult")
	public Map<String, Object> r4FirstCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.r4FirstCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/r4SecondCheckResult")
	public Map<String, Object> r4SecondCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.r4SecondCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/r4ThirdCheckResult")
	public Map<String, Object> r4ThirdCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.r4ThirdCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/indCtrCheckResult")
	public Map<String, Object> indCtrCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.indCtrCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/modCheckResult")
	public Map<String, Object> modCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.modCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/baseInfoJoinCheckResult")
	public Map<String, Object> baseInfoJoinCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.baseInfoJoinCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/ctrInfoJoinCheckResult")
	public Map<String, Object> ctrInfoJoinCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.ctrInfoJoinCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/modInfoJoinCheckResult")
	public Map<String, Object> modInfoJoinCheckResult(HttpServletRequest request) {
		Map<String, Object> params = Util.parseWebParas(request);
		
		try {
			return service.modInfoJoinCheckResult(params);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("列表查询出现异常!", e);
		}
		
		return null;
	}
	
	
	
}
