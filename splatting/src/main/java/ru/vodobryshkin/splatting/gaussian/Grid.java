package ru.vodobryshkin.splatting.gaussian;

import ru.vodobryshkin.splatting.function.FunctionOfTwoVariables;

import java.util.List;

public interface Grid {
    List<FunctionOfTwoVariables> functionRepresentation(int height, int width);
}
