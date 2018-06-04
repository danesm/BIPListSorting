@echo off
rem ============== FTM Scripts ================= 
rem Licensed Materials - Property of IBM;
rem 5724-A82
rem (c) Copyright IBM Corp. 2005,2014;
rem All Rights Reserved;
rem US Government Users Restricted Rights - use,
rem duplication or disclosure restricted by GSA
rem ADP Schedule Contract with IBM Corp.;
rem set CALSSPATH=%CLASSPATH%;%HOME%\src\commons-io-2.5.jar
rem Author Danesh Mishra
rem =============================================
set HOME=C:\FileSearch
set PATH=%PATH%;C:\Program Files\IBM\SQLLIB\java\jdk\bin;


javac -cp %HOME%\com\ibm\lib\commons-io-2.5.jar  -Xlint:unchecked  %HOME%\com\ibm\util\*.java
java -cp %HOME%\com\ibm\lib\commons-io-2.5.jar;%HOME%; com.ibm.util.FileSearch

 
rem move %HOME%\input\*.*  %HOME%\BackupInputFile
rem echo Please press Enter/Return key to finish the program !!  

rem pause >nul



REM
REM End!
REM
