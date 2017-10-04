package calculate;

/**
 * Created by Zhiyi Zhang on 2017/9/22.
 */
public class NewCalculate {
    public static String newcalculate(String exp) {
        int lpar = exp.indexOf('(');       //在表达式中查找左括号
        if (lpar == -1) {
            return Calculate.calculate(exp);   //若没有左括号则直接计算
        } else {
            int rpar = exp.indexOf(')');   //若有，则获取该左括号对应的第一个右括号
            String expression = exp.substring(lpar + 1, rpar);
            exp = exp + "=";
            String ans = Calculate.calculate(expression);   //计算括号内的表达式
            if (ans.indexOf("-") != -1) {
                ans = "#"
                        + ans.substring(1, ans.length());
            }
            exp = exp.substring(0, lpar) + ans
                    + exp.substring(rpar + 1, exp.length());           //用计算结果替换带括号的子串
            return newcalculate(exp);              //返回运算结果
        }
    }
}
