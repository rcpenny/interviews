// 假设你有一个长花圃，其中有些地块已经被种植了，但是有些地块没有。但是，花不能够在相邻的地块下种植 - 
// 他们会争夺水从而导致两者的死亡。
// 给定一个花圃（用一个包含0和1的数组来表示，其中0代表空，1代表非空），和一个数字n，
// 返回n朵新的花在这个花圃上以能否在不违反“无相邻花”的规则种植。

// 输入: flowerbed = [1,0,0,0,1], n = 2
// 输出: False

public class CanPlaceFlowers {
  public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int count = 0;
		int i = 0;

		while (i < flowerbed.length) {
			// 检查是否种满花了
		  if (count >= n) return true;

			// 是花就跳2步
			if (flowerbed[i] == 1) {
				i += 2;
				continue;
			}

			// 不是花，check左
			if (i > 0 && flowerbed[i - 1] == 1) {
				i += 1;
				continue;
			}

			// 不是花，check右
			if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) {
				i += 3;
				continue;
			}

			// 可种
			flowerbed[i] = 1;
			i += 2;
			count++;
		}

		// last check
		return count >= n;
	}
}
