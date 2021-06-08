package pk1;

public class TicTacToe {
	private String[][] board =  { { "     ", "     ", "     " }, { "     ", "     ", "     " }, { "     ", "     ", "     " } };
	private String nextPlayer = "X";
	private String winner = null;
	private int numberOfMove = 0;
	public TicTacToe() {}
	
    public boolean setMove(int x, int y, String player) {
        String input = "  " + player + "  ";
        
        if (!player.equals(nextPlayer)) {
            return false;
        }

        if (board[x-1][y-1].equals("  X  ") || board[x-1][y-1].equals("  O  "))
            return false;

        board[x - 1][y - 1] = input;

        if (board[x-1][0].equals(input) && board[x-1][1].equals(input) && board[x-1][2].equals(input)) // check x row
            winner = player;
        if (board[1][y-1].equals(input) && board[1][y-1].equals(input) && board[2][y-1].equals(input)) // check y column
            winner = player;
        if (x == y || x == (3 - y)) { // check diagonal
            if (board[0][0].equals(input) && board[1][1].equals(input) && board[2][2].equals(input))
                winner = player;
            if (board[0][2].equals(input) && board[1][1].equals(input) && board[2][0].equals(input))
                winner = player;
        }

        if (nextPlayer.equals("X"))
            nextPlayer = "O";
        else if (nextPlayer.equals("O"))
            nextPlayer = "X";

        System.out.println(nextPlayer);
        numberOfMove++;
        return true;
    }
    public String checkWin() {
        if (numberOfMove == 9)
            return "draw";
        return winner;
    }

    @Override
    public String toString() {
        String display = "";
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                display += "|" + board[i][j] ;
            }
            display += "|\n-------------------\n";
        }

        return display;
    }
}

