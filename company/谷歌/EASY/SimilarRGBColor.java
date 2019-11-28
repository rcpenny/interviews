/**
 * 在本题中，每个大写字母代表从“0”到“f”的一些十六进制数字。

    红绿蓝三元色#AABBCC可以简写为#ABC。 例如，#15c是颜色#1155cc的简写。

    现在，假设两种颜色#ABCDEF和#UVWXYZ之间的相似性是-(AB - UV) ^ 2 - (CD - WX) ^ 2 - (EF - YZ) ^ 2。

    给定颜色#ABCDEF，返回与#ABCDEF最相似且含有一个简写的7字符颜色（也就是说，它可以用类似#XYZ的形式表示）。

    color 是一个长度为7的字符串。
    color 是一个合法的RGB颜色: 对于每一个 i > 0, color[i] 是一个 0 到 f的十六进制数字。
    任何一个有最高相似度的答案都是正确的。
    所有的输入输出都是小写字母，输出应为7个字符的字符串。

    输入: color = "#09f166"
    输出: "#11ee66"
    解释:  
    二者相似程度为 -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
    这是所有能够简写的颜色里最接近的颜色。
 */
public class SimilarRGBColor {
  public String similarRGB(String color) {
    StringBuilder sb = new StringBuilder(color.length());
    sb.append("#");
    for (int i = 1; i < color.length(); i += 2){
      sb.append(getHexDigits(color.charAt(i), color.charAt(i + 1)));
    }
    return sb.toString();
  }

  private String getHexDigits(char c1, char c2){
    int d1 = Character.isDigit(c1)? c1 - '0': 10 + c1 - 'a';
    int d2 = Character.isDigit(c2)? c2 - '0': 10 + c2 - 'a';

    int sum       = d1 * 16 + d2;

    // 形如AA的十六进制数必定是17的倍数，我们只要判断离原数最接近的17的倍数是多少即可
    int index     = sum / 17; // [ 0x00(0) , 0x11(17), 0x22(34),  0x33(51), ........., 0xff(255) ]
    int remainder = sum % 17;
    if (remainder > 17 / 2){
        index++;
    }

    char c = 0 <= index && index <= 9? (char)('0' + index): (char)('a' + index - 10);
    return String.valueOf(c) + String.valueOf(c);
  }    
}