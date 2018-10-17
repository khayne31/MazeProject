/*
* Maze Project
* Kellen Haynes
* */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Maze {
    private int[][] maze;
    private  int[] currentPos,previousPos;
    private boolean escaped;
    private ArrayList<int[]> positonHistory = new ArrayList<>();
    private  Map<String, int[]> directionMap = new HashMap<>();
    private CompasRose compassRose = new CompasRose();
    private String currenHeading = "South";

    public Maze(int[][] maze) {
        this.maze = maze;
        currentPos = new int[]{0,2};
        previousPos = currentPos;
        /*Maps the different headings to (x,y) vectors to be added to the current position*/
        directionMap.put("North",new int[]{0,-1});
        directionMap.put("West",new int[]{-1,0});
        directionMap.put("South",new int[]{0,1});
        directionMap.put("East",new int[]{1,0});

    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public boolean getEscaped(){
        return escaped;
    }

    public ArrayList<int[]> getPositonHistory() {
        return positonHistory;
    }

    public String getCurrenHeading() {
        return currenHeading;
    }

    /*prints out the maze for the commandline program*/
    public void displayMaze(){
        System.out.print("\n");
        for(int i = 0; i< maze.length; i++){
            for(int j = 0; j<maze[0].length; j++){
                System.out.print(" "+(maze[i][j] == 0 ? "@" :(maze[i][j] == 2 ? "*":(maze[i][j] == 3 ? "$":" "))) + "");
            }
            System.out.print("\n");
        }
    }


     public void nextStep(){
        /*
        * tests if the character has already escaped. If it has it skips all further instructions, if not it continues with the
        * next step algorithm.
        * */
        if(!escaped){
            /*
            * looks the the compass rose class and gets the index of the direction to the right of its current heading. If its going
            * South it will return West.
            * */
            int testHeading = compassRose.getRightDirection(compassRose.directions.indexOf(currenHeading));
            int[] newPos;
            /* i<4 so it can go through all four possible directions*/
            A: for(int i = 0; i<4; i++){

                /*
                essentially the new position is an int[] with the direction vectors added to the currentPosition according to the
                current heading and the direction to its right.
                * -testHeading -> the index of the direction right of the currentHeading(West for South)
                * -compassRose.directions.get(testHeading) -> returns the direction(String) of the desired newPosition
                * -directionMap.get(...) -> a map that corresponds the direction(String) with the position vectors(int[])
                *  each vector has a corresponding (x,y) point
                * -directionMap.get(...)[x]+currentPos[x] adds the corresponding vector compontns together to get a new position
                * */
                newPos = new int[]{directionMap.get(compassRose.directions.get(testHeading))[1]+currentPos[0],
                        directionMap.get(compassRose.directions.get(testHeading))[0]+currentPos[1]
                };
                /*
                * tests what is at the newPosition. If it != 0(wall) then it
                * 1) updates the position history
                * 2)sets the previous position to the current position
                * 3)sets the current position to the new position, indicating it has moved forwards
                * 4)marks its previous position with a 2, showing that it has been in that square before
                * 5)updates the maze with its current position, marking it with a 3
                * 6)updates its currentHeading
                * */
                B: if(maze[newPos[0]][newPos[1]] != 0){
                    positonHistory.add(currentPos);
                    previousPos=currentPos;
                    currentPos = newPos;
                    maze[previousPos[0]][previousPos[1]] = 2;
                    maze[currentPos[0]][currentPos[1]] = 3;
                    currenHeading = compassRose.directions.get(testHeading);
                    /*
                    * tests to see if the character has escaped the maze. If it has it sets the escaped variable to true, and adds
                    * its currentPosition to the position history
                    * */

                    if(isEscaped()){
                        escaped = true;
                        positonHistory.add(currentPos);
                    }
                    /*
                    * if it has succeeded in moving forward it will break from the for loop
                    * */
                    break A;
                }
                else{
                    /*
                    *if the newPos shows a wall it will change the direction to move left of the right direction.
                    * if the original heading was south it will change the testHeading from "West" back to "South" and then "East"
                    * continuing counterclockwise until a suitable spot is found
                    * */
                    testHeading = compassRose.getLeftDirection(testHeading);
                }
            }
        }

    }
    private boolean isEscaped(){
         /*
         * determines if the character has escaped. Escape is determined if the character is on the boarder(not including the start)
         * */
         return (currentPos[1] == 0 || currentPos[1] == maze[0].length-1
                 || (currentPos[1] != 2 && currentPos[0]==0) || currentPos[0] == maze.length-1);

    }

    public void findEscape(){
        /*automatically runs through the whole maze, calling nextStep() while the maze has yet to be solved*/
        while(!escaped){
            nextStep();
        }
    }

    /*resets the maze back to its original position.*/
    public void reset(){
        for(int i=0; i < maze.length; i++){
            for(int j=0; j< maze[0].length; j++){
                if(maze[i][j] != 0)
                    maze[i][j] = 1;
            }
        }
        currentPos = new int[]{0,2};
        escaped = false;
    }

}
