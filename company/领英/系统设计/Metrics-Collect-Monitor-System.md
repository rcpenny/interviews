### Metrics Collect Monitor System

- 资料
1. LKND monitoring: https://engineering.linkedin.com/blog/topic/monitoring
2. GCP StackDriver Logging: https://www.youtube.com/watch?v=6GQqneNFVkU

- Senario
1. hundreds of datacenters
2. hundreds of services in each datacenter
3. multiple type of events may occur in each service
4. 监控100个server，需要从display到存储到采集数据整个stack都涉及到

- Service & Storage
1. 收集 - collect all events for each service
2. 聚合 - metrics aggregation 
3. 存储 - Database + Cache
4. 查询 - user can monitior it in real time
5. 通知 - if events exceed threshold, alert notify groups

典型的streaming processing achitecture:
message queue(kafka) + workers(storm) + (database,cache) + notification service(response queue)

- Scale
1. collect events 是用pull还是push, notification是用pull还是push 聊清楚trade off.
2. 最新的数据process完 写进db的同时 可以直接放在cache里面，因为很可能被读
3. 要注意engineer 在线和不在线的情况
4. 哪种event 需要通知哪个engineer 可以是一个pub sub的架构也可以存成一个static table or key value
5. 如何scale，如果是10000 hosts怎么收集
6. 要保证用户付了钱的调用流量都允许
7. 收集host的1second，1minute，1hour metrics。如何scale，如果是10000 hosts怎么收集
8. 提供各种方案，kafka，HDFS，pub-sub model

Monitor System
设计Amazon Product Page, 就是在SQL里面一个产品有多个图片多个价格的话怎么设计数据库。然后后台提取数值render到页面上得时候，class怎么设计，服务器怎么安排之类的, 中间也有讨论怎样给suggest product，我提到可以建一个Product weighted graph, 然后用BFS