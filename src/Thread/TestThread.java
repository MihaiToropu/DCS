package Thread;

public class TestThread extends Thread
{

    public static void main(String[] args)
    {
        int intervalStart = 10000000;
        int intervalThread = 769231;
        String hash = "4EE700C65D08646E3F01BC2CCC240E96A4B1D6468ED522DA917FED5A540AD15E1";
        StringBuilder sb = new StringBuilder(hash);
        sb.deleteCharAt(64);
        hash = sb.toString();

        Thread[] thread = new Thread[117];
        for (int i = 0; i < thread.length; i++)
        {
            thread[i] = new ThreadDemo("Thread_" + i + "->", intervalStart + i * intervalThread, intervalStart + (i+1) * intervalThread, intervalThread, hash);
        }
        for (int i = 0; i < thread.length; i++)
        {
            thread[i].start();
        }
        for (int i = 0; i < thread.length; i++)
        {
            try
            {
                thread[i].join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Main finished");
        //for (int i = 0; i < thread.length; i++)
        //System.out.println(thread[i].isAlive());

    }
}
