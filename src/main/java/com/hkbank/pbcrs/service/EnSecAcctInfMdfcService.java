package com.hkbank.pbcrs.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportActlbltyinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportActucotrlinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEGuamotcltctrsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEGuarltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfidcaginfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccinfmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnaccspetrssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctcredsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnacctinfmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfactcotinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinffcssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidrecMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfidsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfmmbinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnbasinfshahodinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEncagoftrdinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnguaaccbsinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnguaracctbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnoricreinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccidcaginfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccmdfbssgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecaccmdfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctinfmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnsecacctmdfcMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGserltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGserltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuacccinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuaracctcredsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportGuarltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActlbltyinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCccinf;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEGuarltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfidcaginf;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccinfmdfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnaccspetrssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinf;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfm;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinfmdfc;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfactcotinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidrec;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfidsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfmmbinf;
import com.hkbank.pbcrs.model.PbcrsReportEnbasinfshahodinf;
import com.hkbank.pbcrs.model.PbcrsReportEncagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportEnguaaccbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnguaracctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnoricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportEnsecaccidcaginf;
import com.hkbank.pbcrs.model.PbcrsReportEnsecaccmdfbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnsecaccmdfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnsecacctinf;
import com.hkbank.pbcrs.model.PbcrsReportEnsecacctinfm;
import com.hkbank.pbcrs.model.PbcrsReportEnsecacctmdfc;
import com.hkbank.pbcrs.model.PbcrsReportGserltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGserltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportGuaracctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportGuarltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMotcltctrinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;





import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class EnSecAcctInfMdfcService implements BaseExternalSpi {
	
	@Autowired
	PbcrsReportEnsecacctinfMapper pbcrsReportEnsecacctinfMapper; //主
	
	@Autowired
	PbcrsReportEnguaracctbssgmtMapper pbcrsReportEnguaracctbssgmtMapper; //基础段
	
	@Autowired
	PbcrsReportEnguaaccbsinfsgmtMapper pbcrsReportEnguaaccbsinfsgmtMapper; //基本信息段

	@Autowired
	PbcrsReportGserltrepinfsgmtMapper pbcrsReportGserltrepinfsgmtMapper; //保账户记录信息段相关还款责任人信息段
	
	@Autowired
	PbcrsReportGserltrepymtinfMapper pbcrsReportGserltrepymtinfMapper; //担保账户记录信息段相关还款责任人信息段详细
	
	@Autowired
	PbcrsReportEGuamotcltctrsgmtMapper pbcrsReportEGuamotcltctrsgmtMapper; //抵质押列表
	
	@Autowired
	PbcrsReportGuacccinfMapper pbcrsReportGuacccinfMapper; //抵质押合同号
	
	@Autowired
	PbcrsReportGuaracctcredsgmtMapper pbcrsReportGuaracctcredsgmtMapper; //授信额度信息段
	
	@Autowired
	PbcrsReportEGuarltrepinfsgmtMapper pbcrsReportGuarltrepinfsgmtMapper; //在保责任信息段
	
	@Autowired
	PbcrsReportEnsecacctinfmMapper pbcrsReportEnsecacctinfmMapper; //修改
	
	@Autowired
	PbcrsReportEnsecacctmdfcMapper pbcrsReportEnsecacctmdfcMapper; //更正段
	
	@Autowired
	PbcrsReportEnsecaccmdfbssgmtMapper pbcrsReportEnsecaccmdfbssgmtMapper; //更正段基础段
	
	@Autowired
	PbcrsReportEnsecaccmdfsgmtMapper pbcrsReportEnsecaccmdfsgmtMapper; //更正企业授信协议更正请求记录信息
	
	@Autowired
	PbcrsReportEnsecaccidcaginfMapper pbcrsReportEnsecaccidcaginfMapper; //企业担保账户标识变更请求记录

	

	@Override
	public Object selectByPrimaryKey(String seqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByPrimaryKeyM(String seqId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertM(String selectStr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByMap(Map map) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public int updateByMapMdc(Map map) {
		// TODO Auto-generated method stub
		pbcrsReportEnsecacctmdfcMapper.updateByMapMdc(map);
//		pbcrsReportEnsecaccmdfbssgmtMapper.updateByMapMdc(map);
		pbcrsReportEnguaracctbssgmtMapper.updateByMapMdc(map);
		pbcrsReportEnguaaccbsinfsgmtMapper.updateByMapMdc(map);
		pbcrsReportGserltrepinfsgmtMapper.updateByMapMdc(map);
		pbcrsReportGserltrepymtinfMapper.updateByMapMdc(map);
//		pbcrsReportEGuamotcltctrsgmtMapper.updateByMap(map);
//		pbcrsReportGuacccinfMapper.updateByMap(map);
		pbcrsReportGuaracctcredsgmtMapper.updateByMapMdc(map);
		pbcrsReportGuarltrepinfsgmtMapper.updateByMapMdc(map);
		return 1;
	}
	


}
