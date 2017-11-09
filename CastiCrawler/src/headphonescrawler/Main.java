package headphonescrawler;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static headphonescrawler.runThread.*;


public class Main {
    public static void main(String[] args) throws Exception {


        ArrayList<String> url_1 = new ArrayList<String>();
        ArrayList<String> url_2 = new ArrayList<String>();


        int pagesUrl_1 = 11;
        int pagesUrl_2 = 25;

        for (int i = 1; i <= pagesUrl_1; i++) {
            url_1.add("https://www.emag.ro/casti-pc/p" + i + "/c");
        }
        for (int i = 1; i <= pagesUrl_2; i++) {
            url_2.add("http://www.cel.ro/casti/0a-" + i); //here + i
        }

        Thread[] thread = new Thread[117];    //here

        for (int i = 0; i <= thread.length + 1; i++) {
            if (i > pagesUrl_1) {
                url_1.add(null);
            }
            if (i > pagesUrl_2) {
                url_2.add(null);
            }
        }


        for (int i = 0; i < thread.length; i++) {
            thread[i] = new runThread(url_1.get(i), url_2.get(i), "" + i + ".txt", i);  //here
        }

        for (int i = 0; i < thread.length; i++) {
            thread[i].start();
        }

        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Casti object = new Casti();
        object.getListForBlackFriday(ListaCastiE, ListaCastiC, ListaCasti);
        object.printList(ListaCasti);
        //FinalList.printList(getListForBlackFriday(ListaCastiE, ListaCastiC);

        //object.printList(ListaCastiC);
        //object.printList(ListaCastiE);
        System.out.println("CASTI EMAG  " + nrCastiEmag);
        System.out.println("CASTI CEL   " + nrCastiCel);


        for (int i = 0; i < pagesUrl_2; i++) {
            Path path = Paths.get("D:/DCS/CastiCrawler/" + i + ".txt");
            try {
                Files.delete(path);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", path);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", path);
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }

        System.out.println("Main finished");

    }
}