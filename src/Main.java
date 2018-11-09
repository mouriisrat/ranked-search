import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner user_input = new Scanner(System.in);
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

        /*i = user_input.nextInt();
        word = user_input.next();
        double tfIdf=documentProperties.get(i).getTermFreqMap().get(word)*inverseDocFreqMap.get(word);
        System.out.println(tfIdf);
*/
        double maximum=0.0;
        int documentNo=0;
        while (true) {
            word = user_input.next();
            if(word.equals("quit"))
                break;
            for (int j = 0; j < documentProperties.size(); j++) {
                double tfIdfOfWord = documentProperties.get(j).getTermFreqMap().getOrDefault(word, 0.0) * inverseDocFreqMap.getOrDefault(word, 0.0);
                System.out.println(tfIdfOfWord);
                if (tfIdfOfWord > maximum) {
                    maximum = tfIdfOfWord;
                    documentNo = j + 1;
                }


            }

            System.out.println(documentNo);
        }
    }
}
