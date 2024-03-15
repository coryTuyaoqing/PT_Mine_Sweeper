package Controller;

import Model.CellState;
import Model.PlayingField;
import View.Visualizer;

import java.util.Scanner;

public class GameController {
    private GameState gameState;
    private Visualizer myVisualizer;
    private PlayingField myPlayingField;

    public GameController(){
        myVisualizer = new Visualizer();
        myVisualizer.printTitle();
        buildPlayingField();
    }
    public PlayingField getMyPlayingField() {
        return myPlayingField;
    }
    public GameState getGameState() {
        return gameState;
    }
    public Visualizer getMyVisualizer(){
        return myVisualizer;
    }


    public void nextStep(){
        String[] userInstruction = myVisualizer.getInstruction();
        String action = userInstruction[0];
        int xCord = Integer.parseInt(userInstruction[1]);
        int yCord = Integer.parseInt(userInstruction[2]);
        switch (action){
            case "OPEN":
                open(xCord, yCord);
                break;
            case "FLAG":
                flag(xCord,yCord);
        }
    }

    public void flag(int xCord, int yCord){
        myPlayingField.getFieldArray().get(xCord).get(yCord).setCellState(CellState.flaged);
    }

    public void open(int xCord, int yCord){
        myPlayingField.getFieldArray().get(xCord).get(yCord).setCellState(CellState.revealed);
    }
    public void judge() {
    }

    public void buildPlayingField(){
        int xDim = 0;
        int yDim = 0;
        int minesNr = 0;
        switch (myVisualizer.getGameMode()){
            case Beginner:
                xDim = 9;
                yDim = 9;
                minesNr = 10;
                break;
            case Intermediate:
                xDim = 16;
                yDim = 16;
                minesNr = 40;
                break;
            case Custom:
                myVisualizer.askForCustom();
                int[] custom = myVisualizer.getCustom();
                xDim = custom[0];
                yDim = custom[1];
                minesNr = custom[2];
                break;
            default: //default beginner mode
                xDim = 9;
                yDim = 9;
                minesNr = 10;
        }
//        System.out.println(xDim);
//        System.out.println(yDim);
//        System.out.println(minesNr);
        myPlayingField = new PlayingField(xDim, yDim, minesNr);
        myVisualizer.drawPlayingField();
    }


    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.nextStep();

//        while(gameController.getGameState() == GameState.playing){
////            visualizer.drawPlayingField();
////            visualizer.getInput();
////            visualizer.parseUserInput();
//            gameController.updateGame();
//            gameController.judge();
//        }
    }
}
