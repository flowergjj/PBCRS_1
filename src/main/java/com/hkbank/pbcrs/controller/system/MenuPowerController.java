package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.model.SysMenu;
import com.hkbank.pbcrs.service.system.MenuPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menuPowerController")
public class MenuPowerController {

	private Logger log = LogManager.getLogger(MenuPowerController.class);

	@Autowired
	private MenuPower menuPower;

	@RequestMapping(value = "/userMenu")
	@ResponseBody
	public List<SysMenu> getUserMenu(HttpServletRequest req,
			HttpServletResponse response) {
		String userid = req.getParameter("userid");
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		userid = user.getUserId();
		log.debug("userid=={}", userid);

		List<SysMenu> menu = menuPower.getUserMenu(userid);

		log.debug("userMenu={}", menu);

		return menu;
	}
	
	@RequestMapping(value = "/userfunc")
	@ResponseBody
	public List<Map<?, ?>> getUserfunc(HttpServletRequest req,
			HttpServletResponse response) {
		String userid = req.getParameter("userid");
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		userid = user.getUserId();
		
		log.debug("userid=={}", userid);
		if(req.getSession().getAttribute("userfunc")!=null){
			List<Map<?, ?>> menu =(List<Map<?, ?>>)req.getSession().getAttribute("userfunc");
			return menu;
		}
		else{
			List<Map<?, ?>> menu = menuPower.getUserFunc(userid);
			
			req.getSession().setAttribute("userfunc", menu);
	
			log.debug("userMenu={}", menu);
	
			return menu;
		}
	}
	
	@RequestMapping(value = "/userRightfunc")
	@ResponseBody
	public Map<?, ?> getUserRightfunc(HttpServletRequest req,
			HttpServletResponse response) {
		String funcid = req.getParameter("funcid");

		String userid = req.getParameter("userid");
		if(userid.equals("")){
			User user = (User) req.getSession().getAttribute("user");
			userid = user.getUserId();
		}
		log.debug("userid=={}", userid);
		//Map<String,String> params = new HashMap<String,String>();
		Map<String,String> ret =new HashMap<String,String>();
	/*	params.put("funcid", funcid);
		params.put("USERID", userid);
		List<Map<?, ?>> menu = menuPower.findUserRightFunc(params);

		log.debug("userMenu={}", menu);
		
		if(menu!=null&&menu.size()>0){
			ret.put("RET_CODE", "SUCCESS");
		}else{
			ret.put("RET_CODE", "FAILED");
			ret.put("RET_MSG", "没有该功能授权权限");
			
			
		}*/
		List<Map<?, ?>> menu =null;
		/*if(req.getSession().getAttribute("userfunc")!=null){
			menu =(List<Map<?, ?>>)req.getSession().getAttribute("userfunc");
			
		}
		else{*/
			menu = menuPower.getUserFunc(userid);
			
//			req.getSession().setAttribute("userfunc", menu);
	
			log.debug("userMenu={}", menu);
	
			
		//}
		for(int i=0;i<menu.size();i++){
			Map<String,Object> item = (Map<String,Object> )menu.get(i);
			String itemFunc = item.get("FUNC_ID").toString();
			if(itemFunc.equals(funcid)){
				ret.put("RET_CODE", "SUCCESS");
				return ret;
			}
			
		}
		ret.put("RET_CODE", "FAILED");
		ret.put("RET_MSG", "没有该功能授权权限");
		return ret;
	}
	

}
