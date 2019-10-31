# system-infra onsite 整理
1. 离口261，这轮主要是讨论很多edge case和error checking，有些input不一定是valid的。
最后考了一道lowest ancestor，普通二叉树和bst，只讨论了思路。
2. 第一题，给一个数组和一个target，问有多少个subset可以相加得到这个target，第二题利口149
3. manager轮，讨论了过往的经历，几个bq，问了怎么处理legacy code
4. 设计key value store，从读写低延迟，可靠性，拓展性出发，按照level db的思路答了。这轮感觉不是很好，被interviewer challenge了几次
5. top k article，5分钟，1小时，一天的，问了怎么传输，怎么存储计算，有几个模块等等，这轮感觉都答上了。
6. 延迟任务规划器，写了完整的代码，讨论了conditional variable原理，lock，何时会抛出interrupted exception，executor的用法，threading pool的好处
我说了kafka用于传输，log用于recovery，cache用于存bucket，aggregation service用于计算，web server用于处理request，但这轮feedback不好.....

第一轮（算法）：印度小哥
第一题刷题网611 后续是有重复怎么办，正确做法是先找3边都一样的再找两边一样的，最后找都不一样的。
第二题找区间的总覆盖，两个最小堆头尾排序就能解。后续是O(n)的解法，用hashmap，但条件是数据量不大。
第二轮（多线程系统设计）：中国小哥
delay task scheduler，设计出来了，但是代码实现的时候受限于没怎么写过多线程code，各种语法和用法上错误。感觉答得比较炸，希望小哥能放我一马。
第三轮（午饭）：中国小哥
全程中文交流，体验极佳。
第四轮（host manager）：国人大叔
全程如履薄冰，问题问的都非常非常细，甚至问到了目前项目的一些具体时间线。之后出了一题看我如何找unknown bug的位置
第五轮（分布式系统设计）中国小哥+中国小姐姐
tinyURL，没啥好说的。把能说都说完之后正好55分钟。
第六轮（数据结构和算法设计）中国小姐姐+印度小哥
设计领英0,1,2,3朋友圈的具体算法。实现就是双向bfs，2,3度用双指针。最后还可以继续用hashset来优化时间复杂度。印度小哥积极引导，我也一直在顺着他说，最后总算是都写完了。
第七轮（算法）中国小姐姐+国人阿姨
第一题刷题网160，后续是带环。我说可以先找环然后把环去掉正常做，最后再接回来。然后不让改输入，用flag解出来了。
第二题刷题网256，后续是265。之后又详细讨论了265不同解法的时间和空间复杂度。之后问除了dp还能怎么解，我说用dfs + pruning，之后又说了下dfs，pruning和dp之间的关系。
第二轮需要先设计一下然后再用代码实现，第六轮需要详细分析时间和空间复杂度，预计的大小和储存方式。

 system design 就是这位国人大哥了 document index. 我说用map reduce 来build index，然后可以存在trie里。他不大想用trie，问我trie和hashmap有啥区别，我说tire节省空间，他表情不同意的样子。后来决定index决定存db里。问了如何scale，我说可以partition data。他说如何sharding，我说可以consistent hashing。他说具体怎么做，我要正要画个圈给他讲consitent hashing，他说不用算法，我们是high level system design，我就给他画了web server，load balancer，db的图（according to recruiter，他说他需要提醒我需要从high level design的方向思考）。中间提到了不同db的优缺点，如果搜集top k的关键字，我讲了memcached和redis的优缺点，和如何按bucket存频率。

1. Coding: 利口而其而，经典面筋题。
我先说了in order traverse拿到sorted array + bi-directional expanding window的常规O(N)解，然后说优化空间可以用findPredecessor+findSuccessor（predecessor好难读啊我舌头烂了）面试官说这个你写出来我给你算bonus，pred/succ不好写。我说没事...我试试...
一开始按照利口解法，每次return一个predecessor/successor，面试官提醒我你不如一次return k个？反正已经traverse了。
2. IDI DS&Algorithm：类似top k的设计，需要return 5min，1hour，24hour之内的数据整合。这里问的不是top k，而是mean - 股票交易系统，经常有price update（incoming message），GetAvg会get过去不同时间区间内的average price. 需要O(1)
重点是深入rolling window这个ds：怎么删去expire的data，如果靠incoming message trigger expired data deprecation，一直没有incoming message怎么办。假设这个fixed sized window可以fit进memory
跟面经不太一样...没有很熟bucket，卒
3. IDI Concurrency：delayed task scheduler
国人小哥迟到了15分钟...一直跟我道歉. 其实这题跟concurrency关系不大，我读过面经，一直在往condition variable那边靠，像blocking queue那种设计，wait() + notify()
但聊到最后，发现heap+hashmap（<timestamp, taskid>）就可以了...还聊了一会heap的原理，heapify up/down，O(log(n)) add/peak，聊偏了...
4. Host Manager: 非常厉害的一个director大哥，以前是做professor的，也在air force工作过，前一天stalk linkedin的时候我就五体投地...
一开始简单BQ，聊了一些why linkedin，找组看中什么，甚至问了我现在还勾搭了哪些公司+什么组...(???)
然后给了个实际的scenario：有个network issue需要你处理，从接到ticket开始你怎么处理。主要侧重behaviorial，比如第一件事先ack，然后找谁，要什么metrics，不同short term/long term option怎么选
director地位高架子可不大，下一个interviewer来的时候还主动offer帮我擦黑板，说你们先聊着我来干这个...
大哥出去的时候，我膝盖已经在地上

5.Coding：利口 留坝
感觉遇到了非典型题...我刚翻了一下这题算hard，是不是不想hire我...大哥出题前还说“let's do a warm up question first...” 最后就做了这一题还没磕磕碰碰没完全写完，大哥出去的时候脸色很难看，卒

6. IDI Complex Systems：tiny url
烙印火急火燎大哥带个还算nice的shallow烙印小哥，大哥全程一直看手机，说话也火急火燎，感觉不怎么听我说的，给feedback的时候也都说“I think that works, that looks good”...体验蛮差的一轮
主体部分常规设计：NoSQL + hashing + offline key generation server + caching. 说了db design，算了QPS数据，db sharding
有趣的是最后有个followup，如果要收集telemetry data，analyse client request/geo location这些东西，我说可以用data streaming queue - kafka (跪舔linkedin家的当家产品)
两人露出了“哦，有意思哦”的表情，追问了下去...可惜楼主的kafka是面试前两天恶补的，空有其表，就细节追问的时候我只能报以尴尬不失礼貌的微笑...

host manager比其他所有轮都重要，对linkedin来说这一轮面好了就成功了一半。因为hm会进hc，并且有一票，他又是interviewer他的feedback和愿不愿意帮你会极大程度上影响hc的决定。hr给我说的原话，如果hm想招你，你其他面得不那么好也有机会。我顺便问了一下，如果其他轮很好，但hm不想招会怎么样。他的回答是在他印象里面最后不招的更多。我的感觉是 hm>>system design >= deep dive > coding. 毕竟linkedin就那100道tag题目并且都不难。不觉得coding能区分candidates。

1. http://bit.ly/34n0FkS
2. http://bit.ly/334K70D
3. http://bit.ly/2NAd2Do
4. http://bit.ly/34nEkTV
5. http://bit.ly/2NxFw0x
6. http://bit.ly/2qfd4Z1
7. http://bit.ly/36iRByZ
8. http://bit.ly/2WwYDMj
9. http://bit.ly/2PD7ryC
10. http://bit.ly/2pwvlRB
11. http://bit.ly/2pwvlRB
12. http://bit.ly/332P4qE with all design
13. http://bit.ly/32a1snK
14. http://bit.ly/2N4mW0P
15. http://bit.ly/36lNNgv
16. http://bit.ly/2BWYQia
17. http://bit.ly/34imcLh
18. http://bit.ly/2N5d6fh
19. http://bit.ly/2BZ4kJh
20. http://bit.ly/323Qjoj
21. http://bit.ly/2q9758l

# behavior Questions
linkedin learning
https://www.linkedin.com/interview-prep/assessments/urn:li:fs_assessment:(1,a)/question/urn:li:fs_assessmentQuestion:(10011,aq11)/

https://business.linkedin.com/content/dam/me/business/en-us/talent-solutions/resources/pdfs/linkedin-30-questions-to-identify-high-potential-candidates-ebook-8-7-17-uk-en.pdf

https://business.linkedin.com/content/dam/me/business/en-us/talent-solutions/resources/pdfs/Guide-to-screening-candidates-30-essential-behavioral-interview-questions-ebook.pdf

# System design 总结
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=446923
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=465815

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175538&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D31

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=556281&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
