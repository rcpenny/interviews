import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implement a load balancer for web servers. It provide the following functionality:

// all O(1)
// Add a new server to the cluster => add(server_id).
// Remove a bad server from the cluster => remove(server_id).
// Pick a server in the cluster randomly with equal probability => pick().
// At beginning, the cluster is empty. 
// When pick() is called you need to randomly return a server_id in the cluster.

public class LoadBalancer {
  private Map<Integer, Integer> idToIndex;
  private List<Integer> cluster;

  public LoadBalancer() {
    this.idToIndex = new HashMap<>();
    this.cluster = new ArrayList<>();
  }

  public void add(int server_id) {
    if (idToIndex.containsKey(server_id)) return;
    idToIndex.put(server_id, cluster.size());
    cluster.add(server_id);
  }

  public void remove(int server_id) {
    if (!idToIndex.containsKey(server_id)) return;
    int indexOfServerId = idToIndex.get(server_id);
    int last = cluster.get(cluster.size() - 1);

    cluster.set(indexOfServerId, last);
    cluster.remove(cluster.size() - 1);
    idToIndex.remove(server_id);
    idToIndex.put(last, indexOfServerId);
  }

  public int pick() {
    int index = (int) (Math.random() * cluster.size());
    return cluster.get(index);
  }
}