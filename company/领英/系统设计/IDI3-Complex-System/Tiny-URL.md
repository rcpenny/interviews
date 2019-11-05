
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