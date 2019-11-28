class MaxProductSubarrayBest {
  // old_pos = pos[i - 1]
  // old_neg = neg[i - 1]
  // new_pos = pos[i]
  // new_neg = neg[i]
  
  public int maxProduct2(int[] nums) {
    if (nums.length == 1) return nums[0];

    int old_pos = Math.max(0, nums[0]);
    int old_neg = Math.min(0, nums[0]);

    int new_pos = old_pos;
    int new_neg = old_neg;

    int max = new_pos;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == 0) {
        new_pos = 0;
        new_neg = 0;
      }

      else if (nums[i] > 0) {
        new_pos = Math.max(old_pos * nums[i], nums[i]);
        new_neg = Math.min(old_neg * nums[i], 0);
      }

      else if (nums[i] < 0) {
        new_pos = Math.max(old_neg * nums[i], 0);
        new_neg = Math.min(old_pos * nums[i], nums[i]);
      }

      max = Math.max(max, new_pos);
      
      old_pos = new_pos;
      old_neg = new_neg;
    }

    return max;
  }
}