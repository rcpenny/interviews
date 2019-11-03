https://engineering.linkedin.com/architecture/brief-history-scaling-linkedin
https://engineering.linkedin.com/blog/2019/the-building-blocks-of-linkedin-skill-assessments
https://engineering.linkedin.com/blog/2019/04/under-the-hood--learning-with-documents
https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store
http://www.pitt.edu/~viz/classes/infsci3350/resources/linkedin_icde12.pdf


# Infrastructure Design and Implementation (3 modules, 1 hour each):  
All IDI problems will involve design --while they are divided into 3 different modules, you should not assume that concepts from one will not appear in the others. For all modules, feel free to ask clarifying questions, state your assumptions, think out loud and talk about tradeoffs. There are no perfect answers.  You’ll be evaluated on the complete module performance, not just the final solution. Solutions themselves are evaluated based on completeness, performance, scalability and fault tolerance if applicable. 

## IDI1 Concurrency
This module focuses on the concepts related to concurrency. Specifically: threads, locks, semaphores, race conditions, shared memory and data structures.  You’ll be presented with a problem where there is resource contention (implicit or explicit) and you’ll need to address it. While you can take advantage of the primitives provided by your language of choice, you’re still expected to understand the concepts, guarantees and implications.  


### delayed task
https://leetcode.com/problems/design-bounded-blocking-queue/
https://soulmachine.gitbooks.io/system-design/content/cn/task-scheduler.html
https://paper.dropbox.com/doc/--AnzuGuI6O2od4tDRqe6PN1VFAg-ONbDQkXzZv3bVw9i1sPvm

第二轮（多线程系统设计）：中国小哥
delay task scheduler，设计出来了，但是代码实现的时候受限于没怎么写过多线程code，各种语法和用法上错误。感觉答得比较炸，希望小哥能放我一马。

延迟任务规划器，写了完整的代码，讨论了conditional variable原理，lock，何时会抛出interrupted exception，executor的用法，threading pool的好处

Concurrency Coding, 先聊了很多同样的spark job在bare metal hardware上跑只要10分钟，在云端(Azure)上跑要20分钟，可能是什么造成的，有什么解决办法。这个聊了一半后让我实现一个blocking queue，讨论了下用condition，synchronized和semaphore的优劣最后拿semaphore实现的。这一轮国人大哥，中文面试，聊了很多career方面的内容。。。这一轮是最好的体验
多线程：写一个DelayedTaskQueue，要用mutex和condition variable做协同
system design：delay scheduler，参考java的写法。跟印度大哥半天解释不清楚，就直接把code写上去了，又写了一个client调用的code，印度大哥大姐很满意。感觉这轮更像算法轮。

6 - sys design 设计用户activity收集系统 这个系统需要回答一些analytical的问题 觉得本质就是OLAP系统

Design1: Delay Task Scheduler。下面这个讲的应该够了。是要实现的，不是pseudocode。
https://soulmachine.gitbooks.io/system-design/cn/task-scheduler.html
 设计单机延迟任务执行器，纠结了半天加锁分布式没时间答了
实现线程池/ExecutorService，跑需要延时的任务。

设计，韩国大叔，问了个消息队列的设计问题。开始扯了一大堆什么，问了requirement，从哪些方面考虑之类的。结果后来，他说你别扯这么多，设计个单机的，主要侧重在功能如何实现，Schema和API之类如何实现。中间走了不少弯路。教训是，一定先要把问题弄明白。上来就套Design那一套行不通

设计dealy task scheduler。不然用数据库，随便扯了会，当时准备了好几个follow up，但面试官都没有问。。。有点小郁闷

6> Design. 三哥 + 国人小哥 shadow
Design 一个 delayed task scheduler。use case 是user提交一个runnable 并设定开始时间，后台就在设定的时间运行这个任务，时间精度容差在1分钟。 需要实现的interface 就是 boolean submitJob() 。后台的server要怎样执行这些任务。
不是纯talking 要写code
楼主用treeset + linkedlist 维护按照时间sorted的任务list。在worker端while true 检查该不该run the latest job
目测没答到点子上。全程只有楼主自说自话，三个人的会议室仿佛是楼主的舞台。

## IDI2 Data Structures & Algorithms
This module focuses on data structures and algorithms. You’ll be given a problem and you’ll be expected to design one or more data structures that are able to solve the problem. You should be prepared to explain the algorithms and logic associated with the structure.  

 Data Structure design, 聊了下什么是columnar database，然后话锋一转说你有一个handler to a large file，问你怎么设计能够最少的读file拿到对应的数据类型。比如说我column 1是个integer，column 2是个string，用DataInputStream的各个method读数据。。。figure out面试官想要的效果已经是50分钟左右了，太vague，也是面的最差的一轮

6. 设计：设计一个基于内存的streaming系统，stream以(timestamp, binary_size)的消息进入，然后client会query以ts结束大小为k的内容。
Design2: Bounded Blocking Queue。准备店面的时候正好看过这个
http://n00tc0d3r.blogspot.com/2013/08/implement-bounded-blocking-queue.html

，三哥，上来没太多废话，题目设计 append only datastore like HDFS。听到题目还心中窃喜，因为对这块比较了解。哪知道三锅明显就不想让我过。一路各种打断，发问，challenge，各种挑毛病。他说的那些，我马上就准备说得。他就抢先挑出来。形成一种事态就是他带着我走。我很有种冲动跟他说 let me finish。后来应该找HR 投诉的。

1.设计分布式key val 存储，聊了会，感觉面试官想要的是分布式文件系统，反正顺着面试官的意思搞了。准备了好几次follow up，面试官都没有问。一直在问一些基本的序列化问题。好无聊

### k-v store
key value store，value体积比较大需要放在硬盘里面 另外随机写到硬盘会比较慢所以assume你要appending only
设计key value store，从读写低延迟，可靠性，拓展性出发，按照level db的思路答了。这轮感觉不是很好，被interviewer challenge了几次
sys_design 设计一个K/V store，支持基于单个KEY的insert/update/delete/fetch 操作，基本上照着RocksDB/Couchbase的实现来聊的

system design：k-v store。high level聊了了consistent hashing，以及如何加减virtual node。single machine聊了memcache的一些知识点，比如lru， slab allocation。着重聊了kv store的log structured database，如何insert，update， invalidate。因为平时工作接触的data base就是log structured database，所以答得比较顺利。
sys design 设计key value store，value体积比较大需要放在硬盘里面 另外随机写到硬盘会比较慢所以assume你要appending only

设计单机键值存储，纠结了半天加锁分布式没时间答了

Design a distributed hashtable

假设已有一些file system的API设计一个KV db。前提条件：

- key很短可以假设只有8bit，可以保证全部keys能够都存在内存中，但是value很大必须存在disk中。
- disk的random读写会非常慢，但是顺序写入会很快
- 假设已经有个file system提供了write read API，但是具体API的接口怎样还得自己定义.
- 实现get put delete

KV database	
https://www.youtube.com/watch?v=rnZmdmlR-2M

### inverted index
 第三轮，inverted index 以及不同的distribution sharding 方法的优劣讨论

publish and query. 存event id和内容，内容大小不定。然后query API是 getEventSince(id, int bufferSize), 求怎么存储数据可以更利于high concurrency。这题答得不太好，要了好多hint。最后面试官还说到想问lock，但是没时间了。

设计搜索引擎，inverted index
倒排索引很多怎么存可以快速查找

 设计一个 distributed inverted index.

  system design 就是这位国人大哥了 document index. 我说用map reduce 来build index，然后可以存在trie里。他不大想用trie，问我trie和hashmap有啥区别，我说tire节省空间，他表情不同意的样子。后来决定index决定存db里。问了如何scale，我说可以partition data。他说如何sharding，我说可以consistent hashing。他说具体怎么做，我要正要画个圈给他讲consitent hashing，他说不用算法，我们是high level system design，我就给他画了web server，load balancer，db的图（according to recruiter，他说他需要提醒我需要从high level design的方向思考）。中间提到了不同db的优缺点，如果搜集top k的关键字，我讲了memcached和redis的优缺点，和如何按bucket存频率。

document repository (indexing system)	https://zhuanlan.zhihu.com/p/32990496
streaming	1. create index
2. search with index	create index with streaming of the file - inverted index - mapreduce
replica - important - make traffic less


## IDI3 Complex Systems
This module focuses on large (distributed) systems. You will be given a scenario and end goal and will be asked to design a system that can meet the requirements.  You’re expected to understand how to break down a problem into components and how the components interact with each other. You should be able to describe the solution at a high level and go into the detail of each component. 

## Design Message Streaming System。
 “client会发1B-16M的message到server，server要把message存在memory里并返回一个id给client，client可以用id fetch message。
很细致的讨论了存哪些metadata，每个metadata到底占多少空间，为什么每个message都存metadata会非常不efficient，怎么样优化存metadata的方式等等。”
Design a system to support multiple reader and single writer, given the data are only appended. (A simplified Kafka).
Design 一个message streaming system. 每个message 可以有 4Bytes ~ 16 KB.
需要考虑的有retention, work load, memory overflow, race condition。只需要考虑一个machine。
不说 high level idea， 说具体的components, data structure

### 2nd degree，3rd degree friends
Given isFriend(x,y) and a node X, how can you find all nodes which is one-degree connection, two-degree, and three-degree with X.
给定一个 int[] getFridend(int user)，O(1) complexity, 求两个users 是不是一级联系，二级联系和三级联系。先在local解，后来问图很大，怎么scale到多个machine上

设计领英0,1,2,3朋友圈的具体算法。实现就是双向bfs，2,3度用双指针。最后还可以继续用hashset来优化时间复杂度。印度小哥积极引导，我也一直在顺着他说，最后总算是都写完了。

2. 设计灵隐， 主要在朋友关系计算。
设计 linkedin friend relationship system
面经找几级朋友那题
Friend relationship	
https://engineering.linkedin.com/real-time-distributed-graph/using-set-cover-algorithm-optimize-query-latency-large-scale-distributed



### Metrics Collect Monitor System
有点像GCP的logging systems和StackDriver

- LinkedIn data pipeline monitor system
https://engineering.linkedin.com/blog/2019/an-inside-look-at-linkedins-data-pipeline-monitoring-system-
https://engineering.linkedin.com/blog/2019/06/smart-alerts-in-thirdeye--linkedins-real-time-monitoring-platfor
https://engineering.linkedin.com/blog/2019/01/introducing-thirdeye--linkedins-business-wide-monitoring-platfor
https://engineering.linkedin.com/blog/2016/04/inmesh--real-time-monitoring-of-remote-sites

- Senario
关键字: hundreds of datacenters
       hundreds of services in each datacenter
       multiple type of events may occur in each service
       监控100个server，需要从display到存储到采集数据整个stack都涉及到

- Service & Storage
要求：  收集 - collect all events for each service
       聚合 - metrics aggregation 
       存储 - 
       查询 - user can monitior it in real time (dashboard,前端display)
       通知 - if events exceed threshold, alert notify groups. (pull vs push)
      
典型的streaming processing achitecture.
message queue(kafka)+workers(storm)+database+cache+notification service(response queue).

- Scale
注意:  1. collect events 是用pull还是push, notification是用pull还是push 聊清楚trade off.
      2. 最新的数据process完 写进db的同时 可以直接放在cache里面，因为很可能被读
      3. 要注意engineer 在线和不在线的情况
      4. 哪种event 需要通知哪个engineer 可以是一个pub sub的架构也可以存成一个static table or key value
      5. 如何scale，如果是10000 hosts怎么收集
      6. 要保证用户付了钱的调用流量都允许
      7. 提供各种方案，kafka，HDFS，pub-sub model

# Top k exception (Kafka) LFU cache
3. IDI Concurrency：delayed task scheduler
国人小哥迟到了15分钟...一直跟我道歉. 其实这题跟concurrency关系不大，我读过面经，一直在往condition variable那边靠，像blocking queue那种设计，wait() + notify()
但聊到最后，发现heap+hashmap（<timestamp, taskid>）就可以了...还聊了一会heap的原理，heapify up/down，O(log(n)) add/peak，聊偏了...
重点是深入rolling window这个ds：怎么删去expire的data，如果靠incoming message trigger expired data deprecation，一直没有incoming message怎么办。假设这个fixed sized window可以fit进memory

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

6. IDI Complex Systems：tiny url
烙印火急火燎大哥带个还算nice的shallow烙印小哥，大哥全程一直看手机，说话也火急火燎，感觉不怎么听我说的，给feedback的时候也都说“I think that works, that looks good”...体验蛮差的一轮
主体部分常规设计：NoSQL + hashing + offline key generation server + caching. 说了db design，算了QPS数据，db sharding
有趣的是最后有个followup，如果要收集telemetry data，analyse client request/geo location这些东西，我说可以用data streaming queue - kafka (跪舔linkedin家的当家产品)


Tiny URL
设计数据结构，能够add(), remove(), randomremove() in O(1)复杂度
Monitor System
设计Amazon Product Page, 就是在SQL里面一个产品有多个图片多个价格的话怎么设计数据库。然后后台提取数值render到页面上得时候，class怎么设计，服务器怎么安排之类的, 中间也有讨论怎样给suggest product，我提到可以建一个Product weighted graph, 然后用BFS
linkedin有很多server在做很多不同的事情，设计一个系统，监测24小时之内top 500的exceptions
dashboard to monitor the top shared url in the last 5 minutes
对于key,value pairs， 在给定的文件系统中实现 put，get，delete 的方法。其中key比较小，全部key可以放在内存中，value有的会比较大。已知一个文件系统，可以create files, delete files, sequentially scan file content, read file content randomly, append file content.
已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends。
Design LinkedIn.
search功能里inverted index 和data of user , data of company 怎么存，分别用Nosql还是sql？然后设计timeline， 问我push/pull模型在哪儿看的
design the backend of linkedin, 讨论各个service如何实现
design a notebook application like Evernote or Onenote, it should support search, collaboration
社交网站上的文章转发，如何设计系统可以得到实时的转发量榜单和weekly digest，要求数据库的设计，有人转发一个文章时request是什么样的，如何快速得到实时的转发量榜单，如何得到weekly digest等。
design a system to block malicious IPs
a restful server with 4GB,  
given a request such as: http://seq=4?len=60?xxxxdata
the system will store the binary data with that sequence number.
given a request: http://startseq=3?maxLen=100, the system returns all data objects with sequence >= 3 with total data length less equal than 100.
multiple clients calling simutaneous
what data structure, concurrency, locking, etc..
问了一个类似dropbox的系统设计问题
设计Delay Scheduler，能够把task schedule在特定的时间执行。
设计一个Message Broker
when a new version of API 上线，怎么和client side 协调好切换版本，出问题了rollback 怎么做



楼主是9.23 onsite，之前总结了地理2019年system design的面经。
领英的System Design分两种（根据地理的信息总结的，不正确的地方欢迎指正）：
Infra Track：Infrastructure, Design & Implementation （IDI）
其他：System Design & Architecture（SDA）

因为楼主面的是app track，所以 SDA 总结的比较仔细，IDI只是简单的放在了other session:

Short url:
短地址设计, 主要关注下面四个方面
   1. 怎么生成短地址
   2. 怎么存储 - 主要是分析Sql和Nosql的优劣
   3. Redirect
   4. 如何统计被访问最多的地址

shorten url的各种变种，比如新添feature：click stats，就是统计每个short url被read多少次。反正把自己能说的都说了，也不知道感觉怎么样。
tiny url设计，而且对于每个url被访问了多少次，能够输出过去24小时之内的总访问量以及给定一个时间范围的总访问量；先画设计图讲了大概的思路，然后面试官会针对设计的各种部分提问，比如pre-generator怎么保证新的url不和已经被用过的重复，url expire的policy怎么设计，统计访问量在有多个server的时候怎么实现，需不需要cache，如果某个server down了怎么保证访问量的数据没有丢失等等

Document index:
inverted index 以及不同的distribution sharding 方法的优劣讨论。楼主自己的onsite是这个，传送门 https://www.1point3acres.com/bbs/thread-556189-1-1.html
blacklist service
设计一个全球范围内的blacklist service，就是有很多恶意ip会发来ddos攻击，你要设计一个blacklist的服务，能够ban掉之前已经诊断为malicious ip发过来的请求。这里不要求你设计怎么样判断一个ip是否是恶意ip，给了个isMalicious()的api signature。难点在于不同data center之间怎么sync数据，availability和consistency怎么取舍。哪里会有single point of failure，然后怎么设计能解决。最后folowup就是结合你的工作经验问这个服务上线之后你最想加一个什么功能，不一定是functional的，可以是logistics上的。面试官比较期待的答案是support和monitoring之类的。
注意ipv4 和ipv6的存储上的区别


calender
1. 创建一个event
2. 看自己的events
3. 发notifications 给attendees
就是怎么建表更快查询其他人schedule面试官提出异议，
给了个方案，就是再建一个表，只存meeting id和user id，然后说这样join少。我也不太懂，平时不摆弄sql和table这些，点头称是，讨论完这个时间到了

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

Others:
给定可用内存的 mini Kafka， 重点考察内存中数据怎么存
Espresso database设计过程中怎么处理hot point的问题，和key的rebalance有关系

你们是怎么处理GDPR的

假设他是副主席，我是他的打手，他现在要迭代一个老的管登陆的服务，没有任何文档，可能有几个人有一些了解，给我一些资源。问我怎么把这个事情弄完。说了十五分钟，讲了流程和可能会碰到的各种情况，然后怎么处理。

你有alexa，go through Amazon，连到fb，要求你实现功能，例如：说一句，把我fb account上最近5条信息读出来。然后，第二句，回复第二条信息。这个怎么实现。要多少时间实现。怎么判断第二句话跟前面一句话有关系。这时候我只能祭出ML两个字...无奈...没get到到底考什么。


4- sys design 设计key value store，value体积比较大需要放在硬盘里面 另外随机写到硬盘会比较慢所以assume你要appending only k-v store。high level聊了了consistent hashing，以及如何加减virtual node。single machine聊了memcache的一些知识点，比如lru， slab allocation。
着重聊了kv store的log structured database，如何insert，update， invalidate。因为平时工作接触的data base就是log structured database，所以答得比较顺利。
5 - sys design 设计monitoring system监控100个server，需要从display到存储到采集数据整个stack都涉及到 类似地理说的Kafka加上aggregator的设计方式。
6 - sys design 设计用户activity收集系统 这个系统需要回答一些analytical的问题 觉得本质就是OLAP系统
.系统设计 statistics aggregation system 我这轮知无不言 但无奈所知实在有限
IDI之一，要求设计一个系统，存储在线用户的在网站上的活动。假定每次用户登录都能用一个UUID来跟踪，用户没走一步会有一些Payload数据（假定是一个100K以内的文本字符串），设计一个系统来存贮这些数据。
IDI之二，Delayed Task Scheduler，要求实现写代码。
设计一个系统监督和管理领英第三方API的流量

希望对大家有帮助