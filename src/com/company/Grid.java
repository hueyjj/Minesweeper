package com.company;

import java.util.Random;

public class Grid {
    private int width, height;
    private int numBombs;

    /*
        0: unmarked
        -1: bomb
        -2: question mark
        -3: opened
        > 0: num of bombs adjacent
     */
    private int[][] grid;

    public Grid(int width, int height, int numBombs){
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
        grid = new int[height][width];
        this.initGrid(this.numBombs);
    }

    public void initGrid(int numBombs) {
        this.clearGrid();

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

    public void clearGrid() {
        // Clear grid
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 0;
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

    public void setQuestionMark(int row, int col) {
        if (0 <= row && row < this.height && 0 <= col && col <= this.width) {
            this.grid[row][col] = -2;
        }
    }
}
