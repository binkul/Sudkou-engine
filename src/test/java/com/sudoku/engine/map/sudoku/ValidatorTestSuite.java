package com.sudoku.engine.map.sudoku;

import com.sudoku.engine.map.solver.Solver;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTestSuite {

    @Test
    public void testIsAllFilled()  {
        // Given
        Sudoku sudoku = new Sudoku();
        sudoku.getField().values().forEach(i -> i.setNumber(2));

        // When
        boolean filled = Validator.isFilled(sudoku);

        // Then
        assertTrue(filled);
    }

    @Test
    public void testIsNotAllFilled()  {
        // Given
        Sudoku sudoku = new Sudoku();

        sudoku.getField()
                .entrySet()
                .stream()
                .filter(i -> !i.getKey().equals(new Position(4, 4)))
                .forEach(i -> i.getValue().setNumber(2));

        // When
        boolean filled = Validator.isFilled(sudoku);

        // Then
        assertFalse(filled);
    }

    @Test
    public void testIsCollisionInRow() {
        // Given
        Sudoku sudoku = new Sudoku();
        sudoku.getElement(2,2).setNumber(9);
        sudoku.getElement(2,9).setNumber(9);

        // When
        boolean collision = Validator.isCollision(sudoku);

        // Then
        assertTrue(collision);
    }

    @Test
    public void testIsCollisionInColumn() {
        // Given
        Sudoku sudoku = new Sudoku();
        sudoku.getElement(2,2).setNumber(9);
        sudoku.getElement(7,2).setNumber(9);

        // When
        boolean collision = Validator.isCollision(sudoku);

        // Then
        assertTrue(collision);
    }

    @Test
    public void testIsCollisionInSection() {
        // Given
        Sudoku sudoku = new Sudoku();
        sudoku.getElement(5,5).setNumber(9);
        sudoku.getElement(6,6).setNumber(9);

        // When
        boolean collision = Validator.isCollision(sudoku);

        // Then
        assertTrue(collision);
    }

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
    public void testNoCollisionInBeforeAndAfterSolverRun() {
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
        boolean collisionBefore = Validator.isCollision(sudoku);
        solver.process();
        boolean collisionAfter = Validator.isCollision(sudoku);

        // Then
        System.out.println(sudoku);
        assertFalse(collisionBefore);
        assertFalse(collisionAfter);
    }

}
