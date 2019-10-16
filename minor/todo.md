# 十月日常
1. SYS DESIGN CASE & SLIDES
2. BEHAVIOR QUESTIONS
3. GAM,面经
4. LADDER REVIEW
5. OOD design

# ladders cleared
dp1 dp2 dp3 dp4
bt_strut, bt_sumpath, bst

https://leetcode-cn.com/problems/sliding-window-maximum/solution/shi-pin-jie-xi-shuang-duan-dui-lie-hua-dong-chuang/

https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store


第三轮：算法 朋克白人大叔
题目是auto complete的变形版。给出一个字符串，要求你输出这个字符串中所有可能包含的单词。方法是先把字典抽出来建tries，然后把字符串挨个char扫过去就行了，还需要一个hashset去看下一步的搜索是不是在字符串中有的。
follow up是输出topK，我给出了三种方法，头权重，头尾权重，和历史权重。写了下sudo code。

第五轮：算法 白人大叔
迟到了近10分钟，orz。
第一题是code review，我感觉这段code每行都有问题，有的行问题还不止一个，有的地方我觉得写的很怪异，但是语法上又没啥问题，所以最后我也不知道这题我答得怎么样。
第二题是刷题网317变种，一堆办公室，用门连接，有的办公室有人，要求是找个房间放咖啡机，离所有人的总距离最近。
我先是自己脑抽在多起点bfs和单起点扫n遍bfs上纠结了半天，之后又在选择data structure表示办公室的位置上和面试官纠结了一会儿，我觉得用个二维数组就好了，他觉得不行，最后是把room的坐标封装，用个hashmap来表示门。
结果是我说了自己的算法，然后demo了一下，没时间写code了。
最后结束的时候，他又提到了有几个人离得很远会导致这个方法不够效率。我面试完之后给hr写了封邮件，让她转发给面试官，说明了定长的bfs搜索可能解决这个问题，虽然不改变时间复杂度，但是constant factor能大大降低。也不知道能起多大用。

第六轮：算法 国人大叔
问题是n叉树，树上的node分成两类，现在要求删掉一类重建这个n叉树。
我用的方法是bfs层级搜索，但是中间穿插一个递归来找每个node下面的所有的新node。
有个deep copy传错参数的地方，在最后demo test case的时候被他看出指了出来，然后改正了，不知道影响有多大


申请职位google cloud. 9月中旬onsite,面了四轮coding,做了6道题,之前发过面经.
题都答出来了,面试官都是国人,还有一位是学姐, 当时觉得机会挺大,结果出来挂了. 分析一下可能失败的原因,大家面试的时候可以吸取经验
1. 代码写完跑test要跟着代码跑, 我在chromebook上写代码,跑test case在whiteboard上画, 没有很好的顺着code过test case,这样只能说明算法是对的,不能发现代码里的bug
2. testcase 可能需要多跑几个, 分析各种corner case
3. 有一轮面试官迟到了五六分钟,然后问了重复的问题,换题,然后chromebook setup有问题,改成share google doc,这些都花了不少时间, 间接导致这一轮只做了一道题. 感觉这种
   情况应该及时给hr反应, 等结果出来了再说就没用了.
4. 写代码用chromebook还是白板,感觉各有利弊, 用chromebook写会快一点,但是debug跑test case的时候没有白板方便. 用白板写代码跑test的时候可以对照代码