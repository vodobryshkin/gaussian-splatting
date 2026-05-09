package ru.vodobryshkin.splatting.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PixelColoredImageTest {
    static Stream<Arguments> readingImagesArgumentProvider() {
        return Stream.of(
                Arguments.of(
                        Path.of("src", "test", "resources", "one_pixel_128_64_32.png"),
                        List.of(List.of(128 / 255.0, 64 / 255.0, 32 / 255.0))
                ),
                Arguments.of(
                        Path.of("src", "test", "resources", "two_by_two_rgbw.png"),
                        List.of(
                                List.of(255 / 255.0, 0 / 255.0, 0 / 255.0),
                                List.of(0 / 255.0, 255 / 255.0, 0 / 255.0),

                                List.of(0 / 255.0, 0 / 255.0, 255 / 255.0),
                                List.of(255 / 255.0, 255 / 255.0, 255 / 255.0)
                        )
                ),
                Arguments.of(
                        Path.of("src", "test", "resources", "three_by_two_known_colors.png"),
                        List.of(
                                List.of(10 / 255.0, 20 / 255.0, 30 / 255.0),
                                List.of(40 / 255.0, 50 / 255.0, 60 / 255.0),
                                List.of(70 / 255.0, 80 / 255.0, 90 / 255.0),

                                List.of(100 / 255.0, 110 / 255.0, 120 / 255.0),
                                List.of(130 / 255.0, 140 / 255.0, 150 / 255.0),
                                List.of(160 / 255.0, 170 / 255.0, 180 / 255.0)
                        )
                ),
                Arguments.of(
                        Path.of("src", "test", "resources", "checkerboard_8x8.png"),
                        List.<List<Double>>of(
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),

                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),

                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),

                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),

                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),

                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),

                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),

                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0),
                                List.of(0.0, 0.0, 0.0),
                                List.of(1.0, 1.0, 1.0)
                        )
                ),
                Arguments.of(
                        Path.of("src", "test", "resources", "gradient_8x8.png"),
                        List.<List<Double>>of(
                                List.of(0 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 0 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 0 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 36 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 36 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 73 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 73 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 109 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 109 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 146 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 146 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 182 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 182 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 219 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 219 / 255.0, 128 / 255.0),

                                List.of(0 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(36 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(73 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(109 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(146 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(182 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(219 / 255.0, 255 / 255.0, 128 / 255.0),
                                List.of(255 / 255.0, 255 / 255.0, 128 / 255.0)
                        )
                )
        );
    }

    @Tag("unit")
    @ParameterizedTest
    @MethodSource("readingImagesArgumentProvider")
    @DisplayName("Прочитанное изображение успешно преобразуется в список пикселей")
    void image_correctly_parses_into_the_list_of_pixels(Path imagePath, List<List<Double>> expected) {
        ColoredImage sut = new PixelColoredImage(imagePath);

        List<List<Double>> actual = sut.list();

        assertThat(actual).isEqualTo(expected);
    }

    @Tag("unit")
    @Test
    @DisplayName("Изображение фиксирует ошибку при некорректно переданном пути к изображению.")
    void image_throws_exception_after_wrong_file_path() {
        String path = "some/bullshit";

        assertThrows(UncheckedIOException.class, () -> new PixelColoredImage(path));
    }
}
