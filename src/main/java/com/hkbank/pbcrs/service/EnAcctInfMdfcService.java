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
import com.hkbank.pbcrs.mapper.PbcrsReportEnmotcltctrinfsgmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnoricreinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportEnrltrepymtinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnmmbinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMnshahodinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportMotcltctrinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportSpvathinfsgmtMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportCotainfsgmtMapper;
import com.hkbank.pbcrs.model.PbcrsReportActlbltyinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportActucotrlinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCccinf;
import com.hkbank.pbcrs.model.PbcrsReportCotainfsgmt;
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
import com.hkbank.pbcrs.model.PbcrsReportEnmotcltctrinfsgm;
import com.hkbank.pbcrs.model.PbcrsReportEnoricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsReportMnmmbinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMnshahodinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportMotcltctrinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportSpvathinfsgmt;






import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;

import com.hkbank.pbcrs.model.PbcrsReportEnbasinffcssgmt;


@Service
public class EnAcctInfMdfcService implements BaseExternalSpi {
	
	@Autowired
	PbcrsReportEnacctbssgmtMapper pbcrsReportEnAcctBsSgmtMapper; //?????????
	
	@Autowired
	PbcrsReportEnacctbsinfsgmtMapper pbcrsReportEnacctbsinfsgmtMapper; //???????????????
	
	@Autowired
	PbcrsReportEnmotcltctrinfsgmMapper pbcrsReportEnmotcltctrinfsgmMapper; //???????????????
	
	@Autowired
	PbcrsReportCccinfMapper pbcrsReportCccinfMapper; //??????????????????
	
	@Autowired
	PbcrsReportEnrltrepinfsgmtMapper pbcrsReportEnrltrepinfsgmtMapper; //??????????????????????????????
	
	@Autowired
	PbcrsReportEnrltrepymtinfMapper pbcrsReportEnrltrepymtinfMapper; //??????????????????????????????
	
	@Autowired
	PbcrsReportEnacctcredsgmtMapper pbcrsReportEnacctcredsgmtMapper; //?????????????????????
	
	@Autowired
	PbcrsReportEnoricreinfsgmtMapper pbcrsReportEnoricreinfsgmtMapper; //?????????????????????
	
	@Autowired
	PbcrsReportActlbltyinfsgmtMapper pbcrsReportActlbltyinfsgmtMapper; //?????????????????????
	
	@Autowired
	PbcrsReportEnaccspetrssgmtMapper pbcrsReportEnaccspetrssgmtMapper; //?????????????????????????????????
	
	@Autowired
	PbcrsReportEncagoftrdinfMapper pbcrsReportEncagoftrdinfMapper; //?????????????????????????????????
	
	@Autowired
	PbcrsReportEnacctinfmMapper pbcrsReportEnacctinfmMapper; //??????
	
	@Autowired
	PbcrsReportEnacctinfmdfcMapper pbcrsReportEnacctinfmdfcMapper; //????????????????????????????????????
	
	@Autowired
	PbcrsReportEnaccinfmdfbssgmtMapper pbcrsReportEnaccinfmdfbssgmtMapper; //???????????????????????????????????????
	
	@Autowired
	PbcrsReportEnaccinfmdfsgmtMapper pbcrsReportEnaccinfmdfsgmtMapper; //?????????????????????????????????
	
	@Autowired
	PbcrsReportEnaccinfidcaginfMapper pbcrsReportEnaccinfidcaginfMapper; //????????????????????????????????????????????????
	
	


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
		pbcrsReportEnacctinfmdfcMapper.updateByMapMdc(map);
		pbcrsReportEnAcctBsSgmtMapper.updateByMapMdc(map);
		pbcrsReportEnacctbsinfsgmtMapper.updateByMapMdc(map);
		pbcrsReportEnrltrepinfsgmtMapper.updateByMapMdc(map);
		pbcrsReportEnrltrepymtinfMapper.updateByMapMdc(map);
		pbcrsReportEnmotcltctrinfsgmMapper.updateByMapMdc(map);
		map.put("MotgaCltalCtrctInfType", "MO");
		pbcrsReportCccinfMapper.updateByMapMdc(map);
		pbcrsReportEnacctcredsgmtMapper.updateByMapMdc(map);
		pbcrsReportEnoricreinfsgmtMapper.updateByMapMdc(map);
		pbcrsReportActlbltyinfsgmtMapper.updateByMapMdc(map);
		pbcrsReportEnaccspetrssgmtMapper.updateByMapMdc(map);
		pbcrsReportEncagoftrdinfMapper.updateByMapMdc(map);
		return 1;
	}


}
