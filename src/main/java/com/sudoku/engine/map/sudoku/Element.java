package com.sudoku.engine.map.sudoku;

import java.util.Arrays;
import java.util.List;

public class Element {
    private int number = 0;
    private List<Integer> candidates = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private Colors fontColor = Colors.BLACK;

    public void removeCandidate(Integer candidate) {
        candidates.remove(candidate);
    }

    public boolean containCandidate(Integer candidate) {
        return candidates.contains(candidate);
    }

    public int getNumber() {
        return number;
    }

    public List<Integer> getCandidates() {
        return candidates;
    }

    public Colors getFontColor() {
        return fontColor;
    }

    public void setNumber(int number) {
        Validator.validateNumber(number);
        this.number = number;
    }

    public void setFontColor(Colors fontColor) {
        this.fontColor = fontColor;
    }

    @Override
    public String toString() {
        return "Element{" +
                "number=" + number +
                ", candidates=" + candidates +
                '}';
    }
}
