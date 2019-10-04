# system infra Q:
Process vs Thread:
thread和process的区别, 要求用stack, queue解释. how to chooese between them
thread之间怎么communicate

heap vs stack:
TCP vs UDP:
TCP和UDP的区别是什么？什么样的应用使用TCP，什么样的使用UDP？HTTP协议使用TCP还是UDP？
TCP UDP 和 use case

Transaction:
数据库事务和ACID 解释Transaction
Transaction的意思是什么。这个比较宽泛。我就回答了数据库里面的transaction。面试进一步问malloc() function的原理

Virtual memory:
虚拟内存是啥

java:
final/finally/finalize的不同
checked/unchecked exception, 抛出异常和返回异常值哪个比较好，为什么？
Java Object的理解以及benefits
NestedClass(static和non-static)怎么instantiate,什么是paging和page fault
java里面map的源码是不是看过 是不是知道怎么实现的等 如何线程‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍安全 如果改写完的让支持异步等等
为什么Java中的所有类都是默认由 Object 类继承？这样设计的思想是什么？
Write-back和Write-though Cache，follow up是哪一个比较快
操作系统相关：进程线程区别，进程间通信协议，操作系统内存管理，系统调用过程


- 243  shortest word distance
- 244  shortest word distance 2
- 339   Nested List Weight Sum
- 364   Nested List Weight Sum II
- 101  symmetric tree
- 46   permutation 1 多线程怎么避免重复? 加synchronized?
- 47   permutation 2 
- 380  Insert Delete GetRandom O(1)
- 39   combination sum
- 40   combination sum 2

1.   Insert Delete GetRandom O(1) dupes
2.   Kth Largest Element in an Array
3.   Can Place Flowers
4.  Search in Rotated Sorted Array 
5.  binary tree upside down foll‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍ow up是输入是错误的如何处理可能的edge case
6.  valid number 不考虑e，但是要考虑逗号：比如12,000,000之类的
7.  valid parenthesis
8.   Plus One Linked List
9.   Serialize and Deserialize BST bfs解需要添加null节点，问怎么优化，要了提示说traversal 于是给了inorder/preorder‍‍‍‌‍
10. Maximum Subarray
11.  First Unique Character in a String   
12.  Daily Temperatures
13.  Maximum Product Subarray
14. pow(x, n)
15.  one edit distance
16.  Flatten Nested List Iterator follow up 加一个 remove 操作
17.  Second Minimum Node In a Binary Tree
18. merge two sorted list
19.  BSTIterator
20. subsets 分析时空复杂度
21. generate parenthesis
22.  valid ip address
- 366   find leaves of binary tree
1.  add intervals 设计一个class，支持addIntervals (int left, int right)，getTotalCovered() 可以用线段树优化


2.  还是二叉树，要么2个子节点要么没有。父节点的值不大于子节点。返回节点值构成的集合中的第k小的值 ????
1.int  hasIntegerSquareRoot(int n).   当时脑子当机竟然想不出logn的方法 （binary serach 即可），用了linear。。（还问了一下edgecase， input超大怎么办）。 当然小哥也没让我一直想， 想了literally 30sec, 小哥说 没事 我们看下一题。。
2.nestedList Sum.   reversed.  我以为reverse应该是follow up结果直接来reverse，没啥好说的 高频面镜。先用arry做了。然后口述了leetcode光头哥大神给的weighted += unweighted的答案小哥并没有很认，除了用arry存， 他还认 两边pass. 第一遍先求depth。 还问了不用stack咋办。我说 queue啊 或者不用queue的bfs。 他说可以用递归吗 我说可以啊  take 3 param  as same ‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍as stack。。
- 有一个Linked List，在第N个节点的位置断开，然后把前面贴到后面的结尾处。比如，1 -> 2 -> 3 -> 4 -> 5，在3和4之间断开，变成了4 -> 5 -> 1 -> 2 -> 3。
这一题多花了些时间，因为N可以是任意自然数（超过list长度，要求取模后处理），要处理一些corner case。写了个bug，提示后改掉了。‍‍‍‌‍‍‍‍‍
第二轮代码，找两个单词在同一个array的距离。followup如果在两个不同的array如何找最大距离，最小距离。再followup如果字典很大经常query需要如何优化。
第一题：merge两个排好序的array。follow up：如果是merge一千个sorted array怎么办？分析时间复杂度
第三题：给你一个int array，返回所有只出现过一次的数字。follow up：返回数字的顺序应该跟数字在input array里出现的顺序相同
第一题： Flip locker for n students, each move for i steps.  一个一位数组表示locker开还是关，最开始全部关闭，然后模拟打开、关闭。

是地里面的原题关于cache的一题 RFU的变形 地里面有原题和答案 完了以后就是各种fullow-ups各种 比如
4. 面经题 一个cache 根据 rank 来evict
implement一个cache
给你一个类框架 实现一个cache 类里提供了一个data source 如果cache里找不到这个数 则从data source里找并cache起来供下次使用 每个数有自‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍己的rank 如果cache满了 要把rank最低的evict再插入
第二题： 面试官给出一个cache的借口，让你实现里面的get和set方法。
Implement a hash table. 需要跟面试官clarify并且讨论怎么处理collision，然后写code。这题讲真不容易一次写对。。。我还忘记了re-hash。不知道结果如何。
Print a tree and fill it to be complete tree， for example
coding是给一个data source (很大，query慢)， 给一个rank() return rankable item的score要求设计一个bestScoreCache。大致是这样，不是很难。但是fo‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍llow up问到了读cache时racing condition的问题于是又继续follow up system里lock啊之类的操作，问的很细。。。lz汗如雨下
best rank cache， 类似于LRU

打电话给我聊了半个多小时，详详细细地跟我讲了onsite都要面什么，说hm面特别重要，一定要突出自己的leadership和design的经验，因为我工作经验短而且是本科学历，投的却是senior的职位，不太容易过，
