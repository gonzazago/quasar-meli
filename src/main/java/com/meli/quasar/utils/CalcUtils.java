package com.meli.quasar.utils;

import com.lemmingapex.trilateration.TrilaterationFunction;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

@Slf4j
public class CalcUtils {


    public static double[] calcPositition(double[][] position, double[] distances) {

        log.info("Init Calc position");
        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(position, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
        return nSolver.solve().getPoint().toArray();

    }
}
