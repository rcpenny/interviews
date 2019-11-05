### 2nd degre 3rd degree friends
Given isFriend(x,y) and a node X, how can you find all nodes which is one-degree connection, two-degree, and three-degree with X.
给定一个 int[] getFridend(int user)，O(1) complexity, 求两个users 是不是一级联系，二级联系和三级联系。先在local解，后来问图很大，怎么scale到多个machine上

设计领英0,1,2,3朋友圈的具体算法。实现就是双向bfs，2,3度用双指针。最后还可以继续用hashset来优化时间复杂度。印度小哥积极引导，我也一直在顺着他说，最后总算是都写完了。

2. 设计灵隐， 主要在朋友关系计算。
设计 linkedin friend relationship system
面经找几级朋友那题
Friend relationship	
https://engineering.linkedin.com/real-time-distributed-graph/using-set-cover-algorithm-optimize-query-latency-large-scale-distributed


已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends。
Design LinkedIn.