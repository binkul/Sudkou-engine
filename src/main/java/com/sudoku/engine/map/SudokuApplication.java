package com.sudoku.engine.map;

import com.sudoku.engine.map.sudoku.Position;

public class SudokuApplication {

    public static void main(String[] args) {

        Position position1 = new Position(2, 2);
        Position position2 = new Position(2, 4);

        System.out.println(position1.compareTo(position2));
    }
}
