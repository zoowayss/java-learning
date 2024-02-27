package top.zoowayss.simple.concurrent.printabc.syncronized;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Use three threads with synchronized to print A, B, C in order .
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/2/27 12:29
 */

public class Application {


    public static void main(String[] args) {

        Printer a = new UnlockedPrinter("A");
        Printer b = new UnlockedPrinter("B");
        Printer c = new UnlockedPrinter("C");

        new Thread(()->{
            a.print(0);
        }).start();

        new Thread(()->{
            b.print(1);
        }).start();

        new Thread(()->{
            c.print(2);
        }).start();
    }
}


@AllArgsConstructor
@Slf4j
class LockedPrinter implements Printer{

    private static int loopNum = 5;

    private  static int state=0;

    private String str;


    public void print(int current) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (Printer.class) {
                while (state%3 != current) {
                    try {
                        Printer.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info(str);
                state++;
                Printer.class.notifyAll();
            }
        }
    }


}


interface Printer{
    void print(int current);
}

@AllArgsConstructor
@Slf4j
class UnlockedPrinter implements Printer{

    private static int loopNum = 5;

    private volatile   static int state=0;

    private String str;


    public void print(int current) {
        for (int i = 0; i < loopNum; i++) {
           while (state%3 != current) {

           }
            log.info(str);
            state++;
        }
    }


}
