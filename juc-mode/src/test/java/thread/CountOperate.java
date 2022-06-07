package thread;


/**
 * @author yanmz
 * @version 1.0
 * @date 2020/11/18 10:11
 */
public class CountOperate extends Thread {
    public CountOperate() {
        System.out.println("CountOperate---begin");
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println("CountOperate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println("run---end");
    }

    public static void main(String[] args) {
        CountOperate countOperate = new CountOperate();
        Thread thread = new Thread(countOperate);
        thread.setName("A");
        thread.start();
    }
}
