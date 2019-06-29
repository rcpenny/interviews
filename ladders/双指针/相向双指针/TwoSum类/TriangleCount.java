/**
 * Given an array of integers, how many three numbers can be found in the array,
 *  so that we can build an triangle whose three edges length is the three numbers that we find?
 */
public class TriangleCount {
	public int triangleCount(int S[]) {
		int left = 0, right = S.length - 1;
		int ans = 0;
		Arrays.sort(S);
		for (int i = 0; i < S.length; i++) {
			left = 0;
			right = i - 1;
			while (left < right) {
				if (S[left] + S[right] > S[i]) {
					ans = ans + (right - left);
					right --;
				} else {
					left ++;
				}
			}
		}
		return ans;
	}
}
