# Tiny URL （LinkedIn Post）

- Senario(func/non-func): 
   1. long URl-> short URL 
   2. short URL -> long URL, redirect
   3. QPS + STORAGE (DAU, shortURL creation QPS, click shortURL QPS, URL-SIZE storage daily)
   4. set expiration time(opt)
   5. highly available / URL redirection in real-time with minimal latency.
   6. Links will expire after a standard default timespan

## Service
- URL service(generate URL service, parse URL service)

- URLservice.decode() GET/short_url return HTTP redirect response (301)
   
- URLservice.encode() POST/data/shorten/ Data ={url:"baidu.com"} return short url
  1. 算法1: 随机生成，去重 缺点越来越慢 DB（short key -> long URL), create index for K,V  NoSQL, two tables
  2. 算法2: A-Za-z0-9 Base62, make it primary key - sequantial ID, 优点：效率高  缺点依赖全局自增ID

## Storage
   1. SQL vs NoSQL 自行比较
   2. encode算法(随机生成，进制转换)

## Scale
- 提高响应速度 （more read than write)
  1. 提速1（Cache） long -> short  short -> long 先cache，再DB
  2. 提速2 优化web server visit speed, use DNS to distribute users to different web servers
  3. 提速3 centralized MySQL + distributed Memcahced(共用DB，cache各自对应)
- 扩展？ 多台数据库解决（Storaget存不下，QPS忙不过） horizontal sharding, sharding key


而且对于每个url被访问了多少次，能够输出过去24小时之内的总访问量以及给定一个时间范围的总访问量
pre-generator怎么保证新的url不和已经被用过的重复，
url expire的policy怎么设计，统计访问量在有多个server的时候怎么实现，
如果某个server down了怎么保证访问量的数据没有丢失等等
如果要收集telemetry data，analyse client request/geo location这些东西
