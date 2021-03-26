/*
 *  Project # - Project 4
 *  Heuristic
 *
 *  William, Hibbert
 *  3/11/2021
 */
package edu.snu.csne.csne2543;

// Import Statements

import java.util.Arrays;

/**
 * Heuristic
 * <p>
 * This Class Creates a Knight Object, determines if its moves are valid, and stay on the bored, and keeps
 * track of previous positions of the Knight. This Class also Outputs ASCII visuals to show where the
 * Knights current position is and where it has previously been.
 *
 * @author William Hibbert
 */
public class Heuristic {

    // Create An Array That Holds the Values of The Board
    private final String[][] _board = new String[8][8];
    // Create An Array That Holds Coordinates Visited
    private int[][] _spacesVisited = new int[1][2];
    private int[][] _spacesVisitedLookAhead = _spacesVisited;
    // Create an Object Knight
    private Knight objKnight;
    private final int[][] heuristic =
                {{2, 3, 4, 4, 4, 4, 3, 2},
                {3, 4, 6, 6, 6, 6, 4, 3},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {3, 4, 6, 6, 6, 6, 4, 3},
                {2, 3, 4, 4, 4, 4, 3, 2}};

    public Heuristic() {
        // Create an Empty Constructor
    }


    // Create Chessboard Constructor
    public void startBoard(String knightSymbol, int knightXPosition, int knightYPosition, int iteration) {

        objKnight = new Knight(knightSymbol, knightXPosition, knightYPosition);
        emptySpacesVisited();
        _spacesVisited[0][0] = objKnight.getCurrentX();
        _spacesVisited[0][1] = objKnight.getCurrentY();

        // Fill the Spaces with "."
        for (String[] strings : _board) {
            Arrays.fill(strings, ".");
        }

        // Put Knight in position ( 3 , 4 ) on the Board
        _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = objKnight.getKnightSymbol();

        //Start MainLoop
        int numberOfMoves = 0;

        // Loop until Break
        while (true) {
            numberOfMoves += 1;


            // Get Moves Available to the Knight , Store in a 2D Array with coordinates (x , y)
            int[][] movesToChoseFrom = objKnight.knightMoves(objKnight.getCurrentX(), objKnight.getCurrentY());
            // Make sure moves are Valid, then Return A List with All Valid Positions
            movesToChoseFrom = movesAreValid(movesToChoseFrom);

            // Due to lack of null from not using ArrayList, if the Array length is 1, and the value is 10 it signals no valid moves
            // Check for Break, Break if Values are 10,
            if ((movesToChoseFrom.length == 1) && (movesToChoseFrom[0][0] == 10)) {
                System.out.println(" ");
                System.out.println("Board Iteration: " + iteration);
                System.out.println("Number of Moves: " + numberOfMoves);

                break;
            }

            // Mark Old Position as A Space Visited
            _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = "@";

            // Add Old Position to _spaceVisited
            setSpacesVisited(objKnight.getCurrentX(), objKnight.getCurrentY());

            // Select A Move from Array of Valid moves and Update Current Position
            int[][] choice = selectMove(movesToChoseFrom);
            objKnight.setCurrentX(choice[0][0]);
            objKnight.setCurrentY(choice[0][1]);

            // Move the Knight's Symbol on the Board
            _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = objKnight.getKnightSymbol();

            // Increase the total number of Moves


            // Print out Updated Board
            printBoard(_board, numberOfMoves, iteration);

            // Repeat
        }
    }



    //  Set Coordinates in _spacesVisited
    public void setSpacesVisited(int x, int y) {
        // Append a new value to the Array
        _spacesVisited = addElement(_spacesVisited, x, y);
    }
    public void setSpacesVisitedLookAhead(int x, int y) {
        // Append a new value to the Array
        _spacesVisitedLookAhead = addElement(_spacesVisitedLookAhead, x, y);
    }

    public void emptySpacesVisited() {
        _spacesVisited = new int[1][2];
        _spacesVisited[0][0] = objKnight.getCurrentX();
        _spacesVisited[0][1] = objKnight.getCurrentY();

    }
    public void emptySpacesVisitedLookAhead() {
        _spacesVisitedLookAhead = _spacesVisited;

    }



    // Select Move And Compare It to Heuristic Diagram
    public int[][] selectMove(int[][] moves) {
        // Initialize an array to return
        int[][] choice = new int[1][2];
        // Create a List the Same Length as moves with the value of the move in the same Index as moves
        int[] valueOfMove = new int[moves.length];
        System.out.println(Arrays.toString(valueOfMove));
        System.out.println(Arrays.deepToString(moves));
        for(int i = 0; i<moves.length; i++)
        {
            valueOfMove[i] = heuristic[moves[i][0]][moves[i][1]];
        }

        // find the Index of the Lowest Value in valueOfMove
        int min = valueOfMove[0];
        int minIndex = 0;

        // Iterate through valueOfMove and find the minimum and its index
        for (int i = 1; i <= valueOfMove.length-1; i++) {
            if(min == valueOfMove[i]){
                int holdX = objKnight.getCurrentX();
                int holdY = objKnight.getCurrentY();

            }

            else if (min > valueOfMove[i]) {
                min = valueOfMove[i];
                minIndex = i;
            }

        }

        System.out.println(minIndex);

        choice[0][0] = moves[minIndex][0];
        choice[0][1] = moves[minIndex][1];
        System.out.println(Arrays.deepToString(choice));


        //return Movement Choice
        return choice;
    }
    public int[][] selectMoveLookAhead(int[][] moves) {
        // Initialize an array to return
        int[][] choice = new int[2][2];
        // Create a List the Same Length as moves with the value of the move in the same Index as moves
        int[] valueOfMove = new int[moves.length];
        System.out.println(Arrays.toString(valueOfMove));
        System.out.println(Arrays.deepToString(moves));
        for(int i = 0; i<moves.length; i++)
        {
            valueOfMove[i] = heuristic[moves[i][0]][moves[i][1]];
        }

        // find the Index of the Lowest Value in valueOfMove
        int min = valueOfMove[0];
        int minIndex = 0;

        // Iterate through valueOfMove and find the minimum and its index
        for (int i = 1; i <= valueOfMove.length-1; i++) {
            if(min == valueOfMove[i]){
                int holdX = objKnight.getCurrentX();
                int holdY = objKnight.getCurrentY();
                minIndex = equalValues( minIndex , i);

            }

            else if (min > valueOfMove[i]) {
                min = valueOfMove[i];
                minIndex = i;
            }

        }

        System.out.println(minIndex);

        choice[0][0] = moves[minIndex][0];
        choice[0][1] = moves[minIndex][1];
        choice[1][0] = minIndex;
        System.out.println(Arrays.deepToString(choice));


        //return Movement Choice
        return choice;
    }

    public int equalValues( int minIndex, int currentIndex){
        int minNet = minIndex;
        // Look Ahead for Value 1, returns Net number
        int moveValueMinIndex = lookAhead(minIndex);

        // Look Ahead for Value 2
        int moveValueCurrentIndex = lookAhead(currentIndex);

        // Choose smallest net Number

        if(moveValueCurrentIndex < moveValueMinIndex)
        {
            minNet = moveValueCurrentIndex;
        }
        return minNet;
    }

    public int lookAhead(int currentPosition){


        int positionValue = 0;
        for(int i = 0; i<2;i++) {
            emptySpacesVisitedLookAhead();
            // Get Moves Available to the Knight , Store in a 2D Array with coordinates (x , y)
            int[][] movesToChoseFrom = objKnight.knightMoves(objKnight.getCurrentX(), objKnight.getCurrentY());
            // Make sure moves are Valid, then Return A List with All Valid Positions
            movesToChoseFrom = movesAreValid(movesToChoseFrom);

            // Add Old Position to _spaceVisited
            setSpacesVisitedLookAhead(objKnight.getCurrentX(), objKnight.getCurrentY());

            // Select A Move from Array of Valid moves and Update Current Position
            int[][] choice = selectMoveLookAhead(movesToChoseFrom);
            objKnight.setCurrentX(choice[0][0]);
            objKnight.setCurrentY(choice[0][1]);

            positionValue += choice[1][0];
        }
        return positionValue;

    }


    public int[][] movesAreValid(int[][] moves) {
        // Create a value to return
        int[][] newArray = moves;
        // Iterate through list of possible moves
        for (int k = 0; k < 7; k++) {
            for (int i = 0; i < newArray.length; i++) {

                // if the move doesn't fall into the range of the board, remove it from the list
                if ((newArray[i][0] < 0) | (newArray[i][0] > 7) | (newArray[i][1] < 0) | (newArray[i][1] > 7)) {
                    newArray = removeElement(newArray, i);
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

                        newArray = removeElement(newArray, i);
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

    public void printBoard(String[][] board, int numMoves, int _boardIteration) {

        System.out.println(" ");
        System.out.println("[" + _boardIteration + "," + numMoves + "]");

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