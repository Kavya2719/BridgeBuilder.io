package BridgeBuilderAdv;
import java.lang.Math;
import java.util.ArrayList;

public class Engineer {
    char token; boolean hardMode;

    // Initialize the token and set the hardMode.
    public Engineer(boolean hardMode){
        this.hardMode = hardMode;
        this.token = '0';
    }

    // Based on the difficulty level (hardMode), make a move on
    // the game board at a position determined by the Engineer's
    // strategy. In easy mode, select a random empty position. In hard
    // mode, select the next empty position after the player's last
    // position, or if the row is full, select the topmost empty
    // position in the same column
    public void makeMove(GameBoard board, int playerLastRow, int playerLastCol){
        int size = board.getSize();

        if(hardMode){
            // trying to place in right
            for(int col = playerLastCol; col < size; col++){
                if(board.isPositionEmpty(playerLastRow, col)){
                    board.placeToken(playerLastRow, col, this.token);
                    return;
                }
            }

            // trying to place in the top
            for(int row = 0; row < size; row++){
                if(board.isPositionEmpty(row, playerLastCol)){
                    board.placeToken(row, playerLastCol, this.token);
                    return;
                }
            }
        }

        // Easy Mode
        ArrayList<int[]> list = board.getEmptyCellsList();
        size = list.size();
        
        int index = (int)(Math.random()*size); // randomly choosed index
        int[] cell = list.get(index);
        int row = cell[0], col = cell[1];

        board.placeToken(row, col, token);
    }

    // Return the token of the engineer.
    public char getToken(){
        return token;
    }
}
