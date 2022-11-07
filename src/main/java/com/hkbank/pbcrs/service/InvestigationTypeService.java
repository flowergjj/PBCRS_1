package com.hkbank.pbcrs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsInvestigationTypeMapper;
import com.hkbank.pbcrs.model.PbcrsInvestigationType;


@Service
@SuppressWarnings("all")
public class InvestigationTypeService {
	private static final Logger log = LogManager
			.getLogger(InvestigationTypeService.class);

	@Autowired
	private PbcrsInvestigationTypeMapper pbcrsInvestigationTypeMapper;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 
	 * @param mapperType 征信类型
	 * @param seqId 主键标识码
	 * @param paper 段落 按段更新时/没有填null         
	 * @return
	 * @throws Exception 
	 */
	public int changeUpdate(String mapperType, String seqId, String paper) throws Exception {
			// 1.查询该类型下对应的操作接口集合
			List<PbcrsInvestigationType> list = new ArrayList<PbcrsInvestigationType>();
			PbcrsInvestigationType pbcrsType = new PbcrsInvestigationType();
			if(mapperType!=null&&!mapperType.equals("")){
				pbcrsType.setMapperType(mapperType);
			}else{
				//传入type为空时直接返回，不执行下面逻辑
				return 0;
			}
			
			pbcrsType.setMapperPapagraph(paper);
			list = pbcrsInvestigationTypeMapper.selectByMapperType(pbcrsType);
			// 2.循环集合
			for (PbcrsInvestigationType temp : list) {

				BaseExternalSpi spi = (BaseExternalSpi)applicationContext.getBean(temp
						.getMapperName());
				/*		BaseExternalBean.getExecuterManager(temp
						.getMapperName());*/
				if (spi != null) {
					// Object obj = spi.selectByPrimaryKey(seqId);
					// if(obj != null){
					// 3.根据标识列查询对应的表记录
					// 4.根据标识列删除修改表对应记录
					try {
						spi.deleteByPrimaryKeyM(seqId);
						// 5.将查询出的记录insert入修改表
						 int cnt =spi.insertM(seqId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*if (cnt == 0)
						return cnt;*/
					// }

				} else {
					throw new Exception();
				}

			}
	

		return 1;
	}

}
