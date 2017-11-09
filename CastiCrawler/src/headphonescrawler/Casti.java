package headphonescrawler;

import java.util.ArrayList;
import java.util.List;

public class Casti extends ListaPreturi {
    private String titlu;
    private String productID;
    public static List<ListaPreturi> listaPreturi = new ArrayList<>();


    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }


    public void setListaPreturi(List<ListaPreturi> listaPreturi) {
        this.listaPreturi = listaPreturi;
        for (ListaPreturi lp :
                listaPreturi) {
            System.out.println("ID  " + lp.getID() + "link" + lp.getLink() + "pret" + lp.getPret());
        }
    }

    public List<ListaPreturi> getListaPreturi() {
        return listaPreturi;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getTitlu() {
        return titlu;
    }


    public void addListaPreturi(ListaPreturi lpo) {
        this.listaPreturi.add(lpo);
    }


    public void printList(List<Casti> ListaCasti) {
        System.out.println("Lista casti:\n");
        for (Casti casca :
                ListaCasti) {
            System.out.print("Titlu: " + casca.getTitlu());
            System.out.print("\nLink: " + casca.getLink() + "\nPret: " + casca.getPret() + "\n\n\n");
            //if (casca.getID2() != null && casca.getLink2() != null && casca.getPret2() != 0)
            // System.out.println("ID: " + casca.getID2() + "\nLink: " + casca.getLink2() + "\nPret: " + casca.getPret2() + "\n\n\n");
        }

    }

    public synchronized List<Casti> getListForBlackFriday(List<Casti> ListaCastiE, List<Casti> ListaCastiC, List<Casti> List) {
        List<Casti> allElements = new ArrayList<>();
        Casti cascheta = null;
        int contor = 0;
        for (Casti cascaE : ListaCastiC) {
            allElements.add(cascaE);
        }
        for (Casti cascaC : ListaCastiE) {
            allElements.add(cascaC);
        }


        for (Casti cascaAll : allElements) {
            for (Casti cascaC : ListaCastiE) {

                cascheta = new Casti();
                if (cascaAll.getProductID().equals(cascaC.productID)) {

                    //System.out.println(cascaE.getProductID() + "==" + cascaC.productID);
                    if (cascaC.getPret() > (cascaAll.getPret() / 100)) {
                        //System.out.println("pret cel = " + cascaC.getPret() + " > pret emag = " + (cascaE.getPret() / 100));
//                        System.out.println(cascaE.getTitlu());
//                        System.out.println(cascaE.getLink());
//                        System.out.println(cascaE.getPret() / 100 + "," + cascaE.getPret() % 100);
                        cascheta.setTitlu(cascaAll.getTitlu());
                        cascheta.setProductID(cascaAll.getProductID());
                        cascheta.setLink(cascaAll.getLink());
                        cascheta.setPret(cascaAll.getPret());


                    } else {
//                        System.out.println(cascaC.getTitlu());
//                        System.out.println(cascaC.getLink());
//                        System.out.println(cascaC.getPret());
                        cascheta.setTitlu(cascaC.getTitlu());
                        cascheta.setProductID(cascaC.getProductID());
                        cascheta.setLink(cascaC.getLink());
                        cascheta.setPret(cascaC.getPret());

                    }
                } else {
                    cascheta.setTitlu(cascaC.getTitlu());
                    cascheta.setProductID(cascaC.getProductID());
                    cascheta.setLink(cascaC.getLink());
                    cascheta.setPret(cascaC.getPret());

                }

                if (contor == 0) {
                    List.add(cascheta);
                    ++contor;
                    System.out.println("contor = " + contor);
                }

                int x = 1;
                for (Casti cascaList :
                        List) {
                    if (cascheta.getProductID().equals(cascaList.getProductID())) {
                        x = 0;
                    }
                }
                if (x == 1) {
                    List.add(cascheta);
                    ++contor;
                }

            }

        }
        System.out.println("\n\n\n" + contor);

            for (Casti cascaE : ListaCastiC) {
                for (Casti cascaAll : allElements) {


                    cascheta = new Casti();
                if (cascaAll.getProductID().equals(cascaE.productID)) {

                    //System.out.println(cascaE.getProductID() + "==" + cascaC.productID);
                    if (cascaE.getPret() > (cascaAll.getPret() / 100)) {
                        //System.out.println("pret cel = " + cascaC.getPret() + " > pret emag = " + (cascaE.getPret() / 100));
//                        System.out.println(cascaE.getTitlu());
//                        System.out.println(cascaE.getLink());
//                        System.out.println(cascaE.getPret() / 100 + "," + cascaE.getPret() % 100);
                        cascheta.setTitlu(cascaAll.getTitlu());
                        cascheta.setProductID(cascaAll.getProductID());
                        cascheta.setLink(cascaAll.getLink());
                        cascheta.setPret(cascaAll.getPret());


                    } else {
//                        System.out.println(cascaC.getTitlu());
//                        System.out.println(cascaC.getLink());
//                        System.out.println(cascaC.getPret());
                        cascheta.setTitlu(cascaE.getTitlu());
                        cascheta.setProductID(cascaE.getProductID());
                        cascheta.setLink(cascaE.getLink());
                        cascheta.setPret(cascaE.getPret());

                    }
                } else {
                    cascheta.setTitlu(cascaE.getTitlu());
                    cascheta.setProductID(cascaE.getProductID());
                    cascheta.setLink(cascaE.getLink());
                    cascheta.setPret(cascaE.getPret());

                }

                if (contor == 0) {
                    List.add(cascheta);
                    ++contor;
                    System.out.println("contor = " + contor);
                }

                int x = 1;
                for (Casti cascaList :
                        List) {
                    if (cascheta.getProductID().equals(cascaList.getProductID())) {
                        x = 0;
                    }
                }
                if (x == 1) {
                    List.add(cascheta);
                    ++contor;
                }

            }

        }


        for (Casti casti : List) {
            System.out.println(casti.getTitlu());
        }

        System.out.println("contor is = " + contor);
        return List;
    }
}

