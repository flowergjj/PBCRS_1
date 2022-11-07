package com.hkbank.pbcrs.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;

public class ServiceTest {
	public static void main(String[] args) throws Exception {
		System.out.println(new Date());
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"config/spring/applicationContext.xml");
		System.out.println(new Date());
		
	/*	
		EnBasInfBsSgmtService service =ac.getBean(EnBasInfBsSgmtService.class);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("page", 1);
		params.put("rows", 20);
		Map<String,Object> map =service.getEnBaseInfoListPage(params);
		System.out.println(map);*/
	/*	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("Nationality","Nationality");
		map.put("RegAdd","RegAdd");
		map.put("AdmDivOfReg","AdmDivOfReg");
		map.put("EstablishDate","EstablishDate");
		map.put("BizEndDate","BizEndDate");
		map.put("BizRange","BizRange");
		map.put("EcoIndusCate","EcoIndusCate");
		map.put("EcoType","EcoType");
		map.put("EntScale","EntScale");
		map.put("FcsInfoUpDate","FcsInfoUpDate");
		Object obj = PbcrsReportEnbasinffcssgmt.class.newInstance();
		
		//PbcrsReportEnbasinffcssgmt enbasinffcssgmt = new PbcrsReportEnbasinffcssgmt();
		//BeanUtils.populate(obj, map);
		obj = map2Object(map,PbcrsReportEnbasinffcssgmt.class);
		System.out.println(obj);*/

	}
	
	public static Object map2Object(Map<String,Object> map ,Class<?> clz) throws Exception{
		Object obj = clz.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field:fields){
			int mod = field.getModifiers();
			if(Modifier.isStatic(mod)||Modifier.isFinal(mod)){
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
		
	}
}
