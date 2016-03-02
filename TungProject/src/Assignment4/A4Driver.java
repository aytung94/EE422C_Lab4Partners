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
	private static Queue<String> a;
	private static Dictionary dictionary;
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

    public static void OutputWordLadder(String startingWord, String endingWord, Dictionary dic) {
        // Check if starting and ending words are in the dictionary
    	if(!(dic.findWord(startingWord) && dic.findWord(endingWord)))
    	{
    		System.err.println("At least one of the words " + startingWord + " and " + endingWord + " are not legitimate 5-letter words from the dictionary\n");
        	System.out.println("**********");
    	}  
    	else if(startingWord.equals(endingWord))
    	{
    		System.out.println("\n**********");
    	}
    	else
    	{
    		a = new LinkedList<String>();
    		a.add(startingWord);
    		dictionary.getWord(startingWord).markVisited();
    		String end = MakeLadder(endingWord);
    		if(end == null)
    		{
    			System.out.println("There is no word ladder between " + startingWord + " and " + endingWord + "!");
    			
    		}
    		else
    		{
    			Stack ofCash = new Stack<String>();
    			String tempWord = end;
    			while(dictionary.getWord(tempWord).getParent() != null){
    				ofCash.push(tempWord);
    				tempWord = dictionary.getWord(tempWord).getParent();
    			}
    			System.out.println(startingWord);
    			while(!ofCash.isEmpty()){
    				System.out.println(ofCash.pop());
    			}	
    		}	
    		dictionary.cleanDictionary();
    		System.out.println("**********");
    	}
    	
		
        // If found, Print Word Ladder
        // else, state
    }

    public static String MakeLadder(String endingWord){
    	String currentWord = a.remove();
    	String tempWord = currentWord;
        for(int i = 0; i < 5; i++)
        {
        	for(int j = 0; j < 26; j++)
        	{
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
        
        
        // recursive call

    }
}

