package com.sudoku.engine.map.sudoku;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sudoku {
    private static int SIZE = 9;
    private Map<Position, Element> field;

    public Sudoku() {
        field = createField();
    }

    private Map<Position, Element> createField() {
        return IntStream.rangeClosed(1, SIZE)
                .mapToObj(this::createRow)
                .flatMap(i -> i.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Position, Element> createRow(int row) {
        return IntStream.rangeClosed(1, SIZE)
                .collect(HashMap::new, (m, i) -> m.put(new Position(row, i), new Element()), Map::putAll);

    }

    public Map<Position, Element> getRow(int row) {
        return field.entrySet().stream()
                .filter(i -> i.getKey().getRow() == row)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Element> getOrderRow(int row) {
        return new TreeMap<>(getRow(row));
    }

    public Map<Position, Element> getColumn(int column) {
        return field.entrySet().stream()
                .filter(i -> i.getKey().getColumn() == column)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Element> getOrderColumn(int column) {
        return new TreeMap<>(getColumn(column));
    }

    public Map<Position, Element> getField() {
        return field;
    }
}
