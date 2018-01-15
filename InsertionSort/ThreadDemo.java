package InsertionSort;

import java.util.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

class ThreadDemo extends Thread {


    int[] arr1;
    Semaphore sem;
    int start;


    ThreadDemo(int start, int[] arr1, Semaphore sem) {
        this.arr1 = arr1;
        this.start = start;
        this.sem = sem;
    }


    @Override
    public void run() {
        System.out.println("Thread ID -> " + Thread.currentThread().getId());
        try {
            sem.acquire();
            doInsertionSort(arr1);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sem.release();

    }


    public void doInsertionSort(int[] input) {

        int temp;

        for (int i = start; i < start + 3; i++) {
            TestThread.sortedArray[i] = input[i];
        }
        for (int i = start; i < start + 3; i++) {
            for (int j = i; j > 0; j--) {
                if (TestThread.sortedArray[j] < TestThread.sortedArray[j - 1]) {
                    temp = TestThread.sortedArray[j];
                    TestThread.sortedArray[j] = TestThread.sortedArray[j - 1];
                    TestThread.sortedArray[j - 1] = temp;
                }
            }
        }
        for (int i = 0; i < start + 3; i++) {
            System.out.print(TestThread.sortedArray[i] + " ");
        }
        System.out.println();
    }
}
