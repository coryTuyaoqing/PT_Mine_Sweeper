package Controller;

import Model.*;
import View.Visualizer;

public class GameController {
    private GameState gameState;
    private Visualizer myVisualizer;
    private PlayingField myPlayingField;

    public GameController(){
        gameState = GameState.playing;
        myVisualizer = new Visualizer(this);
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
                boolean openResult = openCell(xCord, yCord);
                if(!openResult)
                    gameState = GameState.youLose;
                break;
            case "FLAG":
                flagCell(xCord,yCord);
        }
        myVisualizer.drawPlayingField();
    }

    public void flagCell(int xCord, int yCord){
        Cell cell = myPlayingField.getFieldArray().get(yCord).get(xCord);
        cell.flag();
    }

    public boolean openCell(int xCord, int yCord){
        Cell cell = myPlayingField.getFieldArray().get(yCord).get(xCord);
        return cell.open();
    }
    public void judge() {
        if(gameState == GameState.youLose){
            showAllMines();
        }
        else if(gameState == GameState.playing){
            //do something to check if the player win ...
        }
    }

    private void showAllMines() {
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
        myPlayingField = new PlayingField(xDim, yDim, minesNr);
        myPlayingField.RandomMines();
        myVisualizer.drawPlayingField();
    }


    public static void main(String[] args) {
        GameController gameController = new GameController();
        while(gameController.getGameState() == GameState.playing){
            gameController.nextStep();
            gameController.judge();
        }
    }
}
