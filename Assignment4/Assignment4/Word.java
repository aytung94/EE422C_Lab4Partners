/**
 Word Class
 Solves EE422C programming assignment #4
 @author Dylan Keeton (DK23765), Alvin Tung (ayt243)
 @version 1.00 2016-02-025
 */

package Assignment4;

public class Word {

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
    
    public void setParent(String parent) {
        this.parent = parent;
    }
    
// Useful Methods

    public void markVisited() {
        this.visited = true;
    }

    public void cleanWord() {
        this.parent = null; 
        this.visited = false;
    }
}