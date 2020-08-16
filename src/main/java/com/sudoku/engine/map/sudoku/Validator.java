package com.sudoku.engine.map.sudoku;

public class Validator {

    public static void validateRow(int row) {
        if (row < 1 || row > Data.SIZE) throw new IllegalArgumentException("Row should be between 1-9");
    }

    public static void validateColumn(int column) {
        if (column < 1 || column > Data.SIZE) throw new IllegalArgumentException("Column should be between 1-9");
    }

    public static void validateNumber(int number) {
        if (number < 1 || number > Data.MAX_NUMBER) throw new IllegalArgumentException("Number should be between 1-9");
    }
}
