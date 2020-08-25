package com.sudoku.engine.map.solver;

import com.sudoku.engine.map.exception.SudokuCollisionNumberException;
import com.sudoku.engine.map.solver.algorithm.Result;
import com.sudoku.engine.map.solver.algorithm.SolverBackTrack;
import com.sudoku.engine.map.solver.algorithm.SolverStandard;
import com.sudoku.engine.map.sudoku.Sudoku;
import com.sudoku.engine.map.sudoku.Validator;

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
        if (Validator.isCollision(sudoku)) throw new SudokuCollisionNumberException("There are the same numbers in row/column/section");
        solverStandard.process(sudoku);
        if (solverStandard.process(sudoku) != Result.FULL_FILLED) {
            solverBackTrack.process(sudoku);
        }
    }

    public SolverStandard getSolverStandard() {
        return solverStandard;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudokuBoard(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}
