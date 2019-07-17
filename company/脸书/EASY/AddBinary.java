// Given two binary strings, return their sum (also a binary string).

public class AddBinary {
  public String addBinary(String a, String b) {
    // Integer.ValueOf(String s, int radix);
    int sum = Integer.valueOf(a, 2) + Integer.valueOf(b, 2);
    StringBuilder sb = new StringBuilder();

    while (sum != 0) {
      sb.append(String.valueOf(sum % 2));
      sum = sum / 2;
    }
    sb.reverse();
    return sb.toString();
  }
}