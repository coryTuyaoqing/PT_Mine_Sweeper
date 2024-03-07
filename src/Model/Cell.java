package Model;

public class Cell {
    private int neighboorMineNr;
    private int xCoordinate;
    private int yCoordinate;
    private CellState cellState;

    private PlayingView myPlayingView;

    public Cell(int xCoordinate, int yCoordinate, PlayingView myPlayingView) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.myPlayingView = myPlayingView;
        this.cellState = CellState.coverd;
        this.neighboorMineNr = myPlayingView.calculateNeighbourMinesNr(this);
    }
}
