import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<Integer> cols = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            cols.add(i);
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '.';
            }
        }

        List<char[][]> answers = new ArrayList<>();

        queensHelper(answers, n, 0, board, cols);
//        for (int i = 0; i < answers.size(); i++) {
//            printBoard(answers.get(i));
//            System.out.println();
//        }

        return answers;
    }

    public static void queensHelper(List<char[][]> permutations, int n, int row, char[][] board, List<Integer> cols) {
//        System.out.println(cols);
        if (cols.isEmpty()) {
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board.length; j++) {
////                    if (board[i][j] != 'Q') {
////                        board[i][j] = '.';
////                    }
//                }
//            }
//            printBoard(board);
            char[][] copy = copyOf(board);
            permutations.add(copy);
            return;
        }
//        boolean setQueen = false;
        for (int i = 0; i < cols.size(); i++) {
            int col = cols.remove(i);
            if (!checkDiagonal(board, row, col)) {
                board[row][col] = 'Q';
//                setQueen = true;
//                System.out.print("col " + col + " ");
//                System.out.println("row " + row);
                queensHelper(permutations, n, row+1, board, cols);
                board[row][col] = '.';
                cols.add(i, col);
            }
            else {
                cols.add(i, col);
//                return;
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
//    public static void queensHelper(List<Integer> cols, List<char[][]> permutations, int n) {
//        for (int i = 0; i < n; i++) {
//            char[][] board = new char[n][n];
//            boolean putQueen = false;
//            for (int j = 0; j < n; j++) {
//                int col = cols.remove(j);
//                if (!checkDiagonal(board, i, j)) {
//                    board[i][j] = 'Q';
//                }
//                else {
//                    cols.add(j, col);
//                }
//            }
//        }
//    }
}
