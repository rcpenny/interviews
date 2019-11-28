import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// Implement a thread safe bounded blocking queue that has the following methods:

// BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.

// void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.

// int dequeue() Returns the element at the rear of the queue and removes it. 
// If the queue is empty, the calling thread is blocked until the queue is no longer empty.

// int size() Returns the number of elements currently in the queue.

// M producers N consumers

public class MessageBoundedQueue {
  private MessageBoundedQueue() {}
}

// lock + condition
class BoundedBlockingQueue<E> {
	private Queue<E> queue;
	
	// lock held by dequeue
	private final ReentrantLock takeLock = new ReentrantLock();

	/** Wait queue for waiting dequeue() */
	private final Condition notEmpty = takeLock.newCondition();

	// lock held by enqueue
	private final ReentrantLock putLock = new ReentrantLock();

	/** Wait queue for waiting put() */
	private final Condition notFull = putLock.newCondition();

	private final int capacity;
	private final AtomicInteger count;

  public BoundedBlockingQueue(int capacity) {
		if (capacity <= 0) throw new IllegalAccessException();

		this.capacity = capacity;
		this.count = new AtomicInteger(0);

		this.queue = new LinkedList<>();
  }

  public void enqueue(E e) throws InterruptedException {
		if (e == null) return NullPointerException();
		
		final ReentrantLock putLock = this.putLock;
		final AtomicInteger count = this.count;

		putLock.lockInterruptibly();

		try {
			while (count.get() == capacity) {
				notFull.await();
			}
			queue.offer(e);
			
			if (count.incrementAndGet() < capacity) notFull.signal();
		} finally {
			putLock.unlock();
		}

		if (count.get() == 0) signalNotEmpty();
  }
  
  public E dequeue() throws InterruptedException {
		E x;

		final AtomicInteger count = this.count;
		final ReentrantLock takeLock = this.takeLock;

		takeLock.lockInterruptibly();

		try {
			while (count.get() == 0) {
				notEmpty.await();
			}
			x = queue.poll();
			if (count.getAndDecrement() > 1) notEmpty.signal();
		} finally {
			takeLock.unlock();
		}

		// 召唤 nonfull线程
		if (count.get() == capacity) signalNotFull();

		return x;
  }
	
	// use atmoic integer to avoid race condition
  public int size() {
    return count.get();
	}
	
	/**
	 * Signals a waiting take. Called only from enqueue() (which do not
	 * otherwise ordinarily lock takeLock.)
	 */
	private void signalNotEmpty() {
		final ReentrantLock takeLock = this.takeLock;
		takeLock.lock();
		try {
			notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
	}

	private void signalNotFull() {
		final ReentrantLock putLock = this.putLock;
		putLock.lock();
		try {
			notFull.signal();
		} finally {
			putLock.unlock();
		}
	}
}

// semaphore
public class BoundedBlockingQueue11883 {
	private int            maxLength;
	private Queue<Integer> queue;
	private Semaphore semaphore;
	private Semaphore consumerSemaphore = new Semaphore(0);

	public BoundedBlockingQueue11883(int capacity) {
			this.maxLength = capacity;
			this.queue = new LinkedList<>();
			semaphore = new Semaphore(maxLength);
	}

	public void enqueue(int element) throws InterruptedException {
			semaphore.acquire();
			queue.offer(element);
			consumerSemaphore.release();
	}

	public int dequeue() throws InterruptedException {
			consumerSemaphore.acquire();
			int res = queue.poll();
			semaphore.release();
			return res;
	}

	public int size() {
			return queue.size();
	}
}
