package com.hkbank.pbcrs.mapper;

import com.hkbank.pbcrs.model.DataComparisonEnd;

import java.util.List;
import java.util.Map;

public interface DataComparisonEndMapper {
	public List<DataComparisonEnd> getEntCompare(Map<String,Object> params);
	public void addCompare(Map<String,Object> params);
	public List<Map<String,Object>> dataList(Map<String,Object> params);
	public int getCount(Map<String,Object> params);
	
	public List<Map<String,Object>> dataDetailList(Map<String,Object> params);
	public int getDetailCount(Map<String,Object> params);
	
	public int upd(Map<String,Object> params);
	
	  public  List<DataComparisonEnd> Dc(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> Gu(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> Sx(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> Dzy(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> DcInter(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> GuInter(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> SxInter(Map<String, Object> paramMap);
	  
	  public  List<DataComparisonEnd> DzyInter(Map<String, Object> paramMap);
	  
	  public void addDc(Map<String,Object> params);
	  
	  public void addGu(Map<String,Object> params);
	  
	  public void addSx(Map<String,Object> params);
	  
	  public void addDzy(Map<String,Object> params);
	  
	  public void addDcInter(Map<String,Object> params);
	  
	  public void addGuInter(Map<String,Object> params);
	  
	  public void addSxInter(Map<String,Object> params);
	  
	  public void addDzyInter(Map<String,Object> params);
	  
	  
		public List<Map<String,Object>> dataDetailListCompare(Map<String,Object> params);
		public int getDetailCompareCount(Map<String,Object> params);

		public void callPartition(Map<String,Object> params);
}
