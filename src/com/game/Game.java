package com.game;

import java.util.Scanner;

public class Game {
    private final Field field;
    private Player player1;
    private Player player2;

    public Game() {
        field = new Field(3);
        player1 = new Player("O");
        player2 = new Player("X");
    }

    private boolean makeMove(int col, int row, Player player) {
        String sign = player.getSign();
        String[][] gameField = field.getField();
        if (gameField[col][row].equals("*")) {
            field.setField(col, row, sign);
            field.showField();
            System.out.println("                           ");
            return true;
        } else {
            System.out.println("cannot put " + player.getSign() + " here!");
            return false;
        }
    }

    private boolean draw() {
        for (String[] fieldTab : field.getField()) {
            for (String item : fieldTab) {
                if (item.equals("*")) {
                    return false;
                }
            }
        }
        return true;
    }

    private Player switchPlayer(int count) {
        if (count % 2 == 0) {
            return player1;
        } else {
            return player2;
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        field.showField();
        int count = 1;
        boolean goodMove;
        while (true) {
            Player player = switchPlayer(count);
            System.out.println(player.getSign() + " turn: ");
            String move = scanner.nextLine();
            String[] split = move.split(" ", 2);
            try {
                String pos1 = split[0];
                String pos2 = split[1];
                int moveCol = Integer.parseInt(pos1) - 1;
                int moveRaw = Integer.parseInt(pos2) - 1;
                if ((moveCol > field.getField().length - 1) || (moveRaw > field.getField().length - 1)) {
                    continue;
                }
                goodMove = makeMove(moveCol, moveRaw, player);
                if(!goodMove){
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
            if (win(player)) {
                System.out.println("Player " + player.getSign() + " wins!");
                System.out.println(count + " moves made");
                break;
            }
            if (draw()) {
                System.out.println("Draw! try again ;)");
                System.out.println(count + " moves made");
                break;
            }
            count++;
        }
    }

    private boolean win(Player player) {
        String[][] gameField = field.getField();
        String sign = player.getSign();
        for (int i = 0; i < gameField.length; i++) {
            int count = 0;
            int count1 = 0;
            if (gameField[i][0].equals(sign) && gameField[i][1].equals(sign) && gameField[i][2].equals(sign)) {
                return true;
            }
            if (gameField[0][i].equals(sign) && gameField[1][i].equals(sign) && gameField[2][i].equals(sign)) {
                return true;
            }
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[j][j].equals(sign)) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
            for (int j = 0; j < gameField.length; j++) {
                if (gameField[j][gameField.length - j - 1].equals(sign)) {
                    count1++;
                }
                if (count1 == 3) {
                    return true;
                }
            }

        }
        return false;
    }
}
