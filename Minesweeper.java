import java.util.Scanner;

public class Minesweeper {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many rows? Enter a number: ");
        int inRows = scanner.nextInt();
        System.out.print("How many columns? Enter a number: ");
        int inCols = scanner.nextInt();

        Grid minesweeper = new Grid(inCols, inRows, (inRows*inCols)/10);
        int[][] grid = minesweeper.get2DArray();

        //printAdminGrid(grid, minesweeper.getWidth(), minesweeper.getHeight());
        //System.out.println();

        String input;
        boolean running = true;
        while (running) {
            clearScreen();

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
                    if (row < 0 || row >= minesweeper.getHeight() || col < 0 || col >= minesweeper.getWidth()) {
                        continue;
                    }
                    minesweeper.setOpen(row, col);
                    if (minesweeper.hasLost()) {
                        printNakedGrid(grid, minesweeper.getWidth(), minesweeper.getHeight());
                        System.out.println();
                        System.out.println("Try again next time loser!");
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
                    if (row < 0 || row >= minesweeper.getHeight() || col < 0 || col >= minesweeper.getWidth()) {
                        continue;
                    }
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
                    System.out.print("Enter ok to continue: ");
                    input = scanner.next();
                    break;
                default:
                    System.out.println("Unrecognized command.");
                    System.out.println();
                    break;
            }
            if (minesweeper.hasWon()) {
                running = false;
                System.out.println();
                System.out.println("You won! Were you expecting a better congrats? Sorry xd");
                continue;
            }
        }
    }

    // Clears the screen (cls)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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
                    case -4:
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
                String out = "";
                int value = grid[row][col];
                switch(value) {
                    case 0:
                        out = ".";
                        break;
                    case -3:
                        out = " ";
                        break;
                    case -1:
                    case -2:
                    case -4:
                        out = "X";
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

    public static void printAdminGrid(int[][] grid, int width, int height) {
        // Column header
        System.out.printf("%2s ", " ");
        for (int col = 0; col < width; col++)
            System.out.printf("%2s ", col);
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.printf("%2s ", row);
            for (int col = 0; col < width; col++) {
                int value = grid[row][col];
                System.out.printf("%2s ", value + "");
            }
            System.out.println();
        }
    }
}
