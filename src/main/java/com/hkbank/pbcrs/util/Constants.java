package com.hkbank.pbcrs.util;

/**
 * @author yangbo
 * @category 静�?变量实体�?
 * @version 0.0.1
 * @date 2012/12/26
 */
public class Constants {
	
	
	public static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
    
    /** 加密算法,可用 DES,DESede,Blowfish. */
    public static final String ALGORITHM = "DES";
    
    protected static final String  XMLFILE= "/xmlDataBase/pathConfg.xml";
    protected static final String  FILEUPLOADSIGN= "file-upload";
    protected static final String  FILEDOWNLOADSIGN= "file-download";
    protected static final String  SYSTEMSIGN= "system-path";
    protected static final String  VALUENAME= "path";
    
    protected static final String  FOLDERNAME= "xmlDataBase";
    protected static final String  FILENAME= "constants.xml";
    protected static final String  ACTION_NORMAL= "normal";
    
    public static final String MOUDLEPRO ="MOUDLE_PRO_";
    
    public static final String ASSIGNPRO = "ASSIGN_PRO_";
    
    /*** 报送状态：0代表未报送*/
    public static final String REPORTFLAG_IN="0";
    /*** 报送状态：1代表已报送*/
    public static final String REPORTFLAG_UP="1";
    
    /*** 报送状态：1代表批量调用*/
    public static final String REPORT_SOURCE_P="1";
    /*** 报送状态：2代表前端系统调用*/
    public static final String REPORT_SOURCE_S="2";
    
    /*** 报文状态：1未开始*/
    public static final String REPORT_STATE_S="1";
    /*** 报文状态：2已结束*/
    public static final String REPORT_STATE_E="2";
    
    /**
     * 数据维护：提交类型
     */
    public static final String TYPE_SUB="sub";
    /**
     * 数据维护：提交并生成更正类型
     */
    public static final String TYPE_UPDSUB="updSub";
    
}