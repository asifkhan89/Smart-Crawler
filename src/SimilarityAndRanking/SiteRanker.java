/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimilarityAndRanking;

import Computations.Cosine;
import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SiteRanker {

    public TreeMap<String, Double> siteSimilarity(List<String> seedSites, String query) {
        Map<String, Double> rankedSite = new HashMap<>();
//        Cosine_Similarity cs = new Cosine_Similarity();
        System.out.println(">>" + query);
        for (String seedSite : seedSites) {
            String str = seedSite.replaceAll(File.separator, " ");
//            System.out.println(seedSite);
//            double sim_score = cs.Cosine_Similarity_Score(query, seedSite);
//            System.out.println("Cosine Similarity: " + sim_score);
            Computations.Cosine cos = new Cosine();
            double sim = cos.similarity(str, query);
            rankedSite.put(seedSite, sim);
        }
        ValueComparator vc = new ValueComparator(rankedSite);
        TreeMap<String, Double> sortedMap = new TreeMap<>(vc);
        sortedMap.putAll(rankedSite);
        return sortedMap;
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Double> map;

    public ValueComparator(Map<String, Double> base) {
        this.map = base;
    }

    public int compare(String a, String b) {
        if (map.get(a) >= map.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys 
    }
}
