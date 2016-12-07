package ru.spbstu.java_classes.homeworks.homework4;

import static ru.spbstu.java_classes.homeworks.homework4.MatrixGenerationUtils.*;

public class MatrixGenerationTests {

    public static void main(String[] args) {
        System.out.println("start matrix generation 10 / 20");
        System.out.println("not parallel generation");
        exampleNotParallel();
        System.out.println("parallel generation");
        exampleParallel();
    }

    private static void exampleParallel() {
        double[][] matrix;

        long start = System.nanoTime();
        matrix = generateMatrixParallel(10, 20, 0);
        long stop = System.nanoTime();

        System.out.println("Elapsed = " + (stop - start));
    }

    private static void exampleNotParallel() {
        double[][] matrix = generateMatrix(10, 20);

        long start = System.nanoTime();
        processMatrix(matrix);
        long stop = System.nanoTime();

        System.out.println("Elapsed = " + (stop - start));
    }

    private static void processMatrix(double[][] matrix) {
        Thread[] threads = new Thread[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            final double[] row = matrix[i];
            threads[i] = new Thread() {
                @Override
                public void run() {
                    processRow(row);
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
    }

    private static void processRow(double[] ds) {
        for (int i = 0; i < ds.length; i++) {
            Math.pow(ds[i], ds[i]);
        }
    }

}
