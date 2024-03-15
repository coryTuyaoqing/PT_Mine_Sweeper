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
//        int x = 0;
//        int y = 0;
//        for (int i = 0; i < minesNr; i++) {
//            x = (int) (Math.random() * xDimension);
//            y = (int) (Math.random() * yDimension);
//            if (fieldArray.get(x).get(y) instanceof Bomb) {
//                i--;
//            } else {
//                fieldArray.get(x).set(y, new Bomb(x, y, this));
//            }
//        }
//        for (int i = 0 ; i < xDimension; i++){
//            for (int j = 0; j < yDimension; j++){
//                System.out.println(fieldArray.get(i).get(j));
//            }
//        }
        //test
        for (int i = 0 ; i < yDimension; i++){
            fieldArray.add(new ArrayList<>());
            for (int j = 0; j < xDimension; j++){
                if(j == 3 && i == 2)
                    fieldArray.get(i).add(new Bomb(i, j, this));
                else fieldArray.get(i).add(new EmptyCell(i, j, this));
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
