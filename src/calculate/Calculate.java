package calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhiyi Zhang on 2017/9/22.
 */
public class Calculate {
    public static String calculate(String exp){
        List<Character> oper = Operator(exp);
        List<String> fig = Figure(exp);

        for (int i = 0; i < oper.size(); i++) { //遍历运算符容器完成乘除运算
            Character op = oper.get(i);         //取得运算符
            if (op == '*' || op == '÷') {
                oper.remove(op);
                String l = fig.remove(i);
                String r = fig.remove(i);       //取得运算符左右两侧的操作数
                int lele, lden, rele, rden;     //分别定义两个操作数的分子分母
                List<Integer> fra = ToFrac.tofrac(l, r);
                lele = fra.remove(0);
                lden = fra.remove(0);
                rele = fra.remove(0);
                rden = fra.remove(0);
                if (op == '*') {                      //乘法运算
                    fig.add(i, (lele * rele) + "/"    //将运算后的数添加在i位置
                            + (lden * rden));
                } else {
                    fig.add(i, (lele * rden) + "/"    //除法运算
                            + (lden * rele));
                }
                i--;
            }
        }
        while (!oper.isEmpty()) {                 //完成加减运算，为空时停止
            String result = null;
            Character op = oper.remove(0);
            String l = fig.remove(0);
            String r = fig.remove(0);
            int lele, lden, rele, rden;
            List<Integer> fra = ToFrac.tofrac(l, r);
            lele = fra.remove(0);
            lden = fra.remove(0);
            rele = fra.remove(0);
            rden = fra.remove(0);
            if (op == '+') {                                    //加法运算
                result = ((lele*rden) + (lden * rele))
                        + "/" + (lden * rden);
                fig.add(0, result);
            }
            if (op == '-') {                                    //减法运算
                result = ((lele * rden) - (lden* rele))
                        + "/" + (lden*rden);
                fig.add(0, result);
            }
        }
        return fig.get(0);
    }

    /*------------提取运算符--------------*/
    public static List<Character> Operator(String op){
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < op.length(); i++) {
            if (op.charAt(i) == '+' || op.charAt(i) == '-'
                    || op.charAt(i) == '*' || op.charAt(i) == '÷') {
                list.add(op.charAt(i));
            }
        }
        return list;
    }

    /*-------------提取操作数-------------*/
    public static List<String> Figure(String fig){
        int n = 0;
        int count=0;
        int k=0;
        List<String> list = new ArrayList<>();
        for(int i=0;i<fig.length();i++){
            if (fig.charAt(i) == '+' || fig.charAt(i) == '-'
                    || fig.charAt(i) == '*' || fig.charAt(i) == '÷'
                    || fig.charAt(i) == '=') {
                k=i;           //运算符的index
                count++;
            }
        }
        if(count==1){
            list.add(fig.substring(0,k));
            list.add(fig.substring(k+1,fig.length()));
        }
        else {
            for (int i = 0; i < fig.length(); i++) {
                if (fig.charAt(i) == '+' || fig.charAt(i) == '-'
                        || fig.charAt(i) == '*' || fig.charAt(i) == '÷'
                        || fig.charAt(i) == '=') {
                    list.add(fig.substring(n, i));
                    n = i + 1;
                }
            }
        }
        return list;
    }
}
