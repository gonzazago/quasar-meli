package com.meli.quasar.unit;

import com.meli.quasar.utils.CalcUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


class CalcUtilsTest {

    @Test
    void calcTrilaterationOK() {
        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
        double[] distances = new double[]{100.0, 115.5, 142.7};
        double[] expectedPosition = new double[]{-58.315252587138595, -69.55141837312165};
        double[] location = CalcUtils.calcPositition(positions, distances);

        assertNotNull(location);
        assertEquals(expectedPosition[0], location[0]);
        assertEquals(expectedPosition[1], location[1]);

    }


    @Test
    void calcTrilateration_Not_Ok() {

        assertThrows(IllegalArgumentException.class,()->{
        double[][] positions = new double[][]{{1.0, 1.0}};
        double[] distances = new double[]{1.0, 1.0};
        double[] location = CalcUtils.calcPositition(positions, distances);
        });
    }

}
