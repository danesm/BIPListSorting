package com.ibm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileReader;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class FileSearch {
	public static void main(String[] args) {
		BufferedWriter writer = null;
		try {
			//CompareBipList s2 = new CompareBipList();
			Properties prop = new Properties();			
			prop.load(FileSearch.class.getClassLoader()
										.getResourceAsStream("Prop.properties"));
			String root = prop.getProperty("Root");		
			String filePath =root+"input";
			String outFile=root+"output" +"\\Output.txt";
			
			//System.out.println("INPUT FILE PATH: " + filePath + "\n");
			//System.out.println("OUTPUT FILE PATH: " + outFile+ "\n");
			
					
			String inputfilePath=filePath+"\\"+new FileSearch().getFileName(filePath);
			System.out.println("FileSearch.main()"+ inputfilePath);
			File infile = new File(inputfilePath);
			
			
			String fileName = new FileSearch().getListOfAllConfigFiles(filePath);		    
			

		    //Location of the output file.
		    File fileLoc = new File(outFile);	

		    ArrayList<String> k = new ArrayList<String>();
			ArrayList<String> v = new ArrayList<String>();
			
			Scanner scan = new Scanner(new File(root+"\\Prop.properties"));
			while (scan.hasNextLine()) {
				// System.out.println(scan.nextLine());
				String split[] = scan.nextLine().trim().split("=");
				if (split.length == 2) {
					k.add(split[0]);
					v.add(split[1]);
				}
			}

		/*	k.remove("File_Path");
			k.remove("Master_PropertyFile");
			k.remove("Output_File_path");
		*/
			
			//System.out.println("FileSearch.main()"+ new FileSearch().countStringInFile("BIP1286I",fileLoc));
			
			Iterator itr = k.iterator();
			
			writer = new BufferedWriter( new FileWriter(fileLoc));
			
			
			writer.write("Input File : " + infile + "\n");
			writer.write("Output File: " + outFile+ "\n");
			writer.write("\n");
			writer.write("Total EGs Running: "+ new FileSearch().countStringInFile("BIP1286I",infile)+ "\n");
			writer.write("Total EGs Stopped: "+ new FileSearch().countStringInFile("BIP1287I",infile)+"\n");
			writer.write("Total MessageFlows Running: "+ new FileSearch().countStringInFile("BIP1288I",infile)+"\n");
			writer.write("Total MessageFlows Stopped: "+ new FileSearch().countStringInFile("BIP1289I",infile)+"\n");
			
			   
			
			while (itr.hasNext()) {
				String key = itr.next().toString();
				String val = prop.getProperty(key.toString());				
				String bipLine="";    
				
				System.out.println("KEY: " + key + "\n");
								
				bipLine = new FileSearch().searchText(val, fileName);					
				
				System.out.println("");
				System.out.println(bipLine);
				
				
			    //Writing what is read from the input file to a new file output file.	
				
				
				writer.newLine();
				writer.write(bipLine);	
				writer.newLine();
				
			    
				
			}
		   
		   writer.write("-----------------------------------------------"+"\n");	
		 
	
		}catch ( IOException e)
		{
			e.printStackTrace();
			
		}		
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}

	}
	
	
	public void writeOutputFile(String yourfilename,String yourstring){
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( yourfilename));
		    writer.write(yourstring);
            
		}
		catch ( IOException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	
	public String getListOfAllConfigFiles(String directoryName)
	{
		
		 File dir = new File(directoryName);
		 FileFilter fileFilter = new WildcardFileFilter("*");
		 File[] files = dir.listFiles(fileFilter);
		 String fileName = files[0].toString(); 

		 return fileName;
		 
	}

	public String searchText(String text, String file) {

		StringBuffer sb = new StringBuffer();
		try {
	          
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null; 

			while ((line = br.readLine()) != null) {

				if (line.indexOf(text) > 0) {
				
					sb.append(line);
					sb.append('\n');
				}
				
			 
			}

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	
	public int countStringInFile(String stringToLookFor, File inputFile){
		  int count = 0;
		  try{
		    FileInputStream fstream = new FileInputStream(inputFile);
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    while ((strLine = br.readLine()) != null)   {
		      int startIndex = strLine.indexOf(stringToLookFor);
		      while (startIndex != -1) {
		        count++;
		       
				startIndex = strLine.indexOf(stringToLookFor, 
		                                  startIndex +stringToLookFor.length());
		      }
		    }
		    in.close();
		  }catch (Exception e){//Catch exception if any
		    System.err.println("Error: " + e.getMessage());
		  }
		  return count;
		}
	
	
	public String getFileName(String filePath){
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		String fileName= "";

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  //System.out.println("FileSearch.getFileName():"+ listOfFiles[i].getName());
		    	  fileName=listOfFiles[i].getName();
		    }
		      else {
		    	   System.out.println("FileSearch.getFileName(): FILE NOT FOUND IN THE INPUT FOLDER");
		    	   return "INVALID SEARCH";
		    	  }
		      }
		    
		    return fileName;
	}
	
	

}
