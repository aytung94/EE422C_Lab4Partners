/**
  WordLadderBlackBox JUnit Test
  Solves EE422C programming assignment #4
  @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 

package Assignment4;

import java.util.*;
import junit.framework.TestCase;

public class WordLadderBlackBox extends TestCase {
    WordLadder solver;
    Stack<String> solution;
    
    protected void setUp() throws Exception{
        solver = new WordLadder("A4-Words.txt");
    }
    
// Test output values knowing only 5 input, dictionary input, and correct word ladder construct
    
    public void testDictWords() throws Exception{
        String[] start = {"abled","cokes","gunks","hazel","lanai","mires","nouns","prior","revel","sumac"}; // in dictionary
        String[] end = {"setup","sonly","umiak","wassa","zonal","still","slaps","proof","loose","jazzy"};   // in dictionary
        for(int i = 0; i < 10; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            if(solution != null){
                assertTrue(checkSolution(solution));
            }
        }
    }
    
    public void testStartDictWords() throws Exception{
        String[] start = {"abled","cokes","gunks","hazel","lanai","mires","nouns","prior","revel","sumac"}; // in dictionary
        String[] end = {"setap","sonli","umiac","wasss","zinal","stail","slaos","praof","loese","jizzy"};   // not in dictionary
        for(int i = 0; i < 10; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution == null);
        }    
    }

    public void testEndDictWords() throws Exception{
        String[] start = {"setap","sonli","umiac","wasss","zinal","stail","slaos","praof","loese","jizzy"};   // not in dictionary
        String[] end = {"abled","cokes","gunks","hazel","lanai","mires","nouns","prior","revel","sumac"}; // in dictionary
        for(int i = 0; i < 10; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution == null);
        }    
    }    
    
    public void testNonDictWords() throws Exception{
        String[] start = {"setap","sonli","umiac","wasss","zinal","stail","slaos","praof","loese","jizzy"};   // not in dictionary
        String[] end = {"asled","cikes","genks","zazel","danai","madgs","noins","praos","bevol","dumac"}; // in dictionary
        for(int i = 0; i < 10; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution == null);
        }    
    }    
    
    public void testCloseWords() throws Exception{
        String[] start = {"hello","wordy","beeps"};
        String[] end = {"hells","words","beers"};    
        for(int i = 0; i < 3; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution != null);
            assertTrue(checkSolution(solution));
        }        
    }
    
    public void testLongWords() throws Exception{
        String[] start = {"hellos","wordydsadfads","fdasfdafbeeps"};
        String[] end = {"hellsadfsa","wofdsards","beersfasdfa"};    
        for(int i = 0; i < 3; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution == null);
        }        
    }
    
    public void testShortWords() throws Exception{
        String[] start = {"hlos","wods","fda"};
        String[] end = {"hesa","wods","bee"};    
        for(int i = 0; i < 3; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution == null);
        }        
    }
    
    public void testCapitalWords() throws Exception{
        String[] start = {"Abled","Cokes","Gunks","Hazel","lanAi","DIres","noUns","PRIOR","ReVel","SuMac"}; // in dictionary
        String[] end = {"seTup","sonly","Umiak","wAsSa","zOnal","stIll","slAps","prOof","lOOse","Jazzy"};   // in dictionary
        for(int i = 0; i < 10; i++){
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution == null);
        }
    }
    
// Useful Methods
    
    public boolean checkSolution(Stack<String> solution){
        String currentword = solution.pop();
        String nextword;
        while(!solution.isEmpty()){
            nextword = solution.pop();
            if(!oneDifference(currentword,nextword)){
                return false;
            }
            currentword = nextword;
        }
        return true;
    }
    
    public boolean oneDifference(String first, String second){
        int numOff = 0;
        for(int i = 0; i < 5; i++){
            if(first.charAt(i) != second.charAt(i))
                numOff++;
        }
        if(numOff > 1)
            return false;
        else 
            return true;
    }
}
