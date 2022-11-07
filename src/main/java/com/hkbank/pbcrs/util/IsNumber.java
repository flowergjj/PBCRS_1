package com.hkbank.pbcrs.util;

public class IsNumber {
	public static Double isNumber(String str, int row, int column)
			throws Throwable {
		str=str.trim();
		boolean matches1 = str.matches("[0-9]+");
		boolean matches2 = str.matches("[0-9]+\\.[0-9]+");
		if (str.matches("[0-9]+")||str.matches("[0-9]+\\.[0-9]+")) {
			return Double.parseDouble(str);
		} else {
			 throw new Throwable("第" + row + "行" + column + "列" + "中含有非数字");
		}

	}
}
