## Message Streams (Mini Kafka Streams)
Design a Message Streaming System to support Producers Consumers

## 资料
- 题目 https://leetcode.com/problems/design-bounded-blocking-queue/
- LinkedBlockingQueue: https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/LinkedBlockingQueue.java
- Kafka Streams API: https://github.com/apache/kafka/tree/trunk/streams/src/main/java/org/apache/kafka/streams
- example: http://n00tc0d3r.blogspot.com/2013/08/implement-bounded-blocking-queue.html

## Senario
- 先不考虑系统级别设计，假设是single machine，设计具体的Schema, Data Structure, API etc.
- 设计方法 push(timestamp, payload)将 message 发送到 stream 中，payload有范围，比如（4B ~ 16KB).
  方法也可以是push(id, payload),id不用连续但是得单调递增.
- messages被存在一个有fixed amount memory的container中, 用户可以用id pull message
- 设计方法 pull(id), 返回对应的payload。 或者pull(timestamp, k),表示timestamp前大小为K的payload（这个不确定）
- Pull完message后从memory里删除message, memory full时
- push与pull方法需要时间O(1)

## Detailed
- 存哪些metadata，每个metadata到底占多少空间?
- 为什么每个message都存metadata会非常不efficient?
- 怎么样优化存metadata的方式?
- message retention?
- work load?
- memory overflow?
- race condition?

## LinkedBlockingQueue摘选
```java

```
