package test;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {

        initializeBoard();
        System.out.println(Arrays.deepToString(knightMoves(3, 4)));

        // Array holding the heuristic
//        private int[][] heuristic =
//                {{2, 3, 4, 4, 4, 4, 3, 2},
//                {3, 4, 6, 6, 6, 6, 4, 3},
//                {4, 6, 8, 8, 8, 8, 6, 4},
//                {4, 6, 8, 8, 8, 8, 6, 4},
//                {4, 6, 8, 8, 8, 8, 6, 4},
//                {4, 6, 8, 8, 8, 8, 6, 4},
//                {3, 4, 6, 6, 6, 6, 4, 3},
//                {2, 3, 4, 4, 4, 4, 3, 2}};


    }

    public static void initializeBoard() {

        // Create an Array holding all The Possible Positions on the board
        String[][] board = new String[8][8];
        // Fill the Spaces with "_"
        for (String[] strings : board) {
            Arrays.fill(strings, "-");
        }

        // Create a Knight with String "K"
        String Knight = "K";
        // Put Knight in position ( 3 , 4 ) on the Board
        board[3][4] = Knight;

        // Labeling for brackets that show current iteration and move
        System.out.println("[ Iteration, Move ] <--- Brackets show Current Iteration and Move");
        System.out.println();

        // Numbers for the position on the top of the Board
        System.out.println("[0,0]");
        System.out.println("      0  1  2  3  4  5  6  7  ");

        // Top of the Board Border
        System.out.println("    ===========================");

        // Print out the 2d Array of the Board
        for (int i = 0; i < board.length; i++) {
            // Numbers for the position on the side of the Board
            System.out.print(" " + i + "  | ");

            for (int j = 0; j < board[i].length; j++) {
                // Print the Value in the Array and add 2 WhiteSpaces
                System.out.print(board[i][j] + "  ");
            }
            // Right Border of the Board
            System.out.print("|");
            // Create a NewLine
            System.out.println();
            // If the loop is at the last iteration, add Bottom Border of Board
            if (i == board.length - 1) {
                System.out.println("    ===========================");
            }

        }
    }

    public static void showMoves(int[][] moves, int x, int y) {

        // Create an Array holding all The Possible Positions on the board
        String[][] board = new String[8][8];
        // Fill the Spaces with "_"
        for (String[] strings : board) {
            Arrays.fill(strings, "-");
        }

        // Add A Knight to The Board
        String Knight = "K";
        board[y][x] = Knight;

        //Add An "x" Where It can Move


        // Numbers for the position on the top of the Board
        System.out.println("");
        System.out.println("      0  1  2  3  4  5  6  7  ");

        // Top of the Board Border
        System.out.println("    ===========================");

        // Print out the 2d Array of the Board
        for (int i = 0; i < board.length; i++) {
            // Numbers for the position on the side of the Board
            System.out.print(" " + i + "  | ");

            for (int j = 0; j < board[i].length; j++) {
                // Print the Value in the Array and add 2 WhiteSpaces
                System.out.print(board[i][j] + "  ");
            }
            // Right Border of the Board
            System.out.print("|");
            // Create a NewLine
            System.out.println();
            // If the loop is at the last iteration, add Bottom Border of Board
            if (i == board.length - 1) {
                System.out.println("    ===========================");
            }

        }
    }

    public static void printBoard(String[][] board) {
        // Numbers for the position on the top of the Board
        System.out.println("");
        System.out.println("      0  1  2  3  4  5  6  7  ");

        // Top of the Board Border
        System.out.println("    ===========================");

        // Print out the 2d Array of the Board
        for (int i = 0; i < board.length; i++) {
            // Numbers for the position on the side of the Board
            System.out.print(" " + i + "  | ");

            for (int j = 0; j < board[i].length; j++) {
                // Print the Value in the Array and add 2 WhiteSpaces
                System.out.print(board[i][j] + "  ");
            }
            // Right Border of the Board
            System.out.print("|");
            // Create a NewLine
            System.out.println();
            // If the loop is at the last iteration, add Bottom Border of Board
            if (i == board.length - 1) {
                System.out.println("    ===========================");
            }

        }
    }


    // Get all the possible positions for the Knight to move
    public static int[][] knightMoves(int x, int y) {

        // Array to return with moves
        return new int[][]{{y + 2, x + 1}, {y + 2, x - 1}, {y - 2, x + 1}, {y - 2, x - 1}, {y - 1, x + 2}, {y + 1, x + 2}, {y - 1, x - 2}, {y + 1, x - 2}};


    }

    //Validate all Possible moves
    /*

    public static int[][] validateMoves()
    {
        int x = 0;


        return x;
    }
    */


}
