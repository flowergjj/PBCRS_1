/*     */ package com.hkbank.pbcrs.tool;
/*     */ 
/*     */

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StringUtil
/*     */ {
/*     */   public static final String DEFAULT_ENCODING = "UTF-8";
/*     */   
/*     */   public static String toStrHex(String str)
/*     */     throws UtilityException
/*     */   {
/*  49 */     return toStrHex(str, "UTF-8");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toStrHex(String str, String encoding)
/*     */     throws UtilityException
/*     */   {
/*  64 */     if (isEmpty(encoding)) {
/*  65 */       encoding = "UTF-8";
/*     */     }
/*     */     try {
/*  68 */       byte[] b = str.getBytes(encoding);
/*  69 */       return toStrHex(b);
/*     */     } catch (UnsupportedEncodingException e) {
/*  71 */       throw new UtilityException("To hex string failure！", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String toStrHex(byte[] bytes)
/*     */   {
/*  85 */     return Hex.encodeHexString(bytes).toUpperCase();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] hexStringToByteArray(String str)
/*     */     throws UtilityException
/*     */   {
/*     */     try
/*     */     {
/*  99 */       return Hex.decodeHex(str.toCharArray());
/*     */     } catch (DecoderException e) {
/* 101 */       throw new UtilityException("String to byte array failure！", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String spliceStr(String[] strs, String separator)
/*     */   {
/* 113 */     if (separator == null) {
/* 114 */       separator = "";
/*     */     }
/* 116 */     return StringUtils.join(strs, separator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String spliceStr(List<String> strs, String separator)
/*     */   {
/* 127 */     if (separator == null) {
/* 128 */       separator = "";
/*     */     }
/* 130 */     return StringUtils.join(strs, separator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String padRight(String str, int len, char alexin)
/*     */   {
/* 145 */     return StringUtils.rightPad(str, len, alexin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String padLeft(String str, int len, char alexin)
/*     */   {
/* 160 */     return StringUtils.leftPad(str, len, alexin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String firStrUpperCase(String str)
/*     */   {
/* 172 */     char[] cs = str.toCharArray(); int 
/* 173 */       tmp7_6 = 0; char[] tmp7_5 = cs;tmp7_5[tmp7_6] = ((char)(tmp7_5[tmp7_6] - ' '));
/* 174 */     return String.valueOf(cs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String arrayToStr(byte[] array)
/*     */     throws UtilityException
/*     */   {
/* 187 */     return arrayToStr(array, "UTF-8");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String arrayToStr(byte[] array, String encoding)
/*     */     throws UtilityException
/*     */   {
/* 202 */     if (isEmpty(encoding)) {
/* 203 */       encoding = "UTF-8";
/*     */     }
/*     */     try {
/* 206 */       return new String(array, encoding);
/*     */     } catch (UnsupportedEncodingException e) {
/* 208 */       throw new UtilityException("Array to string failure!", e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String upperCaseAndLowerCaseStr(String str)
/*     */   {
/* 221 */     String upStr = str.toUpperCase();
/* 222 */     String lowStr = str.toLowerCase();
/* 223 */     StringBuffer buf = new StringBuffer(str.length());
/* 224 */     for (int i = 0; i < str.length(); i++) {
/* 225 */       if (str.charAt(i) == upStr.charAt(i)) {
/* 226 */         buf.append(lowStr.charAt(i));
/*     */       } else {
/* 228 */         buf.append(upStr.charAt(i));
/*     */       }
/*     */     }
/* 231 */     return buf.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String trimNull(String str)
/*     */   {
/* 242 */     return trimNull(str, "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String trimNull(String src, String deft)
/*     */   {
/* 255 */     if ((src == null) || (src.isEmpty())) {
/* 256 */       src = deft;
/*     */     }
/* 258 */     return src.trim();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isEmpty(String str)
/*     */   {
/* 269 */     if (str == null)
/* 270 */       return true;
/*     */     char[] arrayOfChar;
/* 272 */     int j = (arrayOfChar = str.toCharArray()).length; for (int i = 0; i < j; i++) { char c = arrayOfChar[i];
/* 273 */       if (!Character.isWhitespace(c)) {
/* 274 */         return false;
/*     */       }
/*     */     }
/* 277 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String allTrimStr(String str)
/*     */   {
/* 287 */     return StringUtils.remove(str, ' ');
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String leftTrimStr(String str)
/*     */   {
/* 298 */     return str.substring(str.indexOf(str.trim()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String rightTrimStr(String str)
/*     */   {
/* 310 */     return str.substring(0, str.lastIndexOf(str.trim()) + 
/* 311 */       str.trim().length());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String inputStream2String(InputStream is)
/*     */     throws UtilityException
/*     */   {
/* 326 */     return inputStream2String(is, "UTF-8");
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public static String inputStream2String(InputStream is, String encode)
/*     */     throws UtilityException
/*     */   {
			   InputStreamReader reader = null;
			    try {
			        reader = new InputStreamReader(is, encode);
			    } catch (UnsupportedEncodingException e1) {
			        e1.printStackTrace();
			    }
			    BufferedReader br = new BufferedReader(reader);
			    StringBuilder sb = new StringBuilder();
			    String line = "";
			    try {
			        while ((line = br.readLine()) != null) {
			            sb.append(line);
			        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    return sb.toString();
/*     */   }
/*     */   
/*     */   public static boolean isEncoding(byte[] bytes, String encoding)
/*     */     throws UtilityException
/*     */   {
/*     */     try
/*     */     {
/* 369 */       byte[] transBytes = new String(bytes, encoding).getBytes(encoding);
/* 370 */       return ArrayUtils.isEquals(bytes, transBytes);
/*     */     } catch (UnsupportedEncodingException e) {
/* 372 */       throw new UtilityException("Unsupported encoding!", e);
/*     */     }
/*     */   }
/*     */ }


