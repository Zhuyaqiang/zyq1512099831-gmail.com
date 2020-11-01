package array;

import java.util.Arrays;

/**
 * 生命游戏
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 示例：
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class A0289 {
    public static void main(String[] args) {
        int[][] board =  {{0,1,0}, {0,0,1}, {1,1,1}, {0,0,0}};
        gameOfLife2(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }
    // 暴力法
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        if (m == 0)
            return;
        int n = board[0].length;
        if (n == 0)
            return;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            copy[i] = Arrays.copyOf(board[i], n);
        }
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++)
                System.out.print(copy[i][j]);
            System.out.println();
        }
        System.out.println();
        int[][] direction = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int newX = i + direction[k][0];
                    int newY = j + direction[k][1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                        if (copy[newX][newY] == 1)
                            count ++;
                    }
                }
                if (count < 2) {
                    board[i][j] = 0;
                } else if (count > 3) {
                    board[i][j] = 0;
                } else if (count == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }
    public static void gameOfLife2(int[][] board) {
        int m = board.length;
        if (m == 0)
            return;
        int n = board[0].length;
        if (n == 0)
            return;
        int[][] direction = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};
        // 原0后1为-1, 原1后0为-2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int newX = i + direction[k][0];
                    int newY = j + direction[k][1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                        if (board[newX][newY] == 1 || board[newX][newY] == -2) {
                            count ++;
                        }
                    }
                }
                if (count < 2) {
                    if (board[i][j] == 1)
                        board[i][j] = -2;
                } else if (count > 3) {
                    if (board[i][j] == 1)
                        board[i][j] = -2;
                } else if (count == 3) {
                    if (board[i][j] == 0)
                        board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1)
                    board[i][j] = 1;
                else if (board[i][j] == -2)
                    board[i][j] = 0;
            }
        }
    }
}
