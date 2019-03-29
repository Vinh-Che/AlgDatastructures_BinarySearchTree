import java.util.ArrayList;

public class IndexNode implements Comparable<IndexNode> {

    String ord;
    int teller;
    IndexNode left;
    IndexNode right;
    private ArrayList<Integer> linjeListe;


    public IndexNode() {
        ord = null;
        teller = 0;
        this.left = null;
        this.right = null;
        linjeListe = new ArrayList<Integer>();
    }

    public IndexNode(String e){
        ord = e;
        teller = 1;
        this.left = null;
        this.right = null;
        linjeListe = new ArrayList<Integer>();
    }


    public String getOrd() { return ord; }
    public int getOccurence() {
        return teller;
    }
    public ArrayList getLine() {
        return linjeListe;
    }

    public void setOccurance(int occurance) {
        teller = occurance;
    }
    public void setOrd(String item) {
        ord = item;
    }
    public void setLine(ArrayList e) {
        linjeListe = e;
    }
    public void addLine(int linenumber) {
        linjeListe.add(linenumber);
    }


    @Override
    public String toString(){
        System.out.print("Ord:" + ord);
        System.out.print(" Antall forekomster:" + teller);
        for(int i = 0; i < linjeListe.size(); i++) {
            // Printer ut hvilket linje ordet forekom i hvis det er flere linjer enn én
            if (linjeListe.get(i) > 1)
                System.out.print(" Linje:" + linjeListe.get(i));
        }
        return "";
    }

    @Override
    public int compareTo(IndexNode annenNode) {
        return ord.compareTo(annenNode.toString());
    }

}