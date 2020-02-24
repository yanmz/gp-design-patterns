package simple;

import com.design.factory.pattern.simple.JdPay;
import com.design.factory.pattern.simple.Pay;
import com.design.factory.pattern.simple.SimpleFactory;
import com.design.factory.pattern.simple.WcPay;

public class SimplePayTest {
    public static void main(String[] args) throws Exception {
//        Pay pay = new WcPay();
//        pay.payWay();
        Pay pay = SimpleFactory.pay(JdPay.class);
        pay.payWay();
    }
}
