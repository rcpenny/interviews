
### Top k exception (Kafka) LFU cache

2. IDI DS&Algorithm：类似top k的设计，需要return 5min，1hour，24hour之内的数据整合。这里问的不是top k，而是mean - 股票交易系统，经常有price update（incoming message），GetAvg会get过去不同时间区间内的average price. 需要O(1)

7. system design，套了壳的top K exception，面试官想知道过去5分钟，一小时，一天里LinkedIn member转发最多的content，（URL），如何设计。楼主用的sqs + aggregation service(write) + reading services三层做的。中间讨论了很多数据的size和design的tradeoff，聊的还是挺顺的。
In a cluster of thousand nodes, how to report Java exceptions, if those exceptions does not happen very often, but if happen, a lot of nodes will have them and a series of similar exceptions show up around the same time.

top k article，5分钟，1小时，一天的，问了怎么传输，怎么存储计算，有几个模块等等，这轮感觉都答上了。

设计内存数据结构存键值，每个键有过期时间。难点在于怎么用细粒度锁，到最后也没给出满意的结果，不知道面试官到底想要什么，另一个面试官看我做不出来非常开心。
设计一个系统可以显示top N events have occured in the last X hours.
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

top k exception.
我靠。跟地理的面筋相差很大，好么！地里都说是搞什么loosy counting 和各种 approximation. 然而，面试官跟我说，
  一个cluster有很多server, 每个server 会时不时发出一些exception. 需要我搭建一个设计系统， 它要提供一个api : getTopExceptions(int K, Long t1, Long t2).
  =>整个cluster 在 [t1, t2] 区间里 top K 的 exception的信息，包括stack trace什么的。
  这里 t1, t2 都是以秒为单位， 而且 t2-t1 可以是好几年那么长。这个K呢？我再三向他确认，他说可以是10, 100, 甚至是 100000. 我就跟他说，
  这个api是不是太flexible了。一般不是过去 5分钟，1小时，一天，一个月么。而且谁会看top 100000 的 exception 呢？ 他说我们不能更改要求，必须按照要求来。。。。
Design log system. You have a lot of servers and each is producing thousands of logs per second. You have unlimited resources. Design a system that will aggregate all the logs from all the systems, for a given window of 24 hours. And need to return the top N exceptions.
Top K 地里的讨论帖：https://www.1point3acres.com/bbs/thread-461654-1-1.html

Trending Post
套了壳的top K exception，面试官想知道过去5分钟，一小时，一天里linkedin member转发最多的content，（URL），如何设计。楼主用的sqs + aggregation service(write) + reading services三层做的。中间讨论了很多数据的size和design的tradeoff，聊的还是挺顺的。
用了地里面和网上九章的解法，就是用bucket的那种，但一开始bucket大小没讨论好，后来那个拉丁裔白人就抓住不放了，说要不同长度的时间怎么办啊，我说换不同的bucket大小，然后他就一脸懵逼，不知道具体要什么答案，也没有给有效的引导和提示。死扣细节然后就没时间了。后来shadow的国人小哥给了提示，提示就是换不同的bucket然后sum up起来，可是之前我说的时候那白人又不接话，让我一度以为这不是他想要的思路。不懂这一轮怎么搞的，最后也是挂一轮。

Monitor System
设计Amazon Product Page, 就是在SQL里面一个产品有多个图片多个价格的话怎么设计数据库。然后后台提取数值render到页面上得时候，class怎么设计，服务器怎么安排之类的, 中间也有讨论怎样给suggest product，我提到可以建一个Product weighted graph, 然后用BFS
linkedin有很多server在做很多不同的事情，设计一个系统，监测24小时之内top 500的exceptions