package Thread;

import java.security.MessageDigest;

class ThreadDemo extends Thread{


    Thread t;
    String threadName;
    int intervalStart;
    int intervalEnd;
    int intervalThread;
    String hash="";


    ThreadDemo( String threadName, int intervalStart, int intervalEnd, int intervalThread, String hash)
    {
        this.threadName = threadName;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.intervalThread = intervalThread;
        this.hash = hash;
    }


    @Override
    public void run()
    {

        System.out.println(threadName+Thread.currentThread().getId());

        for ( int i=intervalStart;i<intervalEnd;i++){
            // System.out.println(sha256(i)+"="+i);
            if(sha256(i).equals(hash)) {
                System.out.println(" Thread: " + ((int) (i - 10000000) / intervalThread) + " Found password: " + i);
            }

        }
    }
    /*
         Starts the thread in a separate path of execution, then invokes the run() method on this Thread object.
     */



    String sha256(int possiblePassword)
    {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((""+possiblePassword).getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString().toUpperCase();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
