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

## Cache Invalidation
While caching is fantastic, it does require some maintenance for keeping cache coherent with the source of truth (e.g., database). If the data is modified in the database, it should be invalidated in the cache; if not, this can cause inconsistent application behavior.

Solving this problem is known as cache invalidation; there are three main schemes that are used:

Write-through cache: Under this scheme, data is written into the cache and the corresponding database at the same time. The cached data allows for fast retrieval and, since the same data gets written in the permanent storage, we will have complete data consistency between the cache and the storage. Also, this scheme ensures that nothing will get lost in case of a crash, power failure, or other system disruptions.

Although, write through minimizes the risk of data loss, since every write operation must be done twice before returning success to the client, this scheme has the disadvantage of higher latency for write operations.

Write-around cache: This technique is similar to write through cache, but data is written directly to permanent storage, bypassing the cache. This can reduce the cache being flooded with write operations that will not subsequently be re-read, but has the disadvantage that a read request for recently written data will create a “cache miss” and must be read from slower back-end storage and experience higher latency.

Write-back cache: Under this scheme, data is written to cache alone and completion is immediately confirmed to the client. The write to the permanent storage is done after specified intervals or under certain conditions. This results in low latency and high throughput for write-intensive applications, however, this speed comes with the risk of data loss in case of a crash or other adverse event because the only copy of the written data is in the cache.

## Cache eviction policies
Following are some of the most common cache eviction policies:

1. First In First Out (FIFO)
2. Last In First Out (LIFO)
3. Least Recently Used (LRU): Discards the least recently used items first.
4. Most Recently Used (MRU): Discards, in contrast to LRU, the most recently used items first.
5. Least Frequently Used (LFU): Counts how often an item is needed. Those that are used least often are discarded first.
6. Random Replacement (RR): Randomly selects a candidate item and discards it to make space when necessary.