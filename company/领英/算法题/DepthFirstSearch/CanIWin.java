// 在 "100 game" 中, 两位玩家交替使用 1 到 10 的任意一个整数加到一个和中, 谁加完之后, 和达到或者超过了 100 就获胜.
// 如果我们改变规则, 玩家不能重复使用已经使用过的整数呢？
// 举个例子, 两个玩家可以轮流从一个共同的数字池 (里面的整数为 1 到 15) 中拿出整数, 拿出之后不放回, 
// 直到某个玩家拿出一个数字之后, 已经拿出的数字之和达到或者超过了 100, 该玩家获胜.

// 给定两个整数 maxChoosableInteger 和 desiredTotal, 表示刚开始数字池中有 1, 2, 3, ..., maxChoosableInteger 这些数字, 
// 获胜的目标和是 desiredTotal.

// 判断先手玩家是否必胜, 假设两个玩家都用了最佳策略.

// 输入: maxChoosableInteger = 10, desiredTotal = 11
// 输出: false
// 解释: 
//     无论第一个玩家怎么选都会输. 
//     如果他没选 10, 那么第二个玩家选择 10 就可以取得胜利.
//     如果他选了 10, 那么第二个玩家可以选择任意一个数取得胜利.

// 1. 确定状态
// 2. 转移方程
// 3. 初态边界  
// 4. 顺序计算

public class CanIWin {
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    
  }
}