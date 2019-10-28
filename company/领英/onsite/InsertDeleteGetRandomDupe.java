// 允许有dupe O1

// 使用LinkedHashSet
// LinkedHashSet maintains the insertion order.

class RandomizedCollection {
  List<Integer> list;
  Map<Integer, Set<Integer>> numToIndex;

  public RandomizedCollection() {
    this.list = new ArrayList<>();
    this.numToIndex = new HashMap<>();
  }

  public boolean insert(int val) {
    if (!numToIndex.containsKey(val))
      numToIndex.put(val, new LinkedHashSet<>());  // 使用LinkedHashSet
    
    // 加入这个数字的index
    numToIndex.get(val).add(list.size());
    list.add(val);
    
    return numToIndex.get(val).size() == 1;
  }

  public boolean remove(int val) {
    if (!numToIndex.containsKey(val) || numToIndex.get(val).size() == 0)
      return false;
		
		// 移除这个num最后一个index
    int removed_index = numToIndex.get(val).iterator().next();
    numToIndex.get(val).remove(removed_index);
		
		// 把最后一个数字放进进移除的位置
    int last = list.get(list.size() - 1);
    list.set(removed_index, last);
		
		// 更新map
    numToIndex.get(last).add(removed_index);
    numToIndex.get(last).remove(list.size() - 1); // 这个remove的不是index,是这个index数字....
    
    list.remove(list.size() - 1);
    
    return true;
  }

  public int getRandom() {
    int randomIndex = (int) (list.size() * Math.random());
    return list.get(randomIndex);
  }
}
