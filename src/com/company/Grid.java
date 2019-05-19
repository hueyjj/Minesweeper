package com.company;

import java.util.Random;

public class Grid {
    private int width, height;
    private int numBombs;
    private int[][] grid;

    public Grid(int width, int height, int numBombs){
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
        grid = new int[height][width];
        this.initGrid(this.numBombs);
    }

    public void initGrid(int numBombs) {
        // Clear grid
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 0;
            }
        }

        // Set bombs
        Random random = new Random();
        int n = numBombs;
        while (n > 0) {
            int row = random.nextInt(this.height);
            int col = random.nextInt(this.width);
            // Bomb doesn't already exists
            if (this.grid[row][col] != -1) {
                this.grid[row][col] = -1;
                n--;
            }
        }
    }

    public int[][] get2DArray() {
        return this.grid;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getNumBombs() {
        return this.numBombs;
    }
}
