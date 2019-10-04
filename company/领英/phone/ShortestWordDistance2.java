// 同上，设计成class
// lc244

class ShortestWordDistance2 {
  Map<String, List<Integer>> wordToIndex;
  
  public WordDistance(String[] words) {
    this.wordToIndex = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      this.wordToIndex.putIfAbsent(words[i], new ArrayList<>());
      this.wordToIndex.get(words[i]).add(i);
    }
  }

  public int shortest(String word1, String word2) {
    if (!wordToIndex.containsKey(word1) || !wordToIndex.containsKey(word2)) {
      return -1;
    }
    
    if (word1.equals(word2)) {
      return 0;
    }
    
    List<Integer> w1 = wordToIndex.get(word1);
    List<Integer> w2 = wordToIndex.get(word2);
    
    int p1 = 0;
    int p2 = 0;
    int min = Integer.MAX_VALUE;
    
    while (p1 != w1.size() && p2 != w2.size()) {
      min = Math.min(min, Math.abs(w1.get(p1) - w2.get(p2)));
      
      if (p1 == w1.size()) {
        p2++;
        continue;
      }
      
      if (p2 == w2.size()) {
        p1++;
        continue;
      }
      
      if (w1.get(p1) < w2.get(p2)) p1++;
      else p2++;
    }
    
    return min;
  }
}
