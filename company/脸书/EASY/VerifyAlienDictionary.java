import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Example 1:

// Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
// Output: true
// Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
// Example 2:

// Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
// Output: false
// Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
// Example 3:

// Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
// Output: false
// Explanation: The first three characters "app" match, and the second string is shorter (in size.) 
// According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank 
// character which is less than any other character (More info)

// 不难，就是看清题目

public class VerifyAlienDictionary {
  private Map<Character, Integer> map;

  public boolean isAlienSorted(String[] words, String order) {
    map = new HashMap<>();
    for (int i = 0; i < order.length(); i++)
      map.put(order.charAt(i), i + 1);

    for (int i = 0; i < words.length - 1; i++) {
      String prev = words[i];
      String next = words[i + 1];
      if (!compareWord(prev, next)) return false;
    }

    return true;
  }

  private boolean compareWord(String a, String b) {
    int ptr = 0;
    while (ptr < a.length() && ptr < b.length()) {
      char c1 = a.charAt(ptr);
      char c2= b.charAt(ptr);

      if (map.get(c1) < map.get(c2)) return true;
      if (map.get(c1) > map.get(c2)) return false;
      ptr++;
    }

    if (a.length() == ptr && b.length() > ptr) return true;

    return false;
  }
}
