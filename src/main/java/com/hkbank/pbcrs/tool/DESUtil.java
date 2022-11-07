/*     */ package com.hkbank.pbcrs.tool;
/*     */ 
/*     */

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

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
/*     */ public final class DESUtil
/*     */ {
/*     */   public static final String DES_MODE_CBC = "CBC";
/*     */   public static final String DES_MODE_ECB = "ECB";
/*     */   public static final String DES_MODE_CFB = "CFB";
/*     */   public static final String DES_MODE_OFB = "OFB";
/*     */   public static final String DES_PADDING_NO = "NoPadding";
/*     */   public static final String DES_PADDING_PKCS5 = "PKCS5Padding";
/*     */   public static final String DES_PADDING_PKCS7 = "PKCS7Padding";
/*  43 */   private static final byte[] DEFAULT_IVBYTE = { 1, 2, 3, 4, 5, 6, 7, 8 };
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
/*     */   public static String encrypt(String data, String key, String encoding)
/*     */     throws UtilityException
/*     */   {
/*  63 */     if (StringUtil.isEmpty(encoding)) {
/*  64 */       encoding = "UTF-8";
/*     */     }
/*  66 */     byte[] datas = null;
/*     */     try {
/*  68 */       datas = data.getBytes(encoding);
/*     */     } catch (UnsupportedEncodingException e) {
/*  70 */       throw new UtilityException("Encrypt with DES failure!", e);
/*     */     }
/*  72 */     if (datas.length % 8 != 0) {
/*  73 */       throw new UtilityException("数据字节长度必须是8的倍数");
/*     */     }
/*  75 */     byte[] bytes = encrypt(datas, key.getBytes(), "ECB", null, "NoPadding");
/*  76 */     return StringUtil.toStrHex(bytes);
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
/*     */   public static String decrypt(String data, String key, String encoding)
/*     */     throws UtilityException
/*     */   {
/*  91 */     if (StringUtil.isEmpty(encoding)) {
/*  92 */       encoding = "UTF-8";
/*     */     }
/*  94 */     byte[] datas = StringUtil.hexStringToByteArray(data);
/*  95 */     if (datas.length % 8 != 0) {
/*  96 */       throw new UtilityException("数据字节长度必须是8的倍数");
/*     */     }
/*  98 */     byte[] bytes = decrypt(datas, key.getBytes(), "ECB", null, "NoPadding");
/*     */     try {
/* 100 */       return new String(bytes, encoding);
/*     */     } catch (UnsupportedEncodingException e) {
/* 102 */       throw new UtilityException("Decrypt with DES failure!", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] encrypt(byte[] data, byte[] key, String mode, byte[] ivbyte, String padding)
/*     */     throws UtilityException
/*     */   {
/* 124 */     if (key.length != 8) {
/* 125 */       throw new UtilityException("密钥长度不合要求（8字节）");
/*     */     }
/* 127 */     if (StringUtil.isEmpty(padding)) {
/* 128 */       padding = "NoPadding";
/*     */     }
/* 130 */     CipherInputStream cis = null;
/* 131 */     ByteArrayOutputStream bos = null;
/*     */     try {
/* 133 */       SecretKey secretKey = new SecretKeySpec(key, "DES");
/* 134 */       Cipher cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
/* 135 */       if ("ECB".equals(mode)) {
/* 136 */         cipher.init(1, secretKey);
/*     */       } else {
/* 138 */         if ((ivbyte == null) || (ivbyte.length != 8)) {
/* 139 */           ivbyte = DEFAULT_IVBYTE;
/*     */         }
/* 141 */         AlgorithmParameterSpec iv = new IvParameterSpec(ivbyte);
/* 142 */         cipher.init(1, secretKey, iv);
/*     */       }
/* 144 */       cis = new CipherInputStream(new ByteArrayInputStream(data), cipher);
/* 145 */       bos = new ByteArrayOutputStream();
/*     */       int temp;
/* 147 */       while ((temp = cis.read()) != -1) { //int temp;
/* 148 */         bos.write(temp);
/*     */       }
/* 150 */       return bos.toByteArray();
/*     */     } catch (NoSuchAlgorithmException e) {
/* 152 */       throw new UtilityException("Encrypt with DES failure!", e);
/*     */     } catch (NoSuchPaddingException e) {
/* 154 */       throw new UtilityException("Encrypt with DES failure!", e);
/*     */     } catch (InvalidKeyException e) {
/* 156 */       throw new UtilityException("Encrypt with DES failure!", e);
/*     */     } catch (InvalidAlgorithmParameterException e) {
/* 158 */       throw new UtilityException("Encrypt with DES failure!", e);
/*     */     } catch (IOException e) {
/* 160 */       throw new UtilityException("Encrypt with DES failure!", e);
/*     */     } finally {
/* 162 */       IOUtils.closeQuietly(bos);
/* 163 */       IOUtils.closeQuietly(cis);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] decrypt(byte[] data, byte[] key, String mode, byte[] ivbyte, String padding)
/*     */     throws UtilityException
/*     */   {
/* 186 */     if (key.length != 8) {
/* 187 */       throw new UtilityException("密钥长度不合要求（8字节）");
/*     */     }
/* 189 */     if (StringUtil.isEmpty(padding)) {
/* 190 */       padding = "NoPadding";
/*     */     }
/* 192 */     CipherInputStream cis = null;
/* 193 */     ByteArrayOutputStream bos = null;
/*     */     try {
/* 195 */       SecretKey secretKey = new SecretKeySpec(key, "DES");
/* 196 */       Cipher cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
/* 197 */       if ("ECB".equals(mode)) {
/* 198 */         cipher.init(2, secretKey);
/*     */       } else {
/* 200 */         if ((ivbyte == null) || (ivbyte.length != 8)) {
/* 201 */           ivbyte = DEFAULT_IVBYTE;
/*     */         }
/* 203 */         AlgorithmParameterSpec iv = new IvParameterSpec(ivbyte);
/* 204 */         cipher.init(2, secretKey, iv);
/*     */       }
/* 206 */       cis = new CipherInputStream(new ByteArrayInputStream(data), cipher);
/* 207 */       bos = new ByteArrayOutputStream();
/*     */       int temp;
/* 209 */       while ((temp = cis.read()) != -1) { //int temp;
/* 210 */         bos.write(temp);
/*     */       }
/* 212 */       return bos.toByteArray();
/*     */     } catch (NoSuchAlgorithmException e) {
/* 214 */       throw new UtilityException("Decrypt with DES failure!", e);
/*     */     } catch (NoSuchPaddingException e) {
/* 216 */       throw new UtilityException("Decrypt with DES failure!", e);
/*     */     } catch (InvalidKeyException e) {
/* 218 */       throw new UtilityException("Decrypt with DES failure!", e);
/*     */     } catch (InvalidAlgorithmParameterException e) {
/* 220 */       throw new UtilityException("Decrypt with DES failure!", e);
/*     */     } catch (IOException e) {
/* 222 */       throw new UtilityException("Decrypt with DES failure!", e);
/*     */     } finally {
/* 224 */       IOUtils.closeQuietly(bos);
/* 225 */       IOUtils.closeQuietly(cis);
/*     */     }
/*     */   }
/*     */ }


