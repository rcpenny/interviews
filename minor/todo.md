# 十月日常
1. SYS DESIGN CASE & SLIDES
2. BEHAVIOR QUESTIONS
3. GAM,面经
4. LADDER REVIEW
5. OOD design

# ladders cleared
dp1 dp2 dp3 dp4
bt_strut, bt_sumpath, bst

1. google  1. dropbox 1. LinkedIn, 3. Amazon 4. microsoft  BH   SYS-DESIGN

https://leetcode-cn.com/problems/sliding-window-maximum/solution/shi-pin-jie-xi-shuang-duan-dui-lie-hua-dong-chuang/

https://engineering.linkedin.com/espresso/introducing-espresso-linkedins-hot-new-distributed-document-store

https://www.linkedin.com/interview-prep/assessments/urn:li:fs_assessment:(1,a)/question/urn:li:fs_assessmentQuestion:(10011,aq11)/


第三轮：算法 朋克白人大叔
题目是auto complete的变形版。给出一个字符串，要求你输出这个字符串中所有可能包含的单词。方法是先把字典抽出来建tries，然后把字符串挨个char扫过去就行了，还需要一个hashset去看下一步的搜索是不是在字符串中有的。
follow up是输出topK，我给出了三种方法，头权重，头尾权重，和历史权重。写了下sudo code。

第六轮：算法 国人大叔
问题是n叉树，树上的node分成两类，现在要求删掉一类重建这个n叉树。
我用的方法是bfs层级搜索，但是中间穿插一个递归来找每个node下面的所有的新node。
有个deep copy传错参数的地方，在最后demo test case的时候被他看出指了出来，然后改正了，不知道影响有多大

第一轮，国人小姐姐，来了一年多。第一题lc914。follow up是问把相同数字的要求改成顺子，一个顺子是连续五个升序的数字。同样每个Pile不少于两张牌。
做了一半电脑没电了，好久没充上电。第一轮后半段和第二轮都是全程手写，有点影响心态。

第一问，判断一个int array有没有三个数字 [...x...y...z] such that x < y < z (strictly)。
讲完思路白板上写完代码就没剩几分钟了，前面还聊了会天。follow up问给一个parameter k, 判断有没有k个升序的数字。结束之后才想起来跟longest increasing subsequence类似。

第三轮，国人大哥，进门说只有三十分钟，我问是不是non-technical，他说不是，很惊讶。
题目是给string A and string B, you can make a copy of and remove any character in B, find the minimum copies of B needed to build A.
比如A = ZAZB, B = BAZ, 答案是3。(##Z + #AZ + B##) 这一轮电脑神奇的好了，所以感觉还行。

第四轮，一个比较senior的欧洲大叔，特搞笑的是直接给了我一张纸，上面打印出来了面试题。说给几个task，每个task有Dependency和完成所需时间。有无限的processor。问最短用多长时间完成所有task。

第五轮，感觉是个二代三哥，没有口音，很健谈。先问我ZAZB那个题，我说做过了，就换了个题目。给两个string A B, 判断是否B是在A的中间添加了几个words。比如i like chocolate 和i really like chocolate return true, i like chocolate和no i don't like chocolate return false。做完之后又出了个brainstorm，说有一个stream of query words, Input是几个character，output any three words queried that begin with the given characters in order.比如 [chocolate, charco, chorizo]，给cho，就要return [chocolate, chorizo]。

https://www.lintcode.com/problem/x-of-a-kind-in-a-deck-of-cards/description