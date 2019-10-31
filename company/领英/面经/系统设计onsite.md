https://engineering.linkedin.com/architecture/brief-history-scaling-linkedin
https://engineering.linkedin.com/blog/2019/the-building-blocks-of-linkedin-skill-assessments
https://engineering.linkedin.com/blog/2019/04/under-the-hood--learning-with-documents
https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store
http://www.pitt.edu/~viz/classes/infsci3350/resources/linkedin_icde12.pdf


# IDI3
# Metric Monitor System
https://engineering.linkedin.com/blog/2019/an-inside-look-at-linkedins-data-pipeline-monitoring-system-

4 system design monitoring system. 关键字 hundreds of datacenters,  hundreds of services in each datacenter, multiple type of events may ocuur in each service. 要求，collect all the events for each service, user can monitior it in real time, if some event exceed the threshold, corresponding engineers will get a notification. 典型的steaming processing的achitecture. message queue(kafka)+workers(storm)+database+cache+notification service(response queue). 需要注意的点就是 1 collect events 是用pull还是push, notification是用pull还是push 聊清楚trade off. 2最新的数据process完 写进db的同时 可以直接放在cache里面，因为很可能被读。3要注意engineer 在线和不在线的情况。4 哪种event 需要通知哪个engineer 可以是一个pub sub的架构也可以存成一个static table or key value. 这一轮面试官40分钟问完，问了很多细节的问题，因为我比较熟，所以面试官非常满意。还剩20分钟 说我表现得非常好，他一边给我写feedback 一边让我问问题。
设计一个metric系统，包括怎么收集，aggregation，存储，查询，dashboard，alert
（7）美国小伙（经理）：SDA轮：设计一个API Metering系统，实现一个功能，就是调用累计次数达到阈值
就不让过了，假定：（A）大规模；（B）不需要特别严格，但是要保证用户付了钱的调用流量都允许。
第六轮，设计一个诊断系统，类似地理说的Kafka加上aggregator的设计方式。
5.系统设计 statistics segregation system 我这轮知无不言 但无奈所知实在有限
sys design 设计monitoring system监控100个server，需要从display到存储到采集数据整个stack都涉及到
3. Metrics collection and monitor system。
收集host的1second，1minute，1hour metrics。如何scale，如果是10000 hosts怎么收集。
第六轮，设计，三哥，设计metrics collection and monitor system， 收集host的1second，1minute，1hour metrics。如何scale，如果是10000 hosts怎么收集。提供各种方案，kafka，HDFS，pub-sub model。各种分析优缺点，然后go over workflow。这轮还算比较顺利。

# Top k exception (Kafka) LFU cache
经典题，24小时的top k exception, 然后支持5min的top k，1hr的top k，1年的top k

- 分布式统计 Distributed: 每隔5~10秒向中心节点汇报数据
也就是说，哪些帖子被分享了多少次这些数据，首先在 web server 中进行一次缓存，也就是说web server的一个进程接收到一个分享的请求之后，比如 tweet_id=100 的tweet被分享了。那么他把这个数据先汇报给web server上跑着的 agent 进程，这个agent进程在机器刚启动的时候，就会一直运行着，他接受在台web server上跑着的若干个web 进程(process) 发过来的 count +1 请求. 这个agent整理好这些数据之后，每隔5~10秒汇报给中心节点。这样子通过5~10s的数据延迟，解决了中心节点访问频率过高的问题。这个设计的思路在业界是非常常用的（做这种数据统计服务的都是这么做的) 这个中心节点的任务是，接受来自1000台web server的统计数据，进行整理，并存储数据库中。
所以这台机器不是数据库。数据库是另外的专门的集群。这条机器只负责做一些整理工作，把一些有用的信息放到数据库和缓存里。方便你查询Top K 的时候用。他自己并不存储数据，只负责计算。

- 分阶段统计 Level (bucket)
如果我要去算最近5分钟的数据，我就按照1秒钟为一个bucket的单位，收集最近300个buckets里的数据。
如果是统计最近1小时的数据，那么就以1分钟为单位，收集最近60个Buckets的数据，
如果是最近1天，那么就以小时为单位，收集最近24小时的数据。
当来了一个某个帖子被分享了1次的数据的时候，这条数据被会分别存放在当前时间(以秒为单位），当前分钟，当前小时的三个buckets里，
用于服务之后最近5分钟，最近1小时和最近24小时的数据统计。

- 数据抽样 Sample
可以进行一定程度的抽样，因为那些Top K 的post，一定是被分享了很多很多次的，所以可以进行抽样记录。
如果是5分钟以内的数据，就不抽样，全记录。如果是最近1小时，就可以按照比如 1/100 的概率进行 sample。

- 缓存 Cache
对于最近5分钟的结果，每隔5s才更新一次。
对于最近1小时的结果，每隔1分钟更新一次。
对于最近24小时的结果，每隔10分钟才更新一次。
用户需要看结果的时候，永远看的是 Cache 里的结果。另外用一个进程按照上面的更新频率去逐渐更新Cache

- Lossy Counting 
等待数据流不断流进这个窗口，直到窗口满了，开始统计每个元素出现的频率，
统计结束后，每个元素的频率减1，然后将出现次数为0的元素从HashMap中删除
背后朴素的思想是，出现频率高的元素，不太可能减一后变成0，如果某个元素在某个窗口内降到了0，说明它不太可能是高频元素，可以不再跟踪它的计数器了。
随着处理的窗口越来越多，HashMap也会不断增长，同时HashMap里的低频元素会被清理出去，这样内存占用会保持在一个很低的水平

Design，top k exception， 是老题了，用了地里面和网上九章的解法，就是用bucket的那种，但一开始bucket大小没讨论好，后来那个拉丁裔白人就抓住不放了，说要不同长度的时间怎么办啊，我说换不同的bucket大小，然后他就一脸懵逼，不知道具体要什么答案，也没有给有效的引导和提示。死扣细节然后就没时间了。后来shadow的国人小哥给了提示，提示就是换不同的bucket然后sum up起来，可是之前我说的时候那白人又不接话，让我一度以为这不是他想要的思路。不懂这一轮怎么搞的，最后也是挂一轮。

## Tiny URL （LinkedIn post功能）
- Senario(func/non-func): 
   1. long URl-> short URL 
   2. short URL -> long URL, redirect
   3. QPS + STORAGE (DAU, shortURL creation QPS, click shortURL QPS, URL-SIZE storage daily)
   4. set expiration time(opt)
   5. The system should be highly available / URL redirection should happen in real-time with minimal latency.
- Service
   1. URL service(generate URL service, parse URL service)
   2. URLservice.decode() GET/short_url return HTTP redirect response
   3. URLservice.encode() POST/data/shorten/ Data ={url:"baidu.com"} return short url
- Storage
   1. SQL vs NoSQL 自行比较
   2. encode算法(随机生成，进制转换)
- Scale
   1. 提速1（Cache） long -> short  short -> long 先cache，再DB
   2. 提速2 优化web server visit speed, use DNS to distribute users to different web servers
   3. 提速3 centralized MySQL + distributed Memcahced(共用DB，cache各自对应)
   4. 扩展？ 多台数据库解决（Storaget存不下，QPS忙不过） horizontal sharding, sharding key?

第二轮：面的系统设计，tiny url设计，而且对于每个url被访问了多少次，能够输出过去24小时之内的总访问量以及给定一个时间范围的总访问量；先画设计图讲了大概的思路，然后面试官会针对设计的各种部分提问，比如pre-generator怎么保证新的url不和已经被用过的重复，url expire的policy怎么设计，统计访问量在有多个server的时候怎么实现，需不需要cache，如果某个server down了怎么保证访问量的数据没有丢失等等

## k-v store (设计VOLDEMORT)
key value store，value体积比较大需要放在硬盘里面 另外随机写到硬盘会比较慢所以assume你要appending only

# delayed task
https://soulmachine.gitbooks.io/system-design/content/cn/task-scheduler.html