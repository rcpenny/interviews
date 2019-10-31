permission access 那道题。
follow up 是给定一个non_inherient set， 遇见任何一个node 在这个set 里就马上停止check并且return false。
求点大米。。。

万年面经题一个文件系统，如果folder有access所子folder都继承access,
1.给一个folder查询access
2. 移除多余的‍‍‍‌‍‌‍‌‌‌‌‌‌‌‌‌‍‌‍access，就父文件有access子文件不用

给定一个list of folder 关系，比如[A,B] 代表 B folder 下有 A folder. 再给定 一个set of permission 用户已有的。请实现一个方法， take 一个folder check 用户是否有access。并分析 时间空间复杂度
follow up：
1. 如何精简access set
2 如果有一个不继承的permissionset 如何更新现有的方法。

给出一些filename的关系，比如目录A下面有目录B，目录B下面有文件C，等等，问如果一个用户有权限access文件A，是否可以访问B? followup返回可以访问的所有文件。再followup给出能access的一堆文件，去除多余的文件。比如目录A下面有目录B，给出的文件里有A和B，B就是多余的，因为如果用户能访问A必定能访问B。
求大米～～谢谢谢谢

// rolling hash
Implement file_contains(file path, buffer)
不用 compile， 可是要optimize！
不可以用 MD5 or SHA。
Almost read method are provided.

蠡口 刘玲就
基本就是原题，但是给了个环境设定：原题input是string[] path，这里他把关于input的所有信息都abstract成了一些api
假设有个api叫getPath(string file), 它可以返回这个file/folder的path，你不需要知道它是怎么工作的，就用就行了
然后比较content是不是一样也是用一个getContent(file)的api，而不是input直接告诉你

面试的是个国人大叔，一开始给spec非常模糊，也没有直接给api，有点像coding+design，意思是你需要什么信息你都可以自己define
我大概花了20分钟才明白怎样获得input，很焦灼

getContent也是我自己define的，我说每个file可以计算返回它content的一个hashValue来比较

Followup：
- 也差不多是原题的followup，如果在真实的file system里面，有什么bottleneck
- getContent是大问题，file content可能非常大，计算hashValue是瓶颈
- 在面试官的各种hint下，我说到了利用metadata先第一步筛选：metadata里file size不一样的 content肯定也不一样，只getContent size一样的file

电两次
1. 刷题网 289.  问内存只够3-4行 咋办
2. 文件家找重复， 有的文件很大咋办  



Duplicate Files
Things to consider:
Symbolic link, same file/dir with diff name, cannot detect cycle by visited…cycle? use absolute path/ skip symbolic link (if we search the whole file system)
invalid or malformed files e.g. permission or cannot read
compare file by hashing, MD5 false positive (due to hash collision), use SHA256/512 very very little chance of collision
If dir depth is large: DFS might stack overflow, use BFS; the variable to store pathname might overflow.
Can ask question after interview how Dropbox solve this type of problem.
Most memory consuming: MD5, read in files etc
Race conditions?: someone is writing the file while you are reading etc
If error / hanging up in between: checkpoints, save states from time to time
下面是我个人的经历

1. 与leetcode给定的input不同，面试官只给了我root的path，让自己写程序walk directory， BFS/DFS。还来问如果是云存储那种方法好，BFS 能够很好地利用locality，只需要访问一个folder一次
2. 面试时我直接声明不会写MD5和sha256的java code，所以就假定存在一个method，给定File，返回digest。问到了MD5有什么问题，答key collision， 用sha256替换就足够了
3. 我先写了dfs，然后问如果文件很大怎么办。我就把code改成了两步，先检查file size，把相同的放到list里面。接下来再来访问list，做md5或者sha256， 或者chunk by chunk
4. 写code过程中说明了几个问题，symbolic link的问题，access的问题





题目有点长而且有点绕，其实不难，中间跟面试官沟通 clarify 很多细节。原题没有copy 下来，总结一下 大概是这么个意思

Giving a list of arrays representing a file directory, for example:  

--A
-----B
---------D
---------E

-----C
---------F

-----G

[
  [A, null]，  
  [B， A]，
  [D, B],
  [E, B],
  [C, A],
  [F, C],
  [G, A]
]

Given an access set representing some directories that a user has access to: let access = new Set(B, C);  for any directory, if its parent is accessible then itself is accessible.

Q1: create a function to check if a given directory is accessible.
Q2: what if the function above needs to be called very often? how to optimize
Q3: Introduce another set called non-inheritence access set (access set is still there), for any directory in this set, it is accessible, i.e. not depend on the parent; however, its accessibility can impact its children. Update the functions created in Q1 and Q2



面经题，游戏一生， followup 也是老样子，如果board 是 100M * 100M 怎么处理。不知道他家多久出结果，已经2天了，是不是挂了。。。




SpacePanorama，题目的意思是给一个1000 X 1000 的grid，每一个grid要存储一张1MB的图片， 让我们实现update， fetch method. Follow Up 是问实现GetOlderstEntry method.
