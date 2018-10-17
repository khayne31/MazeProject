/*
* Maze Project
* Kellen Haynes
* */
import java.util.ArrayList;
import java.util.Arrays;

public class CompasRose {
    /**
    * the purpose of this map is to create and easy reference for the headings for the vector map in the Maze class
    * */
    public final ArrayList<String> directions = new ArrayList(Arrays.asList("West", "North", "East", "South"));
    private String currentHeading = "South";
    private String newHeading;

    public CompasRose() {
    }

    public String getNewheading() {
        return newHeading;
    }

    public void setNewheading(String newheading) {
        this.newHeading = newheading;
    }

    public String getCurrentHeading() {
        return currentHeading;
    }

    public void setCurrentHeading(String currentHeading) {
        this.currentHeading = currentHeading;
    }

    /**
    *Both the direction methods make the ArrayList() essentially circular. Calling getRightDirection() will get the index to the
    * right of the current index, except in the care when the index is on the end, then the index returned is 0. The same principle
    * takes place with getLeftDirection except for the 0th index returns 3.
    *
    * */
    public int getRightDirection(int i){
        int returnValue = i;
            if(returnValue== 3)
                returnValue = 0;
            else
                returnValue++;

        return  returnValue;

    }

    public int getLeftDirection(int i){
        int returnValue = i;
        if(returnValue != -1){
            if(returnValue== 0)
                returnValue = 3;
            else
                returnValue--;
        }
        return  returnValue;
    }
}
