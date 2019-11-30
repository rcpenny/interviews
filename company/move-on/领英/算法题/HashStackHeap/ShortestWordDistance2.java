// 同上，设计成class
// leet244

class ShortestWordDistance2 {
  Map<String, List<Integer>> wordToIndex;

  public WordDistance(String[] words) {
    this.wordToIndex = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      wordToIndex.putIfAbsent(words[i], new ArrayList<>());
      wordToIndex.get(words[i]).add(i);
    }
  }
  
  public int shortest(String word1, String word2) {
    List<Integer> index1 = wordToIndex.get(word1);
    List<Integer> index2 = wordToIndex.get(word2);

    int ptr1 = 0;
    int ptr2 = 0;

    int shortest = Integer.MAX_VALUE;

    while (ptr1 < index1.size() && ptr2 < index2.size()) {
      int i1 = index1.get(ptr1);
      int i2 = index2.get(ptr2);

      shortest = Math.min(shortest, Math.abs(i1 - i2));

      if (i1 < i2) ptr1++;
      else ptr2++;
    }

    return shortest;
  }
}
