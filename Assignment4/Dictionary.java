/**
  Dictionary Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment4;

import java.util.*;


//https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html


//dont need this because it does nothing but implement a HashMap
public class Dictionary{
    
    Map<String,Word> wordDict;
    
// Constructors    
    /**
     * Create a hash set for the dicitonary
     */
    public Dictionary(){
        this.wordDict = new HashMap<String,Word>();
    }
    
    public void addWord(Word word){
        this.wordDict.put(word.name, word);
    }

    public Word getWord(String name){
        return this.wordDict.get(name);
    }

    public boolean findWord(String word){
        return this.wordDict.containsKey(word);
    }

    public void cleanDictionary() {
        //clean visited and numsteps
    }
    
}
