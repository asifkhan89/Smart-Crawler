package FileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopwordRemove {

    List<String> stopwords = new ArrayList<>();
    List<String> swordsSent = new ArrayList<>();
    List<String> allUniqueTerms = new ArrayList<>();

    public void S_Words() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("files" + File.separator + "Stopwords1.txt"));
            for (String line; (line = br.readLine()) != null;) {
                stopwords.add(line.trim());
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public List<String> removeStopWords(List<String> stemSent) {
        stopwords = new ArrayList<>();
        swordsSent = new ArrayList<>();
        allUniqueTerms = new ArrayList<>();
        int i = 0;
        S_Words();
        for (i = 0; i < stemSent.size(); i++) {
            String[] mailTokens = stemSent.get(i).split("\\s");
            String swordmail = "";
            for (int j = 0; j < mailTokens.length; j++) {
                int flag = 1;
                for (int k = 0; k < stopwords.size(); k++) {
                    if (mailTokens[j].toLowerCase().equalsIgnoreCase(stopwords.get(k))) { //Remove stopwords
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    //System.out.println("..."+tokens[i]);
                    if (allUniqueTerms.contains(mailTokens[j])) //remove repeated words
                    {
                    } else {
                        if (mailTokens[j].length() > 2) {
                            allUniqueTerms.add(mailTokens[j]);
                        }
                    }
                    if (mailTokens[j].length() > 2) {
                        swordmail = swordmail + mailTokens[j] + " ";
                    }
                }
            }
            //System.out.println("swordMail sent :" + swordmail);
            swordsSent.add(swordmail);
            // break;
        }
        //calculate unique terms from all mails

//        for (int j = 0; j < allUniqueTerms.size(); j++) {
//            System.out.println(""+j+""+allUniqueTerms.get(j));
//        }
        return swordsSent;
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        StopwordRemove stwd = new StopwordRemove();
//        stwd.S_Words();
//        String str = "This film is a wonderful adventure. "
//                + "It may be of particular taste, "
//                + "but any true movie critic cannot deny "
//                + "the characters believability. "
//                + "A relieving and exciting change from the over-produced, "
//                + "generic films riddled with cheesy acting, "
//                + "Four Rooms is a unique film full of laughs, "
//                + "that holds the most entertaining, honest acting "
//                + "I have not seen in comedy to this day. |4.0|";
//        String arr[] = str.split("\\s");
//        for (String arr1 : arr) {
//            list.add(arr1);
//        }
//        stwd.removeStopWords(list);
//    }
}
