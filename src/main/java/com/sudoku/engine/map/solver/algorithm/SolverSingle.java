package com.sudoku.engine.map.solver.algorithm;

import com.sudoku.engine.map.sudoku.Data;
import com.sudoku.engine.map.sudoku.Element;
import com.sudoku.engine.map.sudoku.FontColor;
import com.sudoku.engine.map.sudoku.Sudoku;

import java.util.Set;
import java.util.stream.Collectors;

public class SolverSingle {

    protected Result process(Sudoku sudoku, int row, int column) {
        Element element = sudoku.getElement(row, column);

        Set<Integer> existingNumbers = getNumbersFromSudokuSection(sudoku, row, column);
        existingNumbers.forEach(element::removeCandidate);
        return addNumber(element);
    }

    public Set<Integer> getNumbersFromSudokuSection(Sudoku sudoku, int row, int column) {
        return sudoku.getSudokuSection(row, column)
                .values()
                .stream()
                .map(Element::getNumber)
                .filter(i -> i != Data.EMPTY)
                .collect(Collectors.toSet());
    }

    private Result addNumber(Element element) {
        if (element.getCandidatesSize() == 0) {
            return Result.ERROR;
        } else if (element.getCandidatesSize() == 1) {
            element.setNumber(element.getFirstCandidate());
            element.setFontColor(FontColor.RED);
            return Result.ADDED;
        } else {
            return Result.NONE;
        }
    }

}