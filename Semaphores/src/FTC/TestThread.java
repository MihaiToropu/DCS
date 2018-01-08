
package FTC;

import java.util.concurrent.Semaphore;

public class TestThread extends Thread {
     static int contor = 0;

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1);
        Thread[] thread = new Thread[4];
        int intervalEnd = 117;    //pana unde numaram
        int intervalThread = 30;


        for (int iter = 0; iter < thread.length; iter++) {
            thread[iter] = new ThreadDemo("Thread_" + iter + "->",intervalThread, intervalEnd, sem);
        }


        for (int iter = 0; iter < thread.length; iter++) {
            thread[iter].start();
            try {
                thread[iter].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //for (int iter = 0; iter < thread.length; iter++) {

        //}


        System.out.println("Contorul este = " + contor);
        //for (int i = 0; i < thread.length; i++)
        //System.out.println(thread[i].isAlive());

    }
}
