import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// 实现一个typeahead功能，给出一个字符串和一个字典，包含若干个单词，
// 返回所有含有这个字符串子串的单词；字典不能被修改，并且这个方法可能被调用多次

// 输入:
// dict=["Jason Zhang","James Yu","Li Zhang","Yanxin Shi"]
// search("Zhang")
// search("James")

// 输出:
// ["Jason Zhang","Li Zhang"]
// ["James Yu"]

class TrieNode {
  Map<Integer, TrieNode> children;

  TrieNode() {
    this.children = new HashMap<>();
    this.wordsIndex = new ArrayList<>();
  }
}

@Todo("https://www.jiuzhang.com/solution/typeahead/#tag-highlight 不同的想法")
public class Typeahead {
  private TrieNode root;
  private List<String> words;  

  public Typeahead(Set<String> dict) {
    root = new TrieNode();
    words = new ArrayList<>(dict);
    for (String word : dict)
      add(word, words);
  }

  public List<String> search(String str) {
    List<Integer> results = new ArrayList<>();
    TrieNode current = root;

    for (int i = 0; i < str.length(); i++) {
      int index = str.charAt(i) - 'a';
      if (!current.children.containsKey(index)) return null;
      current = current.children.get(index);
    }
    
    List<String> autocompletes = new ArrayList<>();
    for (Integer i : results) autocompletes.add(words.get(i));
    return autocompletes;
  }

  private void add(String s, List<String> words) {
    TrieNode current = root;
    for (int i = 0; i < s.length(); i++) {
      int index = s.charAt(i) - 'a';
      // 这条没找过
      if (!current.children.containsKey(index)) {
        current.children.put(index, new TrieNode());
        for (int j = 0; j < words.size(); j++)
          if (words.get(j).indexOf(s.substring(0, i + 1)) != -1)
            current.children.get(index).wordsIndex.add(j);
      }
      current = current.children.get(index);
    }
  }
}