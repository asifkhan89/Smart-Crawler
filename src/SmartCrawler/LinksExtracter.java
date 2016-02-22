package SmartCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinksExtracter {

    private LinksExtracter() {
    }

    public static List<String> extractLinks(String path) throws IOException {
        final ArrayList<String> result = new ArrayList<String>();
        File file = new File(path);
        Document doc = Jsoup.parse(file, "UTF-8");//connect(url).get();

        Elements links = doc.select("a[href]");
//        Elements links = doc.select("a[href*=https]");
//        Elements links = doc.select("a[href^=www]");
//        Elements links = doc.select("a[href$=.com]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        // href ...
        for (Element link : links) {
            if (link.attr("abs:href").contains("http://")) {
            } else {
                result.add(link.attr("href"));
//                System.out.println(link.attr("href"));
            }
        }

//        // img ...
//        for (Element src : media) {
//            result.add(src.attr("abs:src"));
//            System.out.println(src.attr("abs:src"));
//        }
//
//        // js, css, ...
//        for (Element link : imports) {
//            result.add(link.attr("abs:href"));
//            System.out.println(link.attr("abs:href"));
//        }
        return result;
    }

    public static List<String> getURL(String urlLink) {

        List<String> list = new ArrayList<>();
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + urlLink);
//            Scanner br = new Scanner(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = new String();
//            while (br.hasNextLine()) {
            while ((line = br.readLine()) != null) {
//                line = br.nextLine();
                System.out.println(line);
                if (line.toLowerCase().contains(".html")) {
                    line = line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"'));
                    list.add(line);
//                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

//  public final static void main(String[] args) throws Exception{
//    //String site = "http://www.rgagnon.com/topics/java-language.html";
//    List<String> links = HTMLUtils.extractLinks("");
//    for (String link : links) {
//      System.out.println(link);
//    }
//  }
}
