package com.sudoku.engine.map.solver;

import com.sudoku.engine.map.solver.algorithm.Result;
import com.sudoku.engine.map.solver.algorithm.SolverBackTrack;
import com.sudoku.engine.map.solver.algorithm.SolverStandard;
import com.sudoku.engine.map.sudoku.Data;
import com.sudoku.engine.map.sudoku.Element;
import com.sudoku.engine.map.sudoku.Sudoku;

public class Solver {
    private Sudoku sudoku;
    private final SolverStandard solverStandard;
    private final SolverBackTrack solverBackTrack;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
        solverStandard = new SolverStandard();
        solverBackTrack = new SolverBackTrack(this);
    }

    public void process() {
        runStandard(sudoku);
        if (runStandard(sudoku) != Result.FULL_FILLED) {
            solverBackTrack.process(sudoku);
        }
    }

    public Result runStandard(Sudoku sudoku) {
        final int[] count = new int[1];
        final Result[] result = new Result[1];

        do {
            count[0] = 0;
            for (int row = 1; row <= Data.SIZE; row++) {
                for (int column = 1; column <= Data.SIZE; column++) {
                    result[0] = solverStandard.process(sudoku, row, column);
                    if (result[0] == Result.ERROR) return result[0];
                    if (result[0] == Result.ADDED) count[0]++;
                }
            }
        } while (count[0] != 0);
        return isFilled(sudoku) ? Result.FULL_FILLED : Result.NONE;
    }

    private boolean isFilled(Sudoku sudoku) {
        return sudoku.getField()
                .values()
                .stream()
                .map(Element::getNumber)
                .noneMatch(i -> i == Data.EMPTY);
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudokuBoard(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}
