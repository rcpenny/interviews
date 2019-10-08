import java.util.Random;

// Shuffle a set of numbers without duplicates.

public class ShuffleArray {
  private int[] nums;
  
  public ShuffleArray(int[] nums) {
      this.nums = nums;
  }
  
  public int[] reset() {
    return nums;
  }

  public int[] shuffle() {
    // loop一遍，rand出一个交换位置
    int[] rand = new int[nums.length];
    for (int i = 0; i < nums.length; i++){
      int r = (int) (Math.random() * (i+1));
      rand[i] = rand[r];
      rand[r] = nums[i];
    }
    return rand;
  }
}
