package Model;

import java.util.ArrayList;

public class Cell {
    private int neighbourMineNr;
    private int xCoordinate;
    private int yCoordinate;
    private CellState cellState;

    private PlayingField myPlayingView;

    public Cell(int xCoordinate, int yCoordinate, PlayingField myPlayingView) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.myPlayingView = myPlayingView;
        this.cellState = CellState.covered;
    }

    public void setNeighbourMineNr(int neighbourMineNr) {
        this.neighbourMineNr = neighbourMineNr;
    }

    public void setCellState(CellState cellState){
        this.cellState = cellState;
    }

    public int getNeighbourMineNr() {
        return neighbourMineNr;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public CellState getCellState() {
        return cellState;
    }

    public PlayingField getMyPlayingView() {
        return myPlayingView;
    }
    public void calculateNeighbourMinesNr(){
        ArrayList<Cell> neighbourMines = new ArrayList<>();
        for(Cell cell: myPlayingView.getNeighbourCells(this)){
            if(cell.getClass().equals(Bomb.class)){
                neighbourMines.add(cell);
            }
        }
        setNeighbourMineNr(neighbourMines.size());
    }
    public boolean open(){
        return false;
    }

    public void flag(){
        if(cellState == CellState.covered){
            setCellState(CellState.flagged);
        }
        else if(cellState == CellState.flagged){
            setCellState(CellState.covered);
        }
    }
}
