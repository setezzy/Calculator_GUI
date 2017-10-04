package create;

/**
 * Created by Zhiyi Zhang on 2017/9/21.
 */
public class CreateOpe {
    char[] op=new char[10];
    void ope(int count){  //定义生成运算符数量
        int i=0;
        for(int j=0;j<count;j++){
           int k=(int)(Math.random()*4);
            switch(k){
                case 0:       //加
                    op[i]='+';
                    break;
                case 1:      //减
                    op[i]='-';
                    break;
                case 2:      //乘
                    if(i>=1){
                    if((op[i-1]=='+')||(op[i-1]=='-')) //若乘号前面是加或减号
                    if((int)((Math.random()*100)%2)==0){        //随机插入括号
                         op[i]=op[i-1];
                         op[i-1]='(';
                         op[i+1]=')';
                         i=i+2; //重新定位到乘号
                    }}
                    op[i]='*';
                    break;
                case 3:      //除
                    if(i>=1){
                    if((op[i-1]=='+')||(op[i-1]=='-')) //若除号前面是加或减号
                        if((int)((Math.random()*100)%2)==0){        //随机插入括号
                            op[i]=op[i-1];
                            op[i-1]='(';
                            op[i+1]=')';
                            i=i+2; //重新定位到除号
                        }}
                    op[i]='÷';
                    break;
                default:
                    break;
            }
            i++;
        }
        op[i]='=';
    }

}
