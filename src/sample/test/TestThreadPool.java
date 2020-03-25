package sample.test;

/**
 * @Description: 多线程测试$
 * @Author: ifacker
 * @Date: $
 */
public class TestThreadPool implements Runnable {

    private int index ;

    public TestThreadPool(int i) {
        this.index = i;
    }

    @Override
    public void run() {
        System.out.println("fuck: " + index);
    }
}
