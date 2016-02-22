package TfIdf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TermFrequency {

    ConcurrentHashMap<String, HashMap<String, Double>> mapTf = new ConcurrentHashMap<>();
    Map<String, Integer> wordsInDoc = new HashMap<>();
    
    public ConcurrentHashMap<String, HashMap<String, Double>> tf(Set<String> allword, Map<String, List<String>> docword) {
        HashMap<String, Double> temp = new HashMap<>();

//            System.out.println(str);
        for (Map.Entry<String, List<String>> entrySet : docword.entrySet()) {
            for (String str : allword) {
                double count = 0;
                String file = entrySet.getKey();
                List<String> dw = entrySet.getValue();
                for (String dw1 : dw) {
                    String currentWord = dw1.trim();
//                    if (str.equals("")) {
//                        break;
//                    }
                    if (currentWord.equalsIgnoreCase(str.trim())) {
                        count++;
                    }
                }
//                count = count / dw.size();
//                count = Math.round(count * 1000.0) / 1000.0;
                temp.put(str, count);
                mapTf.put(file, temp);
            }
            temp = new HashMap<>();
        }

        System.out.println();
        System.out.println("---------------------<< Term count >>------------------\n");
        for (Map.Entry<String, HashMap<String, Double>> entrySet : mapTf.entrySet()) {
            String key = entrySet.getKey();
            HashMap<String, Double> value = entrySet.getValue();
            System.out.println(key);
            System.out.println(value);
        }

        System.out.println();

        for (Map.Entry<String, HashMap<String, Double>> entrySet : mapTf.entrySet()) {
            String key = entrySet.getKey();
            HashMap<String, Double> value = entrySet.getValue();
            int cnt = 0;
            for (Map.Entry<String, Double> entrySet1 : value.entrySet()) {
                String key1 = entrySet1.getKey();
                Double value1 = entrySet1.getValue();
                if (value1 > 0) {
                    cnt++;
                }
            }
            wordsInDoc.put(key, cnt);

            Double maxCount = 0.0;
            for (Map.Entry<String, Double> entrySet1 : value.entrySet()) {
                Double value1 = entrySet1.getValue();
                if (value1 > maxCount) {
                    maxCount = value1;
                }
            }

            for (Map.Entry<String, Double> entrySet1 : value.entrySet()) {
                String key1 = entrySet1.getKey();
                Double value1 = entrySet1.getValue();
                int docWd = wordsInDoc.get(key);
                value1 = value1 / docWd;
//                value1 /= maxCount;//mapTf.size();
//                value1 = 0.5 + ((value1 * 0.5) / maxCount);
//                value1 = (value1) / maxCount;
//                value1 = Math.round(value1 * 100.0) / 1000.0;
                mapTf.get(key).put(key1, value1);
            }
        }

        System.out.println("--------------<< TF >>---------------\n");
        for (Map.Entry<String, HashMap<String, Double>> entrySet : mapTf.entrySet()) {
            String key = entrySet.getKey();
            HashMap<String, Double> value = entrySet.getValue();
            System.out.println(key);
            System.out.println(value);
        }
        return mapTf;
    }

//    public static void main(String[] args) {
//        TermFrequency tf = new TermFrequency();
//        tf.
//    }
}
