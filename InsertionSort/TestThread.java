package InsertionSort;


import java.util.concurrent.Semaphore;

public class TestThread extends Thread {


    static int[] sortedArray = new int[9];

    public static void main(String[] args) {


        int[] arr1 = {5, 8, 3, 7, 6, 9, 2, 4, 1};
        int start = 0;
        Semaphore sem = new Semaphore(1);
        Thread[] thread = new Thread[3];

        for (int iter = 0; iter < thread.length; iter++) {
            thread[iter] = new ThreadDemo((start + iter) * 3, arr1, sem);
        }


        for (int iter = 0; iter < thread.length; iter++) {
            thread[iter].start();
            try {
                thread[iter].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

