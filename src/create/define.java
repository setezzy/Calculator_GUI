package create;

/**
 * Created by Zhiyi Zhang on 2017/9/22.
 */
public class define {
        private int ele; //分子
        private int den; //分母

        public define(){}
        public define(int ele,int den){
            this.den=den;
            this.ele=ele;
        }

        public void setDen(int den) {
            this.den=den;
        }

        public void setEle(int ele) {
            this.ele=ele;
        }

        public int getEle(){
            return ele;
        }

        public int getDen(){
            return den;
        }
}

