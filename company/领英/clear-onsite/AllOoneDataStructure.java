import java.util.HashMap;
import java.util.Map;

// Inc(Key) - 插入值为1的Key。或者将现有Key的值增加1。Key保证为非空字符串。
// Dec(Key) - 如果Key的值为1，则将其从数据结构中删除。 否则将现有Key的值减1。如果Key不存在，则此函数不执行任何操作。 Key保证是非空字符串。
// GetMaxKey() - 返回一个值最大的Key。 如果不存在这样的元素，则返回空字符串""。
// GetMinKey() - 返回一个值最小的Key。 如果不存在这样的元素，则返回空字符串""。

// 所有的操作都需要 O(1) 的时间复杂度。
// leet432

// 用来存储这个freq对应哪些key
class Bucket {
	int count;
	Set<String> keySet;
	Bucket prev;
	Bucket next;
	public Bucket(int cnt) {
		count = cnt;
		keySet = new HashSet<>();
	}
}

public class AllOne {
	private Bucket head;  // head和tail什么都不存,只是负责指向, buffer
	private Bucket tail;
	private Map<String, Integer> keyToCount;     // key -> count
	private Map<Integer, Bucket> countToBucket;  // count -> bucket

	public AllOne() {
			head = new Bucket(Integer.MIN_VALUE);
			tail = new Bucket(Integer.MAX_VALUE);
			head.next = tail;
			tail.prev = head;
			countToBucket = new HashMap<>();
			keyToCount = new HashMap<>();
	}
	
	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc(String key) {
		if (keyToCount.containsKey(key)) {   //如果存在，执行+1操作即可
				changeKey(key, 1);
		} else {
			keyToCount.put(key, 1);      //如果不存在，创建即可
			if (head.next.count != 1) {
				addBucketAfter(new Bucket(1), head);
			}
			head.next.keySet.add(key);
			countToBucket.put(1, head.next);
		}
	}
	
	/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	public void dec(String key) {
		if (keyToCount.containsKey(key)) {
			int count = keyToCount.get(key);
			if (count == 1) {        //如果刚好为1，移除即可
				keyToCount.remove(key);
				removeKeyFromBucket(countToBucket.get(count), key);
			} else {
				changeKey(key, -1);		//执行-1操作
			}
		}
	}
	
	/** Returns one of the keys with maximal value. */
	public String getMaxKey() {
		if (tail.prev == head) return "";
		return (String) tail.prev.keySet.iterator().next();
	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		if (head.next == tail) return "";
		return (String) head.next.keySet.iterator().next();        
	}

	private void changeKey(String key, int offset) {  
		int count = keyToCount.get(key);
		keyToCount.put(key, count + offset);
		Bucket curBucket = countToBucket.get(count);
		Bucket newBucket;
		if (countToBucket.containsKey(count + offset)) {
			newBucket = countToBucket.get(count + offset);
		} else {
			newBucket = new Bucket(count + offset);
			countToBucket.put(count + offset, newBucket);
			addBucketAfter(newBucket, offset == 1 ? curBucket : curBucket.prev);
		}
		newBucket.keySet.add(key);
		removeKeyFromBucket(curBucket, key);
	}
	
	private void removeKeyFromBucket(Bucket bucket, String key) {
		bucket.keySet.remove(key);
		if (bucket.keySet.size() == 0) {
			removeBucketFromList(bucket);
			countToBucket.remove(bucket.count);
		}
	}

	private void removeBucketFromList(Bucket bucket) {
		bucket.prev.next = bucket.next;
		bucket.next.prev = bucket.prev;
		bucket.next = null;
		bucket.prev = null;
	}

	private void addBucketAfter(Bucket newBucket, Bucket prevBucket) {
		newBucket.prev = prevBucket;
		newBucket.next = prevBucket.next;
		prevBucket.next.prev = newBucket;
		prevBucket.next = newBucket;
	}
}

public class AllOoneDataStructure {}
