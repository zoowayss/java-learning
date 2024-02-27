package top.zoowayss.simple;

/**
 * @Description: This is a simple java application which is not used any dependency.
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/2/27 12:10
 */

public class Application {

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
