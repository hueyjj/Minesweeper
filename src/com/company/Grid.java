package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Grid {
    private int width, height;
    private int numBombs;
    private boolean hasLost;

    /*
        0: un-open
        -1: bomb
        -2: marked a bomb
        -4: marked not a bomb
        -3: opened
        > 0: num of bombs adjacent
     */
    private int[][] grid;

    public Grid(int width, int height, int numBombs){
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
        this.hasLost = false;
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

    private class Location {
        public int row, col;
        public Location(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public void setOpen(int row, int col) {
        if (0 <= row && row < this.height && 0 <= col && col <= this.width) {
            // Hit a bomb, we lose
            if (this.grid[row][col] == -1) {
                this.hasLost = true;
                return;
            }

            // Open as much area as possible
            Queue<Location> queue = new LinkedList<>();
            queue.add(new Location(row, col));
            while (queue.size() > 0) {
                Location loc = queue.remove();
                //System.out.printf("Removed: (%2d, %2d)\n", loc.row, loc.col);
                int numAdjacentBombs = 0;
                boolean clear = true;
                // Check top left
                if (loc.col-1 >= 0 && loc.row-1 >= 0
                        && (this.grid[loc.row-1][loc.col-1] == -1 || this.grid[loc.row-1][loc.col-1] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check middle left
                if (loc.col-1 >= 0
                        && (this.grid[loc.row][loc.col-1] == -1 || this.grid[loc.row][loc.col-1] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check bottom left
                if (loc.col-1 >= 0 && loc.row+1 < this.height
                        && (this.grid[loc.row+1][loc.col-1] == -1 || this.grid[loc.row+1][loc.col-1] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check middle top
                if (loc.row-1 >= 0
                        && (this.grid[loc.row-1][loc.col] == -1 || this.grid[loc.row-1][loc.col] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check middle bottom
                if (loc.row+1 < this.height
                        && (this.grid[loc.row+1][loc.col] == -1 || this.grid[loc.row+1][loc.col] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check top right
                if (loc.row-1 >= 0 && loc.col+1 < this.width
                        && (this.grid[loc.row-1][loc.col+1] == -1 || this.grid[loc.row-1][loc.col+1] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check middle right
                if (loc.col+1 < this.width
                        && (this.grid[loc.row][loc.col+1] == -1 || this.grid[loc.row][loc.col+1] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }
                // Check bottom right
                if (loc.col+1 < this.width && loc.row+1 < this.height
                        && (this.grid[loc.row+1][loc.col+1] == -1 || this.grid[loc.row+1][loc.col+1] == -2)) {
                    clear = false;
                    numAdjacentBombs++;
                }

                if (clear) {
                    if (loc.row-1 >= 0 && loc.col-1 >= 0 && this.grid[loc.row-1][loc.col-1] == 0)
                        queue.add(new Location(loc.row - 1, loc.col - 1));
                    if (loc.col-1 >= 0 && this.grid[loc.row][loc.col-1] == 0)
                        queue.add(new Location(loc.row, loc.col-1));
                    if (loc.row+1 < this.height && loc.col-1 >= 0 && this.grid[loc.row+1][col-1] == 0)
                        queue.add(new Location(loc.row+1, loc.col-1));
                    if (loc.row-1 >= 0 && this.grid[loc.row-1][loc.col] == 0)
                        queue.add(new Location(loc.row-1, loc.col));
                    if (loc.row+1 < this.height && this.grid[loc.row+1][loc.col] == 0)
                        queue.add(new Location(loc.row+1, loc.col));
                    if (loc.row-1 >= 0 && loc.col+1 < this.width && this.grid[loc.row-1][loc.col+1] == 0)
                        queue.add(new Location(loc.row-1, loc.col+1));
                    if (loc.col+1 < this.width && this.grid[loc.row][loc.col+1] == 0)
                        queue.add(new Location(loc.row, loc.col+1));
                    if (loc.row+1 < this.height && loc.col+1 < this.width && this.grid[loc.row+1][loc.col+1] == 0)
                        queue.add(new Location(loc.row+1, loc.col+1));

                    this.grid[loc.row][loc.col] = -3;
                } else {
                    this.grid[loc.row][loc.col] = numAdjacentBombs;
                }
            }
        }
    }

    public void setMark(int row, int col) {
        if (0 <= row && row < this.height && 0 <= col && col < this.width) {
            if (this.grid[row][col] == -1) {
                this.grid[row][col] = -2; // Found a bomb and marked
            } else {
                this.grid[row][col] = -3; // Didn't find a bomb, but marking anyway...
            }
        }
    }

    public boolean hasLost() {
        return this.hasLost;
    }
}
