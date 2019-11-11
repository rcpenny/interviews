# system-infra onsite 整理
https://engineering.linkedin.com/architecture/brief-history-scaling-linkedin

1. Design key-value store

假设已有一些file system的API设计一个KV db。前提条件：

- key很短可以假设只有8bit，可以保证全部keys能够都存在内存中，但是value很大必须存在disk中。
- disk的random读写会非常慢，但是顺序写入会很快
- 假设已经有个file system提供了write read API，但是具体API的接口怎样还得自己定义.
- 实现get put delete

2. Delayed task scheduler。
就看了上面那个链接。

3. Metrics collection and monitor system。
收集host的1second，1minute，1hour metrics。如何scale，如果是10000 hosts怎么收集。

4. LinkedIn 二级三级朋友。
https://engineering.linkedin.com/real-time-distributed-graph/using-set-cover-algorithm-optimize-query-latency-large-scale-distributed

5. Inverted Index。

6. Design Message Streaming System。
应该就是我的第三轮。


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

// review this at last
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=446923

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=465815

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175538&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D31

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=556281&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline

# behavior Questions
linkedin learning
https://www.linkedin.com/interview-prep/assessments/urn:li:fs_assessment:(1,a)/question/urn:li:fs_assessmentQuestion:(10011,aq11)/

https://business.linkedin.com/content/dam/me/business/en-us/talent-solutions/resources/pdfs/linkedin-30-questions-to-identify-high-potential-candidates-ebook-8-7-17-uk-en.pdf

https://business.linkedin.com/content/dam/me/business/en-us/talent-solutions/resources/pdfs/Guide-to-screening-candidates-30-essential-behavioral-interview-questions-ebook.pdf
