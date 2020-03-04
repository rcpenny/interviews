// 你有一个日志数组 logs。每条日志都是以空格分隔的字串。
// 对于每条日志，其第一个字为字母数字标识符。然后，要么：
// 标识符后面的每个字将仅由小写字母组成，或；
// 标识符后面的每个字将仅由数字组成。
// 我们将这两种日志分别称为字母日志和数字日志。保证每个日志在其标识符后面至少有一个字。

// 将日志重新排序，使得所有字母日志都排在数字日志之前。字母日志按内容字母顺序排序，忽略标识符；
// 在内容相同时，按标识符排序。数字日志应该按原来的顺序排列。

// 返回日志的最终顺序。

// 示例 ：

// 输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
// 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
//  

// 提示：

// 0 <= logs.length <= 100
// 3 <= logs[i].length <= 100
// logs[i] 保证有一个标识符，并且标识符后面有一个字。

class Log {
	String id;
	String log;
	Log(String id, String log) {
		this.id = id;
		this.log = log;
	}
}

public class ReorderLogFiles {
  public String[] reorderLogFiles(String[] logs) {
		String[] result = new String[logs.length];

		List<Log> letterLogs = new ArrayList<>();
		List<Log> digitLogs = new ArrayList<>();

		for (String log : logs) {
			Log tmp = generateLog(log);
			if (isLetterLog(tmp)) letterLogs.add(tmp);
			else digitLogs.add(tmp);
		}

		Collections.sort(letterLogs, (a, b) -> {
			if (!a.log.equals(b.log)) return a.log.compareTo(b.log);
			return a.id.compareTo(b.id);
		});

		int index = 0;
		for (Log log : letterLogs) result[index++] = log.id + " " + log.log;
		for (Log log : digitLogs) result[index++] = log.id + " " + log.log;

    return result;
	}
	
	private Log generateLog(String s) {
		int sp_index = s.indexOf(' ');
		return new Log(s.substring(0, sp_index), s.substring(sp_index + 1));
	}

	private boolean isLetterLog(Log log) {
		return Character.isLetter(log.log.charAt(0));
	}
}
