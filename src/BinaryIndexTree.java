import java.util.*;

public class BinaryIndexTree {
    BinarySearchTree.Node root;


    BinaryIndexTree(List<Integer> data) {
        ArrayList<BinarySearchTree.Node> listOfNode= new ArrayList<>();

        for(int i:data){
           listOfNode.add(new BinarySearchTree.Node(i));
           System.out.print(listOfNode.get(listOfNode.size()-1).key + " ");
        }

        System.out.println();


        int key;
        while(listOfNode.size()>1){
            ArrayList<BinarySearchTree.Node> temp= new ArrayList<>();

            for(int i=0;i<listOfNode.size();i=i+2) {
                if (i + 1 == listOfNode.size()) {
                    key = listOfNode.get(i).key;
                    BinarySearchTree.Node internalNode = new BinarySearchTree.Node(key);
                    internalNode.left = listOfNode.get(i);
                    temp.add(internalNode);
                } else {
                    key = Math.max(listOfNode.get(i).key, listOfNode.get(i + 1).key);
                    BinarySearchTree.Node internalNode = new BinarySearchTree.Node(key);
                    internalNode.left = listOfNode.get(i);
                    internalNode.right = listOfNode.get(i + 1);
                    temp.add(internalNode);
                }
            }

            for(int j=0;j<temp.size();j++){
                System.out.print(temp.get(j).key+" ");
            }

            System.out.println();

            listOfNode=temp;

        }

        root = listOfNode.get(0);
    }


    public static void main(String[] args) {
        BinaryIndexTree bit = new BinaryIndexTree(Arrays.asList(30, 20, 10, 25, 40, 60 , 30));
    }
}
