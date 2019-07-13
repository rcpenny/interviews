import java.util.Comparator;
import java.util.PriorityQueue;

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

@Todo(improve code with a helper class)
public class ReorderLogFiles {
  private Comparator<String> letter_cpt = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
      int a_sp_index = a.indexOf(' ');
      int b_sp_index = a.indexOf(' ');

      String a_sub = a.substring(a_sp_index);
      String b_sub = b.substring(b_sp_index);
      if (!a_sub.equals(b_sub)) return a_sub.compareTo(b_sub);
      return a.substring(0, a_sp_index).compareTo(b_sub.substring(0, b_sp_index));
    }
  };

  public String[] reorderLogFiles(String[] logs) {

    PriorityQueue<String> heap = new PriorityQueue<>(letter_cpt);
    for (String s : logs) {
      if (isLetterLog(s)) heap.offer(s);
    }

    String[] result = new String[logs.length];
    int i = 0;
    while (!heap.isEmpty()) result[i++] = heap.poll();

    for (String s : logs) {
      if (!isLetterLog(s)) result[i++] = s;
    }

    return result;
  }

  private boolean isLetterLog(String a) {
    int space_index = a.indexOf(' ');
    return Character.isLetter(a.charAt(space_index + 1));
  }
}