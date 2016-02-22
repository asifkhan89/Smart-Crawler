package SmartCrawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLUtils {

    private HTMLUtils() {
    }

    public static List<String> extractLinks(String path) throws IOException {
        final ArrayList<String> result = new ArrayList<String>();
        File file = new File(path);
        Document doc = Jsoup.parse(file, "UTF-8");//connect(url).get();

        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        // href ...
        for (Element link : links) {
            if (link.attr("abs:href").contains("http://")) {
            } else {
                result.add(link.attr("href"));
            }
        }

//        // img ...
//        for (Element src : media) {
//            result.add(src.attr("abs:src"));
//        }
//
//        // js, css, ...
//        for (Element link : imports) {
//            result.add(link.attr("abs:href"));
//        }
        return result;
    }

    public static String extractText(String path) throws IOException {
        File file = new File(path);
        Document doc = Jsoup.parse(file, "UTF-8");//connect(url).get();
        String text = doc.body().text();
        return text;
    }

    public static String extractWebText(String path) {
        String text = new String();
        try {
            Document doc = Jsoup.connect(path).get();
//            Elements el = doc.select("div#mp-tfa");
//            for (Element e : el) {
//                System.out.println(e.text());
                text = doc.text();
//            }
        } catch (IOException ex) {
        }
        return text;
    }

//  public final static void main(String[] args) throws Exception{
//    //String site = "http://www.rgagnon.com/topics/java-language.html";
//    List<String> links = HTMLUtils.extractLinks("");
//    for (String link : links) {
//      System.out.println(link);
//    }
//  }
}
