import com.design.factory.singtelon.ExetuThread;
import com.design.factory.singtelon.contanierSingleton;

public class contanerSingletonTest {
    public static void main(String[] args) throws Exception {
//        Object singtelon = contanierSingleton.getSingtelon("com.design.factory.singtelon.contanierSingleton");
//        System.out.println(singtelon);
        Thread th = new Thread(new ExetuThread());
        th.start();
        Thread th1 = new Thread(new ExetuThread());
        th1.start();
        Thread th2 = new Thread(new ExetuThread());
        th2.start();
    }
}
