package Model;

import java.util.ArrayList;

public class EmptyCell extends Cell{
    public EmptyCell(int xCoordinate, int yCoordinate, PlayingField myPlayingView) {
        super(xCoordinate, yCoordinate, myPlayingView);
    }

    @Override
    public boolean open() {
        setCellState(CellState.revealed);
        calculateNeighbourMinesNr();
        if(getNeighbourMineNr() == 0){
            for(Cell cell: getMyPlayingView().getNeighbourCells(this)){
                if(cell.getCellState() == CellState.covered){
                    cell.open();
                }
            }
        }
        return true;
    }
}
