/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccassign.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author skyle
 */
public class SpiderLeg {

    private List<String> links = new LinkedList<String>();

    private Document htmlDocument; //webpage

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    public boolean crawl(String link) {

        try {

            Connection connection = Jsoup.connect(link).userAgent(USER_AGENT);

            Document htmlDocument = connection.get();

            this.htmlDocument = htmlDocument;

            if (connection.response().statusCode() == 200) // 200 is the HTTP ok status code meaning everything is ok
            {
                System.out.println("**Visiting** Received webpage at " + link);
            }

            if (!connection.response().contentType().contains("text/html")){
                System.out.println("**Failure** Received something other than HTML");
                
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");

            System.out.println("Found (" + linksOnPage.size() + ") links");
            
            for (Element url : linksOnPage) {

                this.links.add(url.absUrl("href"));
            }
            
            return true;
            
        } catch (IOException ie) {
            System.err.println("Error in out HTTP request" + ie);
            
            return false;
        }
    }

    public boolean searchForWord(String searchWord) {
        
        if (this.htmlDocument == null) {
            System.err.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        
        System.out.println("Searching for the word" + searchWord + "...");

        String textBody = this.htmlDocument.body().text();

        return textBody.toLowerCase().contains(searchWord.toLowerCase());
    }
    
    public List<String> getLinks(){
        return this.links;
    }
}
