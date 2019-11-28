/**
  给出一个由许多词根和一个句子组成的字典。 你需要用形成它的词根根替换句子中的所有后继词。 
  如果后继次有许多根可以形成它，则用最短长度的词根替换它。

  输入: dict = ["cat", "bat", "rat"]
  sentence = "the cattle was rattled by the battery"
  输出: "the cat was rat by the bat"
*/

public class ReplaceWords {
  public String replaceWords(List<String> dict, String sentence) {
    TrieNode root = new TrieNode();

    // put all words in dict to trie
    for (String word : dict) {
      generateWord(root, word);
    }

    String[] words = sentence.split(" ");

    // shorten word to 词根
    for (int i = 0; i < words.length; i++) {
      words[i] = shorten(root, words[i]);
    }

    StringBuilder sb = new StringBuilder();
    for (String word : words) {
      sb.append(word);
      sb.append(" ");
    }
    sb.deleteCharAt(sb.length() - 1);

    return sb.toString();
  }

  private void generateWord(TrieNode root, String word) {
    char[] c = word.toCharArray();
    TrieNode copy = root;
    for (char tmp : c) {
      int index = tmp - 'a';
      if (copy.children[index] == null) {
        copy.children[index] = new TrieNode();
      }
      copy = copy.children[index];
    }
    copy.word = word;
  }
  
  private String shorten(TrieNode root, String s) {
    char[] c = s.toCharArray();
    TrieNode copy = root;
    for (char tmp : c) {
      int index = tmp - 'a';
      if (copy.children[index] == null) break;
      if (copy.children[index].word != null)
        return copy.children[index].word;
      copy = copy.children[index];
    }
    return s;
  }
}

class TrieNode {
  TrieNode[] children;
  String word;
  TrieNode() {
    this.children = new TrieNode[26];
    this.word = null;
  }
}