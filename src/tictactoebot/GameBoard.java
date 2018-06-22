/*
 * Created by WilliamBD
 * On 19/06/2018
 * to simulate a tic tac toe game board.
 */
package tictactoebot;

/**
 *
 * @author widea9928
 */
public class GameBoard {

    int[][] board = new int[3][3];

    /**
     * initializes board with all values at zero.
     */
    public void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * adds player move to the board.
     *
     * @param move the move the network specifies
     */
    public void MyMove(int move) {
        //if move is within the first 3 squares
        if (move < 3) {
            if (board[move][0] == 0) {
                board[move][0] = 1;
            }
            return;
        }

        //if move is on the second 3 squares
        move = move - 3;
        if (move < 3) {
            if (board[move][1] == 0) {
                board[move][1] = 1;
            }
            return;
        }

        //if move is on the last 3 squares.
        move = move - 3;
        if (move < 3) {
            if (board[move][2] == 0) {
                board[move][2] = 1;
            }
        }
    }

    /**
     * updates the board to the player 2 move.
     *
     * @param move
     */
    public void OponentMove(int move) {
        //if move is within the first 3 squares
        if (move < 3) {
            if (board[move][0] == 0) {
                board[move][0] = -1;
            }
            return;
        }

        //if move is on the second 3 squares
        move = move - 3;
        if (move < 3) {
            if (board[move][1] == 0) {
                board[move][1] = -1;
            }
            return;
        }

        //if move is on the last 3 squares.
        move = move - 3;
        if (move < 3) {
            if (board[move][2] == 0) {
                board[move][2] = -1;
            }
        }
    }

    /**
     * returns 1 if player 1 is winer, 2 if player 2 is winner, 0 if no winner, 3 if tie.
     *
     * @return winner;
     */
    public int winCheck() {
        //verticals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                return board[i][1];
            }
        }
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[1][i] != 0) {
                return board[1][i];
            }
        }
        
        //checks diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != 0) {
            return board[1][1];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != 0) {
            return board[1][1];
        }
        
        //checks to ensure that there are still empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == 0) {
                    return 0;
                }
            }
        }
        //returns a tie game
        return 3;
    }

}
