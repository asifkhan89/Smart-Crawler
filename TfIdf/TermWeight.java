package TfIdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TermWeight {

    TermFrequency tf = new TermFrequency();
    InverseDocFrequency idf = new InverseDocFrequency();
    Normalization norm = new Normalization();

    public Map<String, ArrayList<Double>> calculateWeight(String str, List<String> list) {

//        norm.normalize();

        Map<String, HashMap<String, Double>> mapIDF = idf.idf(str,list);
        Map<String, HashMap<String, Double>> mapTF = idf.getMap();
        Map<String, ArrayList<Double>> finalMap = new HashMap<>();

//        HashMap<String, Double> submapTF = mapTF.keySet();
        for (String key : mapTF.keySet()) {
            Double val = 0.0;
            Set<String> temp = mapTF.get(key).keySet();
            ArrayList<Double> tempMap = new ArrayList<>();
            for (String temp1 : temp) {
                Double val1 = mapTF.get(key).get(temp1);
                Double val2 = mapIDF.get(key).get(temp1);
                val = val1 * Math.pow(val2, 2);
                tempMap.add(val);
            }
            finalMap.put(key, tempMap);
        }

        for (Map.Entry<String, ArrayList<Double>> entrySet : finalMap.entrySet()) {
            String key = entrySet.getKey();
            ArrayList<Double> value = entrySet.getValue();
            System.out.println(key);
            System.out.println(value);
        }
        return finalMap;
    }
}
