package com.ibm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileDiff {

        public static void main(String[] args) throws Exception {
        	BufferedWriter writer1 = null;
        try{	
        	Properties prop = new Properties();			
    		prop.load(FileSearch.class.getClassLoader()
    									.getResourceAsStream("Prop.properties"));
    		String root = prop.getProperty("Root");		
//    		String filePath =root+"input";
    		String outFilePre=root+"output" +"\\Output_Pre.txt";
    		String outFilePost=root+"output" +"\\Output_Post.txt";
    		
    		
   		   String outFileFinal=root+"output" +"\\FINAL_RESULT.txt";
   		   //Location of the output file.
		    File fileLoc = new File(outFileFinal);
		    
			writer1 = new BufferedWriter( new FileWriter(fileLoc));
		    
		    
        	BufferedReader br1 = null;
            BufferedReader br2 = null;
            String sCurrentLine;
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            br1 = new BufferedReader(new FileReader(outFilePre));
            br2 = new BufferedReader(new FileReader(outFilePost));
            while ((sCurrentLine = br1.readLine()) != null) {
                list1.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                list2.add(sCurrentLine);
            }
            List<String> tmpList = new ArrayList<String>(list1);
            tmpList.removeAll(list2);
            System.out.println("\n\n" + "Content from "+outFilePre+ " which is not there in "+outFilePost+ "::");
            
        //    String outFileFinal=root+"output" +"\\FINAL_RESULT.txt";
            writer1.newLine();
        	writer1.write("\n\n" + "Content from "+outFilePre+ " which is not there in "+outFilePost+"::");        	
        	writer1.newLine();
            
        	
        	for(int i=0;i<tmpList.size();i++){
             
            	writer1.newLine();
            	writer1.append(tmpList.get(i));	
            	writer1.newLine();
                System.out.println(tmpList.get(i)); //content from test.txt which is not there in test2.txt.
                  

           }  
            
        	System.out.println("\n\n"+ "Content from "+outFilePost+ " which is not there in "+outFilePre+ "::");
        	
		
            //System.out.println("\n \n"+ "Content from test2.txt which is not there in test.txt");

            tmpList = list2;
            tmpList.removeAll(list1);
            
            writer1.newLine();
            writer1.write("\n"+ "======================================================================================================================================= ");
        	writer1.write("\n\n"+ "Content from "+outFilePost+ " which is not there in "+outFilePre+"::");        	
        	writer1.newLine();
            for(int i=0;i<tmpList.size();i++){
             
            	
            	System.out.println(tmpList.get(i)); //content from test2.txt which is not there in test.txt
            	writer1.newLine();
            	writer1.append(tmpList.get(i));	
            	writer1.newLine();
                      	
                	
            }
    
        } catch ( IOException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
		    try
		    {
		        if ( writer1 != null)
		        writer1.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
        
        
          } 
        
        }
	
	


