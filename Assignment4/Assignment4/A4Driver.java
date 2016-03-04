/**
  A4Driver Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class A4Driver
{
    /******************************************************************************
    * Method Name: processLinesInFile                                             *
    * Purpose: Opens the file specified in String filename, reads each line in it *
    *          Invokes              on each line in the file, and prints out the  *
    *          corresponding outputs.                                             *
    * Returns: None                                                               *
    * @throws Exception                                                           *
    ******************************************************************************/
    public static void processLinesInFile (String dictFileName, String inputFileName) throws Exception
    {
        // Create solver that uses dictionary file
    	WordLadder solver = new WordLadder(dictFileName);

        // Create Word Ladders
        FileReader freader = null;
        BufferedReader reader = null; 
        try
        {
            // Read Input file
            freader = new FileReader(inputFileName);
            reader = new BufferedReader(freader);

            // Find Word Ladder
            for (String s = reader.readLine(); s != null; s = reader.readLine()){
            	String inputWords[] = s.trim().split("\\s+");
            	if(inputWords.length != 2){
                    System.out.println("Invalid inputs\n**********");	
            	}            		
            	else
            		solver.OutputWordLadder(inputWords[0], inputWords[1]);
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.println ("Error: Input File not found. Exiting...");
            //e.printStackTrace();
            System.exit(-1);
        } 
        catch (IOException e)
        {
            System.err.println ("Error: IO exception. Exiting...");
            //e.printStackTrace();
            System.exit(-1);
        } 
    	freader.close();
    	reader.close();
    	
        return;
    }
   
    /******************************************************************************
    * Method Name: main                                                           *
    * Purpose: Driver                                                             *
    * Returns: None                                                               *
    ******************************************************************************/
    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            System.err.println ("Error: Incorrect number of command line arguments");
            System.exit(-1);
        }
        processLinesInFile (args[0], args[1]);
    }
}