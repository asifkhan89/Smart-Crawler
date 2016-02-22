package FileOperations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class Arffgen {

    public static ArrayList<String> arr = new ArrayList<String>();

    public void IGArffFileGenerater(Vector attr, Map<Integer, Map<Integer, Double>> mapToFile, String filename, double thold) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.write("@RELATION" + " " + "training" + "\n");
            for (int i = 0; i < attr.size(); i++) {
                String atr = attr.get(i).toString();
                atr = atr.replace(", ", "_");
                bw.write("@ATTRIBUTE" + " '" + atr + "' " + "numeric" + "\n");
            }
            bw.write("@ATTRIBUTE" + " '" + "class" + "' " + "{'relevant','not_relevant'}");
            bw.write("\n");
            bw.write("@data" + "\n");

            for (Map.Entry<Integer, Map<Integer, Double>> entrySet : mapToFile.entrySet()) {
                Integer key = entrySet.getKey();
                Map<Integer, Double> value = entrySet.getValue();
                for (Map.Entry<Integer, Double> entrySet1 : value.entrySet()) {
                    Integer key1 = entrySet1.getKey();
                    Double value1 = entrySet1.getValue();
                    String clas = new String();
                    if (value1 < thold) {
                        clas = "not_relevant";
                    } else if (value1 > thold) {
                        clas = "relevant";
                    }
                    bw.write(key + "," + key1 + "," + value1 + "," + clas + "\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
