import java.util.*;
import java.util.stream.IntStream;

public class SearchTreeWithTFIDF {
    static class Node {
        HashMap<String, Double> key;
        int id;
        Node left, right;

        public Node(HashMap<String, Double> item) {
            key = item;
            left = right = null;
        }

        @Override
        public String toString() {
            return "{ key = " + key + " id = " + id + "}";
        }
    }

    Node root;

    SearchTreeWithTFIDF(List<HashMap<String, Double>> listOfTf) {
        ArrayList<Node> listOfNode = new ArrayList<>();
        int j = 1;
        for (HashMap<String, Double> i : listOfTf) {
            Node node = new Node(i);
            node.id = j;
            j++;
            listOfNode.add(node);
            System.out.print(listOfNode.get(listOfNode.size()-1).key + " ");
        }

        System.out.println();
        
        //tree creation
        HashMap<String, Double> key;
        while(listOfNode.size()>1){
            ArrayList<Node> temp= new ArrayList<>();

            for(int i=0;i<listOfNode.size();i=i+2) {
                if (i + 1 == listOfNode.size()) {
                    key = listOfNode.get(i).key;
                    Node internalNode = new Node(key);
                    internalNode.left = listOfNode.get(i);
                    temp.add(internalNode);
                } else {
                    key = max(listOfNode.get(i).key, listOfNode.get(i + 1).key);
                    Node internalNode = new Node(key);
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

    private HashMap<String, Double> max(HashMap<String, Double> left, HashMap<String, Double> right) {
        HashMap<String, Double> ret = (HashMap<String, Double>) left.clone();

        right.forEach((key, value) -> {
            if(ret.containsKey(key)){
                ret.put(key, Math.max(ret.get(key),value));
            }
            else
                ret.put(key,value);
        });
        return ret;
    }

    void findMaxOfSearchQuery(String[] splitStr, HashMap<String, Double> inverseDocFreqMap ) {
        maximum=0;
        tfIdfOfWord=0;
        documentId=0;
        findMaxOfSearchQuery(root, splitStr, inverseDocFreqMap);
    }

    double maximum=0, tfIdfOfWord=0;
    int documentId=0;

    void findMaxOfSearchQuery(Node root, String[] splitStr, HashMap<String, Double> inverseDocFreqMap) {


        if(root.left==null && root.right==null){
            double sum = 0;
            for (String s : splitStr) {
               tfIdfOfWord = root.key.getOrDefault(s, 0.0) * inverseDocFreqMap.getOrDefault(s, 0.0);
               sum = sum + tfIdfOfWord;
            }
            if (sum > maximum) {
               maximum = sum;
               documentId = root.id;
            }

            return;
        }

        else

        {
            if(root.left!=null)
                findMaxOfSearchQuery(root.left, splitStr, inverseDocFreqMap);
            if((root.right!=null))
                findMaxOfSearchQuery(root.right, splitStr, inverseDocFreqMap);
        }
    }

}