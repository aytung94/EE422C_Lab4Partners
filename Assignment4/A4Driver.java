/**
  A4Driver Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
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
    private static Queue<String> a;         // Queue used to BFS dictionary to find word ladder
    private static Dictionary dictionary;   // Dictionary to store words 
    
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
        dictionary = new Dictionary();

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
            FileReader freader = new FileReader(inputFileName);
            BufferedReader reader = new BufferedReader(freader);

            // Find Word Ladder
            for (String s = reader.readLine(); s != null; s = reader.readLine()){
                String inputWords[] = s.split("\\s+");
                OutputWordLadder(inputWords[0], inputWords[1],dictionary);
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
        }        
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

    /******************************************************************************
    * Method Name: outputWordLadder                                               *
    * Purpose: Wrapper for make ladder method                                     *
    * Parameters: String starting word, String ending word, and Dictionary object *
    * Returns: None                                                               *
    ******************************************************************************/
    public static void OutputWordLadder(String startingWord, String endingWord, Dictionary dic) 
    {
        // Check if starting and ending words are in the dictionary
        if(!(dic.findWord(startingWord) && dic.findWord(endingWord)))
        {
            System.err.println("At least one of the words " + startingWord + " and " + endingWord + " are not legitimate 5-letter words from the dictionary\n");
        }
        // Checking if start word is the end word
        else if(startingWord.equals(endingWord))
        {
            System.out.println(startingWord);
        }
        // Else try to create ladder
        else
        {
            // Create the word ladder
            a = new LinkedList();                   // initialize the queue for BFS
            a.add(startingWord);                            // add the first word on the queue
            dictionary.getWord(startingWord).markVisited(); // mark it as visited
            String end = MakeLadder(endingWord);            // make the ladder with endWord as target 
            
            // If end is null, no ladder found
            if(end == null)
            {
                System.out.println("There is no word ladder between " + startingWord + " and " + endingWord + "!");
                
            }
            // If end word is the endingWord, print solution
            else
            {
                // Find and print word ladder
                Stack ofCash = new Stack(); // initialize stack to find path
                String tempWord = end;
                
                // Back track to create word ladder using stack
                while(dictionary.getWord(tempWord).getParent() != null){
                    ofCash.push(tempWord);
                    tempWord = dictionary.getWord(tempWord).getParent();
                }
                
                // Pop from starting to print out word ladder
                System.out.println(startingWord);                
                while(!ofCash.isEmpty()){
                    System.out.println(ofCash.pop());
                }    
            }    
           
            dictionary.cleanDictionary();
        }
        
        System.out.println("**********");
    }
    
    /******************************************************************************
    * Method Name: MakeLadder                                                     *
    * Purpose: Make ladder function to find ladder between two words              *
    * Parameters: String ending word                                              *
    * Returns: Null if target word not found, ending word if found                *
    ******************************************************************************/
    public static String MakeLadder(String endingWord){
        // Pop word to check from queue
        String currentWord = a.remove();
        String tempWord;
        
        // Search for possible next words
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 26; j++)
            {
                // Find all possible words with one letter difference
                if(i == 0){
                    tempWord = (char)(j + 97) + currentWord.substring(1);
                }
                else if(i == 4)
                {
                    tempWord = currentWord.substring(0,4) + (char)(j+97);
                }
                else
                {
                    tempWord = currentWord.substring(0,i) + (char)(j+97) + currentWord.substring(i+1);
                }
                // Check if possible word is valid before marking visited and putting it on the queue
                if(dictionary.findWord(tempWord))
                {
                    if(dictionary.getWord(tempWord).getVisited() == false)
                    {
                        dictionary.getWord(tempWord).setParent(currentWord);
                        if(tempWord.equals(endingWord))
                        {
                            return endingWord;
                        }
                        a.add(tempWord);
                        dictionary.getWord(tempWord).markVisited();
                    }    
                }
            }
        }
        
        if(a.isEmpty())
            return null;
        else
            return MakeLadder(endingWord);
    }
}

