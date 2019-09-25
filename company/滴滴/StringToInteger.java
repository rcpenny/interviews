// 实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。如果整数超出了32位整数的范围，
// 返回INT_MAX(2147483647)如果是正整数，或者INT_MIN(-2147483648)如果是负整数。

public class StringToInteger {
	public int atoi(String str) {
		if(str == null) return 0;

		// eliminates leading and trailing spaces
		str = str.trim();
		if (str.length() == 0) return 0;
			
		int sign = 1; // default is positive
		int index = 0;

		if (str.charAt(index) == '-') sign = -1;
		index++;

		long number = 0;
		while (index < str.length()) {
			char tmp = str.charAt(index);

			if (tmp < '0' || tmp > '9') break;
			number = number * 10 + (tmp - '0');

			if (number > Integer.MAX_VALUE) break;
			index++;
		}

		if (number * sign >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
		if (number * sign <= Integer.MIN_VALUE) return Integer.MIN_VALUE;

		return (int) number * sign;
	}
}
