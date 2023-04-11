public class Sudoku_Solver {
    public static boolean isSafe (int[][] sudoku, int row, int col, int dig) {
        // same row
        for(int i = 0; i < 9; i++) {
            if(sudoku[row][i] == dig) {
                return false;
            }
        }

        // same col
        for(int i = 0; i < 9; i++) {
            if(sudoku[i][col] == dig) {
                return false;
            }
        }
        
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for(int i = sr; i < (sr+3); i++) {
            for(int j = sc; j < (sc+3); j++) {
                if(sudoku[i][j] == dig) {
                    return false;
                }
            }
        }

        return true;
    }
    public static boolean sudokuSolver (int[][] sudoku, int row, int col) {
        if(row == 9) {
            return true;
        }

        int nextrow = row, nextcol = col + 1;
        
        if(nextcol == 9) {
            nextrow = row + 1;
            nextcol = 0;
        }

        if(sudoku[row][col] != 0) {
            return sudokuSolver(sudoku, nextrow, nextcol);
        }

        for(int dig = 1; dig <= 9; dig++) {
            if(isSafe(sudoku, row, col, dig)) {
                sudoku[row][col] = dig;
                if(sudokuSolver(sudoku, nextrow, nextcol)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }

        return false;
    }

    public static void printSudoku (int[][] sudoku) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main (String args[]) {
        int sudoku [][] = { {0, 0, 0, 9, 0, 0, 0, 6, 7},
                            {0, 9, 0, 0, 0, 0, 2, 0, 8},
                            {4, 6, 0, 0, 7, 8, 0, 0, 0},
                            {3, 2, 0, 0, 9, 4, 0, 7, 0},
                            {7, 0, 0, 6, 0, 3, 0, 0, 2},
                            {0, 1, 0, 7, 8, 0, 0, 4, 3},
                            {0, 0, 0, 8, 5, 0, 0, 1, 6},
                            {5, 0, 1, 0, 0, 0, 0, 9, 0},
                            {6, 7, 0, 0, 0, 9, 0, 0, 0} };

        if(sudokuSolver(sudoku, 0, 0)) {
            System.out.println("Solution Exists : ");
            printSudoku(sudoku);
        } else {
            System.out.println("Solution Does Not Exists");
        }
    }
}