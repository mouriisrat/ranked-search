import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner user_input = new Scanner(System.in);
        PriorityQueue<BinarySearchTree.Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.key));
        q.add(new BinarySearchTree.Node(45));
        q.add(new BinarySearchTree.Node(20));
        q.add(new BinarySearchTree.Node(55));
        q.add(new BinarySearchTree.Node(50));
        System.out.println(q);
        System.out.println(q.peek() + " " + q.size());
        System.out.println(q.poll() + " " + q.size());
        System.out.println(q.peek() + " " + q.size());


        String first_name, ln, word;
        int i;
        TfidfCalculation calculation = new TfidfCalculation();

//        ArrayList<HashMap<String, Double>> freq = new ArrayList<>();
        ArrayList<DocumentProperties> documentProperties = new ArrayList<>();
        while (true) {

            ln = user_input.nextLine();
            if (ln.equals("quit")) {
                break;
            }

            HashMap<String, Integer> count = new HashMap<>();
            DocumentProperties docProperties = new DocumentProperties();

            String[] splitStr = ln.split("\\s+");
            for (String s : splitStr) {

                if (count.containsKey(s)) {
                    count.put(s, count.get(s) + 1);
                } else {
                    count.put(s, 1);
                }

            }

            docProperties.setWordCountMap(count);

            docProperties.setTermFreqMap(calculation.calculateTermFrequency(count));
            docProperties.getTermFreqMap().forEach((key, value) -> System.out.println(key + ": " + value));

            documentProperties.add(docProperties);

        }


        HashMap<String,Double> inverseDocFreqMap = calculation.calculateInverseDocFrequency(documentProperties.toArray(new DocumentProperties[0]));

        inverseDocFreqMap.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(documentProperties.size());

        //finding the importance of a word in a specific document

        /*i = user_input.nextInt();
        word = user_input.next();
        double tfIdf=documentProperties.get(i).getTermFreqMap().get(word)*inverseDocFreqMap.get(word);
        System.out.println(tfIdf);
        */

        //Single word search
        while (true) {
            double maximumForSingle=0.0;
            int documentNoForSingle=0;
            word = user_input.next();
            if(word.equals("quit"))
                break;
            for (int j = 0; j < documentProperties.size(); j++) {
                double tfIdfOfWord = documentProperties.get(j).getTermFreqMap().getOrDefault(word, 0.0) * inverseDocFreqMap.getOrDefault(word, 0.0);
                System.out.println(tfIdfOfWord);
                if (tfIdfOfWord > maximumForSingle) {
                    maximumForSingle = tfIdfOfWord;
                    documentNoForSingle = j + 1;
                }


            }

            System.out.println("document returned for single keyword is " + documentNoForSingle);
       }


       //multi keyword search
        String searchQuery;
        searchQuery = user_input.nextLine();
        String[] splitStr = searchQuery.split("\\s+");
        double maximum=0.0, tfIdfOfWord, sum=0.0;
        int documentNo=0;

/*      double idfOfsearchQuery,
        HashMap<String, Double> idfMapOfsearchQuery  = new HashMap<>();
        for (String s : splitStr) {

         idfOfsearchQuery= inverseDocFreqMap.getOrDefault(s,0.0);
         idfMapOfsearchQuery.put(s, idfOfsearchQuery);

        }
        idfMapOfsearchQuery.forEach((key, value) -> System.out.println(key + ": " + value));
*/

        for (int j = 0; j < documentProperties.size(); j++) {
            sum=0.0;
            for (String s : splitStr) {
                tfIdfOfWord = documentProperties.get(j).getTermFreqMap().getOrDefault(s, 0.0) * inverseDocFreqMap.getOrDefault(s, 0.0);
                sum=sum+ tfIdfOfWord;
            }
            if (sum > maximum) {
                maximum = sum;
                documentNo = j + 1;
            }
            System.out.println("tfIdf for document " + documentNo + " is " + sum);

        }
        System.out.println("document returned for this query is" +documentNo);




    }
}
