public class SudokuSolver {

    // Function to solve the Sudoku puzzle
    public static boolean solveSudoku(int[][] grid) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find an empty space in the grid
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) { // 0 represents an empty cell
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If there's no empty space, the puzzle is solved
        if (isEmpty) {
            return true;
        }

        // Try digits from 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num; // Assign number

                // Recursively try to solve the rest of the puzzle
                if (solveSudoku(grid)) {
                    return true;
                }

                // If it doesn't lead to a solution, reset and try another number
                grid[row][col] = 0;
            }
        }
        return false; // Trigger backtracking
    }

    // Function to check if it's safe to place a number in a specific cell
    public static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check the row and column
        for (int x = 0; x < 9; x++) {
            if (grid[row][x] == num || grid[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true; // It's safe to place the number
    }

    // Function to print the Sudoku grid
    public static void printGrid(int[][] grid) {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(grid[r][d] + " ");
            }
            System.out.print("\n");
        }
    }

    // Main method to test the Sudoku solver
    public static void main(String[] args) {
        // Example Sudoku puzzle (0s represent empty cells)
        int[][] grid = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        System.out.println("Unsolved Sudoku Grid:");
        printGrid(grid);

        if (solveSudoku(grid)) {
            System.out.println("\nSolved Sudoku Grid:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}
