package com.hkbank.pbcrs.dao.system;

import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SystemNoticeDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	public int saveNotice(Map<String, Object> params) {
		String sqlStr = "SysNotice.saveNotice";
		int i = baseDaoImpl.save(sqlStr, params);
		return i;
	}

	public Map<String, Object> noticeList(Object params, int pageNo,
			int pageSize) {
		return baseDaoImpl.pageQuery("SysNotice.noticeListCounts",
				"SysNotice.noticeList", params, pageNo, pageSize);
	}

	public Map<String, Object> getNoticeByID(Map<String, Object> params) {
		return baseDaoImpl.selectOne("SysNotice.getNoticeByID", params);
	}

	public int updateNoticeImg(Map<String, Object> params) {
		String sqlStr = "SysNotice.updateNoticeImg";
		int i = baseDaoImpl.update(sqlStr, params);
		return i;
	}

	public int updateNotice(Map<String, Object> params) {
		String sqlStr = "SysNotice.updateNotice";
		int i = baseDaoImpl.update(sqlStr, params);
		return i;
	}

	public int deleteNotice(Map<String, Object> params) {
		String sqlStr = "SysNotice.deleteNotice";
		int i = baseDaoImpl.delete(sqlStr, params);
		return i;
	}

	public Map<String, Object> getNoticeSeq() {
		return baseDaoImpl.selectOne("SysNotice.getNoticeSeq", null);
	}

	public List<Map<String, Object>> findImgList(Map<String, Object> params) {
		return baseDaoImpl.selectList("SysNotice.findImgList", params);
	}

}
