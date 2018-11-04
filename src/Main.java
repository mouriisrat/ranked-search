import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner user_input = new Scanner(System.in);
        String first_name, ln;
        TfidfCalculation calculation = new TfidfCalculation();

        ArrayList<HashMap<String, Double>> freq = new ArrayList<>();
        while (true) {
            HashMap<String, Integer> count = new HashMap<>();

            ln = user_input.nextLine();
            if (ln.equals("quit")) {
                break;
            }
            String[] splitStr = ln.split("\\s+");
            for (String s : splitStr) {

                if (count.containsKey(s)) {
                    count.put(s, count.get(s) + 1);
                } else {
                    count.put(s, 1);
                }
                // count.put(s, count.getOrDefault(s, 0) + 1);
            }

            freq.add(calculation.calculateTermFrequency(count));
        }

        for (HashMap<String, Double> tf : freq) {
            tf.forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println();
        }
    }
}
