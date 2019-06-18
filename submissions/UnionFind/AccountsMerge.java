import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * https://www.lintcode.com/problem/accounts-merge/
 * https://leetcode.com/problems/accounts-merge/
 *
 * 一种做法是将每个原始账户作为一个节点，
 * 但是两个账户可以连接取决于它们共享至少一个邮箱，处理起来比较麻烦
 *
 * 我们可以将每个邮箱作为一个节点，同一个原始账户中的邮箱之间连边。
 * 并用老大哥节点存储用户名
 *
 * 	Input:
			[
				["John","johnsmith@mail.com","john_newyork@mail.com"],
				["John","johnsmith@mail.com","john00@mail.com"],
				["Mary","mary@mail.com"],
				["John","johnnybravo@mail.com"]
			]

		Output:
			[
				["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
				["John","johnnybravo@mail.com"],
				["Mary","mary@mail.com"]
			]
 *
 * 灵活定义并查集的节点
 */

public class AccountsMerge {

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<List<String>> results = new ArrayList<>();
		if (accounts == null || accounts.size() == 0) return results;

		UnionFind uf = new UnionFind(accounts.size());

		// 初始化数组parent[], 来存储每个email对应的account在parent[]中的index
		HashMap<String, Integer> emailToAccountIndex = new HashMap<>();

		for (int accountIndex = 0; accountIndex < accounts.size(); accountIndex++) {
			List<String> account = accounts.get(accountIndex);
			for (int emailIndex = 1; emailIndex < account.size(); emailIndex++) {
				String email = account.get(emailIndex);
				emailToAccountIndex.putIfAbsent(email, accountIndex);
				uf.union(emailToAccountIndex.get(email), accountIndex);
			}
		}

		// 初始化accountIndexToEmails, 将acount index与与之相关emails对应起来, 只剩下bigbro的index
		HashMap<Integer, List<String>> accountIndexToEmails = new HashMap<>();
		for (String email : emailToAccountIndex.keySet()) {
			int accountIndex = emailToAccountIndex.get(email);
			int bigbroIndex = uf.find(accountIndex);
			accountIndexToEmails.putIfAbsent(bigbroIndex, new ArrayList<String>());
			accountIndexToEmails.get(bigbroIndex).add(email);
		}

		for (Integer accountIndex : accountIndexToEmails.keySet()) {
			List<String> perfectAccount = new ArrayList<>();
			String accountName = accounts.get(accountIndex).get(0);
			perfectAccount.add(accountName);

			List<String> emails = accountIndexToEmails.get(accountIndex);
			Collections.sort(emails);
			perfectAccount.addAll(emails);
			results.add(perfectAccount);
		}

		return results;
  }
}

class UnionFind {
	/** parent account's index. "Mary"对应 parent[3] */
	private int[] parent;

	public UnionFind(int size) {
		parent = new int[size];
		for (int i = 0; i < size; i++) parent[i] = i;
	}

	public int find(int child) {
		int childCopy = child;
		while (childCopy != parent[childCopy]) childCopy = parent[childCopy];
		int bigbro = childCopy;

		int prevParent;
		while (child != bigbro) {
			prevParent = parent[child];
			parent[child] = bigbro;
			child = prevParent;
		}
		return bigbro;
	}

	public void union(int a, int b) {
		int a_bigbro = find(a);
		int b_bigbro = find(b);
		if (a_bigbro != b_bigbro) {
			parent[a_bigbro] = parent[b_bigbro];
		}
	}
}
