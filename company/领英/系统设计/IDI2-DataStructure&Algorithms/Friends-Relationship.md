### Friends Relationship
Design LinkedIn Friend Relationship System
(1st 2nd 3rd Degree of Friends)

- 资料
1. 算法 https://www.lintcode.com/problem/friends-within-three-jumps/
2. 系统 https://engineering.linkedin.com/real-time-distributed-graph/using-set-cover-algorithm-optimize-query-latency-large-scale-distributed

- Senario
1. 给出函数 int[] getFriend(int user_id) 返回该用户的所有好友(1st degree),
   实现函数 返回该用户的degree 2 friends, degree 3 friends
2. 给出函数 boolean isFriend(user1, user2),
   实现函数 求两个users 是不是一级联系，二级联系和三级联系
3. 图很大，怎么scale到多个machine上？

设计领英0,1,2,3朋友圈的具体算法。实现就是双向bfs，2,3度用双指针
最后还可以继续用hashset来优化时间复杂度
