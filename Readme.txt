This tool is used to compare IIB BIP LIST files pre and post deployment. 
Below is the instructions on how to use this tool. 


1.This can search for a string in any text file from Input folder and write that to a new file on output folder in Output.txt
The search key and value needs to be defined @ 'Prop.properties' file at root folder. Value string will be searched in the input file.

e.g. EG_Running= BIP1286I: Execution group 
     (KEY)         (VALUE)
	 
2. How to set up the tool? 

Below is one time set-up needed per machine.

Unzip the tool and In the FINDIT.bat set the root folder (HOME) and JAVA Directory path.
set HOME=C:\FileSearch
set PATH=%PATH%;C:\Program Files\IBM\SQLLIB\java\jdk\bin; (Note: JAVA path in every system can be different so make sure you copy your path)

Set Root path in Prop.properties file.
Root=C:\\FileSearch\\ 

3. Make sure only one file is present in input folder at a time. Once input file is copied - click on READ_BIPLIST_FILE.bat to read the file. Now check output folder.

4. Once the pre deployment BIP list is read and output.txt is created, rename that to 'Output_Pre.txt' and same is done after deployment's BIP file's output to 'Output_Post.txt' 
   so there should be these 2 files created after pre and post BIP list files are read. 
    
	Ouput_Pre.txt and Output_Post.txt and then click on 'COMPARE_OUTPUT_FILES.bat' to compare both of them. 
	
	The result will be printed on FINAL_RESULT.txt file which will show differences on each files.


5. user can modify prop.properties file to include any new search string.


Note: Due to limitation on readLine java function , we need to make sure that start if every line of BIP list file has atleast single space. 
Work around is to open the BIPList file in Notepad++ and insert a space using ALT+C command on first column of the file before running the tool. It takes a click to add a space using notepad++.

njoy!! :) 



 











