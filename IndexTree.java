import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class IndexTree {


    private IndexNode root; // Dette er roten
    ArrayList<IndexNode> list;


    public IndexTree() {
        root = null;
        list = new ArrayList<IndexNode>();
    }


    // call your recursive add method
    public void add(String word, int lineNumber){
        if(root == null) {
            root = new IndexNode(word);
            root.addLine(lineNumber);
            list.add(root);

        }
        else {
            add(root, word, lineNumber);
        }

    }


    //a to z - switch compare signs to reverse order but have to reverse delete signs too
    private IndexNode add(IndexNode root, String word, int lineNumber){

        //original root has alrady been taken care of

        int compare = word.compareTo(root.getOrd());

        if(compare == 0) {
            int x = root.getOccurence();
            root.setOccurance(x+1);
            root.addLine(lineNumber);

        }

        //if ord is less than root --> add to the left subtree
        else if(compare < 0) {
            if(root.left != null) {
                add(root.left, word, lineNumber);
            }
            else {
                root.left = new IndexNode(word);
                root.left.addLine(lineNumber);
                list.add(root.left);
            }
        }

        //if ord is greater than root --> add to the right subtree
        else if(compare > 0 )
        {
            if(root.right != null) {
                add(root.right, word, lineNumber);
            }

            else {
                root.right = new IndexNode(word);
                root.right.addLine(lineNumber);
            }
        }
        return root;
    }




    // returns true if the ord is in the index
    public boolean contains(String word){
        for(int i = 0; i < list.size(); i++) {
            String indexnode = list.get(i).getOrd();
            int compare = word.compareTo(indexnode);
            if(compare == 0){
                return true;
            }
        }
        return false;
    }

    // call your recursive method
    public void delete(String word){
        try {
            root = delete(root, word.toUpperCase());
        }
        catch(Exception e) {
            System.out.println("String '" + word.toUpperCase() + "' not found in IndexTree");
        }
    }

    //recursive deletion of an indexnode
    private IndexNode delete(IndexNode root, String word) {
        int comparison = word.compareTo(root.getOrd());

        //this will return when there is no node that is the correct --> the try catch will catch the null pointer exception
        if(root == null) {
            return null;
        }

        //if ord is less than root, search left subtree recursively
        if(comparison < 0) {
            root.left = delete(root.left, word);
            return root;
        }

        //if ord is more than root, search right subtree recursively
        else if(comparison > 0) {
            root.right = delete(root.right, word);
            return root;
        }
        //not checking for the comparison implies you have found the comparison
        else  {
            //root has no children
            if(root.left == null && root.right == null){
                return null;
            }

            //root has right child only
            else if(root.left == null){
                return root.right;
            }

            //root has left child only
            else if(root.right == null){
                return root.left;
            }

            //root has two children
            else {

                //finds left's right most child
                IndexNode current  = root.left;
                while(current.right != null){
                    current = current.right;
                }

                //swaps rightmost child with root
                IndexNode temp = root;
                root.setOrd(current.getOrd());
                root.setLine(current.getLine());
                root.setOccurance(current.getOccurence());

                //sets current to root that needs to be deleted
                current.setOrd(word);
                //deletes current
                root.left = delete(root.left, word);

                return root;

            }

        }
    }


    // prints all the words in the index in inorder order
    public void printIndex(){
        printIndex(root);
        System.out.println("---------------------END");

    }

    public void printIndex(IndexNode root) {
        if( root != null) {
            printIndex(root.left);
            if(root.getOrd().compareTo("") != 0) {
                System.out.println(root.toString());
            }
            printIndex(root.right);
        }
    }


}
