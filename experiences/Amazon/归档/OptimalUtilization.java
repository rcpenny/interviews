public class OptimalUtilization {
  public int[] optimalUtilization(int[] A, int[] B, int K) {
    if (A == null || A.length == 0) return new int[0];
    if (B == null || B.length == 0) return new int[0];
    
    int[] result = new int[2];
    int maxSum = Integer.MIN_VALUE;
    int tmpSum = 0;
    
    if (A[0] + B[0] > K) return new int[0];
    
    for (int aptr = 0; aptr < A.length; aptr++) {
      for (int bptr = 0; bptr < B.length; bptr++) {
        tmpSum = A[aptr] + B[bptr];
        if (tmpSum > K) break;
        
        if (tmpSum > maxSum) {
          maxSum = tmpSum;
          result[0] = aptr;
          result[1] = bptr;
        }
      }
    }
    
    return result;
  }
}
