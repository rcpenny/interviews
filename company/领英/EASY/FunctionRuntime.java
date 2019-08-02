import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 给定一系列的描述函数进入和退出的时间，问每个函数的运行时间是多长。

// 输入: s=["F1 Enter 10","F2 Enter 18","F2 Exit 19","F1 Exit 20"]
// 输出: ["F1|10","F2|1"]
// 解释: F1从10时刻进入，20时刻退出，运行时长为10，F2从18时刻进入，19时刻退出，运行时长为1.
// lint1486

public class FunctionRuntime {

  public String[] getRuntime(String[] a) {
    Map<String, Integer> func2time = new HashMap<>();

    String[] rep;
    for (String s : a) {
      rep = s.split(" ");
  
      if (!func2time.containsKey(rep[0])) {
        func2time.put(rep[0], 0);
      }

      Integer value = Integer.valueOf(rep[2]);
      if (rep[1].equals("Enter"))
        func2time.put(rep[0], func2time.get(rep[0]) - value);
      else
        func2time.put(rep[0], func2time.get(rep[0]) + value);
    }

    // function会被重复call
    String[] result = new String[func2time.size()];
    int i = 0;
    for (String func : func2time.keySet()) {
      result[i] = func;
      i++;
    }

    // 先sort function name再生成result
    Arrays.sort(result);
    for (i = 0; i < result.length; i++) {
      result[i] = result[i] + "|" + String.valueOf(func2time.get(result[i]));
    }
    
    return result;
  }
}
