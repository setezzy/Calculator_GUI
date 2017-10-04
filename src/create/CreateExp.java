package create;

/**
 * Created by Zhiyi Zhang on 2017/9/21.
 */
public class CreateExp {
    public static String exp(int count){
        int i=0;
        String express="";
        CreateFig cf=new CreateFig();
        CreateOpe co=new CreateOpe();
        define[] num=cf.fig(count);
        co.ope(count);
        for(int j=0;j<=count;j++){
            //若有括号则先插入括号
            if(co.op[i]=='('){
                express+=co.op[i];
                i++;
            }
            //插入操作数
            if(num[j].getDen()!=1 && num[j].getEle()!=0) {           //分母不为1且分子不为0时
                express+= num[j].getEle() + "/" + num[j].getDen();   //表示为分数形式
            }
            else {
                express += num[j].getEle();
            }
            if(co.op[i]==')'){
                express+=co.op[i];
                i++;
            }
            //插入运算符
            express+=co.op[i];
            i++;
        }
        return express;
    }
}