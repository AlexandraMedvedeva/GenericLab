package ru.spbstu.java_classes.homeworks.homework4;

import java.util.Random;

public class MatrixGenerationUtils {

    public static double[][] generateMatrix(int rows, int cols) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = random.nextDouble();

        return matrix;
    }

    /**
     * Generate matrix in multiple threads
     *
     * @param rows         matrix rows number
     * @param cols         matrix cols number
     * @param threadsCount number of threads to use during generation
     * @return generated matrix
     */
    public static double[][] generateMatrixParallel(int rows, int cols, int threadsCount) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];

        Thread[] threads = new Thread[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            final double[] row = matrix[i];
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < row.length; j++)
                        row[j] = random.nextDouble();
                }
            };
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return matrix;
    }
}
