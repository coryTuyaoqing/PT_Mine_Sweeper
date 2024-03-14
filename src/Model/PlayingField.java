package Model;

import java.util.ArrayList;

public class PlayingField {
    private int xDimension;
    private int yDimension;

    private int minesNr;
    private ArrayList<ArrayList<Cell>> fieldArray;
    private GameState gameState;

    public PlayingField(int xDimension, int yDimension, int minesNr) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.minesNr = minesNr;
        this.gameState = GameState.playing;
        this.fieldArray = new ArrayList<>();
    }

    public int getxDimension() {
        return xDimension;
    }

    public int getyDimension() {
        return yDimension;
    }

    public int getMinesNr() {
        return minesNr;
    }

    public ArrayList<ArrayList<Cell>> getFieldArray() {
        return fieldArray;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int calculateNeighbourMinesNr(Cell cell){ // implementation needed
        return 0;
    }
}
