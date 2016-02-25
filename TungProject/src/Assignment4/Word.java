/**
 Word Class
 Solves EE422C programming assignment #4
 @author Dylan Keeton (), Alvin Tung (ayt243)
 @version 1.00 2016-02-025
 */

package Assignment4;

import java.util.*;

public class Word {

    int wordLength = 5; // set initially to 5

    String name;
    int numSteps; // number of steps to the target word
    boolean visited;

    public Word(String name) {
        this.name = name;
        this.visited = false;
    }

// Get and Set Methods

    public boolean getVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getNumSteps() {
        return this.numSteps;
    }

// Useful Methods

    public void markVisited() {
        this.visited = true;
    }

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