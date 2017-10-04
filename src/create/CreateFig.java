package create;

/**
 * Created by Zhiyi Zhang on 2017/9/21.
 */
public class CreateFig {
    public define[] fig (int count){ //定义生成操作数的数量
        int flag; //是否生成分数
        define[] num=new define[10];
        define def;
        for (int i=0;i<=count; i++) {
            def=new define();
            flag = (int) (Math.random()*100)%3;
            if(flag==0) {
                def.setDen((int) (Math.random() * 18) + 2); //分母为[2,20)内随机数
                def.setEle((int) (Math.random() * def.getDen()) +1); //分子要小于分母
                num[i]=def;
            } else{
                def.setDen(1);
                def.setEle((int) (Math.random() * 49) + 1); //生成[1,100)范围内的整数
                num[i]=def;
            }
        }
        return num;
    }
}