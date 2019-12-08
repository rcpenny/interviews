Data Partitioning
# Data Partition - 数据分库

https://zhuanlan.zhihu.com/p/83674503

## RDBMS
### ACID 用来描述关系型数据库事务的特性
1. 原子性 - 每个事务内部所有操作要么全部完成，要么全部不完成。
2. 一致性 - 任何事务都使数据库从一个有效的状态转换到另一个有效状态。
3. 隔离性 - 并发执行事务的结果与顺序执行事务的结果相同。
4. 持久性 - 事务提交后，对系统的影响是永久的。

### RDMBS scale technologies：

#### Master-slave replication 主从复制
- master同时负责读取和写入操作，并复制写入到一个或多个从库中，从库只负责读操作。
- 树状形式的从库再将写入复制到更多的从库中去。
- 如果master离线，系统可以以只读模式运行，直到某个slave被提升为主库或有新的主库出现。




、主主复制、联合、分片、非规范化和 SQL调优。