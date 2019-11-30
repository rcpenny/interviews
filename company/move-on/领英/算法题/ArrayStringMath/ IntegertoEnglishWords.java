// 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

public class  IntegertoEnglishWords {
  public static String[] IN_TWENTY = new String [] {
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
        };
        
  public static String[] IN_HUNDRED = new String [] {
          "",
          "Ten",
          "Twenty",
          "Thirty",
          "Forty",
          "Fifty",
          "Sixty",
          "Seventy",
          "Eighty",
          "Ninety"
      };
      
  public static String[] OVER_THOUSAND = new String [] {
          "",
          "Thousand",
          "Million",
          "Billion"
      };
	
	// 降维加thousnad million billion
  public String numberToWords(int num) {
    if (num == 0) return "Zero";

    String res = "";
    int i = 0;
    while (num > 0) {
      String pre = helper(num % 1000);
      if (pre != "") {
          res = pre + " " + OVER_THOUSAND[i] + " "+ res;
      }
      num /= 1000;
      i++;
    }
    return res.trim();
  }
	
	// 算三位数
  private String helper(int n) {
    String s;
    if (n < 20)
      s = IN_TWENTY[n];
    else if (n < 100) 
      s = IN_HUNDRED[n / 10] +  " " + IN_TWENTY[n % 10];
    else
      s = IN_TWENTY[n / 100] + " Hundred " + helper(n % 100);

    return s.trim();
  }
}
