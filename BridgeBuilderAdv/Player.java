package BridgeBuilderAdv;

public class Player {
    char token; int score;

    // Initialize the token and score.
    public Player(){
        token = '+'; score = 0;
    }

    // Place the player's token on the game board at the specified row and column.
    public void makeMove(GameBoard board, int row, int col){
        board.placeToken(row, col, token);
    }

    // Return the player's token.
    public char getToken(){
        return token;
    }

    // Return the player's current score.
    public int getScore(){
        return score;
    }

    // Increase the player's score by the specified increment
    public void addScore(int increment){
        score += increment;
    }
}