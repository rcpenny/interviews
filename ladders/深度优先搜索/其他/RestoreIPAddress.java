import java.util.ArrayList;
import java.util.List;

/**
 * 给一个由数字组成的字符串。求出其可能恢复为的所有IP地址。
 * (你的任务就是往这段字符串中添加三个点, 使它成为一个合法的IP地址. 返回所有可能的IP地址.)
 * 
 * 输入: "1116512311"
 * 输出: ["11.165.123.11","111.65.123.11"]
 */
public class RestoreIPAddress {
  public List<String> restoreIpAddresses(String s) {
		List<String> results = new ArrayList<>();
		if (s == null || s.length() < 4 || s.length() > 12) return results;
		split(s, 0, new ArrayList<String>(), results);
		return results;
	}

	private void split(String s, int index, List<String> fields, 
		List<String> results) {
		if (fields.size() >= 4 || index >= s.length()) {
			if (fields.size() == 4 && index == s.length()) {
				results.add(generateIp(fields));
			}
			return;
		}

		for (int i = 1; i <= 3; i++) {
			if (index + i > s.length()) continue;
			String field = s.substring(index, index + i);
			if (!isValidNumber(field)) continue;
			fields.add(field);
			split(s, index + i, fields, results);
			fields.remove(fields.size() - 1);
		}
	}

	private String generateIp(List<String> fields) {
		StringBuilder ip = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			ip.append(fields.get(i));
			ip.append('.');
		}
		ip.append(fields.get(3));
		return ip.toString();
	}

	private boolean isValidNumber(String sub) {
		// to eliminate cases like "00", "10"
		if (sub.charAt(0) == '0') return sub.equals("0");
		int value = Integer.valueOf(sub);
		return 0 <= value && value < 256;
	}
}
