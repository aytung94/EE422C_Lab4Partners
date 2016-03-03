/**
  Dictionary Class
  Solves EE422C programming assignment #4
  @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
  @version 1.00 2016-02-025
 */
 
package Assignment4;

import java.util.*;

public class Dictionary{
    
    private Map<String,Word> wordDict;
    
// Constructors    
    /**
     * Create a hash set for the dictionary
     */
    public Dictionary(){
        this.wordDict = new HashMap<String,Word>();
    }
    
    public void addWord(String name){
        Word word = new Word(name);
        this.wordDict.put(name, word);
    }

    public Word getWord(String name){
        return this.wordDict.get(name);
    }

    public boolean findWord(String name){
        return this.wordDict.containsKey(name);
    }

    public Iterator<String> getNameIterator(){
        return wordDict.keySet().iterator();
    }

    public Iterator<Word> getWordIterator(){
        return wordDict.values().iterator();
    }
    
    public void cleanDictionary() {
        Iterator<Word> i = this.getWordIterator();
        while (i.hasNext())
        {
            Word temp = i.next(); // grab next item
            temp.cleanWord();     // clean Word (reset values)
        }
    }
    
}
