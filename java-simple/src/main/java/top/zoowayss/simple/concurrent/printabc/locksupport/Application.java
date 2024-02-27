package top.zoowayss.simple.concurrent.printabc.locksupport;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/2/27 19:11
 */

public class Application {

    private static Thread ta,tb,tc;

    public static void main(String[] args) {

        Printer a = new Printer("A");
        Printer b = new Printer("B");
        Printer c = new Printer("C");

        ta = new Thread(() -> {
            a.print(0, tb);
        });

        tb = new Thread(() -> {
            b.print(1, tc);
        });

        tc = new Thread(() -> {
            c.print(2, ta);
        });

        ta.start();
        tb.start();
        tc.start();
    }
}


@Slf4j
@AllArgsConstructor
class Printer{

    private String str;

    private static int loopNum=10000;

    private volatile static int state=0;

    public void print(int state,Thread t) {
        for (int i = 0; i < loopNum; i++) {
                while (Printer.state % 3 != state) {
                    LockSupport.park();
                }
                log.info(str);
                Printer.state++;
                LockSupport.unpark(t);
        }
    }
}
