package SmartCrawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeedSites {

    public SeedSites() {
    }

    public List<String> getSeedSites(String searchString, Integer threshold, List<String> file) {
        List<String> list = file;
        List<String> returnList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Map<String, List<String>> reverseSearchLinks = new HashMap<>();
        for (String file1 : list) {
            try {
                int i = 0;
                String[] str = file1.split("\\.");
                String str1 = "dataset" + File.separator;
                String str2 = str[0];
                String str3 = file1;
                List<String> links = new ArrayList<>();
                if (str[1].equalsIgnoreCase("htm") || str[1].equalsIgnoreCase("html")) {
                    if (searchString.equalsIgnoreCase(str2)) {
                        links = HTMLUtils.extractLinks(str1 + str2 + File.separator + str3);
                        for (String link : links) {
                            i++;
//                            System.out.println(link);
                            returnList.add(link);
                            sb.append("\t").append(link).append("\n");
                            if (threshold.equals(i)) {
                                break;
                            }
                        }
                        reverseSearchLinks.put(file1, links);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

//        for (Map.Entry<String, List<String>> entrySet : reverseSearchLinks.entrySet()) {
//            String key = entrySet.getKey();
//            List<String> value = entrySet.getValue();
//            System.out.println(key + "--->>");
//            for (String value1 : value) {
//                System.out.println(value1);
//            }
//        }
        return returnList;
    }
}
