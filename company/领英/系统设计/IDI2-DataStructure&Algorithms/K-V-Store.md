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

 Data Structure design, 聊了下什么是columnar database，然后话锋一转说你有一个handler to a large file，问你怎么设计能够最少的读file拿到对应的数据类型。比如说我column 1是个integer，column 2是个string，用DataInputStream的各个method读数据。。。figure out面试官想要的效果已经是50分钟左右了，太vague，也是面的最差的一轮

，三哥，上来没太多废话，题目设计 append only datastore like HDFS。听到题目还心中窃喜，因为对这块比较了解。哪知道三锅明显就不想让我过。一路各种打断，发问，challenge，各种挑毛病。他说的那些，我马上就准备说得。他就抢先挑出来。形成一种事态就是他带着我走。我很有种冲动跟他说 let me finish。后来应该找HR 投诉的。

design a notebook application like Evernote or Onenote, it should support search, collaboration


KV database	
https://www.youtube.com/watch?v=rnZmdmlR-2M

4- sys design 设计key value store，value体积比较大需要放在硬盘里面 另外随机写到硬盘会比较慢所以assume你要appending only k-v store。high level聊了了consistent hashing，以及如何加减virtual node。single machine聊了memcache的一些知识点，比如lru， slab allocation。
着重聊了kv store的log structured database，如何insert，update， invalidate。因为平时工作接触的data base就是log structured database，所以答得比较顺利。

Espresso database设计过程中怎么处理hot point的问题，和key的rebalance有关系

对于key,value pairs， 在给定的文件系统中实现 put，get，delete 的方法。其中key比较小，全部key可以放在内存中，value有的会比较大。已知一个文件系统，可以create files, delete files, sequentially scan file content, read file content randomly, append file content.
