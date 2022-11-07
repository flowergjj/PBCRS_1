package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportFieldMapping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcrsReportFieldMappingMapper {
	/**
	 * 通过报文类型和字段名称查询
	 * @param param
	 * @return
	 */
	List<PbcrsReportFieldMapping> getMapping(Map<String,String> param);
	
	List<PbcrsReportFieldMapping> listField(Map<String,Object> param);
	
	List<Map<String,Object>> getFirstCombox(Map param);
	
	List<Map<String,Object>> getSecondCombox(Map param);

}
