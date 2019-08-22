import java.util.LinkedList;
import java.util.Queue;

// lint7
//     3
// 	  / \
// 	 9  20
// 	   /  \
// 	  15   7
//   it will be serialized {3,9,20,#,#,15,7}

public class SerializeDeserializeBinaryTree {

	// serialize比较简单，注意最后删掉末尾的#与, 补上}
  public String serialize(TreeNode root) {
    if (root == null) return "{}";
    
    StringBuilder sb = new StringBuilder();
		sb.append("{");

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node == null) {
				sb.append("#");
				sb.append(",");
			} 
			else {
        sb.append(String.valueOf(node.val));
        sb.append(",");
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }

		// delete trailing # and , 补全}
		int i = sb.length() - 1;
		while (sb.charAt(i) == '#' || sb.charAt(i) == ',') {
      sb.deleteCharAt(i--);
    }
		sb.append("}");

    return sb.toString();
  }



	// deserialize，还是宽搜，用一个flag isleftchild 是否要处理下一个node
  public TreeNode deserialize(String data) {
    if (data == "{}" || data == null) return null;

		// 生成字符串组，包含val与#
		String[] val = data.substring(1, data.length() - 1).split(",");

		TreeNode root = new TreeNode(Integer.valueOf(val[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    boolean isLeftChild = true;
    for (int i = 1; i < val.length; i++) {
      if (!val[i].equals("#")) {
        TreeNode node = new TreeNode(Integer.valueOf(val[i]));
        if (isLeftChild) queue.peek().left = node;
        if (!isLeftChild) queue.peek().right = node;
        queue.offer(node);
      }
      // 还在处理左孩子的话，下一个要处理右孩子，不能poll
      if (!isLeftChild) queue.poll();
      isLeftChild = !isLeftChild;
		}

    return root;
  }
}
