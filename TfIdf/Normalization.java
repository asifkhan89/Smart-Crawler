package TfIdf;

import FileOperations.Stemming;
import FileOperations.StopwordRemove;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Normalization {

    StopwordRemove swr = new StopwordRemove();
    Stemming stem = new Stemming();
    Map<String, List<String>> docStem = new HashMap<>();

    public void normalize(String str, List<String> list) {
        Integer cnt = 1;
        for (String text : list) {
            ArrayList<String> words = new ArrayList<>();
            text = text.replaceAll(File.separator, "\\s");
            String[] array = text.split("\\s");
            for (String array1 : array) {
                words.add(array1);
            }
            List<String> stopwordRemoved = swr.removeStopWords(words);
            List<String> stemword = new ArrayList<>();
            for (String stopwordRemoved1 : stopwordRemoved) {
//                    System.out.print(stopwordRemoved1 + " ");
                String word = new String();
                if (!stopwordRemoved1.equals("")) {
                    word = stem.stripAffixes(stopwordRemoved1);
                }
                if (!word.equals("")) {
                    stemword.add(word);
                }
            }
            docStem.put(cnt.toString(), stemword);
            cnt++;
        }
    }
}
