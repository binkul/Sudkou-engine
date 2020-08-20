package com.sudoku.engine.map.solver.algorithm;

import com.sudoku.engine.map.sudoku.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SolverStandard extends SolverSingle {

    public Result process(Sudoku sudoku, int row, int column) {
        Element element = sudoku.getElement(row, column);
        if (element.getNumber() != Data.EMPTY) return Result.NONE;

        Result result = super.process(sudoku, row, column);
        if (result != Result.NONE) return result;

        Set<Integer> existingNumbers = super.getNumbersFromSudokuSection(sudoku, row, column);
        if (addUniqueFromExisting(sudoku.getRow(row), element, existingNumbers)) return Result.ADDED;
        if (addUniqueFromExisting(sudoku.getColumn(column), element, existingNumbers)) return Result.ADDED;
        if (addUniqueFromExisting(sudoku.getSection(row, column), element, existingNumbers)) return Result.ADDED;

        return Result.NONE;
    }

    private boolean addUniqueFromExisting(Map<Position, Element> elements, Element element, Set<Integer> existingNumbers) {
        Boolean[] result = {false};
        findUniqueCandidates(elements)
                .stream()
                .filter(element::containCandidate)
                .filter(i -> !existingNumbers.contains(i))
                .findFirst()
                .ifPresent((i) -> {
                    element.setNumber(i);
                    element.setFontColor(FontColor.GREEN);
                    result[0] = true;
                });
        return result[0];
    }

    private List<Integer> findUniqueCandidates(Map<Position, Element> elements) {
        return elements.values()
                .stream()
                .filter(i -> i.getNumber() == Data.EMPTY)
                .map(Element::getCandidates)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(i -> i.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
