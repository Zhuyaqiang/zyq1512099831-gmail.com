package other;

/**
 * 顺时针打印矩阵
 *
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。



 示例 1：

 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 输出：[1,2,3,6,9,8,7,4,5]
 示例 2：

 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 输出：[1,2,3,4,8,12,11,10,9,5,6,7]


 限制：

 0 <= matrix.length <= 100
 0 <= matrix[i].length <= 100
 */
public class A0029 {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[] ints = spiralOrder(matrix);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
    public static int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
                if (m == 0)
            return new int[0];
        int n = matrix[0].length;
        if (n == 0)
            return new int[0];
        int N = m * n;
        int[] res = new int[N];
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] seen = new boolean[m][n];
        int dirCount = 0;
        int count = 0, i = 0, j = 0;
        while (count < N) {
            res[count] = matrix[i][j];
            seen[i][j] = true;
            count ++;
            int newX = i + direction[dirCount][0];
            int newY = j + direction[dirCount][1];
            if (newX < 0 || newX >= m || newY < 0  || newY >= n || seen[newX][newY] == true) {
                dirCount = (dirCount + 1) % 4;
                newX = i + direction[dirCount][0];
                newY = j + direction[dirCount][1];
            }
            i = newX;
            j = newY;
        }
        return res;
    }
}
