/**
  Dictionary Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment3;

// https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html

public class Dictionary(){
    
    Set<String> wordDict;
    
// Constructors    
    /**
     * Create a hash set for the dicitonary
     */
    public Dictionary(){
        this.wordDict = new HashSet<String>;
    }
    
    public void addWord(String word){
        this.wordDict.add(word);
    }
    
    public boolean findWord(String word){
        return this.wordDict.contains(word);
    }
    
}