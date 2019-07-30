/** 
 * 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为 k。
 * 当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。
 */

public class WoodCut {

  public int woodCut(int[] L, int k) {
    if (L == null || L.length == 0) return 0;

    int maxLength = 0, minLength = 0;
    // 找到木堆中最长的木头 可行解范围（0 -> 最长木头)
    for (int l : L) 
      maxLength = Math.max(l, maxLength);

    while (minLength + 1 < maxLength) {
      int middleLength = minLength + (maxLength - minLength) / 2;
      int pieces = countPieces(L, middleLength);

      if (pieces < k) maxLength = middleLength;
      else minLength = middleLength;
    }

    if (countPieces(L, maxLength) == k) return maxLength;
    return minLength;
  }

  // 像find dupe number那题，找到答案范围，在二分模板的判断条件写一个customize method来判断
  private int countPieces(int[] L, int woodLength) {
    int count = 0;
    for (int l : L) count = count + l / woodLength;
    return count;
  }
}