package com.hkbank.pbcrs.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class StringUtil {

	private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

	static {
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	private StringUtil() {

	}

	/**
	 * 判断是否为空，空返回true
	 * 
	 * @param str
	 *            字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		if (null == str)
			str = "";
		return "".equals(str);
	}

	/**
	 * 判断是否不为空，不空返回true
	 * 
	 * @param str
	 *            字符串
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 函数名称：arrayToStr 函数功能:把数组转换为字符串列表
	 * 
	 * @param array1
	 *            数组
	 * @return 用","隔开的字符串列表
	 */
	public static String arrayToStr(String[] array1) {
		String sResult = "";
		if (array1 == null) {
			return sResult;
		}
		for (int i = 0; i < array1.length; i++) {
			if ("".equals(sResult)) {
				sResult = array1[i];
			} else {
				sResult += "," + array1[i];
			}
		}
		return sResult;
	}

	/**
	 * 函数名称：arrayToStr 函数功能:把数组转换为字符串列表
	 * 
	 * @param array1
	 *            数组
	 * @param splitChar
	 *            分隔符
	 * @return 用splitChar隔开的字符串列表
	 */
	public static String arrayToStr(String[] array1, String splitChar) {
		String sResult = "";
		if (array1 == null) {
			return sResult;
		}
		for (int i = 0; i < array1.length; i++) {
			if ("".equals(sResult)) {
				sResult = array1[i];
			} else {
				sResult += splitChar + array1[i];
			}
		}
		return sResult;
	}

	/**
	 * if aInStr is null,then return defaultStr
	 * 
	 * @param aInObj
	 *            输入字符串
	 * @param defaultStr
	 *            默认字符串
	 * @return if aInStr is null,then return defaultStr
	 */
	public static String getTrimString(Object aInObj, String defaultStr) {
		if (aInObj == null) {
			return defaultStr.trim();
		} else {
			return aInObj.toString().trim();
		}
	}

	/**
	 * default string is blank.
	 * 
	 * @param aInobj
	 *            输入字符串
	 * 
	 * @return default string is blank.
	 * 
	 */
	public static String getTrimString(Object aInobj) {
		return getTrimString(aInobj, "");
	}

	/**
	 * trim全角空格
	 * 
	 * @param val
	 * @return
	 */
	public static String trimSpc(String val) {
		if (val == null) {
			return "";
		} else {
			val = val.trim();
		}
		int iHead = 0;
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) == ' ' || val.charAt(i) == '　') {
				iHead++;
			} else {
				break;
			}
			}
		String valUse = val.substring(iHead);
		if (null != valUse) {
			int iEnd = valUse.length();
			for (int i = valUse.length() - 1; i >= 0; i--) {
				if (valUse.charAt(i) == ' ' || valUse.charAt(i) == '　') {
					iEnd--;
				} else {
					break;
				}
			}
			valUse = valUse.substring(0, iEnd);
		}
		return valUse;
	}
	
	
	 public static String rightTrim(String str) {
		    String regex = "(.*\\S+)(\\s+$)";
		    Pattern p = Pattern.compile(regex);
		    Matcher m = p.matcher(str);
		    if (m.matches()) {
		       str = m.group(1);
		    }
		  return str;
	}
	 
	/**
	 * 字符串左边补字符
	 * 
	 * @param origin
	 *            输入字符串
	 * @param total
	 *            字符串总长度
	 * @param pad
	 *            补充字符
	 * @return 字符串左边补字符
	 */
	public static String lPadString(String origin, int total, String pad) {
		String temp;
		StringBuffer tempBF = new StringBuffer();
		temp = (origin == null) ? "" : getTrimString(origin);

		for (int i = 0; i < total - getTrimString(origin).length(); i++) {
			tempBF.append(pad);
		}
		tempBF.append(temp);
		return tempBF.toString();

	}

	/**
	 * 去除所有空格,包括中间的
	 * 
	 * @param str
	 *            输入字符串
	 * @return 去除所有空格,包括中间的
	 */
	public static String removeAllSpace(String str) {
		return str.replaceAll("\\s+", "");
	}

	/**
	 * 折扣显示转换
	 * 
	 * @param discount
	 *            输入字符串
	 * @return 转换后的折扣(例: 0.98 -> 98)
	 */
	public static String discountToString(String discount) {
		if (discount != null && !"".equals(discount)) {
			String value = new Float(discount) * 100 + "";
			return value.substring(0, value.indexOf("."));
		} else {
			return "";
		}
	}

	/**
	 * 将页面上折扣转换成存储格式（缩小100倍）
	 * 
	 * @param value
	 *            输入折扣
	 * @return 转换后的折扣(例: 98 -> 0.98)
	 */
	public static Float toDiscount(Float value) {
		return Float.parseFloat(value * 0.01 + "");
	}

	/**
	 * String 转换 数组
	 * 
	 * @param str
	 *            字符串
	 * @param sep
	 *            分割字符
	 * @return 转换后的数组
	 */
	public static String[] str2Array(String str, String sep) {
		StringTokenizer token = null;
		String[] array = null;

		// check
		if (str == null || sep == null) {
			return null;
		}

		// get string array
		token = new StringTokenizer(str, sep);
		array = new String[token.countTokens()];
		for (int i = 0; token.hasMoreTokens(); i++) {
			array[i] = token.nextToken();
		}

		return array;
	}

	/**
	 * 去掉字符串2端的空白字符
	 * 
	 * @param str
	 *            输入字符串
	 * @return 去掉字符串2端的空白字符
	 */
	public static String trim(String str) {
		try {
			if (str == null) {
				return null;
			}
			str = str.trim();
			if (str == null) {
				return null;
			}
			return str;
		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}

	/**
	 * list<String> 转换SQLString<br>
	 * 
	 * @param inputList
	 *            输入的List型
	 * @return String
	 */
	public static String listToSQLString(List<String> inputList) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < inputList.size(); i++) {
			// output.append("'");
			if (StringUtil.isNotEmpty(inputList.get(i))) {
				output.append(inputList.get(i));
				// output.append("'");
				if (i != inputList.size() - 1) {
					output.append(",");
				}
			}
		}
		return output.toString();
	}

	/***
	 * 判断字符串是否数字
	 * 
	 * @param str
	 *            输入字符串
	 * @return flag 是否是数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 返回字符串各个字符的首个大写常用汉语拼音
	 * 
	 * @param chars
	 *            输入字符串
	 * @return 字符串各个字符的首个大写常用汉语拼音
	 */
	public static String toShortPinyin(String chars) {
		if (null == chars || "".equals(chars)) {
			return "";
		}
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < chars.length(); i++) {
			try {
				if ((chars.charAt(i) >= 0x4e00) && (chars.charAt(i) <= 0x9fbb)) {
					String[] ss = PinyinHelper.toHanyuPinyinStringArray(chars.charAt(i), format);
					if (ss.length > 0) {
						res.append(ss[0].substring(0, 1));
					}
				} else {
					res.append(chars.charAt(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
				res = new StringBuffer();
				break;
			}
		}
		return res.toString();
	}

	/**
	 * 返回字符串的大写汉语常用拼音
	 * 
	 * @param chars
	 *            输入字符串
	 * @return 字符串的大写汉语常用拼音
	 */
	public static String toFullPinyin(String chars) {
		if (null == chars || "".equals(chars)) {
			return "";
		}
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < chars.length(); i++) {
			try {
				if ((chars.charAt(i) >= 0x4e00) && (chars.charAt(i) <= 0x9fbb)) {
					String[] ss = PinyinHelper.toHanyuPinyinStringArray(chars.charAt(i), format);
					if (ss.length > 0) {
						res.append(ss[0]);
					}
				} else {
					res.append(chars.charAt(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
				res = new StringBuffer();
				break;
			}
		}
		return res.toString();
	}

	/**
	 * if str is null,then return defaultValue
	 * 
	 * @param str
	 *            输入字符串
	 * @param defaultValue
	 *            默认值
	 * @return if str is null,then return defaultValue
	 */
	public static Integer toInteger(String str, Integer defaultValue) {
		Integer value = toInteger(str);
		return value == null ? defaultValue : value;
	}

	/**
	 * if str is null,then return null
	 * 
	 * @param str
	 *            输入字符串
	 * @return if str is null,then return null
	 */
	public static Integer toInteger(String str) {
		if (isEmpty(str))
			return null;
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * if str is null,then return defaultValue
	 * 
	 * @param str
	 *            输入字符串
	 * @param defaultValue
	 *            默认值
	 * @return if str is null,then return defaultValue
	 */
	public static Double toDouble(String str, Double defaultValue) {
		Double value = toDouble(str);
		return value == null ? defaultValue : value;
	}

	/**
	 * if str is null,then return null
	 * 
	 * @param str
	 *            输入字符串
	 * @return if str is null,then return null
	 */
	public static Double toDouble(String str) {
		if (isEmpty(str))
			return null;
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * return boolean
	 * 
	 * @param str
	 *            输入字符串
	 * @return 字符串是否是true
	 */
	public static boolean isTrue(String str) {
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将list转化为字符串
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List<? extends Object> list, String... separator) {
		String s = separator.length > 0 ? separator[0] : "/";
		StringBuilder result = new StringBuilder();
		;

		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				result.append(s);
			}
			result.append(list.get(i));
		}

		return result.toString();
	}

	/**
	 * 汉字转化为拼音
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYin(String zhongwen) throws BadHanyuPinyinOutputFormatCombination {

		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				zhongWenPinYin += capitalize(pinYin[0]);
			} else {
				zhongWenPinYin += chars[i];
			}
		}
		;
		return StringUtils.delete(zhongWenPinYin.toLowerCase(), " ");
	}

	// 支持多音字 如 空调 返回 kongkongdiaotiao
	public static String getPinYinEx(String zhongwen) throws BadHanyuPinyinOutputFormatCombination {
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());
			// 当转换不是中文字符时,返回null
			Map<String, Boolean> identical = new HashMap<String, Boolean>();
			if (pinYin != null) {
				for (String py : pinYin) {
					if (py != null) {
						Boolean exits = identical.get(py);
						if (exits == null) {
							zhongWenPinYin += py;
							identical.put(py, true);
						}
					}
				}
			} else {
				zhongWenPinYin += chars[i];
			}
		}
		return zhongWenPinYin;
	}

	/**
	 * Default Format 默认输出格式
	 * 
	 * @return
	 */
	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_V);// u显示
		return format;
	}

	/**
	 * Capitalize 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		char ch[];
		ch = s.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		String newString = new String(ch);
		return newString;
	}
	
	 public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower)  
	   {  
	       String buff = "";  
	       Map<String, String> tmpMap = paraMap;  
	       try  
	       {  
	           List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());  
	           // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）  
	           Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()  
	           {  
	  
	               @Override  
	               public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)  
	               {  
	                   return (o1.getKey()).toString().compareTo(o2.getKey());  
	               }  
	           });  
	           // 构造URL 键值对的格式  
	           StringBuilder buf = new StringBuilder();  
	           for (Map.Entry<String, String> item : infoIds)  
	           {  
	               if (!StringUtil.isEmpty(item.getKey()))  
	               {  
	                   String key = item.getKey();  
	                   String val = item.getValue();  
	                   if (urlEncode)  
	                   {  
	                       val = URLEncoder.encode(val, "utf-8");  
	                   }  
	                   if (keyToLower)  
	                   {  
	                       buf.append(key.toLowerCase() + "=" + val);  
	                   } else  
	                   {  
	                       buf.append(key + "=" + val);  
	                   }  
	                   buf.append("&");  
	               }  
	  
	           }  
	           buff = buf.toString();  
	           if (buff.isEmpty() == false)  
	           {  
	               buff = buff.substring(0, buff.length() - 1);  
	           }  
	       } catch (Exception e)  
	       {  
	          return null;  
	       }  
	       return buff;  
	   }  
	 
	 public static byte[] hexStringToBytes(String hexString) {
	        if (hexString == null || hexString.equals("")) {
	            return null;
	        }
	        hexString = hexString.toUpperCase();
	        int length = hexString.length() / 2;
	        char[] hexChars = hexString.toCharArray();
	        byte[] d = new byte[length];
	        for (int i = 0; i < length; i++) {
	            int pos = i * 2;
	            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	            
	        }
	        return d;
	    }
	 
	 public static String getImpage(String url) {
			String imgFile =url;
			InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try   
	        {  
	            in = new FileInputStream(imgFile);          
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
	        }        
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }

	        finally{
	        	if(in!=null){
	        		try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }	
	        //对字节数组Base64编码  
	        BASE64Encoder encoder = new BASE64Encoder();
	        if(data!=null)
	        	return encoder.encode(data);
	        else
	        	return "";
		}
	 
	 private static byte charToByte(char c) {
	        return (byte) "0123456789ABCDEF".indexOf(c);
	    }
	 
	 public static File getFileFromBytes(String content,String path){
		 byte[] b=content.getBytes();
		 BufferedOutputStream stream = null;
		 String sss= (new SimpleDateFormat("SSS")).format(new Date());
		 File file = null;
		 try{
			 file = new File(path+sss+".data");
			 FileOutputStream fstream = new FileOutputStream(file);
			 stream = new BufferedOutputStream(fstream);
			 stream.write(b);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		 }
		 return file;
		 
	 }
	 
	 public static void writeBigStringFile(String content,String filePath) throws IOException{
		 if(content.length()<=500000){
			 byte[] b=content.getBytes();
			 BufferedOutputStream stream = null;
			 File file = null;
			 try{
				 file = new File(filePath);
				
				 FileOutputStream fstream = new FileOutputStream(file);
				 stream = new BufferedOutputStream(fstream);
				 stream.write(b);
				 
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				if(stream!=null){
					try {
						stream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			 }
		 }
		 else{
			 List<String> contents = getStrList(content,500000);
			 for(int i=0;i<contents.size();i++){
				 byte[] b=contents.get(i).getBytes();
				 BufferedOutputStream stream = null;
				 File file = null;
				 try{
					 file = new File(filePath);
					 FileOutputStream fstream = new FileOutputStream(file,true);
					 stream = new BufferedOutputStream(fstream);
					 stream.write(b);
					 
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					if(stream!=null){
						try {
							stream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
				 }
			 }
		 }
	 }
	 
	 public static List<String> getStrList(String inStr,int length){
		 int size = inStr.length()/length;
		 if(inStr.length()%length!=0){
			 size+=1;
		 }
		 return getStrList(inStr,length,size);
	 }
	 
	 public static List<String> getStrList(String inStr,int length,int size){
		 List<String> list = new ArrayList<String>();
		 for(int i=0;i<size;i++){
			String sStr= subString(inStr,i*length,(i+1)*length);
			list.add(sStr);
		 }
		 return list;
	 }
	 
	 public static String subString(String inStr,int b,int e){
		 if(b>inStr.length()){
			 return null;
		 }
		 if(e>inStr.length()){
			 return inStr.substring(b,inStr.length());
		 }else{
			 return inStr.substring(b,e);
		 }
	 }
	 
	  public static String IdCar18to15(String idCard){  
	        idCard = idCard.trim();  
	        StringBuffer idCard15 =new StringBuffer(idCard);  
	        if(idCard!=null&&idCard.length()==18){  
	            idCard15.delete(17,18);  
	            idCard15.delete(6,8);  
	        }  
	        return idCard15.toString();  
	    }  


	 public static String IdCar15to18(String idCard){  
	        idCard = idCard.trim();  
	        StringBuffer idCard18 =new StringBuffer(idCard);  
	        //加权因子  
	        //校验码值  
	        char[]  checkBit = {'1','0','X','9','8','7','6','5','4','3','2'};  
	        int sum = 0;  
	        if(idCard!=null&&idCard.length()==15){  
	            idCard18.insert(6, "19");  
	            for(int index=0;index<idCard18.length();index++){  
	                char c  = idCard18.charAt(index);  
	                int ai = Integer.parseInt(new  Character(c).toString());  
	                //加权因子的算法  
	                int Wi = ((int)Math.pow(2, idCard18.length()-index))%11;  
	                sum = sum+ai*Wi;  
	            }  
	            int indexOfCheckBit = sum%11; //取模  
	            idCard18.append(checkBit[indexOfCheckBit]);  
	              
	        }  
	        return idCard18.toString();  
	    }  
	
	public static void main(String[] args) throws Exception{
		/*File f = new File("D:\\d\\");
		//String ff="D10025210H00052019061311000010_PART_";
		File[] fileArray=f.listFiles();
		File tfile = new File("D:\\d\\D10025210H00052019061311000010.txt");
		for(int i=0;i<fileArray.length;i++){
			FileInputStream in = new FileInputStream(fileArray[i]);
			
			FileOutputStream out = new FileOutputStream(tfile,true);
			byte[] buffer = new byte[16];
			int num;
			while((num=in.read(buffer))!=-1){
				out.write(buffer,0,num);
			}
			in.close();
			out.close();
			
			
			
		}*/
	/*	String s="1222222";
		BigDecimal bd = new BigDecimal(s);
		
		List<String> slist=getStrList(s,3);
		String res="";
		for(int i=0;i<slist.size();i++){
			if(i==slist.size()-1)
				res=res+slist.get(i);
			else
				res=res+slist.get(i)+",";
		}
		
		NumberFormat nf = new DecimalFormat("###,###");
		System.out.println(nf.format(bd));
		
		String aaa ="一十一";
		System.out.println(aaa.indexOf("一十"));*/
		/*String en = "22222";
		BASE64Encoder encoder = new BASE64Encoder();
		String enStr = encoder.encode(en.getBytes("UTF-8"));
		System.out.println( enStr);
		enStr="MjIyMjIK";
		 BASE64Decoder decoder = new BASE64Decoder();
      	 System.out.println( new String(decoder.decodeBuffer(enStr),"UTF-8"));*/
		/*int count=139;
		int page = count/100;
		System.out.println( page);
		for(int i=0;i<page;i++){
			for(int j=0;j<100;j++){
				int index = i*100+j;
				System.out.println(index);
			}
		}
		for(int i=0;i<=count-page*100;i++){
			System.out.println(page*100+i);
		}*/
		String sgmtid= "FILE";
		String[] sgmtids = sgmtid.split(",");
		for(int i=0;i<sgmtids.length;i++){
			System.out.println(sgmtids[i]);
		}
		//System.out.println( page);
		
	}
		/*File txtFile = new File("F:/Hans/Project/股权管理系统/股权相关业务清单/股权相关业务清单/PXTJ1229GR.TXT");
		 FileInputStream in = new FileInputStream(txtFile);
         
         BufferedReader br = new BufferedReader(new InputStreamReader(in,"GBK"));
         String lineTxt = null;
         int linenumber=0;
         int sumlinenumber=0;
         int remarklinenumber=0;
         while((lineTxt=br.readLine())!=null){
         	linenumber++;
         	if(linenumber==6){
         		String endYear=lineTxt.substring(43,47);
         		String endMonth = lPadString(lineTxt.substring(48,50).trim(),2,"0");
         		String endDay = lPadString(lineTxt.substring(51,53).trim(),2,"0");
         		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
         		Date endDate = sf.parse(endYear+endMonth+endDay);
         		System.out.println("endDate:"+endDate);
         		    
         	}
         	if(linenumber==8){
         		String endYear=lineTxt.substring(58,62);
         		String endMonth = lPadString(lineTxt.substring(63,65).trim(),2,"0");
         		String endDay = lPadString(lineTxt.substring(66,68).trim(),2,"0");
         		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
         		Date printDate = sf.parse(endYear+endMonth+endDay);
         		System.out.println("printDate:"+printDate);
         	}
         	if(linenumber>=12){
         		if(lineTxt.indexOf('-')>=0){
         			if(sumlinenumber==0)
         				sumlinenumber=linenumber;
         			else
         				remarklinenumber=linenumber;
         		}
         		if(sumlinenumber==0){
         			//数据行
         			lineTxt=lineTxt.replaceAll(" +"," ");
         			String[] datas = lineTxt.split(" ");
         			StringBuffer sb = new StringBuffer();
         			sb.append("序号:").append(datas[1])
         			.append("年度:").append(datas[2])
         			.append("登记日期:").append(datas[3])
         			.append("派息笔数:").append(datas[4])
         			.append("派息股数:").append(datas[5])
         			.append("派息金额:").append(datas[6])
         			.append("已领笔数:").append(datas[7])
         			.append("已领金额:").append(datas[8])
         			.append("未领笔数:").append(datas[9])
         			.append("未领金额:").append(datas[10]);
         			System.out.println(sb.toString());
         		}else if(remarklinenumber==0){
         			//加总行
         			if(linenumber>sumlinenumber){
         				lineTxt=lineTxt.replaceAll(" +"," ");
             			String[] datas = lineTxt.split(" ");
             			StringBuffer sb = new StringBuffer();
             			sb.append("合计             ")
             			.append("派息笔数:").append(datas[1])
             			.append("派息股数:").append(datas[2])
             			.append("派息金额:").append(datas[3])
             			.append("已领笔数:").append(datas[4])
             			.append("已领金额:").append(datas[5])
             			.append("未领笔数:").append(datas[6])
             			.append("未领金额:").append(datas[7]);
             			System.out.println(sb.toString());
         			}
         		}else{
         			//备注行
         			if(linenumber>remarklinenumber){
         				System.out.println(lineTxt);
         			}
         		}
         		
         	}
         }
         br.close();
         in.close();
	}*/

	

	
	 
	 /*public static void main(String[] args){
	        String s = "              ";
	        System.out.println("a" + s.replaceAll(" +"," ") + "b");
	        System.out.println("a" + s.replace(" +"," ") + "b");
	    }*/
	
}
