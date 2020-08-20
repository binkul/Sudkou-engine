package com.sudoku.engine.map.sudoku;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SudokuTestSuite {

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
        for (int i = 1; i <= Data.SIZE; i++) {
            System.out.println("\nRow: " + i);
            for (int j = 1; j <= Data.SIZE; j++) {
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
        assertThat(Data.SIZE, is(elements.size()));
        for (int i = 1; i <= Data.SIZE; i++) {
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
        Map<Position, Element> elements = sudoku.getRow(row);

        // When
        System.out.println("Test of one sorted row: " + elements.size());
        System.out.println("Elements in row: " + elements.size());

        // Then
        assertThat(Data.SIZE, is(elements.size()));
        Iterator<Map.Entry<Position, Element>> iterator = elements.entrySet().iterator();
        Map.Entry<Position, Element> current;
        for (int i = 1; i <= Data.SIZE; i++) {
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
        assertThat(Data.SIZE, is(elements.size()));
        for (int i = 1; i <= Data.SIZE; i++) {
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
        Map<Position, Element> elements = sudoku.getColumn(column);

        // When
        System.out.println("Test of one sorted column: " + elements.size());
        System.out.println("Elements in column: " + elements.size());

        // Then
        assertThat(Data.SIZE, is(elements.size()));
        Iterator<Map.Entry<Position, Element>> iterator = elements.entrySet().iterator();
        Map.Entry<Position, Element> current;
        for (int i = 1; i <= Data.SIZE; i++) {
            current = iterator.next();
            Position position = new Position(i, column);
            assertEquals(position, current.getKey());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionWhenGetRow() {
        // Given
        int row = 0;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getRow(row);

        // When

        // Then
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionWhenGetColumn() {
        // Given
        int column = 0;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getColumn(column);

        // When

        // Then
    }

    @Test
    public void testGetSection() {
        // Given
        int row = 5;
        int column = 8;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getSection(row, column);

        // When
        System.out.println("Elements in section: " + elements.size());

        // Then
        int x = ((row - 1) / Data.SECTION) * Data.SECTION + 1;
        int y = ((column - 1) / Data.SECTION) * Data.SECTION + 1;
        assertThat(9, is(elements.size()));
        assertTrue(elements.containsKey(new Position(x, y)));
        assertTrue(elements.containsKey(new Position(x, y + 1)));
        assertTrue(elements.containsKey(new Position(x, y + 2)));
        assertTrue(elements.containsKey(new Position(x + 1, y)));
        assertTrue(elements.containsKey(new Position(x + 1, y + 1)));
        assertTrue(elements.containsKey(new Position(x + 1, y + 2)));
        assertTrue(elements.containsKey(new Position(x + 2, y)));
        assertTrue(elements.containsKey(new Position(x + 2, y + 1)));
        assertTrue(elements.containsKey(new Position(x + 2, y + 2)));
    }

    @Test
    public void testGetSudokuSection() {
        // Given
        int row = 5;
        int column = 8;
        Sudoku sudoku = new Sudoku();
        Map<Position, Element> elements = sudoku.getSudokuSection(row, column);

        // When
        System.out.println("Test of one column elements (not sorted): " + elements.size());
        System.out.println("Elements in column: " + elements.size());

        // Then
        assertThat(21, is(elements.size()));

        for (int i = 1; i <= Data.SIZE; i++) {
            Position position = new Position(i, column);
            assertTrue(elements.containsKey(position));
            System.out.println(position + " ok!");
        }

        for (int i = 1; i <= Data.SIZE; i++) {
            Position position = new Position(row, i);
            assertTrue(elements.containsKey(position));
            System.out.println(position + " ok!");
        }

        int x = ((row - 1) / Data.SECTION) * Data.SECTION + 1;
        int y = ((column - 1) / Data.SECTION) * Data.SECTION + 1;
        assertTrue(elements.containsKey(new Position(x, y)));
        assertTrue(elements.containsKey(new Position(x, y + 1)));
        assertTrue(elements.containsKey(new Position(x, y + 2)));
        assertTrue(elements.containsKey(new Position(x + 1, y)));
        assertTrue(elements.containsKey(new Position(x + 1, y + 1)));
        assertTrue(elements.containsKey(new Position(x + 1, y + 2)));
        assertTrue(elements.containsKey(new Position(x + 2, y)));
        assertTrue(elements.containsKey(new Position(x + 2, y + 1)));
        assertTrue(elements.containsKey(new Position(x + 2, y + 2)));
    }

    @Test
    public void testUpdateElementInSudoku() {
        // Given
        int row = 3;
        int column = 7;
        int number = 9;
        Sudoku sudoku = new Sudoku();
        Element element = sudoku.getElement(row, column);

        // When
        System.out.println("Test of setting value in element: ");
        element.setNumber(number);

        // Then
        assertThat(number, is(sudoku.getElement(row, column).getNumber()));
    }
}
