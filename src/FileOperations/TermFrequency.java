package FileOperations;

import java.util.HashMap;
import java.util.List;

public class TermFrequency {

    public HashMap<String, Double> tf(List<String> stemwd) {
        HashMap<String, Double> wordCount = new HashMap<>();

        for (String str : stemwd) {
            double count = 0;
            for (String wordArray1 : stemwd) {
                String currentWord = wordArray1.trim();
                if (str.equals("")) {
                    break;
                }
                if (currentWord.equalsIgnoreCase(str.trim())) {
                    count++;
                }
            }
            
            if (!str.equals("")) {
                count = 1 + Math.log(count);
                count = Math.round(count * 100.0) / 100.0;
                wordCount.put(str, count);
            }
        }

        return wordCount;
    }
}
