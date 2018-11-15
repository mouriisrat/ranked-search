import java.util.*;

public class BinaryIndexTree {
    BinarySearchTree.Node root;


    BinaryIndexTree(List<Integer> data) {
        ArrayList<BinarySearchTree.Node> listOfNode= new ArrayList<>();
        int j = 1;
        for(int i:data){
            BinarySearchTree.Node node = new BinarySearchTree.Node(i);
            node.id = j;
            j++;
            listOfNode.add(node);
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

            for(int k=0;k<temp.size();k++){
                System.out.print(temp.get(k).key+" ");
            }

            System.out.println();

            listOfNode=temp;

        }

        root = listOfNode.get(0);
    }

    BinarySearchTree.Node findMaxId() {
        return findMaxId(root);
    }

    BinarySearchTree.Node findMaxId(BinarySearchTree.Node root) {

        if(root.left==null && root.right==null)
            return root;
        else if(root.right==null || root.left.key>root.right.key)
            root = root.left;
        else
            root= root.right;

        return findMaxId(root);
    }


    public static void main(String[] args) {
        BinaryIndexTree bit = new BinaryIndexTree(Arrays.asList(10, 20, 10, 25, 20, 5 , 30));
        BinarySearchTree.Node r = bit.findMaxId();
        System.out.printf("Maximum node's key is %d and id %d.", r.key, r.id);
    }
}
