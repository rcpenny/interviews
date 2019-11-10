# K-V Store 
Design a distributed Database

## 资料
1. https://www.youtube.com/watch?v=rnZmdmlR-2M
2. https://soulmachine.gitbooks.io/system-design/content/cn/key-value-store.html
3. https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store
4. JIUZHANG PPT

## Senario
1. key很短可以假设只有8bit，可以保证全部keys能够都存在内存中，但是value很大必须存在disk中。
2. disk的random读写会非常慢，但是顺序写入会很快
3. 对于key,value pairs， 在给定的文件系统中实现 put，get，delete 的方法
   假设已经有个file system提供了write read API，但是具体API的接口怎样还得自己定义.
   实现get put delete
   着重聊了kv store的log structured database，如何insert，update， invalidate。
4. 已知一个文件系统，可以create files, delete files, sequentially scan file content, read file content randomly, append file content.

## High Level 
1. high level聊了了consistent hashing
2. 以及如何加减virtual node
3. single machine聊了memcache的一些知识点，比如lru， slab allocation。
4. 从读写低延迟，可靠性，拓展性出发，按照level db的思路答了

## 知识点
1. 一直在问一些基本的序列化问题

假设已有一些file system的API设计一个KV db。前提条件：

 Data Structure design, 聊了下什么是columnar database，然后话锋一转说你有一个handler to a large file，问你怎么设计能够最少的读file拿到对应的数据类型。比如说我column 1是个integer，column 2是个string，用DataInputStream的各个method读数据。。。figure out面试官想要的效果已经是50分钟左右了，太vague，也是面的最差的一轮

Espresso database设计过程中怎么处理hot point的问题，和key的rebalance有关系
