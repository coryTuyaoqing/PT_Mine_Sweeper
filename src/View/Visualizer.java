package View;

import Controller.GameController;
import Controller.GameMode;

import java.util.Scanner;

public class Visualizer {
    private GameController myGameController;

    public void drawPlayingField() {

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
