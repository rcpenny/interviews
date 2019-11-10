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


## Actual code
1. TaskScheduler class
2. DelayQueue class
3. Delaytask class
4. DelayTaskProducer class
5. DelayTaskconusmer class

## 方案
方案1: PriorityBlockingQueue + Polling
我们很快可以想到第一个办法：
用一个java.util.concurrent.PriorityBlockingQueue来作为优先队列。因为我们需要一个优先队列，又需要线程安全，用PriorityBlockingQueue再合适不过了。你也可以手工实现一个自己的PriorityBlockingQueue，用java.util.PriorityQueue + ReentrantLock，用一把锁把这个队列保护起来，就是线程安全的啦
对于生产者，可以用一个while(true)，造一些随机任务塞进去
对于消费者，起一个线程，在 while(true)里每隔几秒检查一下队列，如果有任务，则取出来执行。
这个方案的确可行，总结起来就是轮询(polling)。轮询通常有个很大的缺点，就是时间间隔不好设置，间隔太长，任务无法及时处理，间隔太短，会很耗CPU。
方案2: PriorityBlockingQueue + 时间差
可以把方案1改进一下，while(true)里的逻辑变成：
偷看一下堆顶的元素，但并不取出来，如果该任务过期了，则取出来
如果没过期，则计算一下时间差，然后 sleep()该时间差
不再是 sleep() 一个固定间隔了，消除了轮询的缺点。
稍等！这个方案其实有个致命的缺陷，导致它比 PiorityBlockingQueue + Polling 更加不可用，这个缺点是什么呢？。。。假设当前堆顶的任务在100秒后执行，消费者线程peek()偷看到了后，开始sleep 100秒，这时候一个新的任务插了进来，该任务在10秒后应该执行，但是由于消费者线程要睡眠100秒，这个新任务无法及时处理。

方案4: 时间轮(HashedWheelTimer)
时间轮(HashedWheelTimer)其实很简单，就是一个循环队列，如下图所示，
上图是一个长度为8的循环队列，假设该时间轮精度为秒，即每秒走一格，像手表那样，走完一圈就是8秒。每个格子指向一个任务集合，时间轮无限循环，每转到一个格子，就扫描该格子下面的所有任务，把时间到期的任务取出来执行。
举个例子，假设指针当前正指向格子0，来了一个任务需要4秒后执行，那么这个任务就会放在格子4下面，如果来了一个任务需要20秒后执行怎么？由于这个循环队列转一圈只需要8秒，这个任务需要多转2圈，所以这个任务的位置虽然依旧在格子4(20%8+0=4)下面，不过需要多转2圈后才执行。因此每个任务需要有一个字段记录需圈数，每转一圈就减1，减到0则立刻取出来执行。
怎么实现时间轮呢？Netty中已经有了一个时间轮的实现, HashedWheelTimer.java，可以参考它的源代码。
时间轮的优点是性能高，插入和删除的时间复杂度都是O(1)。Linux 内核中的定时器采用的就是这个方案。