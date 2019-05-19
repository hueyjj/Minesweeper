package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grid minesweeper = new Grid(10, 10, 25);
        int[][] grid = minesweeper.get2DArray();
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean running = true;
        while (running) {
            System.out.print("Input: ");
            input = scanner.next();
            switch(input) {
                case "quit":
                    System.out.println();
                    System.out.println("Exit minesweeper.");
                    running = true;
                    continue;
                default:
                    break;
            }
        }
    }

    public static void printGrid(int[][] grid, int width, int height) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                String out = "";
                int value = grid[row][col];
                switch(value) {
                    case 0:
                        out = ".";
                        break;
                    case -1:
                        out = "X";
                        break;
                    case -2:
                        out = "?";
                        break;
                    case -3:
                        out = " ";
                        break;
                    default:
                        break;
                }
                System.out.printf("%2s ", out);
            }
            System.out.println();
        }
    }
}
