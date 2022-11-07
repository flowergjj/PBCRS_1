package com.hkbank.pbcrs.dao.system;

import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EcssDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	public int obtainCONSUMER_SEQ_NO() {
		Map<String, Object> sqNO = baseDaoImpl
				.selectOne("ECSSService.obtainSEQ_ECSS_SEQ_NO");
		return Integer.parseInt(String.valueOf(sqNO.get("NEXTVAL")));
	}

	public List<Map<String, Object>> findAchivesByBusinessID(String businessid) {

		String sqlName = "ECSSService.findAchivesByBusinessID";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AC_IMG_ID", businessid);
		List<Map<String, Object>> result = baseDaoImpl.selectList(sqlName,
				params);
		return result;
	}
	
	public List<Map<String, Object>> findAchivesByBusinessCode(String bussinessCode) {
		
		String sqlName = "ECSSService.findAchivesByBusinessCode";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("BUSSINESS_CODE", bussinessCode);
		List<Map<String, Object>> result = baseDaoImpl.selectList(sqlName,
				params);
		return result;
	}

	public int saveArchives(Map<String, Object> params) {
		String sqlName = "ECSSService.saveArchives";
		int i = this.baseDaoImpl.save(sqlName, params);
		return i;
	}
	
	public int deleteArchiveByBussinessId(Map<String, Object> params) {
		String sqlName = "ECSSService.deleteArchiveByBussinessId";
		int i = this.baseDaoImpl.save(sqlName, params);
		return i;
	}
	
	public int deleteArchiveByBussinessCode(Map<String, Object> params) {
		String sqlName = "ECSSService.deleteArchiveByBussinessCode";
		int i = this.baseDaoImpl.save(sqlName, params);
		return i;
	}
}
