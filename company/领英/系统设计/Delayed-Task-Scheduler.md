# Delayed Task Scheduler (LinkedIn )
设计延迟任务规划器，能够把task schedule在特定的时间执行。
use case 是user提交一个runnable 并设定开始时间，后台就在设定的时间运行这个任务，时间精度容差在1分钟。 需要实现的interface 就是 boolean submitJob() 。后台的server要怎样执行这些任务。

- 资料
https://soulmachine.gitbooks.io/system-design/content/cn/task-scheduler.html

1. 要用mutex和condition variable做协同
2. 讨论了conditional variable原理，lock，何时会抛出interrupted exception，executor的用法，threading pool的好处
3. 这个聊了一半后让我实现一个blocking queue，讨论了下用condition，synchronized和semaphore的优劣最后拿semaphore实现的
设计单机延迟任务执行器，纠结了半天加锁分布式没时间答了
实现线程池/ExecutorService，跑需要延时的任务。

楼主用treeset + linkedlist 维护按照时间sorted的任务list。在worker端while true 检查该不该run the latest job
目测没答到点子上。全程只有楼主自说自话，三个人的会议室仿佛是楼主的舞台。

1. IDI Concurrency：delayed task scheduler
一直在往condition variable那边靠，像blocking queue那种设计，wait() + notify()
但聊到最后，发现heap+hashmap（<timestamp, taskid>）就可以了...还聊了一会heap的原理，heapify up/down，O(log(n)) add/peak，聊偏了...
重点是深入rolling window这个ds：怎么删去expire的data，如果靠incoming message trigger expired data deprecation，一直没有incoming message怎么办。假设这个fixed sized window可以fit进memory