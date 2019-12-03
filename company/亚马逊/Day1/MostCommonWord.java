//leet819

@HashMap
@HashSet
public class MostCommonWord {
  public String mostCommonWord(String paragraph, String[] banned) {
    paragraph += ".";

    Set<String> dict = new HashSet<>();
    for (String ban : banned) dict.add(ban);

    int maxFreq = 0;
    String word = null;
    Map<String, Integer> map = new HashMap<>();

    StringBuilder sb = new StringBuilder();
    for (char c : paragraph.toCharArray()) {
      if (Character.isLetter(c)) {
        sb.append(Character.toLowerCase(c));
      } else if (sb.length() > 0){
        String seg = sb.toString();
        if (!dict.contains(seg)) {
          map.put(seg, map.getOrDefault(seg, 0) + 1);

          if (map.get(seg) > maxFreq) {
            word = seg;
            maxFreq = map.get(seg);
          }
        }
        sb = new StringBuilder();
      }
    }

    return word;
  }
}