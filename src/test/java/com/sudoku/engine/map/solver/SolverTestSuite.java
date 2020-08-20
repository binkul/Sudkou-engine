package com.sudoku.engine.map.solver;

import com.sudoku.engine.map.sudoku.Data;
import com.sudoku.engine.map.sudoku.Element;
import com.sudoku.engine.map.sudoku.Sudoku;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SolverTestSuite {

    /**
     0 1 0 0 0 3 0 0 0
     0 3 0 0 8 0 0 4 6
     0 0 7 0 0 0 2 0 0
     6 0 0 8 0 1 0 0 0
     0 8 0 0 3 0 0 5 0
     0 0 0 4 0 7 0 0 9
     0 0 6 0 0 0 5 0 0
     5 7 0 0 2 0 0 9 0
     0 0 0 1 0 0 0 7 0
     **/
    @Test
    public void testSolveSudokuStandard() {
        // Given
        Sudoku sudoku = new Sudoku();
        Solver solver = new Solver(sudoku);
        sudoku.getElement(1, 2).setNumber(1);
        sudoku.getElement(1, 6).setNumber(3);

        sudoku.getElement(2, 2).setNumber(3);
        sudoku.getElement(2, 5).setNumber(8);
        sudoku.getElement(2, 8).setNumber(4);
        sudoku.getElement(2, 9).setNumber(6);

        sudoku.getElement(3, 3).setNumber(7);
        sudoku.getElement(3, 7).setNumber(2);

        sudoku.getElement(4, 1).setNumber(6);
        sudoku.getElement(4, 4).setNumber(8);
        sudoku.getElement(4, 6).setNumber(1);

        sudoku.getElement(5, 2).setNumber(8);
        sudoku.getElement(5, 5).setNumber(3);
        sudoku.getElement(5, 8).setNumber(5);

        sudoku.getElement(6, 4).setNumber(4);
        sudoku.getElement(6, 6).setNumber(7);
        sudoku.getElement(6, 9).setNumber(9);

        sudoku.getElement(7, 3).setNumber(6);
        sudoku.getElement(7, 7).setNumber(5);

        sudoku.getElement(8, 1).setNumber(5);
        sudoku.getElement(8, 2).setNumber(7);
        sudoku.getElement(8, 5).setNumber(2);
        sudoku.getElement(8, 8).setNumber(9);

        sudoku.getElement(9, 4).setNumber(1);
        sudoku.getElement(9, 8).setNumber(7);

        // When
        solver.process();
        long size = solver.getSudoku().getField()
                .values()
                .stream()
                .filter(i -> i.getNumber() != Data.EMPTY)
                .map(Element::getNumber)
                .count();

        // Then
        assertThat(81L, is(size));
        System.out.println("Filled position: " + size);
        System.out.println(solver.getSudoku());
    }

    /**
     0 3 0 0 7 0 0 0 0
     6 0 0 1 0 5 0 0 0
     0 9 0 0 0 0 0 0 0
     8 0 0 0 6 0 0 0 3
     0 0 0 8 0 0 0 0 0
     7 0 0 0 0 0 0 0 6
     0 0 0 0 0 0 2 0 0
     0 0 0 4 0 0 0 0 0
     0 0 0 0 0 0 0 0 9
     **/
    @Test
    public void testSolveSudokuBackTrack() {
        // Given
        Sudoku sudoku = new Sudoku();
        Solver solver = new Solver(sudoku);
        sudoku.getElement(1, 2).setNumber(3);
        sudoku.getElement(1, 5).setNumber(7);

        sudoku.getElement(2, 1).setNumber(6);
        sudoku.getElement(2, 4).setNumber(1);
        sudoku.getElement(2, 6).setNumber(5);

        sudoku.getElement(3, 2).setNumber(9);

        sudoku.getElement(4, 1).setNumber(8);
        sudoku.getElement(4, 5).setNumber(6);
        sudoku.getElement(4, 9).setNumber(3);

        sudoku.getElement(5, 4).setNumber(8);

        sudoku.getElement(6, 1).setNumber(7);
        sudoku.getElement(6, 9).setNumber(6);

        sudoku.getElement(7, 7).setNumber(2);

        sudoku.getElement(8, 4).setNumber(4);

        sudoku.getElement(9, 9).setNumber(9);

        // When
        solver.process();
        long size = solver.getSudoku().getField()
                .values()
                .stream()
                .filter(i -> i.getNumber() != Data.EMPTY)
                .map(Element::getNumber)
                .count();

        // Then
        assertThat(81L, is(size));
        System.out.println("Filled position: " + size);
        System.out.println(solver.getSudoku());
    }

    /**
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     **/
    @Test
    public void testSolveEmptySudoku() {
        // Given
        Sudoku sudoku = new Sudoku();
        Solver solver = new Solver(sudoku);

        // When
        solver.process();
        long size = solver.getSudoku().getField()
                .values()
                .stream()
                .filter(i -> i.getNumber() != Data.EMPTY)
                .map(Element::getNumber)
                .count();

        // Then
        assertThat(81L, is(size));
        System.out.println("Filled position: " + size);
        System.out.println(solver.getSudoku());
    }

}
