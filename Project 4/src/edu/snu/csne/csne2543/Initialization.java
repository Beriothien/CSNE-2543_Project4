/*
 *  Initialization
 *
 *
 *  William, Hibbert
 *  3/11/2021
 */
package edu.snu.csne.csne2543;

// Import Statements

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Initialization {

    private final Scanner stdin = new Scanner(System.in);
    private final Chessboard board = new Chessboard();
    private int _startingX;
    private int _startingY;
    private boolean _promptUser = true;

    public void run() {
        while (_promptUser) {

            // Prompt User For Which Version Of App To Run
            System.out.println("Selections:");
            System.out.println("    1. Run Simulation Once");
            System.out.println("    2. Run With Heuristics ");
            System.out.println("    3. Run 1000x, Print Data");
            System.out.println("    4. Run until solution");
            System.out.println("Type '1','2','3', or '4'");

            // Get User Input

            String selection = stdin.nextLine();

            // Respond to User Input
            switch (selection) {
                case "1":
                    // Prompt User For Manual Or Random Starting Location
                    essentials();

                    // Create a New Board and Knight Objects
                    board.startBoard("K", _startingX, _startingY, 1);

                    // End Prompting Loop
                    _promptUser = false;

                    break;
                case "2":
                    System.out.println("Not Programmed Yet");
                    break;
                case "3":
                    // Prompt User For Starting Location, Output
                    essentials();
                    // Create a New Board and Knight
                    for (int i = 1; i < 1000; i++) {
                        board.startBoard("K", _startingX, _startingY, i);
                    }
                    System.out.println(Arrays.toString(board.getNumMovesArray()));

                    // End Prompting Loop
                    _promptUser = false;


                    break;
                case "4":
                    // Prompt User For Starting Location, Output
                    essentials();
                    // Create a New Board and Knight
                    int i = 1;
                    while (i <= 1000) {
                        board.startBoard("K", _startingX, _startingY, i);
                        i++;
                    }
                    System.out.println(Arrays.toString(board.getNumMovesArray()));

                    // End Prompting Loop
                    _promptUser = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("Type '1','2','3', or '4'");
                    break;
            }
        }
    }


    public void startingLocation() {
        System.out.println("Choose A Starting Position:");
        System.out.println("    1. Manual Input For Location");
        System.out.println("    2. Random Starting Location");
        System.out.println("Type '1', or '2' to make a selection");
        String selection = stdin.nextLine();
        if (selection.equals("1")) {
            System.out.println("X Location:");
            _startingX = stdin.nextByte();
            System.out.println("Y Location:");
            _startingY = stdin.nextByte();
        } else if (selection.equals("2")) {
            Random rand = new Random();
            _startingX = rand.nextInt(7);
            _startingY = rand.nextInt(7);
            System.out.println("X Generated = " + _startingX);
            System.out.println("Y Generated = " + _startingY);
        }
    }


    // Essentials For a Running Program
    public void essentials() {
        // Prompt User For Manual Or Random Starting Location
        startingLocation();
        // Initial OutPut To show how to read [0,0] in output
        System.out.println("[ Iteration, Move ] <--- Brackets show Current " +
                "Board Iteration and Board Move");
        // End Prompting Loop
        _promptUser = false;
    }












}
