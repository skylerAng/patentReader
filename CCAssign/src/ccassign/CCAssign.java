/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        
package ccassign;
import java.util.concurrent.ConcurrentHashMap;
import ccassign.util.FileOperation;
import java.io.File;
import java.util.*;
import ccassign.util.*;
import java.text.*;
import javax.swing.JOptionPane;

public class CCAssign {
    //Master HashMap store all the discovery of words from documents
    private static ConcurrentHashMap<String, Integer> masterHashMap = new ConcurrentHashMap<String, Integer>();
 
    // public static ConcurrentHashMap getMasterHasMap() {
    //     return masterHashMap;
    // }
    public static void addWord(String x) {
        x.toLowerCase();

        if (masterHashMap.containsKey(x)) { // Check if the particular word (key) alrd exists
            masterHashMap.put(x, masterHashMap.get(x) + 1);
        } else {
            masterHashMap.put(x, 1);
        }
    }
    public static void showFrequencyTable(List<ReadDocs> docs) {

        List<String> allWord = new ArrayList<String>();
        List<String> docuName = new ArrayList<String>();
        List<List<String>> datas = new ArrayList<>();
        for (Map.Entry ent : masterHashMap.entrySet()){
            allWord.add(ent.getKey().toString());
        }
        for (ReadDocs doc: docs) {
            docuName.add(doc.getFileName());
        }
       
        for (ReadDocs doc: docs){
            List<String> data = new ArrayList<String>();
            for (String a: allWord){
                data.add(doc.getTemp().get(a) != null ? doc.getTemp().get(a).toString() : "0");
            }
            datas.add(data);
        }


        ProcessingUtil.printTable(allWord, docuName, datas);

              
    }

    public static void showCosineTable(List<ReadDocs> docs) {
        List<String> docuName = new ArrayList<String>();
        List<List<String>> datas = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.00");  

        for (ReadDocs doc: docs) {
            docuName.add(doc.getFileName());
        }

        for (ReadDocs thisdoc: docs){
            List<String> data = new ArrayList<String>();
            for (ReadDocs thatdoc: docs){

                data.add(thisdoc != thatdoc ? formatter.format(thisdoc.cosineValue(thatdoc)) : " ");
            }
            datas.add(data);
        }     
        ProcessingUtil.printTable(docuName, docuName, datas);          

    }      
    
    public static void showEuclideanTable(List<ReadDocs> docs) {
        List<String> docuName = new ArrayList<String>();
        List<List<String>> datas = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.00");  

        for (ReadDocs doc: docs) {
            docuName.add(doc.getFileName());
        }

        for (ReadDocs thisdoc: docs){
            List<String> data = new ArrayList<String>();
            for (ReadDocs thatdoc: docs){

                data.add(thisdoc != thatdoc ? formatter.format(thisdoc.euclideanValue(thatdoc)) : " ");
            }
            datas.add(data);
        }     
        ProcessingUtil.printTable(docuName, docuName, datas);          

    }      
    
    public static void showManhattanTable(List<ReadDocs> docs) {
        List<String> docuName = new ArrayList<String>();
        List<List<String>> datas = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.00");  

        for (ReadDocs doc: docs) {
            docuName.add(doc.getFileName());
        }

        for (ReadDocs thisdoc: docs){
            List<String> data = new ArrayList<String>();
            for (ReadDocs thatdoc: docs){

                data.add(thisdoc != thatdoc ? formatter.format(thisdoc.manhattanValue(thatdoc)) : " ");
            }
            datas.add(data);
        }     
        ProcessingUtil.printTable(docuName, docuName, datas);          

    }      
    public static void main(String[] args) {

        //Get all files
        List<File> files = FileOperation.getAllTextFiles();
        List<ReadDocs> docs = new ArrayList<ReadDocs>();
        List<Thread> runnables = new ArrayList<Thread>();

        for (File f : files){
            ReadDocs rd = new ReadDocs(f);
            docs.add(rd);
            runnables.add(new Thread(rd));
        }
        for (Thread run : runnables){
            run.start();
        }
        for (Thread run : runnables){
             try {
                run.join();
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException from Main caught!s");
            } 
        }
        /*
        //Webcrawler word check 
        Spider spider = new Spider();
        String url = JOptionPane.showInputDialog(null, "Input website url");
        String wordSearch = JOptionPane.showInputDialog(null, "Input word to search");
        spider.search(url, wordSearch);
        
        */
        /*
        ProcessingUtil.printMap(docs.get(0).getTemp());
        ProcessingUtil.printMap(docs.get(0).getNormalized());
        HashMap a = new HashMap();
        a.putAll(masterHashMap);
        ProcessingUtil.printMap(a);

        */
        showFrequencyTable(docs);
        showCosineTable(docs);
        showEuclideanTable(docs);
        showManhattanTable(docs);
        
        FileOperation.saveEuclideanCSV(docs);
        FileOperation.saveManhattanCSV(docs);
        FileOperation.saveCosineCSV(docs);
        
        //ProcessingUtil.printTable()
        
         // try {
        //     Task1.join();
        // } catch (InterruptedException ex) {
        //     System.out.println("InterruptedException from Main caught!s");
        // }       
        //Thread Task1 = new Thread(runProg);
        // RunProg runProg = new RunProg();
        
        // Thread Task1 = new Thread(runProg);
        
        // Task1.start();
        
        // try {
        //     Task1.join();
        // } catch (InterruptedException ex) {
        //     System.out.println("InterruptedException from Main caught!s");
        // }
        
    }
    
}
