import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

// 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
// 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
// 返回结果需要有序

// 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
// 输出: [1,2],[1,4],[1,6]
// 解释: 返回序列中的前 3 对数：
//      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

public class FindKPairsWithSmallestSums {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> answer = new ArrayList<>();
        
		if(nums1.length == 0 || nums2.length == 0 || k == 0) return answer;
		
		PriorityQueue<List<Integer>> pq = 
			new PriorityQueue<>((a,b) -> (a.get(0) + a.get(1)) - (b.get(0) + b.get(1)));
		
		// could use maxheap to increase sp
		for(int num1: nums1){
			for(int num2: nums2){
				List<Integer> temp2 = new ArrayList<>();
				temp2.add(num1);
				temp2.add(num2);
				pq.add(temp2);
			}
		}

		while(!pq.isEmpty() && k > 0){
			answer.add(pq.poll());
			k--;
		}
		
		return answer;        
	}
}
