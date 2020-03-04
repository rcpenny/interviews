import java.util.HashSet;
import java.util.Set;

// 给n个人的朋友名单，告诉你user，请找出user最可能认识的人。（他和user有最多的共同好友且他不是user的朋友）
//
// n <= 500 好友关系是相互的。（b若出现在a的好友名单中，a一定出现在b的好友名单中）
// 每个人的好友关系不超过 m 条，m <= 3000
// 如果有两个人和user的共同好友数目一样，***编号更小***的那个认为是最可能认识的人。
// 如果user和所有陌生人都没有共同好友，输出-1

// 输入：list = [[1,2,3,5],[0,4,5],[0,4,5],[0,5],[1,2],[0,1,2,3]]，user = 0,  
// 输出：4
// 解释：虽然5和0有3个共同好友，4和0只有2个共同好友，但是5是0的好友，所以4是0最可能认识的人

public class RecommendFriends {
	public int recommendFriends(int[][] friends, int user) {
		Set<Integer> user_friends = new HashSet<>();

		for (int i = 0; i < friends[user].length; i++)
			user_friends.add(friends[user][i]);
		
		int max_shared = 0;
		int recommend_user = -1;

		for (int i = 0; i < friends.length; i++) {
			if (i == user || user_friends.contains(i)) continue;

			int cur_shared = 0;
			for (int j = 0; j < friends[i].length; j++)
				if (user_friends.contains(friends[i][j])) 
					cur_shared++;

			if (cur_shared > max_shared) {
				max_shared = cur_shared;
				recommend_user = i;
			}
		}

		return recommend_user;
	}
}
