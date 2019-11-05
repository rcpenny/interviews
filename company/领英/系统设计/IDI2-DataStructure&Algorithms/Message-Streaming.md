## Message Streaming (simplified Kafka)

- 资料
1. 题目 https://leetcode.com/problems/design-bounded-blocking-queue/
2. 

 “client会发1B-16M的message到server，server要把message存在memory里并返回一个id给client，client可以用id fetch message。
很细致的讨论了存哪些metadata，每个metadata到底占多少空间，为什么每个message都存metadata会非常不efficient，怎么样优化存metadata的方式等等。”

Design a system to support multiple reader and single writer, given the data are only appended. (A simplified Kafka).

Design 一个message streaming system. 每个message 可以有 4Bytes ~ 16 KB.
需要考虑的有retention, work load, memory overflow, race condition。只需要考虑一个machine。
不说 high level idea， 说具体的components, data structure

1. 设计：设计一个基于内存的streaming系统，stream以(timestamp, binary_size)的消息进入，然后client会query以ts结束大小为k的内容。
Design2: Bounded Blocking Queue。准备店面的时候正好看过这个
http://n00tc0d3r.blogspot.com/2013/08/implement-bounded-blocking-queue.html

给定可用内存的 mini Kafka， 重点考察内存中数据怎么存

设计，韩国大叔，问了个消息队列的设计问题。开始扯了一大堆什么，问了requirement，从哪些方面考虑之类的。结果后来，他说你别扯这么多，设计个单机的，主要侧重在功能如何实现，Schema和API之类如何实现。中间走了不少弯路。教训是，一定先要把问题弄明白。上来就套Design那一套行不通

设计一个Message Broker
