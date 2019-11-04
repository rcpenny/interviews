// 给定一串只含有小写形式的、排序过的 letters，并且给定一个目标字母 target ，
// 请找出在给定字母串中，大于目标字母的最小的那一个字母。
// 在本题中，字母是绕回编址的（即“z”后一位重新变为“a”）
// 比如说，如果target = 'z'，而给定字母串为letters = ['a', 'b']，那么答案为“a”。

// letters = ["c", "f", "j"]
// target = "a"
// 输出: "c"

// leet744

public class FindSmallestLetterGreaterThanTarget {
  public char nextGreatestLetter(char[] letters, char target) {
    int start = 0, end = letters.length - 1;
    if (target >= letters[end]) return letters[0];

    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (letters[middle] > target)
        end = middle; // 第一个比target大的
      else
        start = middle;
    }

    if (letters[start] > target) return letters[start];
    return letters[end];
  }
}