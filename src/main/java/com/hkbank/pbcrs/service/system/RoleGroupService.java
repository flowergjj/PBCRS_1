package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.LoginLogDao;
import com.hkbank.pbcrs.dao.system.RoleDao;
import com.hkbank.pbcrs.dao.system.RoleGroupDao;
import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.exception.EismException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleGroupService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleGroupDao roleGroupDao;

	@Autowired
	private LoginLogDao loginLogDao;
	
	private static final Logger log = LogManager
			.getLogger(RoleGroupService.class);
	
	public Map<String, Object> findAllRoleGroup(Map<String, Object> params, int pageNo, int pageSize) {
		return roleGroupDao.getAllRoleGroup(params, pageNo, pageSize);
	}
	public List<Map<String,Object>> findRoleGroupStatusCode(){
		return roleGroupDao.findRoleGroupStatusCode();
	}
	public List<Map<String,Object>> findRoleList(){
		return roleGroupDao.findRoleList();
	}
	public int saveRoleGroup(Map<String,Object> insertdata) throws Exception{
		int i = 0;
		//更新业务主表
		insertdata.put("seqNo", roleGroupDao.getGroupSeqNo());
		i = roleGroupDao.saveRoleGroupInfo(insertdata);
		if(i == 0){
			log.error("保存业务数据至表GSSJ_SYS_ROLE_GROUP_INFO出现异常, 回滚事务! 当前保存的数据为={};",
					insertdata);
			throw new EismException(
					"保存业务数据出现异常, 请联系管理人员!");
		}
		//更新日志表
		i = 0;
		insertdata.put("logSeqNo", roleGroupDao.getGroupSeqNo());
		i = roleGroupDao.saveRoleGroupInfoLog(insertdata);
		if(i == 0){
			log.error("保存业务数据至表GSSJ_SYS_ROLE_GROUP_INFO_LOG出现异常, 回滚事务! 当前保存的数据为={};",
					insertdata);
			throw new EismException(
					"保存业务数据出现异常, 请联系管理人员!");
		}
		//更新关联表
		if(!"".equals(insertdata.get("powerList").toString())){
			i = 0;
			String[] powerList = insertdata.get("powerList").toString().split(",");
			for(int j=0;j<powerList.length;j++){
				insertdata.put("roleId", powerList[j]);
//				insertdata.put("seqNo", roleGroupDao.getGroupSeqNo());
				//更新关联主表
				i = roleGroupDao.saveRoleGroup(insertdata);
				if(i == 0){
					log.error("保存业务数据至表GSSJ_SYS_ROLE_GROUP出现异常, 回滚事务! 当前保存的数据为={};",
							insertdata);
					throw new EismException(
							"保存业务数据出现异常, 请联系管理人员!");
				}
				//更新关联日志表
				i = 0;
				insertdata.put("logSeqNo", roleGroupDao.getGroupSeqNo());
				i = roleGroupDao.saveRoleGroupLog(insertdata);
				if(i == 0){
					log.error("保存业务数据至表GSSJ_SYS_ROLE_GROUP_LOG出现异常, 回滚事务! 当前保存的数据为={};",
							insertdata);
					throw new EismException(
							"保存业务数据出现异常, 请联系管理人员!");
				}
			}
		}
		return i;
	}
	
	public List<Map<String,Object>> findRoleListByGroupId(Map<String, Object> group){
		return roleGroupDao.findRoleListByGroupId(group);
	}
	
	public int updateRoleGroup(Map<String,Object> insertdata) throws Exception{
		int i = 0;
		//更新业务主表
		insertdata.put("seqNo", insertdata.get("groupId"));
		i = roleGroupDao.updateRoleGroupInfo(insertdata);
		if(i == 0){
			log.error("更新业务数据至表GSSJ_SYS_ROLE_GROUP_INFO出现异常, 回滚事务! 当前更新的数据为={};",
					insertdata);
			throw new EismException(
					"更新业务数据出现异常, 请联系管理人员!");
		}
		//更新日志表
		i = 0;
		insertdata.put("logSeqNo", roleGroupDao.getGroupSeqNo());
		i = roleGroupDao.saveRoleGroupInfoLog(insertdata);
		if(i == 0){
			log.error("更新业务数据至表GSSJ_SYS_ROLE_GROUP_INFO_LOG出现异常, 回滚事务! 当前更新的数据为={};",
					insertdata);
			throw new EismException(
					"更新业务数据出现异常, 请联系管理人员!");
		}
		//清理原有role_group关联数据
		i = 0;
		i = roleGroupDao.delRoleGroup(insertdata);
//		if(i == 0){
//			log.error("从表GSSJ_SYS_ROLE_GROUP删除业务数据出现异常, 回滚事务! 当前数据为={};",
//					insertdata);
//			throw new EisException(
//					"删除业务数据出现异常, 请联系管理人员!");
//		}
		//更新关联表
		if(!"".equals(insertdata.get("powerList").toString())){
			i = 0;
			String[] powerList = insertdata.get("powerList").toString().split(",");
			for(int j=0;j<powerList.length;j++){
				insertdata.put("roleId", powerList[j]);
//				insertdata.put("seqNo", roleGroupDao.getGroupSeqNo());
				//更新关联主表
				i = roleGroupDao.saveRoleGroup(insertdata);
				if(i == 0){
					log.error("更新业务数据至表GSSJ_SYS_ROLE_GROUP出现异常, 回滚事务! 当前更新的数据为={};",
							insertdata);
					throw new EismException(
							"更新业务数据出现异常, 请联系管理人员!");
				}
				//更新关联日志表
				i = 0;
				insertdata.put("logSeqNo", roleGroupDao.getGroupSeqNo());
				i = roleGroupDao.saveRoleGroupLog(insertdata);
				if(i == 0){
					log.error("更新业务数据至表GSSJ_SYS_ROLE_GROUP_LOG出现异常, 回滚事务! 当前更新的数据为={};",
							insertdata);
					throw new EismException(
							"更新业务数据出现异常, 请联系管理人员!");
				}
			}
		}
		return i;
	}
	
	public int deleteRoleGroup(String[] listdata,User user) throws Exception{
		int i = 0;
		for(int j=0;j<listdata.length;j++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupId", listdata[j]);
			//获取明细数据
			Map<String,Object> rgInfo = roleGroupDao.findRoleInfoByGroupId(map);
			//添加日志删除信息
			rgInfo.put("updateUser", user.getUserId());
			rgInfo.put("updateDate", new Date(System.currentTimeMillis()));
			rgInfo.put("seqNo", listdata[j]);
			rgInfo.put("logSeqNo", roleGroupDao.getGroupSeqNo());
			rgInfo.put("delFlag", "1");
			i = roleGroupDao.saveRoleGroupInfoLog(rgInfo);
			if(i == 0){
				log.error("更新业务数据至表GSSJ_SYS_ROLE_GROUP_INFO_LOG出现异常, 回滚事务! 当前更新的数据为={};",
						rgInfo);
				throw new EismException(
						"更新业务数据出现异常, 请联系管理人员!");
			}
			//删除主表信息
			i = 0;
			i = roleGroupDao.deleteRoleGroupInfo(map);
			if(i == 0){
				log.error("从表GSSJ_SYS_ROLE_GROUP_INFO删除业务数据出现异常, 回滚事务! 当前数据为={};",
						map);
				throw new EismException(
						"删除业务数据出现异常, 请联系管理人员!");
			}
			//删除关联表信息
//			i = 0;
			roleGroupDao.delRoleGroup(map);
//			if(i == 0){
//				log.error("从表GSSJ_SYS_ROLE_GROUP删除业务数据出现异常, 回滚事务! 当前数据为={};",
//						map);
//				throw new EisException(
//						"删除业务数据出现异常, 请联系管理人员!");
//			}
		}
		return i;
	}

}
