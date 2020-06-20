package proxy;

import com.design.factory.proxy.JdkMeipo;
import com.design.factory.proxy.ZhangLaosan;
import com.design.factory.proxy.ZhangSan;
import com.design.factory.proxy.ZhaoLiu;

public class Test {
    public static void main(String[] args) {
//        ZhangLaosan zhangLaosan = new ZhangLaosan(new ZhangSan());
//        zhangLaosan.findLove();
        JdkMeipo  jdkMeipo = new  JdkMeipo();
        jdkMeipo.getInstace(new ZhaoLiu()).findLove();



    }
}
