package calculate;

/**
 * Created by Zhiyi Zhang on 2017/9/22.
 */

import java.util.ArrayList;
import java.util.List;

public class ToFrac {
    public static List<Integer> tofrac(String first, String second) {
        List<Integer> list = new ArrayList<>();
        if (first.indexOf("#") != -1) {
            first = "-" + first.substring(1, first.length());
        }
        if (second.indexOf("#") != -1) {
            second = "-" + second.substring(1, second.length());
        }
        int a1 = first.indexOf("/");   //返回'/'所在位置
        int a2 = second.indexOf("/");
        int firstele, firstDen, secondele, secondDen;
        if (a1 != -1 && a2 != -1) {
            //两个操作数都为分数
            //提取分子
            firstele = Integer.parseInt(first.substring(0, a1));
            //提取分母
            firstDen = Integer.parseInt(first.substring(a1 + 1,first.length()));
            secondele = Integer.parseInt(second.substring(0, a2));
            secondDen = Integer.parseInt(second.substring(a2 + 1, second.length()));
        } else if (a1 == -1 && a2 != -1) {                 //若第一个操作数为整数，分母设为1
            firstele = Integer.parseInt(first);
            firstDen = 1;
            secondele = Integer.parseInt(second.substring(0, a2));
            secondDen = Integer.parseInt(second.substring(a2 + 1, second.length()));
        } else if (a1 != -1 && a2 == -1) {                                //若第二个操作数为整数，分母设为1
            firstele = Integer.parseInt(first.substring(0, a1));
            firstDen = Integer.parseInt(first.substring(a1 + 1, first.length()));
            secondele = Integer.parseInt(second);
            secondDen= 1;
        } else {
            //两个操作数都为整数
            firstele = Integer.parseInt(first);
            firstDen = 1;
            secondele = Integer.parseInt(second);
            secondDen = 1;
        }
        list.add(0, firstele);
        list.add(1, firstDen);
        list.add(2, secondele);
        list.add(3, secondDen);
        return list;
    }
}
