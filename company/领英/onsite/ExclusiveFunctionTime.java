import java.util.ArrayList;

// The CPU is single threaded which means that only one function is being executed at a given time unit.
// Return the exclusive time of each function, sorted by their function id.

// Input:
// n = 2
// logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
// Output: [3, 4]
// Explanation:
// Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
// Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
// Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
// So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

// lc 636
public class ExclusiveFunctionTime {
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] ans = new int[n];
		if (logs == null || logs.size() == 0) 
		{
				return ans;
		}
		
		Stack<Integer> stack = new Stack<>();
		
		int lastTimestamp = 0;
		for (String log : logs) 
		{
				String[] arr = log.split(":");
				int id = Integer.parseInt(arr[0]);
				String flag = arr[1];
				int timestamp = Integer.parseInt(arr[2]);
				
				if (!stack.isEmpty()) 
				{
						ans[stack.peek()] += (timestamp - lastTimestamp);
				}
				
				if ("start".equals(flag)) 
				{
						stack.push(id);
				} 
				else 
				{
						timestamp ++;
						ans[stack.peek()] ++;
						stack.pop();
				}
				lastTimestamp = timestamp;
		}
		return ans;
	}
}
