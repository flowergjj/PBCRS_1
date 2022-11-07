package com.hkbank.pbcrs.util;

import java.util.Random;

/**
 * @author yangbo
 * @category 获取随机数工具类
 * @version 0.0.1
 * @date 2012/12/26
 */
public final class RandomUtils {

	/**
	 * 符号
	 */
	public static final String ALL_SYMBOL = "`~!@#$%^&*()_-+={}[]\\|/-,.<>?";

	/**
	 * 字母
	 */
	public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 数字
	 */
	public static final String NUMBER_CHAR = "0123456789";

	/**
	 * 数字+字母
	 */
	public static final String ALL_CHAR = NUMBER_CHAR + LETTER_CHAR;

	/**
	 * 全部字符
	 */
	public static final String ALL_KEY = ALL_SYMBOL + ALL_CHAR;

	private RandomUtils() {

	}

	/**
	 * 返回一个定长的随机字符串(包含键盘所有可见字符)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateAllCharString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALL_KEY.charAt(random.nextInt(ALL_KEY.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机字符串(只包含特殊符号)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateSymbol(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALL_SYMBOL.charAt(random.nextInt(ALL_SYMBOL.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机字符串(只包含数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateNumberString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个指定范围内的随机正整数
	 * 
	 * @param scope
	 *            随机数字范围
	 * @return 随机数
	 */
	public static int generateInt(int scope) {
		Random random = new Random();
		return random.nextInt(scope);
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 *            参数
	 */
	public static void main(String[] args) {
		System.out.println(generateAllCharString(15));
		System.out.println(generateSymbol(15));
		System.out.println(generateString(15));
		System.out.println(generateNumberString(8));
		System.out.println(generateMixString(15));
		System.out.println(generateLowerString(15));
		System.out.println(generateUpperString(15));
		System.out.println(generateZeroString(15));
		System.out.println(toFixdLengthString(123, 15));
		System.out.println(toFixdLengthString(12345678901234L, 15));
	}
}
