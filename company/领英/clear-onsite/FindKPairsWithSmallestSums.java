import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

// 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2
// 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)
// 返回结果需要有序
// leet371

public class FindKPairsWithSmallestSums {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		if(nums1.length == 0 || nums2.length == 0 || k == 0) {
      return new ArrayList<>();
    }

		PriorityQueue<List<Integer>> maxheap = 
			new PriorityQueue<>(k, (a,b) -> (b.get(0) + b.get(1)) - (a.get(0) + a.get(1)));
		
		for(int num1: nums1){
			// 提速1: num1 + nums[0] 已经大于cur max sum
      if (maxheap.size() == k && (num1 + nums2[0] > (maxheap.peek().get(0) + maxheap.peek().get(1)))) {
				if (num1 + nums2[0] > (maxheap.peek().get(0) + maxheap.peek().get(1))) {
					break;
				}
      }

			for(int num2: nums2){
				List<Integer> tmp = Arrays.asList(num1, num2);
        if (maxheap.size() < k) {
          maxheap.offer(tmp);
          continue;
        }
        
        if ((num1 + num2) < (maxheap.peek().get(0) + maxheap.peek().get(1))) {
          maxheap.offer(tmp);
          maxheap.poll();
          continue;
				}
				
				// 提速2: num1 + num2 已经大于cur max sum
        break;
			}
		}

		List<List<Integer>> answer = new ArrayList<>(maxheap);
    Collections.reverse(answer);
	
		return answer;        
	}
}
