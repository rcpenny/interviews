// 实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。如果整数超出了32位整数的范围，
// 返回INT_MAX(2147483647)如果是正整数，或者INT_MIN(-2147483648)如果是负整数。

// leet8

public class StringToInteger {
	public int myAtoi(String str) {
		if(str == null) {
			return 0;
		}

		str = str.trim();
		
		if (str.length() == 0) {
			return 0;
		}
				
		int sign = 1;
		int index = 0;

		if (str.charAt(index) == '+') {
			index++;
		} else if (str.charAt(index) == '-') {
			sign = -1;
			index++;
		}

		long num = 0;
		
		while (index < str.length()) {
			if (str.charAt(index) < '0' || str.charAt(index) > '9') {
				break;
			}
			
			num = num * 10 + (str.charAt(index) - '0');
			
			if (num > Integer.MAX_VALUE ) {
				break;
			}

			index++;
		}

		if (num * sign >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
		if (num * sign <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
		
		return (int) num * sign;
	}
}
