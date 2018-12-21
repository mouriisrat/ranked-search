import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner user_input = new Scanner(System.in);
        Scanner file_input = new Scanner(new File("input.txt"));

        String first_name, ln, word;
        int i;
        TfidfCalculation calculation = new TfidfCalculation();
//      ArrayList<HashMap<String, Double>> freq = new ArrayList<>();
        ArrayList<DocumentProperties> documentProperties = new ArrayList<>();
        while (true) {

            ln = file_input.nextLine();
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

            //printing tf map for each line
            System.out.println("TF map of the entered line ");
            docProperties.setTermFreqMap(calculation.calculateTermFrequency(count));
            docProperties.getTermFreqMap().forEach((key, value) -> System.out.println(key + ": " + value));

            documentProperties.add(docProperties);

        }


        //passing the tf maps of documents to a function to create leaf nodes and tree
        ArrayList<HashMap<String, Double>> listOfTf = new ArrayList<>();
        for(int j=0;j<documentProperties.size();j++){
            listOfTf.add(documentProperties.get(j).getTermFreqMap());
        }
        System.out.println("Binary index tree of the entered documents");
        SearchTreeWithTFIDF indexTree=new SearchTreeWithTFIDF(listOfTf);

        //creating and printing idf map
        System.out.println("IDF map of the documents");
        HashMap<String,Double> inverseDocFreqMap = calculation.calculateInverseDocFrequency(documentProperties.toArray(new DocumentProperties[0]));
        inverseDocFreqMap.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(documentProperties.size());

        //multi keyword search that returns a single document
        String searchQuery;
        System.out.println("Enter a search query");
        while (user_input.hasNext()){
            searchQuery = user_input.nextLine();
            String[] splitStr = searchQuery.split("\\s+");
            indexTree.findMaxOfSearchQuery(splitStr, inverseDocFreqMap);
            System.out.println("maximum nodes id for this query is " + indexTree.documentId);
            System.out.println("Enter a search query");
        }


    }
}
