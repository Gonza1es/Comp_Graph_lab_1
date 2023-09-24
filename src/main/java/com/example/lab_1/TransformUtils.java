package com.example.lab_1;

public class TransformUtils {


    private static final Double[][] mX = {{1.0, 0.0}, {0.0, -1.0}};
    private static final Double[][] mY = {{-1.0, 0.0}, {0.0, 1.0}};
    private static final Double[][] mXY = {{-1.0, 0.0}, {0.0, -1.0}};




    public static Double[][] transform(Double[][] matrix, TransformType type) {
        Double[][] result = matrix;
        switch (type) {
            case TURN_X -> result = multiply(matrix, mX);
            case TURN_Y -> result = multiply(matrix, mY);
            case TURN_XY -> result = multiply(matrix, mXY);
        }

        return result;
    }

    public static Double[][] scaleMatrix(Double[][] matrix, Double scale) {

        return multiply(matrix, scale(scale));
    }



    private static Double[][] multiply(Double[][] matrix, Double[][] transformMatrix) {
        Double[][] matrixM = new Double[matrix.length][transformMatrix.length];
        for (var i = 0; i < matrixM.length; i++) {
            for (var j = 0; j < matrixM[0].length; j++) {
                matrixM[i][j] = 0.0;
                for (var k = 0; k < matrix[0].length; k++) {
                    matrixM[i][j] += matrix[i][k] * transformMatrix[k][j];
                }
            }
        }

        return matrixM;
    }

    private static Double[][] scale(Double scale) {

        return new Double[][]{{scale, 0.0}, {0.0, scale}};
    }
}
