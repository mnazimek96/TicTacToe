package com.company;

import java.util.Scanner;

public class Game {
    private final Field field;

    public Game(){
        field = new Field(3);
    }

    private void makeMoveX(int col, int row){
        String[][] gameField = field.getField();
            if (gameField[col][row].equals("*")){
                field.setField(col, row, "X");
                field.showField();
                System.out.println("                           ");
            } else {
                System.out.println("It's busy");
            }
    }

    private void makeMoveO(int col, int row){
        String[][] gameField = field.getField();
            if (gameField[col][row].equals("*")){
                field.setField(col, row, "O");
                field.showField();
                System.out.println("                           ");
            } else {
                System.out.println("It's busy");
            }


    }

    private boolean draw(){
        for(String[] fieldTab : field.getField()){
            for(String item : fieldTab){
                if (item.equals("*")){
                    return false;
                }
            }
        }
        return true;
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        field.showField();
        while (true){
            System.out.println("O turn: ");
            String moveO = scanner.nextLine();
            String[] splitO = moveO.split(" ", 2);
            try {
                String pos1O = splitO[0];
                String pos2O = splitO[1];
                int moveColO = Integer.parseInt(pos1O) - 1;
                int moveRawO = Integer.parseInt(pos2O) - 1;
                if((moveColO > field.getField().length - 1) || (moveRawO > field.getField().length - 1)){
                    continue;
                }
                makeMoveO(moveColO, moveRawO);
            } catch (Exception e){
                continue;
            }
            if (winO()){
                System.out.println("Player O wins!");
                break;
            }
            if(draw()){
                System.out.println("Draw! try again ;)");
                break;
            }
            System.out.println("X turn: ");
            String moveX = scanner.nextLine();
            String[] splitX = moveX.split(" ", 2);
            try {
                String pos1X = splitX[0];
                String pos2X = splitX[1];
                int moveColX = Integer.parseInt(pos1X) - 1;
                int moveRawX = Integer.parseInt(pos2X) - 1;
                if((moveColX > field.getField().length - 1) || (moveRawX > field.getField().length - 1)){
                    continue;
                }
                makeMoveX(moveColX, moveRawX);
            } catch (Exception e){
                continue;
            }
            if (winX()){
                System.out.println("Player X wins!");
                break;
            }
            if(draw()){
                System.out.println("Draw! try again ;)");
                break;
            }
        }
    }

    private boolean winX(){
        String[][] gameField = field.getField();

        for (int i=0; i < gameField.length; i++){
            int count = 0;
            int count1 = 0;
            if(gameField[i][0].equals("X") && gameField[i][1].equals("X") && gameField[i][2].equals("X")){
                return true;
            }
            if (gameField[0][i].equals("X") && gameField[1][i].equals("X") && gameField[2][i].equals("X")){
                return true;
            }
            for(int j=0; j < gameField.length; j++){
                if (gameField[j][j].equals("X")){
                    count++;
                    if(count == 3){
                        return true;
                    }
                }
            }
            for (int j=0; j < gameField.length; j++){
                if (gameField[j][gameField.length - j - 1].equals("X")){
                    count1++;
                }
                if(count1 == 3){
                    return true;
                }
            }

        }
        return false;
    }

    private boolean winO() {
        String[][] gameField = field.getField();

        for (int i = 0; i < gameField.length; i++) {
            int count = 0;
            int count1 = 0;
            if (gameField[i][0].equals("O") && gameField[i][1].equals("O") && gameField[i][2].equals("O")) {
                return true;
            }
            if (gameField[0][i].equals("O") && gameField[1][i].equals("O") && gameField[2][i].equals("O")) {
                return true;
            }
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[j][j].equals("O")) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
            for (int j=0; j < gameField.length; j++){
                if (gameField[j][gameField.length - j - 1].equals("O")){
                    count1++;
                }
                if(count1 == 3){
                    return true;
                }
            }
        }
        return false;
    }
}
