https://engineering.linkedin.com/architecture/brief-history-scaling-linkedin


https://engineering.linkedin.com/blog/2019/the-building-blocks-of-linkedin-skill-assessments

https://engineering.linkedin.com/blog/2019/04/under-the-hood--learning-with-documents

3. https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store

http://www.pitt.edu/~viz/classes/infsci3350/resources/linkedin_icde12.pdf

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
随着处理的窗口越来越多，HashMap也会不断增长，同时HashMap里的低频元素会被清理出去，这样内存占用会保持在一个很低的水平。

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

