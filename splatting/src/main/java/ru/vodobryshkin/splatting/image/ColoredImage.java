package ru.vodobryshkin.splatting.image;

import java.util.List;

public interface ColoredImage {
    List<List<Double>> list();
    int height();
    int width();
}
