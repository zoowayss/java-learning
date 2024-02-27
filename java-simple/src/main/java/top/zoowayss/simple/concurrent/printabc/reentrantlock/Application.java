package top.zoowayss.simple.concurrent.printabc.reentrantlock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Use three threads with reentrantLock to print A, B, C in order .
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/2/27 12:13
 */

public class Application {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition ac = lock.newCondition();
        Condition bc = lock.newCondition();
        Condition cc = lock.newCondition();
        int loopNum = 5;

        Printer a = new Printer(lock, "A", loopNum);
        Printer b = new Printer(lock, "B", loopNum);
        Printer c = new Printer(lock, "C", loopNum);

        new Thread(()->{
            a.print(ac, bc);
        }).start();

        new Thread(()->{
            b.print(bc, cc);
        }).start();

        new Thread(()->{
            c.print(cc, ac);
        }).start();

        lock.lock();
        try {
            ac.signal();
        } finally {
            lock.unlock();
        }

    }


}

@AllArgsConstructor
@Slf4j
class Printer {
    private ReentrantLock lock;
    private String str;

    private int loopNum;



    public void print(Condition currentCondition, Condition nextCondition) {
        for (int i = 0; i < loopNum; i++) {
            lock.lock();


            try {
                try {
                    currentCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.info(str);

                nextCondition.signal();
            } finally {
                lock.unlock();
            }

        }
    }
}
