package com.company;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10, 25);
        int[][] gridArr = grid.get2DArray();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                System.out.printf("%2d ", gridArr[i][j]);
            }
            System.out.println();
        }
    }
}
