public class LFUCache {
  int capacity;

	HashMap<Integer, Integer> keyToVal;
  HashMap<Integer, Integer> keyToFreq;
  HashMap<Integer, LinkedHashSet<Integer>> FreqToKeyLists;
	int min = -1;
	
  public LFUCache(int capacity) {
			this.capacity = capacity;
			
			this.keyToVal = new HashMap<>();
      keyToFreq = new HashMap<>();
      FreqToKeyLists = new HashMap<>();
      FreqToKeyLists.put(1, new LinkedHashSet<>());
  }
  
  public int get(int key) {
      if(!keyToVal.containsKey(key))
          return -1;
      int count = keyToFreq.get(key);
      keyToFreq.put(key, count+1);
      FreqToKeyLists.get(count).remove(key);
      if(count==min && FreqToKeyLists.get(count).size()==0)
          min++;
      if(!FreqToKeyLists.containsKey(count+1))
          FreqToKeyLists.put(count+1, new LinkedHashSet<>());
      FreqToKeyLists.get(count+1).add(key);
      return keyToVal.get(key);
  }
  
  public void set(int key, int value) {
      if(cap<=0)
          return;
      if(keyToVal.containsKey(key)) {
          keyToVal.put(key, value);
          get(key);
          return;
      } 
      if(keyToVal.size() >= cap) {
          int evit = FreqToKeyLists.get(min).iterator().next();
          FreqToKeyLists.get(min).remove(evit);
          keyToVal.remove(evit);
      }
      keyToVal.put(key, value);
      keyToFreq.put(key, 1);
      min = 1;
      FreqToKeyLists.get(1).add(key);
  }
}
