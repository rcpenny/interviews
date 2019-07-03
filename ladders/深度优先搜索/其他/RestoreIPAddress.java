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
		// 初始化答案与考虑corner case
		List<String> results = new ArrayList<>();
		if (s == null || s.length() < 4 || s.length() > 12) return results;

		// 递归操作
		split(s, 0, new ArrayList<String>(), results);

		// 返回值
		return results;
	}

	// 递归的定义：原始数据string s, 控制变量 s 查看起点index, dfs当前节点状态fields
	private void split(String s, int index, List<String> fields, 
		List<String> results) {
		// 递归的出口: index超过s长度，fields大于4个
		if (index == s.length() && fields.size() == 4) {
			String ip = generateIp(fields);
			results.add(new ArrayList<>(ip));
		}
		if (fields.size() >= 4 || index >= s.length()) return;

		// 递归的拆解：进入下层有三种切割可能性
		for (int i = 1; i <= 3; i++) {
			// 进入递归的条件：index不越界，substring是合理的ip数字
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
		for (int i = 0; i < 3; i++) ip.append(fields.get(i) + ".");
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
