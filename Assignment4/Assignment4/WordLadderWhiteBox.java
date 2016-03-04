/**
  WordLadderWhiteBox JUnit Test
  Solves EE422C programming assignment #4
  @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import junit.framework.TestCase;

public class WordLadderWhiteBox extends TestCase {
    private WordLadder solver;
    private Stack<String> solution;
    private ByteArrayOutputStream out;
    private final String notInDict0 = "At least one of the words ";
    private final String notInDict1 = " and ";
    private final String notInDict2 = " are not legitimate 5-letter words from the dictionary";
    private final String noSol0 = "There is no word ladder between ";
    private final String noSol1 = " and ";
    private final String noSol2 = "!";
    private final String separator = System.getProperty("line.separator");
    private final String stars = separator + "**********" + separator;
    private final String stars1 = "**********" + separator;

    protected void setUp() throws Exception{
        solver = new WordLadder("A4-Words.txt");
    }

// Test print statements knowing where and when they will occur. 
   
    public void testDictWords() throws Exception{
        String[] start = {"cokes","mires","nouns","revel"}; // in dictionary
        String[] end = {"sonly","still","slaps","loose"};   // in dictionary
        for(int i = 0; i < 4; i++){
            out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            solution = solver.OutputWordLadder(start[i], end[i]);
            assertTrue(solution != null);
            assertEquals(checkSolutionPrint(solution) + stars1, out.toString());
            System.setOut(null);
        }
    }

    public void testNonDictWords() throws Exception{
        String[] start = {"se*ap","*onli","umiac","wasss","zinal","stail","slaos","praof","loese","jizzy"};   // not in dictionary
        String[] end = {"as*ed","*ikes","genks","zazel","danai","madgs","noins","praos","bevol","dumac"}; // in dictionary
        for(int i = 0; i < 10; i++){
            out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            solver.OutputWordLadder(start[i], end[i]);            
            assertEquals(notInDict0 + start[i] + notInDict1 + end[i] +notInDict2 + stars, out.toString());
            System.setOut(null);
        }    
    }    
    
    public void testSameWords() throws Exception{
        String[] start = {"hello","wordy","beeps"};
        String[] end = {"hello","wordy","beeps"};    
        for(int i = 0; i < 3; i++){
            out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            solver.OutputWordLadder(start[i], end[i]);            
            assertEquals(start[i] + stars, out.toString());
            System.setOut(null);
        }        
    }
    
    public void testNullWords() throws Exception{
        String[] start = {null,null,"fdasfdafbeeps"};
        String[] end = {"hellsadfsa",null,null};    
        for(int i = 0; i < 3; i++){
            out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            solver.OutputWordLadder(start[i], end[i]);            
            assertEquals(notInDict0 + start[i] + notInDict1 + end[i] +notInDict2 + stars, out.toString());
            System.setOut(null);
        }        
    }
    
    public void testNoSolutionWords() throws Exception{
        String[] start = {"hello","xylem","mumbo"};
        String[] end = {"world","zebra","ghost"};    
        for(int i = 0; i < 3; i++){
            out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            solver.OutputWordLadder(start[i], end[i]);            
            assertEquals(noSol0 + start[i] + noSol1 + end[i] + noSol2 + stars, out.toString());
            System.setOut(null);
        }        
    }
    
// Useful Methods    
    
    public String checkSolutionPrint(Stack<String> solution){
        String output = solution.pop() + separator;
        while(!solution.isEmpty()){
            output = output + solution.pop() + separator;
        }
        return output;
    }
}

