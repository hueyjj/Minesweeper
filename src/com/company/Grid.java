package com.company;

import java.util.Random;

public class Grid {
    private int width, height;
    private int[][] grid;
    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        grid = new int[height][width];
    }

    public void initGrid(int numBombs) {
        Random random = new Random();
        // Clear grid and set bombs
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (random.nextInt(numBombs))
                grid[row][col] = -1;
            }
        }
    }
}
