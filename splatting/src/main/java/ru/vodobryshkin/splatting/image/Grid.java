package ru.vodobryshkin.splatting.image;

import function.FunctionOfTwoVariables;

import java.util.List;

public interface Grid {
    List<FunctionOfTwoVariables> functionRepresentation(int height, int width);
}
