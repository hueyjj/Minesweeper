package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grid minesweeper = new Grid(10, 10, 10);
        int[][] grid = minesweeper.get2DArray();

        printNakedGrid(grid, minesweeper.getWidth(), minesweeper.getHeight());
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean running = true;
        while (running) {
            printGrid(grid, minesweeper.getWidth(), minesweeper.getHeight());
            System.out.print("Command (open (o), mark (m), quit (q), help (h)): ");
            input = scanner.next();
            switch(input) {
                case "o":
                case "open": {
                    System.out.print("Row: ");
                    int row = scanner.nextInt();
                    System.out.print("Column: ");
                    int col = scanner.nextInt();
                    minesweeper.setOpen(row, col);
                    if (minesweeper.hasLost()) {
                        running = false;
                        continue;
                    }
                    break;
                }
                case "m":
                case "mark": {
                    System.out.print("Row: ");
                    int row = scanner.nextInt();
                    System.out.print("Column: ");
                    int col = scanner.nextInt();
                    minesweeper.setMark(row, col);
                    break;
                }
                case "q":
                case "quit":
                    System.out.println();
                    System.out.println("Bye. minesweeper finished.");
                    running = false;
                    continue;
                case "h":
                case "help":
                    System.out.println("Example:");
                    System.out.println("    Enter 'o', enter a row index, enter a column index");
                    System.out.println();
                    break;
                default:
                    System.out.println("Unrecognized command.");
                    System.out.println();
                    break;
            }
        }
    }

    public static void printGrid(int[][] grid, int width, int height) {
        // Column header
        System.out.printf("%2s ", " ");
        for (int col = 0; col < width; col++)
            System.out.printf("%2s ", col);
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.printf("%2s ", row);
            for (int col = 0; col < width; col++) {
                String out = "";
                int value = grid[row][col];
                switch(value) {
                    case 0:
                    case -1:
                        out = ".";
                        break;
                    case -2:
                        out = "?";
                        break;
                    case -3:
                        out = " ";
                        break;
                    default:
                        out = grid[row][col] + "";
                        break;
                }
                System.out.printf("%2s ", out);
            }
            System.out.println();
        }
    }

    public static void printNakedGrid(int[][] grid, int width, int height) {
        // Column header
        System.out.printf("%2s ", " ");
        for (int col = 0; col < width; col++)
            System.out.printf("%2s ", col);
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.printf("%2s ", row);
            for (int col = 0; col < width; col++) {
                System.out.printf("%2s ", grid[row][col]);
            }
            System.out.println();
        }
    }
}
