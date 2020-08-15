package com.sudoku.engine.map.sudoku;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SudokuTestSuite {
    private static final int SIZE = 9;

    @Test
    public void testSudokuElementCount() {
        // Given
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getField();

        // When
        System.out.println("Numbers of elements in sudoku field: " + elements.size());

        // Then
        assertThat(81, is(elements.size()));
    }

    @Test
    public void testSudokuElement() {
        // Given
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getField();

        // When
        System.out.println("Test of all elements in sudoku field:");
        // Then
        for (int i = 1; i <= SIZE; i++) {
            System.out.println("\nRow: " + i);
            for (int j = 1; j <= SIZE; j++) {
                Position position = new Position(i, j);
                assertTrue(elements.containsKey(position));
                System.out.println(position + " ok!");
            }
        }
    }

    @Test
    public void testGetRow() {
        // Given
        int row = 5;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getRow(row);

        // When
        System.out.println("Test of one row elements (not sorted): " + elements.size());
        System.out.println("Elements in row: " + elements.size());

        // Then
        assertThat(SIZE, is(elements.size()));
        for (int i = 1; i <= SIZE; i++) {
            Position position = new Position(row, i);
            assertTrue(elements.containsKey(position));
            System.out.println(position + " ok!");
        }
    }

    @Test
    public void testGetRowOrdered() {
        // Given
        int row = 5;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getOrderRow(row);

        // When
        System.out.println("Test of one sorted row: " + elements.size());
        System.out.println("Elements in row: " + elements.size());

        // Then
        assertThat(SIZE, is(elements.size()));
        Iterator<Map.Entry<Position, Element>> iterator = elements.entrySet().iterator();
        Map.Entry<Position, Element> current;
        for (int i = 1; i <= SIZE; i++) {
            current = iterator.next();
            Position position = new Position(row, i);
            assertEquals(position, current.getKey());
        }
    }

    @Test
    public void testGetColumn() {
        // Given
        int column = 7;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getColumn(column);

        // When
        System.out.println("Test of one column elements (not sorted): " + elements.size());
        System.out.println("Elements in column: " + elements.size());

        // Then
        assertThat(SIZE, is(elements.size()));
        for (int i = 1; i <= SIZE; i++) {
            Position position = new Position(i, column);
            assertTrue(elements.containsKey(position));
            System.out.println(position + " ok!");
        }
    }

    @Test
    public void testGetColumnOrdered() {
        // Given
        int column = 7;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getOrderColumn(column);

        // When
        System.out.println("Test of one sorted column: " + elements.size());
        System.out.println("Elements in column: " + elements.size());

        // Then
        assertThat(SIZE, is(elements.size()));
        Iterator<Map.Entry<Position, Element>> iterator = elements.entrySet().iterator();
        Map.Entry<Position, Element> current;
        for (int i = 1; i <= SIZE; i++) {
            current = iterator.next();
            Position position = new Position(i, column);
            assertEquals(position, current.getKey());
        }
    }

}
