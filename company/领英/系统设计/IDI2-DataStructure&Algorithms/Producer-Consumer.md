## Producer Consumer
## 大意
Design a Message Streaming to support N Producers and M consumers

## 资料
- 题目 https://leetcode.com/problems/design-bounded-blocking-queue/
-  

## Senario
- 先不考虑系统级别设计，假设是single machine，设计具体的Schema, Data Structure, API etc.
- Message以(timestamp, binary_size)的形式发送到stream中的，message size (1Byte ~ 16MB) / (4Bytes ~ 16 KB) anyway
- message可以存在memory中并返回一个id给client，用户可以用id fetch message
- 用户可以查询以timestamp为结束，大小为k的内容  query(timestamp, k)

## Detailed
- 存哪些metadata，每个metadata到底占多少空间?
- 为什么每个message都存metadata会非常不efficient?
- 怎么样优化存metadata的方式?
- message retention?
- work load?
- memory overflow?
- race condition?

https://tech.meituan.com/2016/07/01/mq-design.html
https://segmentfault.com/a/1190000018640106
https://www.jianshu.com/p/38812c5497bc
https://www.jianshu.com/p/36a7775b04ec


Push(seq num, Payload);
Push need not have consecutive keys. But keys pushed are monotonically increasing.

Payload Pull(num);
Pull can be for any seq number.
If the seq number is not found, return the payload for the immediate next higher number. If none exists, return null.

Container has fixed amount of memory.
Once an item is pulled, its unavailable for other pulls and can be discarded by the container.
When container is full, you can delete lowest sequence numbers.

Requirement: Push and Pull have to be O(1) in time.

http://n00tc0d3r.blogspot.com/2013/08/implement-bounded-blocking-queue.html
