package Model;

import Controller.GameState;

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
        int x = 0;
        int y = 0;
        for (int i = 0; i < minesNr; i++) {
            x = (int) (Math.random() * xDimension);
            y = (int) (Math.random() * yDimension);
            if (fieldArray.get(x).get(y) instanceof Bomb) {
                i--;
            } else {
                fieldArray.get(x).set(y, new Bomb(x, y, this));
            }
        }
        for (int i = 0 ; i < xDimension; i++){
            for (int j = 0; j < yDimension; j++){
                System.out.println(fieldArray.get(i).get(j));
            }
        }
    }

    public int calculateNeighbourMinesNr(Cell cell){
        int Celx = cell.getxCoordinate();
        int Cely = cell.getyCoordinate();
        int count = 0;
        for (int i = Celx - 1; i <= Celx + 1; i++){
            for (int j = Cely - 1; j <= Cely + 1; j++){
                if (i >= 0 && i < xDimension && j >= 0 && j < yDimension){
                    if (fieldArray.get(i).get(j) instanceof Bomb){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
