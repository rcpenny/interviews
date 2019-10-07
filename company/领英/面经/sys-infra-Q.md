https://zhuanlan.zhihu.com/p/30161567

# system infra Q:

## Process vs Thread:
thread和process的区别, 要求用stack, heap. how to chooese between them
thread之间怎么communicate
https://zhuanlan.zhihu.com/p/85530908

https://medium.com/@nickteixeira/stack-vs-heap-whats-the-difference-and-why-should-i-care-5abc78da1a88
What are the five segments of memory?
 The memory that is assigned to a program or application in a computer can be divided into five parts. 
 5: heap, stack, initialized data segment, uninitialized data segment, and the text segment.
   The initialized data segment consists of all the global and static variables that are initialized when a file gets compiled.
   The text segment, also known as the code segment, contains the machine instructions which make up your program. The text segment is often read-only and prevents a program from accidentally modifying its instructions.

What is the stack?
The stack is a segment of memory where data like your local variables and function calls get added and/or removed in a last-in-first-out (LIFO) manner.
These four segments have a constant memory size during compilation. The memory size for these four segments is predetermined by your operating system before compiling your programs.

The heap is the segment of memory that is not set to a constant size before compilation and can be controlled dynamically by the programmer. Think of the heap as a “free pool” of memory you can use when running your application. The size of the heap for an application is determined by the physical constraints of your RAM (Random access memory) and is generally much larger in size than the stack.

when we need to allocate more memory than what’s available on the stack, or when we need to create variables that last the duration of our application. We can do that in the C programming language by using malloc, realloc, calloc and/or free. 
We allocate memory from the heap using the malloc() function
If we do not release the memory from our program before terminating the application, our application has memory leaks.

 conpcepts: A thread is a path of execution within a process, lightweight process.

 threads within the same process share the same address space 
 (share same address space)
This allows threads to read from and write to the same data structures and variables, and also facilitates communication between threads.
线程和进程的区别在于，进程拥有自己的资源 does not share with other process. 
而线程直接使用分配给进程的资源，它自己不能占有资源。A thread may share some memory with its peer threads.

stack heap level: thread only contains necessary information like stack(with local variables, function args, return values),
a copy of registers, program counter and thread-specific data. thus A thread contains it's own stack.
threads can access different portion of the address space of the process.

 Other data is shared by the process to all threads, PCB info,
code segement, data segement.(heap) 

mechanism to coordinate threads among each other

https://www.cnblogs.com/xh0102/p/5710074.html
communication: wait notify

responsiveness:
single threaded application will be unresponsive to user until a lengthy of operations are completed.
resources sharing:
threads share memory and resources
economy:
thread mgmt is less time consuming than process mgmt
scability:
multi-threaded process can run multi-core CPU

## TCP vs UDP:
TCP和UDP的区别是什么？ https://zhuanlan.zhihu.com/p/24860273
什么样的应用使用TCP，什么样的使用UDP？HTTP协议使用TCP还是UDP？
1、基于连接与无连接； connection-oriented, connectionless
2、对系统资源的要求（TCP较多，UDP少）；
3、UDP程序结构较简单；
4、流模式与数据报模式 ；
5、TCP保证数据正确性，UDP可能丢包；
6、TCP保证数据顺序，UDP不保证。

TCP UDP 和 use case
Apps with TCP: HTTP, Telnet, FTP, SMTP
UDP: media stream, teleconferencing

## Transaction:
数据库事务和ACID 解释Transaction
Transaction的意思是什么。这个比较宽泛。我就回答了数据库里面的transaction。面试进一步问malloc() function的原理

## Virtual memory:
separation of user logical memory from physical memory.
Only part of the program needs to be in memory for execution•Logical address space can therefore be much larger than physical address space•Allows address spaces to be shared by several processes•Allows for more efficient process creation•Virtual memory can be implemented via:•Demand paging •Demand segmentation

什么是paging和page fault

## java:
final/finally/finalize的不同
	Final is used to apply restrictions on class, method and variable. Final class can't be inherited, final method can't be overridden and final variable value can't be changed.	Finally is used to place important code, it will be executed whether exception is handled or not.	Finalize is used to perform clean up processing just before object is garbage collected.
2)	Final is a keyword.	Finally is a block.	Finalize is a method.

checked/unchecked exception, 抛出异常和返回异常值哪个比较好，为什么？
compile time/runtime
Java: Return Values vs Exceptions
You should only use exceptions for exceptional situations. An exceptional situation is an unexpected situation that is out of the ordinary. A situation is not exceptional just because it's less common than other situations. In a non-exceptional situation you should use return values instead of exceptions.
Exceptional Examples	
An attempt to open a file failed
Computer ran out of memory
Program tried to divide by 0
Non-exceptional Examples
Requested username already taken
A text string was entered in a numeric field
User does not have permission to change the data

NestedClass(static和non-static)怎么instantiate
Nested classes are divided into two categories: static and non-static. Nested classes that are declared static are called static nested classes. Non-static nested classes are called inner classes.
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();

java里面map的源码是不是看过 是不是知道怎么实现的等 如何线程‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍安全 如果改写完的让支持异步等等
hashCode() {return Objects.hashCode(key) ^ Objects.hashCode(value)};
use linkedlist
http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/util/HashMap.java
https://blog.csdn.net/tuke_tuke/article/details/51588156
当链表数组的容量超过初始容量的0.75时, 再散列将链表数组扩大2倍，把原链表数组的搬移到新的数组中

Java Object的理解以及benefits, 为什么Java中的所有类都是默认由 Object 类继承？这样设计的思想是什么？
2）继承
继承性，使不同的类，有相同的方法，这样可以最大程度地重用代码。
3）多态
多态使得我们可以使用相同的方法处理不同对象的行为：我们可以使用相同的代码处理不同的类型的对象，只要它们继承和实现了相同的类型。

Write-back和Write-though Cache，follow up是哪一个比较快
The benefit of write-through to main memory is that it simplifies the design of the computer system. With write-through, the main memory always has an up-to-date copy of the line. So when a read is done, main memory can always reply with the requested data.
If write-back is used, sometimes the up-to-date data is in a processor cache, and sometimes it is in main memory. If the data is in a processor cache, then that processor must stop main memory from replying to the read request, because the main memory might have a stale copy of the data. This is more complicated than write-through.
Also, write-through can simplify the cache coherency protocol because it doesn't need the Modify state. The Modify state records that the cache must write back the cache line before it invalidates or evicts the line. In write-through a cache line can always be invalidated without writing back since memory already has an up-to-date copy of the line.
One more thing - on a write-back architecture software that writes to memory-mapped I/O registers must take extra steps to make sure that writes are immediately sent out of the cache. Otherwise writes are not visible outside the core until the line is read by another processor or the line is evicted.
Write-through与Write-back和买卖东西相似，Write-Through就相当于你亲自去买东西，
你买到什么就可以亲手拿到；而Write-Back就和中介差不多，你给了中介钱，然后它告
诉你说你的东西买到了，然后就相信拿到这个东西了，但是要是出现特殊情况中介跑了，
你再去检查，东西原来没有真正到手。



LFU的变形 地里面有原题和答案 完了以后就是各种fullow-ups各种 比如
面经题 一个cache 根据 rank 来evict
implement一个cache
给你一个类框架 实现一个cache 类里提供了一个data source 如果cache里找不到这个数 则从data source里找并cache起来供下次使用 每个数有自‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍己的rank 如果cache满了 要把rank最低的evict再插入
第二题： 面试官给出一个cache的借口，让你实现里面的get和set方法。
Implement a hash table. 需要跟面试官clarify并且讨论怎么处理collision，然后写code。这题讲真不容易一次写对。。。我还忘记了re-hash。不知道结果如何。
coding是给一个data source (很大，query慢)， 给一个rank() return rankable item的score要求设计一个bestScoreCache。大致是这样，不是很难。但是fo‍‍‍‌‍‍‍‍‍‌‍‍‌‍‌‍‌‍‍llow up问到了读cache时racing condition的问题于是又继续follow up system里lock啊之类的操作，问的很细。。。lz汗如雨下
best rank cache， 类似于LRU

打电话给我聊了半个多小时，详详细细地跟我讲了onsite都要面什么，说hm面特别重要，一定要突出自己的leadership和design的经验，
因为我工作经验短而且是本科学历，投的却是senior的职位，不太容易过，
