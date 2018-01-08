
package FTC;

import java.util.concurrent.Semaphore;

class ThreadDemo extends Thread {


    String threadName;
    int intervalThread; // 30
    int intervalEnd;    // 117
    Semaphore sem;


    ThreadDemo(String threadName, int intervalThread, int intervalEnd, Semaphore sem) {
        this.sem = sem;
        this.threadName = threadName;
        this.intervalEnd = intervalEnd;
        this.intervalThread = intervalThread;
    }


    @Override
    public void run() {
        if (intervalEnd > TestThread.contor) {
            System.out.println(threadName + Thread.currentThread().getId());
            try {
                sem.acquire();
                for (int i = 0; i < intervalThread; i++) {
                    if (intervalEnd > TestThread.contor) {
                        ++TestThread.contor;
                        //System.out.println(threadName + Thread.currentThread().getId() + "   Contor = " + TestThread.contor);

                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sem.release();
    }
}

