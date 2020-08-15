package com.sudoku.engine.map.sudoku;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PositionTestSuite {

    @Before
    public void printBefore() {
        System.out.println("Test start ...");
    }

    @Test
    public void testCompareRowAndColumn() {
        // Given
        Position position1 = new Position(1, 2);
        Position position2 = new Position(2, 2);
        Position position3 = new Position(2, 4);
        Position position4 = new Position(2, 4);

        // When
        boolean compA = position1.compareTo(position2) < 0;
        boolean compB = position2.compareTo(position3) < 0;
        boolean compC = position3.compareTo(position4) == 0;

        // Then
        assertTrue(compA);
        assertTrue(compB);
        assertTrue(compC);
    }

    @Test
    public void testListSort() {
        // Given
        Position position1 = new Position(1, 2);
        Position position2 = new Position(2, 2);
        Position position3 = new Position(2, 4);
        Position position4 = new Position(5, 7);

        // When
        List<Position> positions = new ArrayList<>();
        positions.add(position4);
        positions.add(position2);
        positions.add(position1);
        positions.add(position3);
        Collections.sort(positions);

        // Then
        assertThat(positions.get(0), is(position1));
        assertThat(positions.get(1), is(position2));
        assertThat(positions.get(2), is(position3));
        assertThat(positions.get(3), is(position4));
    }
}
