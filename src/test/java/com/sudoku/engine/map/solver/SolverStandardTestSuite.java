package com.sudoku.engine.map.solver;

import org.junit.Test;


public class SolverStandardTestSuite {

    /**
     0 0 0 0 0 0 0 0 0
     1 2 3 0 5 6 0 0 9
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 7 0
     0 0 0 0 0 0 0 0 0
     0 0 0 0 0 0 0 0 0
     0 0 0 3 0 0 7 0 0

     in position (2,4) should put number 7
     **/
    @Test
    public void testStandardAlgorithm() {
        // Given
/*
        Sudoku sudoku = new Sudoku();
        SolverStandard solverStandard = new SolverStandard();
        sudoku.getElement(2, 1).setNumber(1);
        sudoku.getElement(2, 2).setNumber(2);
        sudoku.getElement(2, 3).setNumber(3);
        sudoku.getElement(2, 5).setNumber(5);
        sudoku.getElement(2, 6).setNumber(6);
        sudoku.getElement(2, 9).setNumber(9);

        sudoku.getElement(9, 4).setNumber(3);
        sudoku.getElement(9, 7).setNumber(7);
        sudoku.getElement(6, 8).setNumber(7);

        // When
        solverStandard.fillNumbers(sudoku, 2, 8);
        solverStandard.fillNumbers(sudoku, 2, 7);
        solverStandard.fillNumbers(sudoku, 2, 4);
        Element elementOne = sudoku.getElement(2, 8);
        Element elementTwo = sudoku.getElement(2, 7);
        Element elementThree = sudoku.getElement(2, 4);

        // Then
        assertThat(7, is(elementThree.getNumber()));
        assertThat(2, is(elementOne.getCandidatesSize()));
        assertThat(2, is(elementTwo.getCandidatesSize()));
        assertThat(3, is(elementThree.getCandidatesSize()));
        assertThat(4, is(elementOne.getCandidates().get(0)));
        assertThat(8, is(elementOne.getCandidates().get(1)));
        assertThat(4, is(elementTwo.getCandidates().get(0)));
        assertThat(8, is(elementTwo.getCandidates().get(1)));
        assertThat(4, is(elementThree.getCandidates().get(0)));
        assertThat(7, is(elementThree.getCandidates().get(1)));
        assertThat(8, is(elementThree.getCandidates().get(2)));
*/
    }
}
