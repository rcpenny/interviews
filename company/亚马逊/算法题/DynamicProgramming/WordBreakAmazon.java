class WordBreakAmazon {
  public boolean wordBreak(String s, List<String> wordDict) {
    if (s == null || s.length() == 0) {
      return false;
    }

    int n = s.length();
    
    // if first i chars can be broken into words
    boolean[] f = new boolean[n + 1];
    f[0] = true;

    String sub;

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        sub = s.substring(j, i);
        if (!f[j] || !wordDict.contains(sub)) continue;
        f[i] = true;
        break;
      }
    }

    return f[n];
  }
}