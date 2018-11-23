import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    int count=0;
    BinarySearchTree.Node findEvenMaxId() {
        return findEvenMaxId(root);
    }

    BinarySearchTree.Node findEvenMaxId(BinarySearchTree.Node root) {

        count++;
        if(root.left==null && root.right==null)
            return root;
        else if(root.right==null)
            return findEvenMaxId(root.left);

        BinarySearchTree.Node left = findEvenMaxId(root.left);
        if(left.key>=root.right.key)
            return left;
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


    void findTwoLargestNumber()
    {
        findTwoLargestNumber(root);
    }

    ArrayList<BinarySearchTree.Node> K= new ArrayList<>();
    int countKLargestNumber=0;

    void findTwoLargestNumber(BinarySearchTree.Node root){

        countKLargestNumber++;

        if(root.left==null && root.right==null){
            if(K.size()<=1)
                K.add(root);
            else if(root.key>K.get(0).key && root.key<K.get(1).key)
                K.set(0,root);
            else if(root.key<K.get(0).key && root.key>K.get(1).key)
                K.set(1,root);
            else if(root.key>K.get(0).key && root.key>K.get(1).key){
                if(K.get(0).key>K.get(1).key)
                    K.set(1,root);
                else
                    K.set(0,root);
            }
            return;
        }

        else
        {
            if(root.left!=null) findTwoLargestNumber(root.left);
            if((root.right!=null) && (K.size()<2 || root.key>Math.min(K.get(0).key, K.get(1).key))) findTwoLargestNumber(root.right);
        }
    }

    PriorityQueue<BinarySearchTree.Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.key));

    void findKLargestNumber(int k)
    {
        findKLargestNumber(root, k);
    }
    void findKLargestNumber(BinarySearchTree.Node root, int k){


        if(root.left==null && root.right==null){
            if(q.size()<=k-1)
                q.add(root);
            else if(root.key>q.peek().key){
                q.poll();
                q.add(root);
            }
            return;
        }

        else
        {
            if(root.left!=null)
                findKLargestNumber(root.left, k);
            if((root.right!=null) && (q.size()<k || root.key>q.peek().key))
                findKLargestNumber(root.right, k);
        }


    }



    public static void main(String[] args) {
        BinaryIndexTree bit = new BinaryIndexTree(Arrays.asList(50, 29, 20, 10, 60, 90, 55, 52, 45, 70, 33, 45, 567, 1, 34, 56, 78));
        Scanner user_input = new Scanner(System.in);
        /*BinarySearchTree.Node r = bit.findMaxId();
        System.out.printf("Maximum node's key is %d and id %d.\n", r.key, r.id);

        BinarySearchTree.Node maxEvenNode = bit.findEvenMaxId();
        System.out.printf("Maximum even node's key is %d and id %d.\n", maxEvenNode.key, maxEvenNode.id);
        System.out.println("the number of function called "+bit.count);

        System.out.println("the sum of all nodes "+bit.sumOfNodes());
        Scanner user_input = new Scanner(System.in);
        System.out.println("input a number to find its nearest number " );
        int a=user_input.nextInt();
        System.out.println("the nearest number is : "+ bit.findKNumber(a).key);
        bit.findTwoLargestNumber();
        for (BinarySearchTree.Node e:bit.K){
            System.out.println("the 2 largest numbers are "+ e.key);
        }
        System.out.println("the number of findTwoLargestNumber called " + bit.countKLargestNumber);
        System.out.println("input k to find k largest numbers " );*/
//        int k=user_input.nextInt();
       for(int k=1;k<17;k++) {
           bit.findKLargestNumber(k);
           System.out.println("the " +k+ " largest numbers are " + Arrays.toString(IntStream.range(0, bit.q.size()).mapToObj(i -> bit.q.poll()).toArray()));
       }
    }
}
