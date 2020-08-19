package com.sudoku.engine.map.solver;

import com.sudoku.engine.map.solver.algorithm.Result;
import com.sudoku.engine.map.solver.algorithm.SolverSingle;
import com.sudoku.engine.map.sudoku.Element;
import com.sudoku.engine.map.sudoku.Sudoku;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SolverSingleTestSuite extends SolverSingle {

    public SolverSingleTestSuite() {

    }

    @Test
    public void testGetAllNumbersFromSudokuSection() {
        // Given
        Sudoku sudoku = new Sudoku();
        SolverSingle solverSingle = new SolverSingle();
        sudoku.getElement(2, 4).setNumber(2);
        sudoku.getElement(3, 4).setNumber(5);
        sudoku.getElement(4, 5).setNumber(7);
        sudoku.getElement(6, 6).setNumber(8);
        sudoku.getElement(5, 1).setNumber(1);
        sudoku.getElement(3, 5).setNumber(3);

        // When
        Set<Integer> numbers = solverSingle.getNumbersFromSudokuSection(sudoku, 5, 4);

        // Then
        assertTrue(numbers.contains(1));
        assertTrue(numbers.contains(2));
        assertTrue(numbers.contains(5));
        assertTrue(numbers.contains(7));
        assertTrue(numbers.contains(8));
        assertFalse(numbers.contains(3));
    }

    @Test
    public void testFillSingleSudokuPosition() {
        // Given
        Sudoku sudoku = new Sudoku();
        SolverSingle solverSingle = new SolverSingle();
        sudoku.getElement(6, 1).setNumber(1);
        sudoku.getElement(6, 2).setNumber(2);
        sudoku.getElement(6, 3).setNumber(3);
        sudoku.getElement(6, 4).setNumber(4);
        sudoku.getElement(6, 6).setNumber(6);
        sudoku.getElement(6, 7).setNumber(7);
        sudoku.getElement(6, 8).setNumber(8);
        sudoku.getElement(6, 9).setNumber(9);

        // When
        Result result = process(sudoku, 6, 5);
        int number = sudoku.getElement(6, 5).getNumber();

        // Then
        assertEquals(Result.ADDED, result);
        assertEquals(5, number);
    }

    @Test
    public void testNotFillSingleSudokuPosition() {
        // Given
        Sudoku sudoku = new Sudoku();
        sudoku.getElement(6, 1).setNumber(1);
        sudoku.getElement(6, 2).setNumber(2);
        sudoku.getElement(6, 3).setNumber(3);
        sudoku.getElement(6, 4).setNumber(4);
        sudoku.getElement(6, 6).setNumber(6);
        sudoku.getElement(6, 7).setNumber(7);
        sudoku.getElement(6, 8).setNumber(8);

        // When
        Result result = process(sudoku, 6, 5);
        int number = sudoku.getElement(6, 5).getNumber();
        Element element = sudoku.getElement(6, 5);

        // Then
        assertThat(Result.NONE, is(result));
        assertEquals(0, number);
        assertEquals(2, element.getCandidates().size());
        assertEquals((Integer)5, element.getCandidates().get(0));
        assertEquals((Integer)9, element.getCandidates().get(1));
    }
}
