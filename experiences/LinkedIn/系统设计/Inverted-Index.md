### Inverted Index 
(LinkedIn Search)
设计搜索引擎，inverted index 倒排索引很多怎么存可以快速查找

 设计一个 distributed inverted index.

  system design
  就是这位国人大哥了 document index. 我说用map reduce 来build index
  后来决定index决定存db里。问了如何scale，我说可以partition data 他说如何sharding，我说可以consistent hashing
  我们是high level system design，我就给他画了web server，load balancer，db的图（according to recruiter，他说他需要提醒我需要从high level design的方向思考）
  中间提到了不同db的优缺点，如果搜集top k的关键字，我讲了memcached和redis的优缺点，和如何按bucket存频率


1. search with index	
create index with streaming of the file - inverted index - mapreduce
replica - important - make traffic less

Document index:
inverted index 以及不同的distribution sharding 方法的优劣讨论。楼主自己的onsite是这个，传送门 https://www.1point3acres.com/bbs/thread-556189-1-1.html

search功能里inverted index 和data of user , data of company 怎么存，分别用Nosql还是sql？然后设计timeline， 问我push/pull模型在哪儿看的
design the backend of linkedin, 讨论各个service如何实现

https://www.mtyun.com/library/adp-rtidx-ls

https://www.youtube.com/watch?v=CeGtqouT8eA

4S.

Senario   search for linkedin   DAU, MAU, query....
service    search() build inverted index

https://engineering.linkedin.com/architecture/brief-history-scaling-linkedin