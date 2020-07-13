import com.design.factory.singtelon.ExetuThread;
import com.design.factory.singtelon.contanierSingleton;

public class contanerSingletonTest {
    public static void main(String[] args) throws Exception {
//        Object singtelon = contanierSingleton.getSingtelon("com.design.factory.singtelon.contanierSingleton");
//        System.out.println(singtelon);
//        Thread th = new Thread(new ExetuThread());
////        th.start();
////        Thread th1 = new Thread(new ExetuThread());
////        th1.start();
////        Thread th2 = new Thread(new ExetuThread());
////        th2.start();
        int[] i = new int[]{1, 2, 3, 4, 5, 6};
        twoSum(i, 5);

    }

    public static void twoSum(int[] nums, int target) {
        int k = 0;
        int h = 0;
        for (int i = 0; i < nums.length; i++) {
            k = i;
            int ii = target - nums[i];
            for (int j = ++i; j < nums.length; j++) {
                if (ii == nums[j]) {
                    h = j;
                    break;
                }
            }
            break;
        }
        System.out.println(k + "-------" + h);
    }
}
