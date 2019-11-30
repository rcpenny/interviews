// 给定一个只包含大小写字母的英文单词，问最少需要按键几次才能将单词输入
//（可以按caps lock以及shift键，一开始默认输入小写字母）
// lint1586

// 输入：Hadoop
// 输出：7
// 解释：先按住Shift键加h输入H，然后依次按adoop即可输入。`

// 输入：HADOOp
// 输出：8
// 解释：先按一下caps lock，依次按hadoo，然后按`caps lock，最后按一下p

// 1. 确定状态
// 2. 转移方程
// 3. 初态边界  
// 4. 顺序计算

public class MinimumNumberOfKeystrokes {
  public int getAns(String ss) {
    char[] s = ss.toCharArray();


  }
}