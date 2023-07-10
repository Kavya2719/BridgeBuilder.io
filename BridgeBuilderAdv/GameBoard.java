package BridgeBuilderAdv;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameBoard {
    char board[][]; int size;

    // Initialize the board to a 2D char array with '.' representing empty positions.
    public GameBoard(int size){
        this.size = size;
        board = new char[size][size];

        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                board[row][col] = '.';
            }
        }
    }

    // Place the given token at the specified row and column on the game board.
    public void placeToken(int row, int col, char token){
        board[row][col] = token;
    }

    // -- Check whether the specified position on the board is empty (denoted by '.').
    public boolean isPositionEmpty(int row, int col){
        return (board[row][col] == '.'); 
    }

    // Return the size of the game board.
    public int getSize(){
        return size;
    }
    
    // Print the game board on the console, including row and column numbers.
    public void displayBoard(){
        String str = " ";
        for(int col = 0; col < size; col++){
            str += " " + (char)('A' + col);
        }
        System.out.println(str);

        for(int row = 0; row < size; row++){
            str = "";
            str += (char)(row+'0');
            for(int col = 0; col < size; col++){
                str += " " + (char) board[row][col];
            }
            System.out.println(str);
        }
    }

    // search if there's a path exist for going from source to destination.
    private boolean bfs(ArrayList<int[]> src, ArrayList<int[]> dest){
        Queue<int[]> queue = new LinkedList<>();
        boolean visited[][] = new boolean[size][size];
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

        for(int[] curCell : src) {
            queue.add(curCell);
        }

        while(!queue.isEmpty()){
            int[] curCell = queue.poll();
            int row = curCell[0]; int col = curCell[1];
            
            if(visited[row][col]) continue;
            visited[row][col] = true;

            for(int[] targetCell : dest){
                if(row == targetCell[0] && col == targetCell[1]) return true;
            }

            for (int[] dir : directions) {
                int x = row + dir[0], y = col + dir[1];
                if(x < 0 || y < 0 || x >= size || y >= size) continue;
                if(board[x][y] == '+')
                    queue.add(new int[]{x,y});
            }
        }
        return false;
    }

    // Check whether the player has won the game in any direction (left-to-right, bottom-to-top, or diagonally).
    // Return 1 for left-to-right, 2 for bottom-to-top, 3 for diagonal, and 3 for no win.
    public int checkForWinDirection(Player player){
        // checking diagonal path
        boolean diagonalWin = true;
        for(int row = 0; row < size; row++){
            int col = row;
            if(board[row][col] != '+'){
                diagonalWin = false;
            }
        }
        if(diagonalWin) return 3;

        ArrayList<int[]> src = new ArrayList<int[]>();
        ArrayList<int[]> dest = new ArrayList<int[]>();

        // top to bottom
        src.clear(); dest.clear();
        for(int col = 0; col < size; col++){
            if(board[0][col] == '+')
                src.add(new int[]{0, col});
            dest.add(new int[]{size - 1, col});
        }
        if(bfs(src,dest)) return 2;

        // left to right
        src.clear(); dest.clear();
        for(int row = 0; row < size; row++){
            if(board[row][0] == '+')
                src.add(new int[]{row, 0});
            dest.add(new int[]{row, size - 1});
        }
        if(bfs(src,dest)) return 1;

        // no win
        return 0;
    }

    // Check whether the game board is full, indicating a tie.
    public boolean checkForTie(){
        boolean isTie = true;
        for(int row = 0 ; row < size; row++){
            for(int col = 0; col < size; col++){
                if(isPositionEmpty(row,col)){
                    isTie = false; break;
                }
            }
            if(!isTie) break;
        }
        return isTie;
    }

    // Returns a List of cells that are currently empty
    public ArrayList<int[]> getEmptyCellsList(){
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                if(isPositionEmpty(row,col)) 
                    list.add(new int[]{row,col});
            }
        }
        return list;
    }
}