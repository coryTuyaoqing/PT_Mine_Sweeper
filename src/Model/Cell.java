package Model;

public class Cell {
    private int neighbourMineNr;
    private int xCoordinate;
    private int yCoordinate;
    private CellState cellState;

    private String type;

    private PlayingField myPlayingView;

    public Cell(int xCoordinate, int yCoordinate, PlayingField myPlayingView) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.myPlayingView = myPlayingView;
        this.cellState = CellState.coverd;
    }

    public int setNeighbourMineNr() {
        neighbourMineNr = myPlayingView.calculateNeighbourMinesNr(this);
        return neighbourMineNr;
    }

    public void setCellState(CellState cellState){
        this.cellState = cellState;
    }

    public int getNeighbourMineNr() {
        return neighbourMineNr;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public CellState getCellState() {
        return cellState;
    }

    public String getType(){
        return type;
    }
}
