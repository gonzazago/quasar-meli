package com.meli.quasar.unit.utils;

import com.meli.quasar.utils.CalcUtils;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;


class CalcUtilsTest {

    @Test
    void calcTrilaterationOK() {
        double[][] positions = new double[][]{{100.0, -100.0}, {500.0, 100.0}, {-500.0, -200.0}};
        double[] distances = new double[]{115.5, 142.7, 100.0};
        double[] expectedPosition = new double[]{-58.315, -69.551};
        double[] location = CalcUtils.calcPositition(positions, distances);
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_UP);

        assertAll(
                () -> assertNotNull(location),
                () -> assertEquals(df.format(expectedPosition[0]), df.format(location[0])),
                () -> assertEquals(df.format(expectedPosition[1]), df.format(location[1]))
        );


    }


    @Test
    void calcTrilateration_Not_Ok() {

        assertThrows(IllegalArgumentException.class, () -> {
            double[][] positions = new double[][]{{1.0, 1.0}};
            double[] distances = new double[]{1.0, 1.0};
            double[] location = CalcUtils.calcPositition(positions, distances);
        });
    }

}
