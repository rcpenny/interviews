# Coding problems
finish linkedin tags twice

# behavior Questions
https://www.linkedin.com/interview-prep/assessments/urn:li:fs_assessment:(1,a)/question/urn:li:fs_assessmentQuestion:(10011,aq11)/

https://business.linkedin.com/content/dam/me/business/en-us/talent-solutions/resources/pdfs/linkedin-30-questions-to-identify-high-potential-candidates-ebook-8-7-17-uk-en.pdf

https://business.linkedin.com/content/dam/me/business/en-us/talent-solutions/resources/pdfs/Guide-to-screening-candidates-30-essential-behavioral-interview-questions-ebook.pdf

# System design 总结
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=446923
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=465815

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175538&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D31

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=556281&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline

https://engineering.linkedin.com/blog/2019/the-building-blocks-of-linkedin-skill-assessments

https://engineering.linkedin.com/blog/2019/04/under-the-hood--learning-with-documents

3. https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store

http://www.pitt.edu/~viz/classes/infsci3350/resources/linkedin_icde12.pdf

## Top k exception (Kafka)
经典题，24小时的top k exception, 然后支持5min的top k，1hr的top k，1年的top k。这个绝对是领英家第一高频题了，都是套一个kafka就行
https://www.1point3acres.com/bbs/thread-159538-1-1.html 
https://www.jiuzhang.com/qa/219/
https://www.bookstack.cn/read/system-design/cn-bigdata-heavy-hitters.md

## k-v store (设计VOLDEMORT)
key value store，value体积比较大需要放在硬盘里面 另外随机写到硬盘会比较慢所以assume你要appending only 

## Tiny URL （post功能）
(https://www.linkedin.com/help/linkedin/answer/3439/short-urls-in-shared-posts?lang=en)
短地址设计, 主要关注下面四个方面
   1. 怎么生成短地址
   2. 怎么存储 - 主要是分析Sql和Nosql的优劣
   3. Redirect
   4. 如何统计被访问最多的地址

4. 设计一个monitoring 系统, 需要实时监测System Level (cpu usage, network usage ...)和 app level (exception, error...) 的 matrix.‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍ 然后要可以发notification给owner

5. blacklist API 
设计一个全球范围内的blacklist service，就是有很多恶意ip会发来ddos攻击，你要设计一个blacklist的服务，能够ban掉之前已经诊断为malicious ip发过来的请求。这里不要求你设计怎么样判断一个ip是否是恶意ip，给了个isMalicious()的api signature。难点在于不同data center之间怎么sync数据，availability和consistency怎么取舍。哪里会有single point of failure，然后怎么设计能解决。最后folowup就是结合你的工作经验问这个服务上线之后你最想加一个什么功能，不一定是functional的，可以是logistics上的。面试官比较期待的答案是support和monitoring之类的。

6. 设计：设计一个基于内存的streaming系统，stream以(timestamp, binary_si‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍ze)的消息进入，然后client会query以ts结束大小为k的内容。

7. onboarding Q&A implementation

8. 系统设计：centralized logging
 monitoring system. 关键字 hundreds of datacenters,  hundreds of services in e‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‌‌ach datacenter, multiple type of events may ocuur in each service. 要求，collect all the events for each service, user can monitior it in real time, if some event exceed the threshold, corresponding engineers will get a notification. 典型的steaming processing的achitecture. message queue(kafka)+workers(storm)+database+cache+notification service(response queue). 需要注意的点就是 1 collect events 是用pull还是push, notification是用pull还是push 聊清楚trade off. 2最新的数据process完 写进db的同时 可以直接放在cache里面，因为很可能被读。3要注意engineer 在线和不在线的情况。4 哪种event 需要通知哪个engineer 可以是一个pub sub的架构也可以存成一个static table or key value. 这一轮面试官40分钟问完，问了很多细节的问题，因为我比较熟，所以面试官非常满意。还剩20分钟 说我表现得非常好，他一边给我写feedback 一边让我问问题。
