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
    public static void processLinesInFile (String filename) throws Exception 
    {
        A4Driver driver = new A4Driver();
        Dictionary dictionary = new Dictionary();
        try 
        {
            // Read file 
            FileReader freader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(freader);
            
            // Iterate over and process each line of file to create dictionary
            for (String s = reader.readLine(); s != null; s = reader.readLine()) 
            {
                // Add word into dictionary object
                if(s.charAt(0) != '*'){
                    Word word = new Word(s.substring(0,4));
                    dictionary.addWord(word);
                }
            }

            /* test to make sure hashmap is correct*/
            Iterator<Item> i = dictionary.iterator();
            while (i.hasNext())
            {
                Item temp = i.next();                     // grab next item
                double tempPrice = temp.calculatePrice(); // calculate final price
                temp.printItemAttributes();               // print instance variables
                System.out.println("Total Cost: $" + String.format("%1$,.2f", tempPrice) + "\n");
                // print out total cost for item
                totalShoppingCartCost += tempPrice;       // add final price to total shopping cart cost
            }

            // do word ladder creation
            for(;;);
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println ("Error: File not found. Exiting...");
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
        if (args.length != 1)
        {
            System.err.println ("Error: Incorrect number of command line arguments");
            System.exit(-1);
        }
        processLinesInFile (args[0]);
    }
}