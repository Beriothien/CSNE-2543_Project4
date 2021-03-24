/*
 *  Project # - Project 4
 *  RepetitionBoard
 *
 *  William, Hibbert
 *  3/11/2021
 */
package edu.snu.csne.csne2543;


import java.util.Arrays;
import java.util.Random;

public class RepetitionBoard {

    private final String[][] _board = new String[8][8];
    Knight objKnight;
    private int[][] _spacesVisited = new int[1][2];
    private int _numMoves = 0;
    private int _boardIteration = 0;

    // Create Board Constructor
    public void board(String knightSymbol, int knightXPosition, int knightYPosition, int iteration) {

        objKnight = new Knight(knightSymbol, knightXPosition, knightYPosition);
        _spacesVisited[0][0] = objKnight.getCurrentX();
        _spacesVisited[0][1] = objKnight.getCurrentY();
        _boardIteration = iteration;

        newBoard(_board);
    }

    //Set Spaces Visited
    public void setSpacesVisited(int x, int y) {
        _spacesVisited = addElement(_spacesVisited, x, y);
    }

    //Get Spaces Visited
    public int[][] getSpacesVisited() {
        return _spacesVisited;
    }

    public void newBoard(String[][] board) {

        // Fill the Spaces with "."
        for (String[] strings : board) {
            Arrays.fill(strings, ".");
        }

        // Put Knight in position ( 3 , 4 ) on the Board
        board[objKnight.getCurrentY()][objKnight.getCurrentX()] = objKnight.getKnightSymbol();
        // Labeling for brackets that show current iteration and move

        // Numbers for the position on the top of the Board

        printBoard(board, _numMoves, _boardIteration);

        boardLoop();


    }

    public void boardLoop() {
        // Loop until Break
        _numMoves = 0;
        while (true) {

            // Get Moves Available
            int[][] movesToChoseFrom = objKnight.knightMoves(objKnight.getCurrentX(), objKnight.getCurrentY());

            movesToChoseFrom = movesAvailable(movesToChoseFrom);

            // Check for Break, Break if Values are 10,
            if ((movesToChoseFrom.length == 1) && (movesToChoseFrom[0][0] == 10)) {
                break;
            }

            // Mark Old Position as A Space Visited
            _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = "@";

            // Add Old Position to -spaceVisited
            setSpacesVisited(objKnight.getCurrentX(), objKnight.getCurrentY());

            // Select A Move and Update Current Position
            int[][] choice = randMove(movesToChoseFrom);
            System.out.println("choice " + Arrays.deepToString(choice));
            objKnight.setCurrentX(choice[0][0]);
            objKnight.setCurrentY(choice[0][1]);
            _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = objKnight.getKnightSymbol();

            _numMoves += 1;

            // Print out Updated Board
            printBoard(_board, _numMoves, _boardIteration);

            // Repeat

        }
    }


    public int[][] randMove(int[][] moves) {
        // Initialize an array to return
        int[][] choice;
        // Create a random value between 0 and Array Length
        Random rand = new Random();
        int randNum = rand.nextInt(moves.length);
        // Strip The Array of Choices other than random selection
        choice = stripElement(moves, randNum);

        //return Movement Choice
        return choice;
    }

    public int[][] movesAvailable(int[][] moves) {
        // Create a value to return
        int[][] newArray = moves;
        // Iterate through list of possible moves
        // 3 Loops because It didn't want to work otherwise
        for (int k = 0; k < 7; k++) {
            for (int i = 0; i < newArray.length; i++) {

                // if the move doesn't fall into the range of the board, remove it from the list
                if ((newArray[i][0] < 0) | (newArray[i][0] > 7) | (newArray[i][1] < 0) | (newArray[i][1] > 7)) {

                    //System.out.println("newArray " + Arrays.deepToString(newArray));
                    //System.out.println("newArray Index " + newArray[i][0] + " " + newArray[i][1]);

                    newArray = removeElement(newArray, i);


                    //System.out.println("newArray " + Arrays.deepToString(newArray));
                    break;
                }
            }
        }

        // Check if the space has already been visited, if so remove it from possible moves

        // Check the list for as many times as there are values in the list
        for (int j = 0; j < 8; j++) {
            // Iterate Through and remove when the values equal; Break and start from beginning
            for (int i = 0; i < newArray.length; i++) {
                for (int[] ints : _spacesVisited) {
                    if ((newArray[i][0] == ints[0]) && (newArray[i][1] == ints[1])) {
                        //System.out.println("visited "+newArray[i][0] + " " + newArray[i][1]);

                        newArray = removeElement(newArray, i);
                        //System.out.println("visited compare" + Arrays.deepToString(newArray));
                        break;
                    }
                }
            }
        }
        return newArray;
    }

    public int[][] removeElement(int[][] originalArray, int indexRemoved) {
        // Create a new array 1 less than the length of the original Array
        int[][] newArray;
        if (originalArray.length == 1) {
            // If the Array Length Is one Set to a Value that can't be reached otherwise
            newArray = new int[1][2];
            newArray[0][0] = 10;
            // Could set to null if using ArrayList
        } else {
            newArray = new int[originalArray.length - 1][2];
            // Iterate through the array
            for (int i = 0, k = 0; i < originalArray.length; i++) {
                // If i equals the desired index skip the next section of the loop
                if (i == indexRemoved) {
                    continue;
                }
                // Else add the value to the next index in the new Array
                newArray[k][0] = originalArray[i][0];
                newArray[k][1] = originalArray[i][1];
                k++;

            }
        }
        return newArray;
    }


    public int[][] addElement(int[][] originalArray, int value1, int value2) {
        // Create a new array 1 less than the length of the original Array
        int[][] newArray = new int[originalArray.length + 1][2];
        for (int i = 0, k = 0; i < originalArray.length; i++) {
            // Add Old Array Values to New Array
            newArray[k][0] = originalArray[i][0];
            newArray[k][1] = originalArray[i][1];
            k++;
        }
        // Add two new values to the end of the Array
        int lastIndex = newArray.length - 1;
        newArray[lastIndex][0] = value1;
        newArray[lastIndex][1] = value2;

        return newArray;
    }


    public int[][] stripElement(int[][] originalArray, int indexKept) {
        // Create a new array 1 Long and 2 deep
        int[][] newArray = new int[1][2];

        // Iterate through the array
        for (int i = 0, k = 0; i < originalArray.length; i++) {
            // If i doesn't equal the desired index skip the next section of the loop
            if (i != indexKept) {
                continue;
            }
            // Else add the value to the next index in the new Array
            newArray[k][0] = originalArray[i][0];
            newArray[k][1] = originalArray[i][1];
            k++;
        }
        return newArray;
    }

    //    public void printBoard(String[][] board)
//    {
//
//        System.out.println("");
//
//        // Top of the Board Border
//        System.out.println("    ===========================");
//
//        // Print out the 2d Array of the Board
//        for (int i = 0; i < board.length; i++) {
//            // Numbers for the position on the side of the Board
//            System.out.print(" " + i +"  | ");
//
//            for (int j = 0; j < board[i].length; j++) {
//                // Print the Value in the Array and add 2 WhiteSpaces
//                System.out.print( board[i][j] + "  ");
//            }
//            // Right Border of the Board
//            System.out.print("|");
//            // Create a NewLine
//            System.out.println();
//            // If the loop is at the last iteration, add Bottom Border of Board
//            if(i == board.length-1) {
//                System.out.println("    ===========================");
//                // Numbers for the position on the bottom of the Board
//                System.out.println("      0  1  2  3  4  5  6  7  ");
//            }
//
//        }
//    }
    public void printBoard(String[][] board, int numMoves, int boardIteration) {

        System.out.println("");
        System.out.println("[" + boardIteration + "," + numMoves + "]");

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
                // Numbers for the position on the bottom of the Board
                System.out.println("      0  1  2  3  4  5  6  7  ");
            }

        }
    }
}