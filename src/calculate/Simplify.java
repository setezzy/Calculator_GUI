package calculate;

/**
 * Created by Zhiyi Zhang on 2017/9/22.
 */
public class Simplify {      //分数化简
    public static String gcd(String exp){
        int p=exp.indexOf('/');
        int ele,den,r,m,n=0;
        String result=null;
        ele=Integer.parseInt(exp.substring(0,p));
        den=Integer.parseInt(exp.substring(p+1,exp.length()));
        if(ele>den) {
             m=ele;
             n=den;
        }
        else{
            m=den;
            n=ele;
        }
        r=m%n;
        while (r != 0) {   //辗转相除法求最大公约数
                m = n;
                n = r;
                r=m%n;
        }
        if(den/n==1)
            result=String.valueOf(ele/n);
        else
            result = (ele / n) + "/" + (den / n);

        return result;
    }

}
