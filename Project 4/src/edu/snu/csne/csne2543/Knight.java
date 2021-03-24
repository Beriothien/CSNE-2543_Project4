/*
 *  Project # - Project 4
 *  Knight
 *
 *  William, Hibbert
 *  3/11/2021
 */
package edu.snu.csne.csne2543;

public class Knight {

    private String _knightSymbol = null;
    private int[][] _currentPosition = new int[1][2];

    // Create A Constructor:
    public Knight(String symbol, int xPosition, int yPosition) {
        _knightSymbol = symbol;
        _currentPosition[0][0] = xPosition;
        _currentPosition[0][1] = yPosition;
    }

    public String getKnightSymbol() {
        return _knightSymbol;
    }

    //Get and Set Symbol
    public void setKnightSymbol(String symbol) {
        _knightSymbol = symbol;
    }

    public int getCurrentX() {
        return _currentPosition[0][0];
    }

    // Get and Set X
    public void setCurrentX(int x) {
        _currentPosition[0][0] = x;
    }

    public int getCurrentY() {
        return _currentPosition[0][1];
    }

    //Get and Set Y
    public void setCurrentY(int y) {
        _currentPosition[0][1] = y;
    }

    public int[][] knightMoves(int x, int y) {
        // Array to return with moves
        return new int[][]{{x + 2, y + 1}, {x + 2, y - 1}, {x - 2, y + 1}, {x - 2, y - 1}, {x - 1, y + 2}, {x + 1, y + 2}, {x - 1, y - 2}, {x + 1, y - 2}};
    }


}
