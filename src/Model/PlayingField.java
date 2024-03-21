package Model;

import java.util.ArrayList;

public class PlayingField {
    private int xDimension;
    private int yDimension;

    private int minesNr;
    private ArrayList<ArrayList<Cell>> fieldArray;

    public PlayingField(int xDimension, int yDimension, int minesNr) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.minesNr = minesNr;
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


    public void RandomMines(){
        //Initialize the fieldArray with EmptyCell objects
        for (int i = 0 ; i < yDimension; i++){
            fieldArray.add(new ArrayList<>());
            for (int j = 0; j < xDimension; j++){
                fieldArray.get(i).add(new EmptyCell(i, j, this));
            }
        }

        // Randomly place the Bomb objects
        for (int i = 0; i < minesNr; i++) {
            int x = (int) (Math.random() * xDimension);
            int y = (int) (Math.random() * yDimension);
            if (fieldArray.get(y).get(x) instanceof Bomb) {
                i--;
            } else {
                fieldArray.get(y).set(x, new Bomb(y, x, this));
            }
        }
    }

    public ArrayList<Cell> getNeighbourCells(Cell cell){
        int xCord = cell.getXCoordinate();
        int yCord = cell.getYCoordinate();
        ArrayList<Cell> neighbourCells = new ArrayList<>();
        for(ArrayList<Cell> cellArrayList: fieldArray){
            for(Cell c: cellArrayList){
                if(c == cell)
                    continue;
                int xC = c.getXCoordinate();
                int yC = c.getYCoordinate();
                if(xC >= xCord-1 && xC <= xCord+1 && yC >= yCord-1 && yC <= yCord+1){
                        neighbourCells.add(c);
                }
            }
        }
        return neighbourCells;
    }
}
