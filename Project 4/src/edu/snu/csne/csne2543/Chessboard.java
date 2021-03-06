 /*
  *  Project # - Project 4
  *  Chessboard
  *
  *  William, Hibbert
  *  3/11/2021
  */
 package edu.snu.csne.csne2543;

// Import Statements

 import java.util.Arrays;
 import java.util.Random;

 /**
  * Chessboard
  * <p>
  * This Class Creates a Knight Object, determines if its moves are valid, and stay on the bored, and keeps
  * track of previous positions of the Knight. This Class also Outputs ASCII visuals to show where the
  * Knights current position is and where it has previously been.
  *
  * @author William Hibbert
  */
 public class Chessboard {

     // Create An Array That Holds the Values of The Board
     private final String[][] _board = new String[8][8];
     // Create An Array That Holds Coordinates Visited
     private int[][] _spacesVisited = new int[1][2];
     // Create an Object Knight
     private Knight objKnight;
     // Holds Current boardIteration
     private int _boardIteration = 0;
     // Array that holds all numberOfMoves from Iterations
     private int[] _numMovesArray = new int[1];
     // Boolean to indicate stopping in brute force attempt
     private boolean _stop = false;

     public Chessboard()
     {
         // Create an Empty Constructor
     }


     // Create A Method to Create and Start Simulation
     public void startBoard(String knightSymbol, int knightXPosition, int knightYPosition, int currentIteration) {
         // Create Knight Piece Object
         objKnight = new Knight(knightSymbol, knightXPosition, knightYPosition);
         // Empty the Array _spacesVisited, used when iterating through multiple times
         emptySpacesVisited();
         // Set current Knight position as a Space Visited
         _spacesVisited[0][0] = objKnight.getCurrentX();
         _spacesVisited[0][1] = objKnight.getCurrentY();
         // Initialize the variable to currentIteration
         _boardIteration = currentIteration;

         // Fill the Spaces with "."
         for (String[] strings : _board) {
             Arrays.fill(strings, ".");
         }

         // Put Knight in given position on the Board
         _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = objKnight.getKnightSymbol();

         //Start MainLoop
         int numberOfMoves = 1;

         // Loop until Break
         while (true) {

             numberOfMoves += 1;

             // Get Moves Available to the Knight , Store in a 2D Array with coordinates (x , y)
             int[][] movesToChoseFrom = objKnight.knightMoves(objKnight.getCurrentX(), objKnight.getCurrentY());

             // Make sure moves are Valid, then Return A List with All Valid Positions
             movesToChoseFrom = movesAreValid(movesToChoseFrom);

             // Due to lack of null from not using ArrayList, if the Array length is 1, and the value is 10 it signals no valid moves
             // Check for Break, Break if Values are 10,
             if ((movesToChoseFrom.length == 1) && (movesToChoseFrom[0][0] == 10))
             {
                 System.out.println("");
                 System.out.println("Board Iteration: " + _boardIteration);
                 System.out.println("Number of Moves: " + numberOfMoves);

                 // Creates beginning of _numMovesArray, adds a value to previously undefined value
                 if (_numMovesArray[0] == 0)
                 {
                     _numMovesArray[0] = numberOfMoves;
                 }
                 // Adds current Number of Moves to Array _numMovesArray
                 else
                 {
                     _numMovesArray = addElement(_numMovesArray, numberOfMoves);
                 }
                 // If all moves can be made, Stop
                 if (numberOfMoves == 64) {
                     _stop = true;
                     break;
                 }


                 break;
             }

             // Mark Old Position as A Space Visited
             _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = "@";

             // Add Old Position to _spaceVisited
             setSpacesVisited(objKnight.getCurrentX(), objKnight.getCurrentY());

             // Select A Move Randomly from Array of Valid moves and Update Current Position
             int[][] choice = randMove(movesToChoseFrom);
             objKnight.setCurrentX(choice[0][0]);
             objKnight.setCurrentY(choice[0][1]);

             // Move the Knight's Symbol on the Board
             _board[objKnight.getCurrentY()][objKnight.getCurrentX()] = objKnight.getKnightSymbol();

             // Increase the total number of Moves


             // Print out Updated Board
             printBoard(_board, numberOfMoves, _boardIteration);

             // Repeat
         }
     }




     // Get _numMovesArray
     public int[] getNumMovesArray() {
         return _numMovesArray;
     }

     //  Set Coordinates in _spacesVisited
     public void setSpacesVisited(int x, int y) {
         // Append a new value to the Array
         _spacesVisited = addElement(_spacesVisited, x, y);
     }

     // Empties the _spacesVisited Array
     public void emptySpacesVisited() {
         _spacesVisited = new int[1][2];
         _spacesVisited[0][0] = objKnight.getCurrentX();
         _spacesVisited[0][1] = objKnight.getCurrentY();

     }

     // Returns the _stop Boolean to Initialization Class
     public boolean stop() {
         return _stop;
     }

     // Randomly select
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

     // Checks if Moves in the Given Array are Valid moves
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

     // Removes Elements from a Two Dimensional Array
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

    // Adds an Element to a Two Dimensional Array
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

     // Adds an Element to a One Dimensional Array
     public int[] addElement(int[] originalArray, int value1) {
         // Create a new array 1 less than the length of the original Array
         int[] newArray = new int[originalArray.length + 1];
         // Add Old Array Values to New Array
         System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
         // Add new value to the end of the Array
         int lastIndex = newArray.length - 1;
         newArray[lastIndex] = value1;

         return newArray;
     }

     // Strips an Array to One Given Index
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

     // Prints an Updated Version of _board
     public void printBoard(String[][] board, int numMoves, int _boardIteration) {

         System.out.println("");
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