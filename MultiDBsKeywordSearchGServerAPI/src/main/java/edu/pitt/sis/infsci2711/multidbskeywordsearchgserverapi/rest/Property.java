package edu.pitt.sis.infsci2711.multidbskeywordsearchgserverapi.rest;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;


public class Property {
	
	BufferedReader br = null;
	BufferedWriter output = null;
	
	Set<String> container = null; // container contains each line of properties
	File file = null; // create an instance of File
	
	public Property () {
		
		 container = new HashSet<String>();
		 file = new File("property.csv");
		 
		 try {
			 output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true))); 
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 // initialize a BufferedReader, read each line of property.csv
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		 } catch (FileNotFoundException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 // store the each line of properties into container
		 try {
			 String str;
			 while( (str = br.readLine()) != null) {
				 container.add(str);
			 }
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 
		 // close BufferedReader
		 try {
			 br.close();
		 } catch(IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		
	}
	
	/**
	 * check property.csv contains a new line of properties or not
	 * @param str
	 * @return
	 */
	public boolean outputCSV (String str) {
		System.out.println("----begin outputCSV----");
		boolean result = false;
		try {
			if (!container.contains(str)) {
				output.write(str + "\n");
				container.add(str);
				System.out.println("----add a line of property to CSV----");
				result = true;
			}
		} catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();			
		}
		System.out.println("----end outputCSV----: " + result);
		return result;
		
	} // end outputCSV
	
	/**
	 * close BufferedWriter
	 */
	public void outputClose() {
		try{
			output.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
