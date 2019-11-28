// 输入: tuple = "(1,2,3),(4,5,6),(7,8,9)", n = 2
// 输出: 80
// 解释: 每个数组的第2个元素为 2, 5, 8，乘积为80
// lint933

public class TupleMultiply {
  public long tupleMultiply(String tuple, int n) {
    // 需要注意的是( 和) 是特殊字符，需要加\ 否则会出现runtime error
    String[] tuples = tuple.split("\\),\\(");
    long result = 1;

    tuples[0] = tuples[0].substring(1);
    String last = tuples[tuples.length - 1];
    tuples[tuples.length - 1] = last.substring(0, last.length() - 1);

    for (String t : tuples) {
      String[] parts = t.split(",");
      String tmp = parts[n - 1];
      Long next = Long.valueOf(tmp);

      result = result * next;
    }

    return result;
  }
}