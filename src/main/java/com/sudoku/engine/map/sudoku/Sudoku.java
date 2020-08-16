package com.sudoku.engine.map.sudoku;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sudoku {
    private Map<Position, Element> field;

    public Sudoku() {
        field = createField();
    }

    private Map<Position, Element> createField() {
        return IntStream.rangeClosed(1, Data.SIZE)
                .mapToObj(this::createRow)
                .flatMap(i -> i.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Position, Element> createRow(int row) {
        return IntStream.rangeClosed(1, Data.SIZE)
                .collect(HashMap::new, (m, i) -> m.put(new Position(row, i), new Element()), Map::putAll);
    }

    public Element getElement(int row, int column) {
        Validator.validateRow(row);
        Validator.validateColumn(column);
        return field.get(new Position(row, column));
    }

    public Map<Position, Element> getRow(int row) {
        Validator.validateRow(row);

        return field.entrySet().stream()
                .filter(i -> i.getKey().getRow() == row)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Element> getOrderedRow(int row) {
        return new TreeMap<>(getRow(row));
    }

    public Map<Position, Element> getColumn(int column) {
        Validator.validateColumn(column);

        return field.entrySet().stream()
                .filter(i -> i.getKey().getColumn() == column)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Element> getOrderedColumn(int column) {
        return new TreeMap<>(getColumn(column));
    }

    public Map<Position, Element> getSection(int row, int column) {
        Validator.validateRow(row);
        Validator.validateColumn(column);

        int x = ((row - 1) / Data.SECTION) * Data.SECTION + 1;
        int y = ((column - 1) / Data.SECTION) * Data.SECTION + 1;

        return field.entrySet().stream()
                .filter(i -> i.getKey().getRow() >= x && i.getKey().getRow() < x + Data.SECTION)
                .filter(i -> i.getKey().getColumn() >= y && i.getKey().getColumn() < y + Data.SECTION)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Element> getSudokuSection(int row, int column) {
        return Stream.of(getRow(row), getColumn(column), getSection(row, column))
                .flatMap(i -> i.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
    }

    public Map<Position, Element> getOrderSection(int row, int column) {
        return new TreeMap<>(getSection(row, column));
    }

    public Map<Position, Element> getField() {
        return field;
    }
}
