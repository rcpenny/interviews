# Delayed Task Scheduler
设计Delay Scheduler，能够把task schedule在特定的时间执行。



- 资料
1. 多线程 https://paper.dropbox.com/doc/--AnzuGuI6O2od4tDRqe6PN1VFAg-ONbDQkXzZv3bVw9i1sPvm

https://soulmachine.gitbooks.io/system-design/content/cn/task-scheduler.html



延迟任务规划器，写了完整的代码，讨论了conditional variable原理，lock，何时会抛出interrupted exception，executor的用法，threading pool的好处

Concurrency Coding, 先聊了很多同样的spark job在bare metal hardware上跑只要10分钟，在云端(Azure)上跑要20分钟，可能是什么造成的，有什么解决办法。这个聊了一半后让我实现一个blocking queue，讨论了下用condition，synchronized和semaphore的优劣最后拿semaphore实现的。这一轮国人大哥，中文面试，聊了很多career方面的内容。。。这一轮是最好的体验
多线程：写一个DelayedTaskQueue，要用mutex和condition variable做协同

system design：delay scheduler，参考java的写法。跟印度大哥半天解释不清楚，就直接把code写上去了，又写了一个client调用的code，印度大哥大姐很满意。感觉这轮更像算法轮。


Design1: Delay Task Scheduler。下面这个讲的应该够了。是要实现的，不是pseudocode。
https://soulmachine.gitbooks.io/system-design/cn/task-scheduler.html
 设计单机延迟任务执行器，纠结了半天加锁分布式没时间答了
实现线程池/ExecutorService，跑需要延时的任务。



设计dealy task scheduler。不然用数据库，随便扯了会，当时准备了好几个follow up，但面试官都没有问。。。有点小郁闷

6> Design. 三哥 + 国人小哥 shadow
Design 一个 delayed task scheduler。use case 是user提交一个runnable 并设定开始时间，后台就在设定的时间运行这个任务，时间精度容差在1分钟。 需要实现的interface 就是 boolean submitJob() 。后台的server要怎样执行这些任务。
不是纯talking 要写code
楼主用treeset + linkedlist 维护按照时间sorted的任务list。在worker端while true 检查该不该run the latest job
目测没答到点子上。全程只有楼主自说自话，三个人的会议室仿佛是楼主的舞台。

1. IDI Concurrency：delayed task scheduler
国人小哥迟到了15分钟...一直跟我道歉. 其实这题跟concurrency关系不大，我读过面经，一直在往condition variable那边靠，像blocking queue那种设计，wait() + notify()
但聊到最后，发现heap+hashmap（<timestamp, taskid>）就可以了...还聊了一会heap的原理，heapify up/down，O(log(n)) add/peak，聊偏了...
重点是深入rolling window这个ds：怎么删去expire的data，如果靠incoming message trigger expired data deprecation，一直没有incoming message怎么办。假设这个fixed sized window可以fit进memory