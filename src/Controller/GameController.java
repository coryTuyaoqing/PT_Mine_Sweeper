package Controller;

import Model.*;
import View.Visualizer;

import java.util.ArrayList;

public class GameController {
    private GameState gameState;
    private Visualizer myVisualizer;
    private PlayingField myPlayingField;
    private ArrayList<ArrayList<CellState>> History;

    public GameController(){
        gameState = GameState.playing;
        myVisualizer = new Visualizer(this);
        myVisualizer.printTitle();
        buildPlayingField();
        History = new ArrayList<>();
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
        switch (action){
            case "help":
            case "HELP":
                myVisualizer.printHelp();
                return;
            case "cheat":
                RevealPlayingField();
                myVisualizer.drawPlayingField();
                return;
            case "CHEAT":
                for(ArrayList<Cell> cellArrayList: myPlayingField.getFieldArray()){
                    for(Cell cell: cellArrayList){
                        if(cell instanceof EmptyCell){
                            cell.setCellState(CellState.revealed);
                        }
                    }
                }
                myVisualizer.drawPlayingField();
                return;
            case "UNCHEAT":
            case "uncheat":
                RecoverPlayingField();
                myVisualizer.drawPlayingField();
                return;
        }
        int xCord = Integer.parseInt(userInstruction[1]);
        int yCord = Integer.parseInt(userInstruction[2]);
        switch (action){
            case "open":
            case "OPEN":
                boolean openResult = openCell(xCord, yCord);
                if(!openResult)
                    gameState = GameState.youLose;
                break;
            case "flag":
            case "FLAG":
                flagCell(xCord,yCord);
                break;
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
    public void judge() { //todo
         if(gameState == GameState.playing){
            //do something to check if the player win ...
            //getEmptyCell
            ArrayList<Cell> emptyCell = new ArrayList<>();
            for(ArrayList<Cell> cellArrayList: myPlayingField.getFieldArray()){
                for(Cell cell: cellArrayList){
                    if(cell instanceof EmptyCell){
                        emptyCell.add(cell);
                    }
                }
            }
            for(Cell cell: emptyCell){
                if(cell.getCellState() != CellState.revealed)
                    return;
            }
            gameState = GameState.win;
        }
    }

    public void buildPlayingField(){
        int xDim = 0;
        int yDim = 0;
        int minesNr = 0;
        switch (myVisualizer.getGameMode()){
            case Beginner:
                xDim = 8;
                yDim = 8;
                minesNr = 10;
                break;
            case Intermediate:
                xDim = 16;
                yDim = 16;
                minesNr = 40;
                break;
            case Hard:
                xDim = 30;
                yDim = 16;
                minesNr = 99;
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

    public void RevealPlayingField(){
        for(int i = 0; i < myPlayingField.getyDimension(); i++) {
            History.add(new ArrayList<>());
            for (int j = 0; j < myPlayingField.getxDimension(); j++) {
                Cell cell = myPlayingField.getFieldArray().get(i).get(j);
                History.get(i).add(cell.getCellState());
                if(cell instanceof Bomb && cell.getCellState() != CellState.flagged) {
                    cell.setCellState(CellState.revealed);
                }
            }
        }
    }

    public void RecoverPlayingField(){
        for(int i = 0; i < myPlayingField.getyDimension(); i++) {
            for (int j = 0; j < myPlayingField.getxDimension(); j++) {
                Cell cell = myPlayingField.getFieldArray().get(i).get(j);
                cell.setCellState(History.get(i).get(j));
            }
        }
        History.clear();
    }


    public void gameSettlement(){
        if(gameState == GameState.youLose){
            RevealPlayingField();
            myVisualizer.drawPlayingField();
            System.out.println("hit a mine!\nyou lose!\ngame over!");
        }
        else if(gameState == GameState.win){
            System.out.println("you win!\ngame over!");
        }
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        while(gameController.getGameState() == GameState.playing){
            gameController.nextStep();
            gameController.judge();
        }
        gameController.gameSettlement();
    }
}
