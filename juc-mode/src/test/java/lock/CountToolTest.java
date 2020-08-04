package lock;

public class CountToolTest {
    public static void main(String[] args) {
        CountTool tool = new CountTool();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    tool.decre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    tool.incre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    tool.decre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();


        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    tool.incre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
