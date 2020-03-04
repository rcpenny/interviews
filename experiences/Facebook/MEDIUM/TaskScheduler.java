import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 给定一个字符串，表示CPU需要执行的任务。 这个字符串由大写字母A到Z构成，不同的字母代表不同的任务。
// 完成任务不需要按照给定的顺序。 每项任务都可以在一个单位时间内被完成。 在每个单位时间，CPU可以选择完成一个任务或是不工作。
// 但是，题目会给定一个非负的冷却时间“n”，表示在执行两个“相同的任务”之间，
// 必须至少有n个单位时间，此时CPU不能执行该任务，只能执行其他任务或者不工作。
// 您需要返回CPU完成所有给定任务所需的最少单位时间数

// 任务数量的范围为 [1, 10000].
// 整数 n 的范围为 [0, 100].

// 输入: tasks = ['A','A','A','B','B','B'], n = 2
// 输出: 8
// 解释:
// A -> B -> idle -> A -> B -> idle -> A -> B.

// follow up: tasks不能被打乱

public class TaskScheduler {
  public int leastInterval(char[] tasks, int n) {
    int[] freqs = new int[26];
    for (int i = 0; i < tasks.length; i++)
      freqs[tasks[i] - 'A']++;

    Arrays.sort(freqs);

    // number of characters with max freqs
    int count = 1;
    for (int i = 24; i >= 0; i--) {
      if (freqs[i] != freqs[25]) break;
      count++;
    }
    
    // 这个比较想一想这个例子：AAABBBCCCDDD n = 2
    // 其实一旦无法fit in A _ _ A _ _ A _ _ 这个pattern,说明最长就是tasks length了. 还是hin tricky
    return Math.max(tasks.length, (freqs[25] - 1) * (n + 1) + count);
  }
}