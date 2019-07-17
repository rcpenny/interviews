import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// You have an array of logs.  Each log is a space delimited string of words.
// For each log, the first word in each log is an alphanumeric identifier.  Then, either:
// Each word after the identifier will consist only of lowercase letters, or;
// Each word after the identifier will consist only of digits.
// We will call these two varieties of logs letter-logs and digit-logs.  
// It is guaranteed that each log has at least one word after its identifier.

// Reorder the logs so that all of the letter-logs come before any digit-log.  
// The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  
// The digit-logs should be put in their original order.

// Return the final order of the logs.

// Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
// Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

class Log {
	String id;
	String log;
	Log(String id, String log) {
		this.id = id;
		this.log = log;
	}
}

// 此题第一遍写没有写helper class Log, 败笔.
// 用了PQ,多此一举，用list就好了.
public class ReorderLogFiles {

	private Comparator<Log> cpt = new Comparator<Log>() {
		@Override public int compare(Log a, Log b) {
			if (!a.log.equals(b.log)) return a.log.compareTo(b.log);
			return a.id.compareTo(b.id);
		}
	};

  public String[] reorderLogFiles(String[] logs) {
		String[] result = new String[logs.length];

		List<Log> letterLogs = new ArrayList<>();
		List<Log> digitLogs = new ArrayList<>();

		for (String log : logs) {
			Log tmp = generateLog(log);
			if (isLetterLog(tmp)) letterLogs.add(tmp);
			else digitLogs.add(tmp);
		}

		Collections.sort(letterLogs, cpt);

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
