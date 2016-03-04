/**
  WordLadder Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment4;

import java.util.*;

public class WordLadder{
    private Dictionary dictionary;   // Dictionary to store words 
    private Queue<String> bfs;       // Queue used to BFS dictionary to find word ladder
    
    public WordLadder(String dictFileName) throws Exception{
        this.dictionary = new Dictionary(dictFileName);
    }
    
    /******************************************************************************
    * Method Name: outputWordLadder                                               *
    * Purpose: Wrapper for make ladder method                                     *
    * Parameters: String starting word, String ending word, and Dictionary object *
    * Returns: None                                                               *
    ******************************************************************************/
    public Stack<String> OutputWordLadder(String startingWord, String endingWord) throws Exception 
    {
        try{
            // To Hold Solution
            Stack<String> solution = null;
            
            // Check if starting and ending words are in the dictionary
            if(!(dictionary.findWord(startingWord) && dictionary.findWord(endingWord)))
            {
                System.out.println("At least one of the words " + startingWord + " and " + endingWord + " are not legitimate 5-letter words from the dictionary");
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
                bfs = new LinkedList<String>();                 // initialize the queue for BFS
                bfs.add(startingWord);                          // add the first word on the queue
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
                    solution = new Stack<String>(); // initialize stack to find path
                    String tempWord = end;
                    
                    // Back track to create word ladder using stack
                    while(dictionary.getWord(tempWord).getParent() != null){
                        solution.push(tempWord);
                        tempWord = dictionary.getWord(tempWord).getParent();
                    }
                    solution.push(startingWord);
                                    
                    // Pop from starting to print out word ladder
                    Stack<String> outputSolution = (Stack<String>)(solution.clone());
                    while(!outputSolution.isEmpty()){
                        System.out.println(outputSolution.pop());
                    }    
                }    
               
                dictionary.cleanDictionary();
            }
            System.out.println("**********");
            return solution;
        }
        catch(StackOverflowError t){
            System.out.println("Stack overflow! Add -Xss3m in VM Arguments to improve your eclipse!");
            System.out.println("**********");
        }
        return null;
    }
    
    /******************************************************************************
    * Method Name: MakeLadder                                                     *
    * Purpose: Make ladder function to find ladder between two words              *
    * Parameters: String ending word                                              *
    * Returns: Null if target word not found, ending word if found                *
    ******************************************************************************/
    public String MakeLadder(String endingWord){
        // Pop word to check from queue
        String currentWord = bfs.remove();
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
                        bfs.add(tempWord);
                        dictionary.getWord(tempWord).markVisited();
                    }    
                }
            }
        }
        
        if(bfs.isEmpty())
            return null;
        else
            return MakeLadder(endingWord);
    }
}
