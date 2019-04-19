import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    private int blankRow;
    private int blankCol;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        tiles = b;
        n = b.length;
        goal = new int[n][n];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                goal[i][j] = i*n + j + 1;
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }
        goal[n-1][n-1] = 0;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int pos = tiles[i][j];
                int row;
                int col;
                if (pos != 0) {
                    row = (pos-1)/n;
                    col = (pos-1)%n;
                }
                else {
                    continue;
                }
//                System.out.println("row " + row + "col " + col);
                dist += Math.abs(row-i);
                dist += Math.abs(col-j);
            }
        }
        return dist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        if (manhattan() == 0) {
            return true;
        }
        return false;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here`
        int inversions = numInversions();

        // if grid is odd
        if (n%2 == 1) {
            if (inversions%2 == 0) {
                return true;
            }
            return false;
        }

        // if grid is even &
        if ((n-blankRow)%2 == 0) {
            if (inversions%2 == 1) {
                return true;
            }
            return false;
        }

        if (inversions%2 == 0) {
            return true;
        }
        return false;
    }

    public int numInversions() {
        PriorityQueue<Integer> seen = new PriorityQueue<>();
        int inversions = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int num = tiles[row][col];
                if (num == 0) {
                    continue;
                }
                Iterator iter = seen.iterator();
                if (!seen.isEmpty()) {
                    int smaller = 0;
                    while(iter.hasNext()) {
                        int next = (Integer) iter.next();
                        if (next < num) {
                            smaller++;
                        }
                    }
                    inversions += (num-1-smaller);
//                    if (inversions > 0) {
//                        System.out.println(num);
//                    }
                }
                else {
                    inversions += num-1;
                }
                seen.add(num);
            }
        }
//        System.out.println(inversions);
        return inversions;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        LinkedList<Board> boards = new LinkedList<>();
        // not on top edge
        if (blankRow != 0) {
            int[][] board = copyBoard();
            int newRow = blankRow-1;
            int temp = board[blankRow][blankCol];
            board[blankRow][blankCol] = board[newRow][blankCol];
            board[newRow][blankCol] = temp;
            Board newBoard = new Board(board);
            boards.add(newBoard);
        }

        if (blankRow < n-1) {
            int[][] board = copyBoard();
            int newRow = blankRow+1;
            int temp = board[blankRow][blankCol];
            board[blankRow][blankCol] = board[newRow][blankCol];
            board[newRow][blankCol] = temp;
            Board newBoard = new Board(board);
            boards.add(newBoard);
        }

        // not on left edge
        if (blankCol != 0) {
            int[][] board = copyBoard();
            int newCol = blankCol-1;
            int temp = board[blankRow][blankCol];
            board[blankRow][blankCol] = board[blankRow][newCol];
            board[blankRow][newCol] = temp;
            Board newBoard = new Board(board);
            boards.add(newBoard);
        }

        if (blankCol < n-1) {
            int[][] board = copyBoard();
            int newCol = blankCol+1;
            int temp = board[blankRow][blankCol];
            board[blankRow][blankCol] =  board[blankRow][newCol];
            board[blankRow][newCol] = temp;
            Board newBoard = new Board(board);
            boards.add(newBoard);
        }
//        for (Board thing : boards) {
//            thing.printBoard();
//        }
        return boards;
    }

    public int[][] copyBoard() {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tiles[i][j];
            }
        }
        return board;
    }
    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
