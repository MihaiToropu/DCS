package headphonescrawler;


import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class runThread extends Thread {

    //private volatile static JSONObject listaCasti = new JSONObject();
    public volatile static int nrCastiEmag = 0;
    public volatile static int nrCastiCel = 0;
    public volatile static List<Casti> ListaCastiE = new ArrayList<Casti>();
    public volatile static List<Casti> ListaCastiC = new ArrayList<Casti>();
    public volatile static List<Casti> ListaCasti = new ArrayList<Casti>();


    private Casti Casca;
    private ListaPreturi ListaPreturiObject;

    private int tid;   //here
    //volatile static Dekker dekker = new Dekker();     //here

    private String url_1;
    private String url_2;
    private String _writer;
    private ArrayList<String> urls = new ArrayList<String>();

    private ArrayList<String> linkuri = new ArrayList<String>();
    private ArrayList<String> titluri = new ArrayList<String>();
    private ArrayList<String> iduri = new ArrayList<String>();
    private ArrayList<String> productIDuri = new ArrayList<String>();
    private ArrayList<String> preturi = new ArrayList<String>();

    private ArrayList<String> linkuri_emag = new ArrayList<String>();
    private ArrayList<String> titluri_emag = new ArrayList<String>();
    private ArrayList<String> iduri_emag = new ArrayList<String>();
    private ArrayList<String> productIDuri_emag = new ArrayList<String>();
    private ArrayList<String> preturi_emag = new ArrayList<String>();


    URL url;
    InputStream is = null;
    BufferedReader br;
    String line;
    BufferedWriter writer;


    public runThread(String url_1, String url_2, String writer, int tid) throws IOException {
        urls = new ArrayList<String>();
        this.url_1 = url_1;
        this.url_2 = url_2;
        urls.add(url_1);
        urls.add(url_2);
        this._writer = writer;
        this.tid = tid;     //here

        //System.out.println("writer is " + _writer);
    }


    public void run() {

        downloadFile();




        emag();
        cel();

        //testSIZE();           HERE

    }


    public void downloadFile() {
        for (int i = 0; i < urls.size(); i++) {
            //System.out.println(urls.get(i));
            try {
                url = new URL(urls.get(i));
                is = url.openStream();  // throws an IOException
                br = new BufferedReader(new InputStreamReader(is));
                try {
                    writer = new BufferedWriter(new FileWriter(_writer, true));

                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException e) {
                }
            } catch (MalformedURLException mue) {
                //mue.printStackTrace();
            } catch (IOException ioe) {
                //ioe.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException ioe) {
                }
            }
        }
    }


    public void emag() {
        try {
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(_writer));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();
            } catch (IOException e) {
            }
            String content = contentBuilder.toString();
            //System.out.println(content);
            Document doc = Jsoup.parse(content);
            Elements links = doc.select("h2.card-body [href]");
            Elements titles = doc.select("h2.card-body");
            Elements prices = doc.select("p.product-new-price");
            Elements ids = doc.select("input[name='product[]']");
            //Elements productIDs = doc.select("div.thumbnail-wrapper a[href]");

            for (org.jsoup.nodes.Element element : links) {
                linkuri_emag.add(element.attr("href"));
                synchronized (this) {
                    ++nrCastiEmag;
                }
            }
            for (Element element : titles) {
                titluri_emag.add(element.text());
            }
            for (Element element : prices) {
                String str = element.text().replace(" Lei", "");
                //str = new StringBuilder(str).insert(str.length() - 2, ".").toString();
                preturi_emag.add(str.replace(".", ""));
                //System.out.println(str.replace(".", ""));
            }
            for (Element element : ids) {
                iduri_emag.add(element.attr("value"));
            }

            for (String element : linkuri_emag) {
                String url_2 = element;
                Document doc_2 = Jsoup.connect(url_2).get();
                Elements productIDs = doc_2.select("span.product-code-display");

                for (Element element1 :
                        productIDs) {
                    String temp = element1.text();
                    String[] parts = temp.split(":");
                    String second = parts[1];
                    second = second.replaceFirst(" ", "");
                    productIDuri_emag.add(second.toUpperCase());


                }
            }


            for (int x = 0; x < linkuri_emag.size(); x++) {
//                System.out.println("link_EMAG  " + linkuri_emag.get(x));
//                System.out.println("titlu_EMAG  " + titluri_emag.get(x));
//                System.out.println("pret_EMAG  " + preturi_emag.get(x));
//                System.out.println("idiuri_EMAG  " + iduri_emag.get(x));
//                System.out.println("productID_EMAG  " + productIDuri_emag.get(x));


                int gasit = 0;
//                for (Casti iter :
//                        ListaCasti) {
//                    if (productIDuri_emag.get(x).equals(iter.getProductID())) {
//                        gasit = 1;
//                        Casca.setID(iduri_emag.get(x));
//                        Casca.setLink(linkuri_emag.get(x));
//                        Casca.setPret(Integer.parseInt(preturi_emag.get(x)));
//                        synchronized (this) {
//                            iter.listaPreturi.add(ListaPreturiObject);      //critical section
//                        }
//                    }
//                }
//                System.out.println("\n");


                if (1 != gasit) {
                    Casca = new Casti();
                    Casca.setLink(linkuri_emag.get(x));
                    Casca.setTitlu(titluri_emag.get(x));
                    Casca.setProductID(productIDuri_emag.get(x));
                    Casca.setID(iduri_emag.get(x));
                    Casca.setLink(linkuri_emag.get(x));
                    Casca.setPret(Integer.parseInt(preturi_emag.get(x)));
                    synchronized (this) {                               // critical section
                        ListaCastiE.add(Casca);                          //
                    }
                }




            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cel() {
        try {
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(_writer));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();
            } catch (IOException e) {
            }
            String content = contentBuilder.toString();
            //System.out.println(content);
            Document doc = Jsoup.parse(content);
            Elements links = doc.select("a[class=\"productListing-data-b product_link product_name\"]");
            //Elements links = doc.select("h4.productTitle[href]");
            //Elements titles = doc.select("h4.productTitle");
            Elements prices = doc.select("b[itemprop='price']");
            Elements ids = doc.select("div.stoc_list span[id]");
            Elements productIDs = doc.select("span[itemprop=productID]");

            for (Element element : links) {
                linkuri.add(element.attr("href"));
                titluri.add(element.text());

                //dekker.Pmutex(tid);     //here
                synchronized (this) {
                    nrCastiCel++;
                }//here
                //dekker.Vmutex(tid);     //here

            }
//            for (Element element : titles) {
//                titluri.add(element.text());
//            }
            for (Element element : prices) {
                preturi.add(element.attr("content"));
            }
            for (Element element : ids) {
                String str = element.attr("id");
                str = new StringBuilder(str).substring(1, str.length() - 2);
                iduri.add(str);
            }
            for (Element element : productIDs) {
                String str = element.attr("content").replace("\"", "");
                productIDuri.add(str.toUpperCase());
            }


            for (int x = 0; x < linkuri.size(); x++) {
                Casca = new Casti();
                int gasit = 0;
//                for (Casti iter :
//                        ListaCasti) {
//                    if (productIDuri.get(x).equals(iter.getProductID())) {
//                        gasit = 1;
//                        Casca.setProductID(productIDuri.get(x));
//                        Casca.setTitlu(titluri.get(x));
//                        Casca.setID("personal");
//                        Casca.setLink(linkuri.get(x));
//                        Casca.setPret(Integer.parseInt(preturi.get(x)));
//                        synchronized (this) {
//                            ListaCasti.add(Casca);      //critical section
//                        }
//                    }
//                }


                if (1 != gasit) {
                    Casca.setLink(linkuri.get(x));
                    Casca.setTitlu(titluri.get(x));
                    Casca.setProductID(productIDuri.get(x));
                    Casca.setID(iduri.get(x));
                    Casca.setLink(linkuri.get(x));
                    Casca.setPret(Integer.parseInt(preturi.get(x)));
                    synchronized (this) {                               // critical section
                        ListaCastiC.add(Casca);                          //
                    }
                }

            }


        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    public void testSIZE() {
        System.out.println("link_cel SIZE  " + linkuri.size());
        System.out.println("titlu_cel SIZE  " + titluri.size());
        System.out.println("pret_cel SIZE  " + preturi.size());
        System.out.println("idiuri_cel SIZE  " + iduri.size());
        System.out.println("productID_cel SIZE  " + productIDuri.size());
        System.out.println("URLS URLS SIZE  " + urls.size());

        System.out.println("\n\n\n");

        System.out.println("link_EMAG SIZE  " + linkuri_emag.size());
        System.out.println("titlu_EMAG SIZE  " + titluri_emag.size());
        System.out.println("pret_EMAG SIZE  " + preturi_emag.size());
        System.out.println("idiuri_EMAG SIZE  " + iduri_emag.size());
        System.out.println("productID_EMAG SIZE  " + productIDuri_emag.size());
        System.out.println("URLS URLS SIZE  " + urls.size());
        System.out.println("\n\n\n");
        for (int i = 0; i < preturi_emag.size(); i++) {
            System.out.println(preturi_emag.get(i));
        }
    }


}











