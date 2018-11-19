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

    BinarySearchTree.Node findEvenMaxId() {
        return findEvenMaxId(root);
    }

    BinarySearchTree.Node findEvenMaxId(BinarySearchTree.Node root) {

        if(root.left==null && root.right==null)
            return root;

            BinarySearchTree.Node left = findEvenMaxId(root.left);
            BinarySearchTree.Node right = findEvenMaxId(root.right);


            if(right.key%2==0 && left.key%2==0){
                if(left.key>right.key)
                    return left;
                else
                    return right;
            }

            else if(right.key%2==0){
                return right;
            }
            else if (left.key%2==0){
                return left;
            }
            else return new BinarySearchTree.Node(-10000000);


    }


    int sumOfNodes() {
       return sumOfNodes(root);
    }

    int sumOfNodes(BinarySearchTree.Node root) {
        if (root != null) {
            return sumOfNodes(root.left) + root.key + sumOfNodes(root.right);
        }
        else return 0;
    }
    BinarySearchTree.Node findKNumber(int k) {

        return findKNumber(root,k);
    }

    BinarySearchTree.Node findKNumber(BinarySearchTree.Node root,int k ) {

        if(root!=null) {
            BinarySearchTree.Node left = findKNumber(root.left, k);
            BinarySearchTree.Node right = findKNumber(root.right, k);
            int a = Math.abs(left.key - k);
            int b = Math.abs(root.key - k);
            int c = Math.abs(right.key - k);

            int smallest = a;
            if (smallest > b) {
                smallest = b;
            }
            if (smallest > c) {
                smallest = c;
            }

            if(a == smallest) return left;
            if(b == smallest) return root;
            if(c == smallest) return right;
        }
        return new BinarySearchTree.Node(100000000);
    }



    public static void main(String[] args) {
        BinaryIndexTree bit = new BinaryIndexTree(Arrays.asList(30, 45, 10, 62));
        BinarySearchTree.Node r = bit.findMaxId();
        System.out.printf("Maximum node's key is %d and id %d.\n", r.key, r.id);

        BinarySearchTree.Node maxEvenNode = bit.findEvenMaxId();
        System.out.printf("Maximum even node's key is %d and id %d.\n", maxEvenNode.key, maxEvenNode.id);

        System.out.println("the sum of all nodes "+bit.sumOfNodes());
        Scanner user_input = new Scanner(System.in);
        System.out.println("input a number to find its nearest number " );
        int a=user_input.nextInt();
        System.out.println("the nearest number is : "+ bit.findKNumber(a).key);

    }
}
