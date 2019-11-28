import java.util.HashSet;

// In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
// (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

// We may rotate the i-th domino, so that A[i] and B[i] swap values.

// Return the minimum number of rotations so that all the values in A are the same, 
// or all the values in B are the same.

// If it cannot be done, return -1.

// Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
// Output: 2

public class MinimumDominoRotationsForEqualRow {
  public int minDominoRotations(int[] A, int[] B) {
    int result = Integer.MAX_VALUE;
    
    Set<Integer> set_a = new HashSet<>();
    for (int elem : A) set_a.add(elem);
    
    // check A first
    for (int elem : set_a) {
      int swaps = 0;
      for (int i = 0; i < A.length; i++) {
        if (A[i] == elem) {
          if (i == A.length - 1) result = Math.min(result, swaps);
          continue;
        }
        if (B[i] == elem) {
          swaps++;
          if (i == A.length - 1) result = Math.min(result, swaps);
          continue;
        }
        break;
      }
    }

    Set<Integer> set_b = new HashSet<>();
    for (int elem : B) set_b.add(elem);
    
    // check B
    for (int elem : set_b) {
      int swaps = 0;
      for (int i = 0; i < B.length; i++) {
        if (B[i] == elem) {
          if (i == A.length - 1) result = Math.min(result, swaps);
          continue;
        }
        if (A[i] == elem) {
          swaps++;
          if (i == B.length - 1) result = Math.min(result, swaps);
          continue;
        }
        break;
      }
    }

    return result == Integer.MAX_VALUE ? -1 : result;
  }
}