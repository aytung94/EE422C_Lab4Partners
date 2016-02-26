/**
  A4Driver Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment4;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


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
        //A4Driver driver = new A4Driver();
        Dictionary dictionary = new Dictionary();

        // Create Dictionary
        try 
        {
            // Read Dictionary file
            FileReader freader = new FileReader(dictFileName);
            BufferedReader reader = new BufferedReader(freader);
            
            // Read and process lines to add to dictionary
            for (String s = reader.readLine(); s != null; s = reader.readLine()) 
            {
                // Add word into dictionary 
                if(s.charAt(0) != '*'){
                    String name = s.substring(0,5);
                    dictionary.addWord(name);
                }
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println ("Error: Dictionary File not found. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) 
        {
            System.err.println ("Error: IO exception. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }

        // Create Word Ladder
        try
        {
            // Read Input file
            FileReader freader = new FileReader(dictFileName);
            BufferedReader reader = new BufferedReader(freader);

            // Find Word Ladder
            for (String s = reader.readLine(); s != null; s = reader.readLine()){
                String inputWords[] = s.split("\\s+");
                OutputWordLadder(inputWords[0], inputWords[1]);
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.println ("Error: Input File not found. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e)
        {
            System.err.println ("Error: IO exception. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }        return;
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

    public static void OutputWordLadder(String startingWord, String endingWord) {
        // Check if starting and ending words are in the dictionary

        // If not in dictionary, throw exception
            System.out.println("At least one of the words " + startingWord + " and " + endingWord + " are not legitimate 5-letter words from the dictionary");

        ArrayList<String> wordLadder = MakeLadder(startingWord, endingWord, -1);


        // If found, Print Word Ladder

            System.out.println("**********");

        // else, state
            System.out.println("There is no word ladder between " + startingWord + " and " + endingWord + "!");
    }

    // Not sure if arraylist of strings is correct return value
    public static ArrayList<String> MakeLadder(String startingWord, String endingWord, int position){
        // Check if done statements


        // recursive call

    }
}

