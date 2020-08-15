package com.sudoku.engine.map;

import com.sudoku.engine.map.sudoku.Sudoku;

public class SudokuApplication {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        System.out.println(sudoku.getField().size());
        System.out.println(sudoku.getField());
    }
}
