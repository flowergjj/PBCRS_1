package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.PbcrsReportBatchDelInfo;
import com.hkbank.pbcrs.model.PbcrsReportBatchInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcrsReportBatchInfoMapper {
   public int insert(PbcrsReportBatchInfo batchInfo);
   
   public int insertDel(PbcrsReportBatchDelInfo batchDelInfo);
}
