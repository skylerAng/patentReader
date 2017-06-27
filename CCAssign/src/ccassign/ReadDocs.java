package ccassign;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import ccassign.util.ProcessingUtil;

public class ReadDocs implements Runnable {

    //file name to be process
    private File file;

    //local hashmap
    private HashMap<String, Integer> temp;

    //local normalized hashmap
    private HashMap<String, Double> normalizedTemp;

    // BufferedReader for reaading file
    private BufferedReader br;

    public String getFileName() {
        return file.getName();
    }

    public HashMap<String, Integer> getTemp() {
        return temp;
    }
    public HashMap<String, Double> getNormalized() {
        return normalizedTemp;
    }

    public ReadDocs(File file) {
        this.file = file;
        this.temp = new HashMap<String, Integer>();
        this.normalizedTemp = new HashMap<String, Double>();
    }

    private void wordCount(String[] split) {

        Set keyset = temp.keySet();

        for (String x : split) { // Printing out each line in the file
            //System.out.println(x);
            x.toLowerCase();

            if (temp.containsKey(x)) { // Check if the particular word (key) alrd exists
                temp.put(x, temp.get(x) + 1);
            } else {
                temp.put(x, 1);
                
            }
            CCAssign.addWord(x);
            // System.out.println("Words stored in all array is : " + temp);
            // System.out.println(globalWords);
        }
    }

    private double getMax(HashMap<String, Integer> temp) {
        double cellValue;
        double maxValue = Integer.MIN_VALUE;
        for (Map.Entry x : temp.entrySet()) {
            cellValue = (double) ((int) x.getValue());

            if (cellValue > maxValue) {
                maxValue = cellValue;
            }
        }
        return maxValue;
    }

    private void normalizeValues() {

        //find max
        //Get maxmimum -----------------------------
        // ----------------------------------------------------------------------------------------
        double max = getMax(temp);
        double cellValue;
        double average;
        double normalize;
        double totalNormalize = 0;

        //find max value of all word
        //Loop and converts value (Normalize)
        //System.out.println(max);

        for (Map.Entry m : temp.entrySet()) {

            cellValue = ((double) (int) m.getValue());
            //takes every value and divide by max
            normalize = (cellValue / max);
            //System.out.println(normalize);

            //then stores the obtained value to the normalized temp
            if (normalizedTemp.containsKey(m)) { // Check if the particular word (key) alrd exists
                break;
            } else {
                normalizedTemp.put((String) m.getKey(), normalize);
            }
            //normalizedTemp.put(m.getKey().toString(), normalize);
            //System.out.println(m.getKey() + " value: " + m.getValue());
        }
    }

    public double euclideanValue(ReadDocs doc) {
        //other document normalized
        HashMap<String, Double> that = doc.getNormalized();
        //store comman word set
        Set<String> keyset = new HashSet<String>();
        //construct coomand word set
        for (Map.Entry ent : normalizedTemp.entrySet()) {
            keyset.add(ent.getKey().toString());

        }
        for (Map.Entry ent : that.entrySet()) {
            keyset.add(ent.getKey().toString());
        } 
        List<Double> difference = new ArrayList<Double>();
        
        for (String ent : keyset){
            double thisvalue = normalizedTemp.get(ent) != null ? normalizedTemp.get(ent) : 0;
            double thatvalue = that.get(ent) != null ? that.get(ent) : 0;
            difference.add(Math.abs(thisvalue - thatvalue));
        }
        double sum = 0.0;
        for (double num: difference) {
                sum += num;
        }
        return Math.sqrt(sum);

    } 
    
    public double manhattanValue(ReadDocs doc) {
        //other document normalized
        HashMap<String, Double> that = doc.getNormalized();
        //store comman word set
        Set<String> keyset = new HashSet<String>();
        //construct coomand word set
        for (Map.Entry ent : normalizedTemp.entrySet()) {
            keyset.add(ent.getKey().toString());

        }
        for (Map.Entry ent : that.entrySet()) {
            keyset.add(ent.getKey().toString());
        } 
        List<Double> difference = new ArrayList<Double>();
        
        for (String ent : keyset){
            double thisvalue = normalizedTemp.get(ent) != null ? normalizedTemp.get(ent) : 0;
            double thatvalue = that.get(ent) != null ? that.get(ent) : 0;
            difference.add(Math.abs(thisvalue - thatvalue));
        }
        double distance = 0.0;
        for (double num: difference) {
                distance += num;
        }
        return distance;

    } 
    
    public double similarityValue(ReadDocs doc) {
        //other document normalized
        HashMap<String, Double> that = doc.getNormalized();
        //store comman word set
        Set<String> keyset = new HashSet<String>();
        //construct coomand word set
        for (Map.Entry ent : normalizedTemp.entrySet()) {
            keyset.add(ent.getKey().toString());

        }
        for (Map.Entry ent : that.entrySet()) {
            keyset.add(ent.getKey().toString());
        } 
        List<Double> difference = new ArrayList<Double>();
        
        double dotProduct = 0.0;
        double numA = 0;
        double numB = 0;
        
        for (String ent : keyset){
            double thisvalue = normalizedTemp.get(ent) != null ? normalizedTemp.get(ent) : 0;
            double thatvalue = that.get(ent) != null ? that.get(ent) : 0;
            difference.add(Math.abs(thisvalue - thatvalue));
            numA += Math.pow(thisvalue, 2);
            numB += Math.pow(thatvalue, 2);
        }
        
        for (double num: difference) {
                dotProduct += num;
                
        }
        return dotProduct / (Math.sqrt(numA) + Math.sqrt(numB));

    } 

    public void compareWordsInDocument(HashMap<String, Double> normalizedTemp, HashMap<String, Double> normalizedTemp2) {

        Set<String> set1 = new HashSet<String>(normalizedTemp.keySet());
        Set<String> set2 = new HashSet<String>(normalizedTemp2.keySet());

        if (set1.contains(set2)) {
            //System.out.println(set1);
        }
        HashMap<String, Double> checkerMap = new HashMap<String, Double>();
        for (Map.Entry keys : normalizedTemp.entrySet()) {

            String cache = (String) keys.getKey();
            double cache2 = (double) keys.getValue();

            checkerMap.put(cache, cache2);
        }
        /*    
        for (Entry<String, Double> entry: normalizedTemp.entrySet()) {
            // Check if the current value is a key in the 2nd map
            if (!normalizedTemp2.containsKey(entry.getValue())) {

            // hMap2 doesn't have the key for this value. Add key-value in new map.
            checkerMap.put(entry.getKey(), entry.getValue());
                System.out.println("Checker map :" + entry.getKey());
            
            }
        }*/

    }

    @Override
    public void run() {

        // ------------------------------------------------------------------------------------------------------
        // BEGIN READING THE DOCUMENTS
        // ------------------------------------------------------------------------------------------------------

        // read variable to reading each line
        String read = "TempStringForRead";


        try {

            // Subject to change depending on the location of the file
            br = new BufferedReader(new FileReader(file));

            while ((read = br.readLine()) != null) {

                //replace everything with nothing except for myHashMapInReadDocs-z, A-Z, 0-9, while maintain spaces
                String filter = read.replaceAll("[^a-z A-Z 0-9\\s+]", "");
                
                //removing stop words
                String[] splitFilter = ProcessingUtil.removeStopWords(filter.toLowerCase());

                //starting count word
                wordCount(splitFilter);

                //normalizeValues
                normalizeValues();
                
            }

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException from ReadDocs caught!");
        } catch (IOException ex) {
            System.out.println("IOException from ReadDocs caught!");
        }

        //myHashMapInReadDocs.put(fileNameInReadDocs[index], temp);

    }

    

}
