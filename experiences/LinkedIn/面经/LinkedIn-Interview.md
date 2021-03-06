# Coding Interviews (2 hours - separate rounds)

## Coding and Algorithms 1 (60 mins): 
1. coding 1的白板coding给出适中的算法，时间 空间复杂度即可。
2. 这轮的重点是代码的模块化，延展性，及解决bug的能力
3. 这轮涉及pointers, edge case, abstraction等。旨在模拟一个已经设计好的项目的日常coding. 

## Coding and Algorithms 2 (60 mins): 
1. Coding 2更注重分析能力，算法，问题解决能力
2. 与Coding 1相比，Coding 2会相对少侧重一点实际写出来的代码
3. 在这轮面试中，solution的总体的runtime和scalability会更重要

## Things to keep in mind for coding rounds:
1. interviewer不会给出问题的全部信息，你需要clarify问题的例子，corner case等
2. 多和interviewer互动，多提问，搞清楚问题并有思路之后再开始写代码。
3. 如果你觉得你的解法有问题，解释清楚你的解法和这么做的原因，interviewer才能给你更好的提示。

## During these interviews, the engineers will be looking for:
1. 是否可以给出一个可行的解法
2. 代码的完整度
3. 代码的整洁度
4. 完成问题的时间
5. 是否是最优解法，能否优化

## Array String Math
- 56/57	       Merge/Insert Interval
- 605	         Can Place Flowers
- 149          Max Points in a Line
- 277	         Find Celebrity
- 611          Valid Triangle Number
- 8	           String to Integer (atoi)
- 68	         Text Justification
- 273          Integer to English Words
- 65	         Valid Number

## 动态规划
- 256/265      Paint House I & II
- 416          Partition Equal Subset Sum
- 647          Palindromic Substrings
- 516          Longest Palindromic Subsequence
- 300          Longest Increasing Subsequence
- 72           Edit Distance
- 53           Max Subarray
- 152          Max Product Subarray

## 二分
- 50	         Pow(X, N)
- 69           Sqrt(x) I & II (int/double)
- 367          Valid Perfect Square
- 33/81        Search in Rotated Sorted Array I & II

## 树
- 366          Find Leaves Of Binary Tree
- 156          Binary Tree Upside Down
- 100/101	     Same/Symmetric Tree
- 671          Second Minimum Node In a Binary Tree
- 236          Lowest Common Ancestor
- 270/272      Closest BST Value I & II

## 数据结构
- 205          Isomorphic Strings
- 244	         Shortest Word Distance II
- 373          Find KPairs with SmallestSums                       
- 20	         Valid Parentheses
- 150	         Evaluate Reverse Polish Notation
- 173	         BST Iterator
- 341          Flatten Nested List Iterator
- 739	         Daily Temperatures

## 链表 及 双指针
- 141/142      Linkedlist Cycle I & II
- 160          Intersect of 2 Linkedlist
- 21/23        Merge 2/K sorted list
- 369          Plus One Linked List
- 61           Rotate List
-	76	         Minimum Window Substring
- 125	         Valid Palindrome
- 24           Swap Nodes in Pairs

## DFS
- 339/364	     Nested List Weight Sum I && II
- 22           Generate Parentheses
- 254          Factor Combnation
- 113	         Path Sum II
- 46/47        Permutations I & II
- 39/40        Combination Sum I & II
- 698          Partition to K Equal Sum Subsets
- 124          Binary Tree Maximum Path Sum

## BFS / UnionFind
- 200/305      Number of Islands I && II
- 126/127      Word Ladder I && II
- 261          Graph Valid Trees
- 323          Number of Connected Components in an Undirected Graph
- 785          Is Graph Bipartite?

## 设计
- 716          Max Stack
- 380/381      Insert Delete GetRandom O(1) & Dupe allowed
- 179          Two Sum III
- 297/449      Serialize and Deserialize BST
- 146          LRU Cache
- 432          All O(1) Data Structure
- 460          LFU Cache
- 706          Design HashMap

# Infrastructure Design and Implementation (3 modules, 1 hour each):  
- 所有IDI问题都涉及设计，虽然IDI被分成三个模块，但你不能假设某个模块中的概念不出现在其他模块。
- 对于所有模块，问清楚问题，提出你的假设，大胆思考并且说说tradeoffs.
- 没有完美的答案，除了最终solution，你整个module的表现也会被evaluate.
- Solutions评估标准：completeness, performance, scalability,是否fault tolerant.

## IDI1 Concurrency
This module focuses on the concepts related to concurrency. Specifically: threads, locks, semaphores, race conditions, shared memory and data structures.  You’ll be presented with a problem where there is resource contention (implicit or explicit) and you’ll need to address it. While you can take advantage of the primitives provided by your language of choice, you’re still expected to understand the concepts, guarantees and implications.  

## IDI2 Data Structures & Algorithms
This module focuses on data structures and algorithms. You’ll be given a problem and you’ll be expected to design one or more data structures that are able to solve the problem. You should be prepared to explain the algorithms and logic associated with the structure.  

## IDI3 Complex Systems
This module focuses on large (distributed) systems. You will be given a scenario and end goal and will be asked to design a system that can meet the requirements.  You’re expected to understand how to break down a problem into components and how the components interact with each other. You should be able to describe the solution at a high level and go into the detail of each component. 
