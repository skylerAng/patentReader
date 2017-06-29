/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccassign.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
    
    private static final int MAX_PAGES_TO_SEARCH = 20;
    
    private Set<String> pagesVisited = new HashSet<String>();
    
    private List<String> pagesToVisit = new LinkedList<String>();
    
    //methods
    
    private String nextLink(){
        
        String nextLink;
        
        do{
            nextLink = this.pagesToVisit.remove(0);
            
        }while (this.pagesVisited.contains(nextLink));
        
        this.pagesVisited.add(nextLink);
        
        return nextLink;
        
    }
    
    public void search(String link, String searchWord){
        
        {
            while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
            {
                String currentLink;
                SpiderLeg leg = new SpiderLeg();
                
                if(this.pagesToVisit.isEmpty()){
                    currentLink = link;
                    this.pagesVisited.add(link);
                }
                else
                {
                    currentLink = this.nextLink();
                }
                
                leg.crawl(currentLink);
                
                boolean success = leg.searchForWord(searchWord);
                
                if(success){
                    System.out.println(String.format("**Success** Word %s found at %s ", searchWord, currentLink));
                    
                    break; 
                }
                
                this.pagesToVisit.addAll(leg.getLinks());
            }
            System.out.println(String.format("**Done** Visited %s websites", this.pagesVisited.size()));
        
        }
    }
    
}
