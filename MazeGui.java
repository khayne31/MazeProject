/*
* Maze Project
* Kellen Haynes
* */
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;


public class MazeGui extends Application {
    private Image flag =  new Image("/imgs/Flag.jpg");
    private Image wall = new  Image("/imgs/Wall.jpg");
    private Image water = new Image("/imgs/Water3.jpg");
    private Image dot = new Image("/imgs/Buoy.jpg");
    private Image boat = new Image("/imgs/Boat2.jpg");
    private ImageView animationNode = new ImageView(boat);
    private PathTransition pt = new PathTransition();
    private ArrayList<ImageView> imageViewHistory = new ArrayList<>();
    /*
     new int[]{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     new int[]{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0},
     new int[]{0,0,0,1,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0},
     new int[]{0,0,0,1,1,1,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0},
     new int[]{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
     new int[]{0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,1},
     new int[]{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0},
     new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,1,0},
     new int[]{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0},
     new int[]{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0},
     new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,0,1,0},
     new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},
     new int[]{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,1,0},
     new int[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0},
     new int[]{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,0},
     new int[]{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
     new int[]{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
     * */
    private int[][] mazeArray  = new int[][]{
            new int[]{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            new int[]{0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
            new int[]{0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0},
            new int[]{0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
            new int[]{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            new int[]{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    private int height = 30;
    private int width = 30;
    private Pane pane = new Pane();

    private Maze maze = new Maze(mazeArray);
    Path path= new Path();

    @Override
    public void start(Stage primaryStage) throws Exception {
        int windowHeight = mazeArray.length*30;
        int windowwidth = mazeArray[0].length*30;

        displayGui();


        Button btstep = new Button("Next Step");
        Button btanimate = new Button("Animate");
        Button btshow = new Button("Show Path");
        Button btreset = new Button("Reset");
        Button btquit = new Button("Quit");
        btstep.setAlignment(Pos.CENTER);
        BorderPane bp = new BorderPane();
        HBox hBox = new HBox(btstep, btanimate, btshow, btreset, btquit);
        hBox.setAlignment(Pos.CENTER);
        bp.setCenter(pane);
        bp.setBottom(hBox);


        /*This lambda function is called when the "Next Text" button is clicked by the user and basically moves the maze along
         * one step, getting closer to the exit. When this button is clicked the Animation button is disabled.
         * */
        btstep.setOnAction(e -> {
            maze.nextStep();
            displayGui();
            btanimate.setDisable(true);
        });

        /*This lambda function is called when the "Animate" button is clicked by the user and basically shows an animation of the
         * boat finishing the maze. When this button is clicked the Next Step and Show button are both disabled. The Animation
         * button can be clicked again to restart the animation from he beginning.*/
        btanimate.setOnAction(e->{
            btstep.setDisable(true);
            btshow.setDisable(true);
            animationSetup();
            pt.playFromStart();

            btanimate.setOnAction(e1 ->{
                btstep.setDisable(true);
                btshow.setDisable(true);
                pt.playFromStart();

            });
        });

        /*This lambda function is called when the "Show" button is clicked by the user and basically shows path the boat would have
         * to take to finish the maze. When this button is pressed both the Next Step and the Animate buttons are disabled
         * */
        btshow.setOnAction(e ->{
            btstep.setDisable(true);
            btanimate.setDisable(true);
            Maze maze2 = new Maze(mazeArray);
            maze2.findEscape();
            for(int i=0; i<maze2.getMaze().length; i++){
                for(int j = 0; j <maze2.getMaze()[0].length; j++){
                    ImageView imgvw = new ImageView();
                    if(maze2.getMaze()[i][j] == 2){
                        imgvw = new ImageView(dot);
                    }
                    if(((i == 0 && j != 2) || i == maze2.getMaze().length-1 || j == 0 || j == maze2.getMaze()[0].length) &&
                            maze2.getMaze()[i][j] == 3){
                        imgvw = new ImageView(boat);
                        imgvw.setRotate(180);
                    }
                    imgvw.setFitHeight(height);
                    imgvw.setFitWidth(width);
                    imgvw.setX(j*30);
                    imgvw.setY(i*30);
                    pane.getChildren().add(imgvw);

                }
            }
        });

        /*This lambda function is called when the "Reset" button is clicked by the user and resets the board. When the
         * button is pressed all buttons are enabled and the board returns to its orginal state.
         * */
        btreset.setOnAction(e -> {
            pt = new PathTransition();
            maze.reset();
            displayGui();
            animationSetup();
            btanimate.setDisable(false);
            btshow.setDisable(false);
            btstep.setDisable(false);
        });
        /*quits the program*/
        btquit.setOnAction(e->{
            primaryStage.close();
        });
        
        Scene scene = new Scene(bp, windowwidth,windowHeight+ 17/*+17 for buttons*/);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Maze");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /*This function sets the path for the path animation*/
    private void setPath(){
        Maze tempMazeArray = new Maze(mazeArray);
        tempMazeArray.findEscape();
        for(int[] list: tempMazeArray.getPositonHistory()){
            ImageView  tempImgViw =  imageViewHistory.get((list[0])*maze.getMaze()[0].length+list[1]);
            path.getElements().add(new LineTo(tempImgViw.getX()+height/2, tempImgViw.getY()+width/2));
        }
    }

    /*This function displays each imageview in order to display the maze visually. Goes through the maze and tests to see if each
     * element is a 0(wall), 1(open path), 2(previous spot), or 3(current position)*/
    private  void displayGui(){
        for(int i=0; i<maze.getMaze().length; i++){
            for(int j = 0; j <maze.getMaze()[0].length; j++){
                ImageView imgvw = new ImageView();
                if(maze.getMaze()[i][j] == 0){
                    imgvw = new ImageView(wall);

                }
                else if(maze.getMaze()[i][j] == 1){
                    if((i == 0 && j!= 2) || i == maze.getMaze().length-1 || j == 0 || j == maze.getMaze()[0].length-1)
                        imgvw = new ImageView(flag);
                    else
                        imgvw = new ImageView(water);
                }
                else if(maze.getMaze()[i][j]== 3){
                    imgvw = new ImageView(boat);
                    /*Changes the orientation of the imagview in accordance to its orientation in the maze*/
                    if(maze.getCurrenHeading().equals("South"))
                        imgvw.setRotate(180);
                    else if(maze.getCurrenHeading().equals("West"))
                        imgvw.setRotate(270);
                    else if(maze.getCurrenHeading().equals("North"))
                        imgvw.setRotate(0);
                    else if(maze.getCurrenHeading().equals("East"))
                        imgvw.setRotate(90);
                }
                else if(maze.getMaze()[i][j] == 2){
                    imgvw = new ImageView(dot);
                }

                imgvw.setFitHeight(height);
                imgvw.setFitWidth(width);
                imgvw.setX(j*30);
                imgvw.setY(i*30);
                imageViewHistory.add(imgvw);
                pane.getChildren().add(imgvw);
            }
        }
    }

    /* This sets up the path transition. The node, size of the node, sets up the path, the duration, and the orientation type.*/
    private void animationSetup(){

        ImageView mazeArray = (ImageView) pane.getChildren().get(2);
        path.getElements().add(new MoveTo(mazeArray.getX()+height/2, mazeArray.getY()-width/2));
        maze.findEscape();
        setPath();
        maze.reset();
        
        animationNode = new ImageView(boat);;
        animationNode.setY(-30);
        animationNode.setFitWidth(30);
        animationNode.setFitHeight(30);
        animationNode.setRotate(180);
        pane.getChildren().add(animationNode);
        
        pt = new PathTransition();
        pt.setPath(path);
        pt.setNode(animationNode);
        pt.setDuration(Duration.millis(30000));
        pt.setAutoReverse(false);
        pt.setOrientation(PathTransition.OrientationType.NONE);
    }
    public static void main(String[] args){launch(args);}
}
