package factorymethod;

import com.design.factory.pattern.factorymethod.JdPayFactory;
import com.design.factory.pattern.factorymethod.Pay;

public class factorymethodTest {
    public static void main(String[] args) {
        Pay  pay = new JdPayFactory().pay();
         pay.payWay();
    }
}
