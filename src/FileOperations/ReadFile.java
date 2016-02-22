package FileOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    List<String> links = new ArrayList<>();

    public List<String> getLinks() {
        return links;
    }
    
    public StringBuilder read(String fileName, List<String> list) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = new String();
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase("@data")) {
                    break;
                }
            }
            while ((line = br.readLine()) != null) {
                if (!line.contains("not_relevant")) {
                    String[] arr = line.split(",");
                    sb.append( list.get(Integer.parseInt(arr[0]))).append("\t"+arr[3]).append("\n");
                    links.add(list.get(Integer.parseInt(arr[0])));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb;
    }
}
