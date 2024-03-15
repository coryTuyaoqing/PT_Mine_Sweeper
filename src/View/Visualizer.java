package View;

import Controller.GameController;
import Controller.GameMode;
import Model.Cell;

import java.util.ArrayList;
import java.util.Scanner;

public class Visualizer {
    private GameController myGameController;

    public Visualizer(GameController gameController){
        myGameController = gameController;
    }

    public void drawPlayingField() {
        System.out.println();
        System.out.println("""
        mines cell: *
        closed cell: 口
        empty cell: nothing
        flag cell: />
        """);
        System.out.print("\t\t");
        ArrayList<ArrayList<Cell>> fieldArray = myGameController.getMyPlayingField().getFieldArray();
        for(int i=0; i<fieldArray.get(0).size();i++){
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println();
        int a = 0;
        for(ArrayList<Cell> cellArrayList: fieldArray){
            System.out.print(a++);
            System.out.print("\t\t");
            for(Cell cell: cellArrayList){
                switch (cell.getCellState()){
                    case covered:
                        System.out.print("口\t");
                        break;
                    case revealed:
                        if(cell.getNeighbourMineNr() == 0){
                            System.out.print("\t");
                        }
                        else{
                            System.out.print(cell.getNeighbourMineNr() + "\t");
                        }
                        break;
                    case flagged:
                        System.out.print("/>\t");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public GameMode getGameMode(){
        return GameMode.values()[Integer.parseInt(getInput())-1];
    }

    public int[] getCustom(){
        int[] custom = new int[3];
        Scanner scanner = new Scanner(getInput());
        for(int i=0; i<custom.length; i++){
            if(scanner.hasNext()){
                custom[i] = scanner.nextInt();
            }
        }
        return custom;
    }

    public String[] getInstruction(){
        String[] instruction = new String[3];
        Scanner scanner = new Scanner(getInput());
        for(int i=0; i<instruction.length; i++){
            if(scanner.hasNext()){
                instruction[i] = scanner.next();
            }
        }
        return instruction;
    }

    public void printHelp(){
        System.out.println("""
                ------MineSweeper instructions set------
                To open a cell, use:
                OPEN <xCoordinate> <yCoordinate>

                To flag a cell, use:
                FLAG <xCoordinate> <yCoordinate>
                """);
    }

    public void printTitle(){
        System.out.println("""
                Welcome to MineSweeper!
                Please choose game mode:
                1. Beginner
                2. Intermediate
                3. Custom
                """);
    }

    public void askForCustom(){
        System.out.println("""
                Please enter:
                <Field Width> <Field Height> <Mines Number>
                """);
    }


}
