/**
 Word Class
 Solves EE422C programming assignment #4
 @author Dylan Keeton (), Alvin Tung (ayt243)
 @version 1.00 2016-02-025
 */

package Assignment4;

import java.util.*;

public class Word {

    private static final int  wordLength = 5;     // set initially to 5

    private String name;
    private String parent;          
    private boolean visited;

    public Word(String name) {
        this.name = name;
        this.parent = null;
        this.visited = false;
    }

// Get and Set Methods

    public boolean getVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getParent() {
        return this.parent;
    }
    
    public void setParent(String parent)
    {
    	this.parent = parent;
    }
// Useful Methods

    public void markVisited() {
        this.visited = true;
    }

    public void cleanWord() {this.parent = null; this.visited = false;}

    // calculate number of steps from other word to this word
    public int compareSteps(String otherName) {
        int numSteps = 5;
        for(int i = 0; i < wordLength; i++ ) {
            if(this.name.charAt(i) == otherName.charAt(i)) {
                numSteps--;
            }
        }
        return numSteps;
    }
}