import java.util.HashMap;
import java.util.Map;

// 给定一个数组arr和一个非负整数k，你需要从这个数组中找到一个连续子数组，使得这个子数组的和为k
// 返回这个子数组的长度。如果有多个这样的子串，返回结束位置最小的，如果还有多个，返回起始位置最小的
// 如果找不到这样的子数组，返回-1

// FB round 1 Q1
public class SearchSubarray {
  public int searchSubarray(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
      int sum = 0;
      for(int i = arr.length - 1; i >= 0; i--){
        sum += arr[i];
        map.put(sum, i);
      }      
      sum = 0;
      int res = -1;
      for (int i = arr.length - 1; i >= 0; i--){
        if(map.containsKey(sum + k) && map.get(sum + k) <= i){
          res = i - map.get(sum + k) + 1;
        }
        sum += arr[i];
      }
    return res;
  }
}