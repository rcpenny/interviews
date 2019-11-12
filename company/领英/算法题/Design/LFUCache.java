public class LFUCache {
  int capacity;

  HashMap<Integer, Integer> keyToVal;
  HashMap<Integer, Integer> keyToFreq;
  HashMap<Integer, LinkedHashSet<Integer>> freqToKeyLists;
  
  int min = -1;
	
  public LFUCache(int capacity) {
	this.capacity = capacity;
			
	this.keyToVal = new HashMap<>();
    this.keyToFreq = new HashMap<>();
    
    this.freqToKeyLists = new HashMap<>();
    freqToKeyLists.put(1, new LinkedHashSet<>());
  }
  
  public int get(int key) {
    if(!keyToVal.containsKey(key)) return -1;

    int count = keyToFreq.get(key);
    keyToFreq.put(key, count + 1);
    
    freqToKeyLists.get(count).remove(key);

    if(count == min && freqToKeyLists.get(count).size() == 0) {
      min++;
    }
        
    if(!freqToKeyLists.containsKey(count + 1)) {
      freqToKeyLists.put(count + 1, new LinkedHashSet<>());
    }

    freqToKeyLists.get(count + 1).add(key);
    
    return keyToVal.get(key);
  }
  
  public void set(int key, int value) {
    if(capacity <= 0) return;

    if(keyToVal.containsKey(key)) {
    keyToVal.put(key, value);
    get(key);
    return;
    }

    if(keyToVal.size() >= capacity) {
    int evit = freqToKeyLists.get(min).iterator().next();

    freqToKeyLists.get(min).remove(evit);

    keyToVal.remove(evit);
    }

    keyToVal.put(key, value);
    
    keyToFreq.put(key, 1);
    
    min = 1;
    
    freqToKeyLists.get(1).add(key);
  }
}
