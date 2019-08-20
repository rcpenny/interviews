import java.util.HashMap;
import java.util.Map;

// Inc(Key) - 插入值为1的Key。或者将现有Key的值增加1。Key保证为非空字符串。
// Dec(Key) - 如果Key的值为1，则将其从数据结构中删除。 否则将现有Key的值减1。如果Key不存在，则此函数不执行任何操作。 Key保证是非空字符串。
// GetMaxKey() - 返回一个值最大的Key。 如果不存在这样的元素，则返回空字符串""。
// GetMinKey() - 返回一个值最小的Key。 如果不存在这样的元素，则返回空字符串""。

// 所有的操作都需要 O(1) 的时间复杂度。
// lint1245

class AllOoneDataStructure {
  Map<String, Integer> keyToFreq;

  public AllOne() {
    this.keyToFreq = new HashMap<>();
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    
  }
  
  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
      
  }
  
  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
      
  }
  
  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
      
  }
}
