# Friends Relationship
Design LinkedIn Friend Relationship 1, 2, 3 degrees

## 资料
- 算法 https://www.lintcode.com/problem/friends-within-three-jumps/
- 系统 https://engineering.linkedin.com/real-time-distributed-graph/using-set-cover-algorithm-optimize-query-latency-large-scale-distributed

## Senario
- 给出 int[] getFriend(int user_id) 返回所有1st degree friend, 实现一个method来返回该用户的2nd 3rd degree friends
- 给出 boolean isFriend(user1, user2),实现method求两个users是否 <=3 degrees
- 系统设计层面的问题：scalability, latency, etc.

## Achritecture:
### API Layer
- the access point for front-ends
- getDistances() from Cache
- getConnections() / getSharedConnections() from DB

### Cache System
- a distributed Cache System (consistent hashing cache cluster)
- getSecondDegrees() from DB

1. 计算，存储，查询 a user's second-degree set
2. second-degree set每次访问时即使计算，cache hit raito 90%
3. 从多个graphDB node获取set数据，在cache node merge所有partial result

## GraphDB
- a partitioned/replicated graph DB.
- **Challenge**: when the graph is partitioned across a cluster of machines
- **Scale**: The Scaling Problem in blog, Set Cover Algorithm

设计领英0,1,2,3朋友圈的具体算法。实现就是双向bfs，2,3度用双指针
最后还可以继续用hashset来优化时间复杂度
