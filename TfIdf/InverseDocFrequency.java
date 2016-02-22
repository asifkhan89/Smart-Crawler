package TfIdf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InverseDocFrequency {

    TfIdf.Normalization norm = new Normalization();
    TermFrequency tf = new TermFrequency();
    ConcurrentHashMap<String, HashMap<String, Double>> map;

    public ConcurrentHashMap<String, HashMap<String, Double>> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<String, HashMap<String, Double>> map) {
        this.map = map;
    }

    public Map<String, HashMap<String, Double>> idf(String str, List<String> list) {
        norm.normalize(str, list);

        Set<String> allWords = new HashSet<>();
        for (Map.Entry<String, List<String>> entrySet : norm.docStem.entrySet()) {
            String key = entrySet.getKey();
            List<String> value = entrySet.getValue();
            System.out.println(key + "--> ");
            System.out.println(value);
            allWords.addAll(value);
        }
        map = tf.tf(allWords, norm.docStem);
        Integer totalDoc = norm.docStem.size();
        Map<String, HashMap<String, Double>> mapIdf = new HashMap<>();
        for (Map.Entry<String, HashMap<String, Double>> entrySet : map.entrySet()) {
            String key = entrySet.getKey();
            HashMap<String, Double> value = entrySet.getValue();
//            System.out.println(key);
//            System.out.println(value);
            Set<String> words = value.keySet();
            HashMap<String, Double> wordIdf = new HashMap<>();
            for (String word : words) {
                double cnt = 0;
                for (Map.Entry<String, List<String>> entrySet1 : norm.docStem.entrySet()) {
                    String key1 = entrySet1.getKey();
                    List<String> value1 = entrySet1.getValue();
                    for (String value11 : value1) {
                        if (value11.equals(word)) {
                            cnt++;
                            break;
                        }
                    }
                }
//                Double val = (Math.log(totalDoc / (cnt))) / Math.log(2);
                Double val = totalDoc / (cnt);
                val = (Math.log(val)) / Math.log(2);
//                System.out.println(val);
                wordIdf.put(word, val);
            }
            mapIdf.put(key, wordIdf);
        }
        System.out.println();
        System.out.println("------------------<< IDF >>----------------\n");
        for (Map.Entry<String, HashMap<String, Double>> entrySet : mapIdf.entrySet()) {
            String key = entrySet.getKey();
            HashMap<String, Double> value = entrySet.getValue();
            System.out.println(key);
            System.out.println(value);
        }
        return mapIdf;
    }
}
