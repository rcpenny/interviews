# Caching - 缓存

## 好处
1. 更好更快的利用你已有的resource, data
2. 让一些很难实现的feature变得可行


## 存什么
1. 高频查询数据的副本 (e.g. 高频read的短网址) 
2. 提前计算好的答案 pre-caculated result (e.g. 过去24小时的热搜)
3. 提前生成的索引数据 pre-generated expensives (e.g. Youtube推荐视频)


## 在哪缓存
系统价构中几乎所有地方都可以缓存 (CDN, 硬件,OS,浏览器 etc.)
### 应用层缓存 Application Cache
1. 需要在应用代码中具体集成实现
2. 先查数据是否在缓存，不在则去数据库取，然后再存入缓存 (LRU) Read Through Cache
![alt text](https://github.com/rcpenny/interviews/blob/master/design/sys-design/statics/app-cache.png)
## 数据库缓存 Database Cache
1. 数据库缓存的魅力之一是不用写应用层级的代码就能获得性能提升
2. 数据库参数优化缓存之后能减少I/O，降低请求延迟
![alt text](https://github.com/rcpenny/interviews/blob/master/design/sys-design/statics/db-cache.png)
## 内存缓存 In-memory Cache
1. 最有力，最直接提高性能的缓存（Redis, MemCached）
2. 因为存在RAM上，所以缓存容量不如硬盘，常用策略是LRU 


## Scale 缓存系统
1. 如果请求会定点到特定服务器上，那每个服务器自建缓存是可以这么做的。
2. 如果负载均衡器给请求随机分配服务器，这会降低缓存命中，两个解决办法是：全局缓存，分布式缓存。


## Cache invalidation
- 缓存很棒，但是缓存需要额外的维护来保持与source of truth一致。否则会导致应用的不一致行为。
- http://bit.ly/2Y1GxCw
- 解决这个问题的办法称为cache invalidation，主要有三种方式：
### write-through cache:
1. 数据同时写入缓存与数据库，保持一致性，也保证了缓存系统奔溃时不会导致数据丢失。
2. 虽然这种方式降低了数据丢失的风险，但因为每次写操作需要执行两次，所以这种策略有更高的延迟。
### write-around cache:
Write-around cache: This technique is similar to write through cache, but data is written directly to permanent storage, bypassing the cache. This can reduce the cache being flooded with write operations that will not subsequently be re-read, but has the disadvantage that a read request for recently written data will create a “cache miss” and must be read from slower back-end storage and experience higher latency.
### write-back cache:
1. 先写入缓存，立即返回给用户，一段时间后在满足一些条件之后，最终写入数据库。
2. 优点：低延迟且 提供高写系统很大的
Under this scheme, data is written to cache alone and completion is immediately confirmed to the client. The write to the permanent storage is done after specified intervals or under certain conditions. This results in low latency and high throughput for write-intensive applications, however, this speed comes with the risk of data loss in case of a crash or other adverse event because the only copy of the written data is in the cache.

## 缓存删除策略
1. FIFO
2. LIFO
3. LRU
4. MRU
5. LFU
6. RR: random replacement