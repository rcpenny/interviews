class ShortestWordDistance3 {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    boolean sameWord = word1.equals(word2);
    boolean changePtr1 = true;

    int ptr1 = -1;
    int ptr2 = -1;

    int shortest = Integer.MAX_VALUE;

    for (int i = 0; i < words.length; i++) {
      String cur = words[i];

      if (!cur.equals(word1) && !cur.equals(word2)) {
        continue;
      }

      if (!sameWord) {
        if (cur.equals(word1)) ptr1 = i;
        else ptr2 = i;
      } else {
        if (changePtr1) ptr1 = i;
        else ptr2 = i;
        changePtr1 = !changePtr1;
      }

      if (ptr1 != -1 && ptr2 != -1) {
        shortest = Math.min(shortest, Math.abs(ptr1 - ptr2));
      }
    }

    return shortest;
  }
}
