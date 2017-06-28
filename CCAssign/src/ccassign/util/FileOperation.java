/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccassign.util;

import java.io.File;
import java.util.*;
import ccassign.*;
import java.text.*;
import java.io.FileNotFoundException;
import java.util.StringJoiner;

public class FileOperation {
	public static List<File> getAllTextFiles() {
		List<File> list = new ArrayList<File>();
		for (int index = 0; index < 20; index++) {
			list.add(new File("Patent Doc " + index + ".txt"));
		}
		return list;
	}
	public static void saveEuclideanCSV(List<ReadDocs> docs) {
          java.io.File OutputFile = new java.io.File("OutputEuclidean.csv");

            // Check if file exists
            if (OutputFile.exists()) {
                System.out.println("File already exists!");
            }

            try {

                // Start writing data into the file "Output.txt"
                java.io.PrintWriter output = new java.io.PrintWriter(OutputFile);	

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
		        int count = 0;   
		        for (ReadDocs thisdoc: docs){
		        	output.print(thisdoc.getFileName() );
		        	 StringJoiner sj = new StringJoiner(",");
		        	 for (String a : datas.get(count)){
		        	 	sj.add(a);
		        	 }
		        	output.print(sj.toString());
		        	output.println();
		        }


		        output.close();
             } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException from PrintOutput caught!");
            }	
	}
        
        public static void saveManhattanCSV(List<ReadDocs> docs) {
          java.io.File OutputFile = new java.io.File("OutputManhattan.csv");

            // Check if file exists
            if (OutputFile.exists()) {
                System.out.println("File already exists!");
            }

            try {

                // Start writing data into the file "Output.txt"
                java.io.PrintWriter output = new java.io.PrintWriter(OutputFile);	

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
		        int count = 0;   
		        for (ReadDocs thisdoc: docs){
		        	output.print(thisdoc.getFileName() );
		        	 StringJoiner sj = new StringJoiner(",");
		        	 for (String a : datas.get(count)){
		        	 	sj.add(a);
		        	 }
		        	output.print(sj.toString());
		        	output.println();
                                ProcessingUtil.printTable(docuName, docuName, datas);   
		        }


		        output.close();
             } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException from PrintOutput caught!");
            }	
	}
        
        public static void saveSimilarityCSV(List<ReadDocs> docs) {
          java.io.File OutputFile = new java.io.File("OutputSimilarity.csv");

            // Check if file exists
            if (OutputFile.exists()) {
                System.out.println("File already exists!");
            }

            try {

                // Start writing data into the file "Output.txt"
                java.io.PrintWriter output = new java.io.PrintWriter(OutputFile);	

		        List<String> docuName = new ArrayList<String>();
		        List<List<String>> datas = new ArrayList<>();
		        NumberFormat formatter = new DecimalFormat("#0.00");  

		        for (ReadDocs doc: docs) {
		            docuName.add(doc.getFileName());
		        }

		        for (ReadDocs thisdoc: docs){
		            List<String> data = new ArrayList<String>();
		            for (ReadDocs thatdoc: docs){

		                data.add(thisdoc != thatdoc ? formatter.format(thisdoc.similarityValue(thatdoc)) : " ");
		            }
		            datas.add(data);
		        }                 
		        int count = 0;   
		        for (ReadDocs thisdoc: docs){
		        	output.print(thisdoc.getFileName() );
		        	 StringJoiner sj = new StringJoiner(",");
		        	 for (String a : datas.get(count)){
		        	 	sj.add(a);
		        	 }
		        	output.print(sj.toString());
		        	output.println();
		        }


		        output.close();
             } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException from PrintOutput caught!");
            }	
	}
}