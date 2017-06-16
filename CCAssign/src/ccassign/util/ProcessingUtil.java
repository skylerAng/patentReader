/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccassign.util;
import java.util.ArrayList;
import java.util.*;
public class ProcessingUtil {

    public static String[] removeStopWords(String lowFilter) {
        //set stop words
        String[] stopWords = new String[]{"i", "a", "and", "about", "an", "are", "as", "at", "be", "by", "com", "for", "from", "how", "in", "is", "it", "not", "of", "on", "or", "that", "the", "this", "to", "was", "what", "when", "where", "who", "will", "with", "the", "www"};

        ArrayList<String> wordsList = new ArrayList<String>();

        String[] split = lowFilter.split("\\s+");

        for (String word : split) {
            wordsList.add(word);
        }

        //remove stop words here from the temp list
        for (int i = 0; i < wordsList.size(); i++) {
            // get the item as string
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].contains(wordsList.get(i))) {
                    wordsList.remove(i);
                }
            }
        }

        String[] convertedArray = wordsList.toArray(new String[wordsList.size()]);

        // for (int x = 0; x < convertedArray.length; x++) {
        //     System.out.println(convertedArray[x]);
        // }

        return convertedArray;

    }	

    public static void printMap(HashMap <?,?> myMap) {
         for (Map.Entry ent : myMap.entrySet()){

            String key = ent.getKey().toString();
            String value = ent.getValue().toString();  
            System.out.println(key + " " + value);  


        } 
    }
    public static void printTable(List<String> topHeaders, List<String> sideHeaders, List<List<String>> datas) {
        System.out.print("\t\t");
        for(String header: topHeaders) {
            System.out.print(header.substring(0, header.length()>8? 7:header.length() ) + "\t");
        }
        //new line
        System.out.println();
        int count = 0;
        for (List<String> list : datas){
            System.out.print(sideHeaders.get(count).substring( sideHeaders.get(count).length()>8? 7:sideHeaders.get(count).length()) + "\t");
            int hcount = 0;
            for (String item: list){
                System.out.print(item + "\t");
                // if(topHeaders.get(hcount).length() >= 8)
                //      System.out.print("\t");
                hcount++;
            }
            System.out.println();
            count++;
        }
    }


}