package com.game;


import java.util.Arrays;

public class Field {

    private final int dimensions;
    private final String[][] field;

    public Field(int dimensions){
        this.dimensions = dimensions;
         field = new String[dimensions][dimensions];

        for (int i=0; i < dimensions; i++){
            Arrays.fill(field[i],"*");
        }
    }

    public void showField(){
        for (String[] item : field){
            System.out.println(Arrays.toString(item));
        }
    }

    public void setField(int col, int row, String sign) {
        field[col][row] = sign;
    }

    public String[][] getField() {
        return field;
    }
}
