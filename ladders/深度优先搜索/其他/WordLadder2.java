import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列。
		变换规则如下：

		每次只能改变一个字母。
		变换过程中的中间单词必须在字典中出现。

		输入：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
		输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
		解释：
		1."hit"->"hot"->"dot"->"dog"->"cog"
		2."hit"->"hot"->"lot"->"log"->"cog"
		第一个序列的字典序小于第二个。

	Notice
	所有单词具有相同的长度。
	所有单词都只包含小写字母。
	题目确保存在合法的路径。
 */

 /**
	* 1. BFS找到最短路径，并且把tree build起来
	* 2. 使用DFS在tree中找出所有的ladders
	*/

@Todo("next DFS revisit")
public class WordLadder2 {
	// 写崩了呗
  public List<List<String>> findLadders(String start, String end, Set<String> dict) {
	}
}

