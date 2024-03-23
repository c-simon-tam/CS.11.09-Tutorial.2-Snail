public class Snail {
    private static int[] turnRight(int di, int dj) {
        return new int[]{dj, -di};
    }

    private static boolean outOfBounds(int[][] a, int i, int j) {
        return a==null || i<0 || i>=a.length || j<0 || j>=a[0].length;
    }

    private static int[] flattenHelper(int[][] source, int[] dest) {
        travel(dest, source, false);
        return dest;
    }

    private static int[][] makeHelper(int[] source, int[][] dest) {
        travel(source, dest, true);
        return dest;
    }

    private static void travel(int[] array1d, int[][] array2d, boolean to2D) {
        int i=0, j=0, di=0, dj=1, current=0;
        boolean[][] visited = new boolean[array2d.length][array2d.length];
        while (!visited[i][j]) {
            visited[i][j] = true;
            if (to2D)
                array2d[i][j] = array1d[current++];
            else
                array1d[current++] = array2d[i][j];

            if (outOfBounds(array2d, i+di, j+dj) || visited[i+di][j+dj]) {
                int[] newV = turnRight(di, dj);
                di = newV[0];
                dj = newV[1];
            }
            i += di;
            j += dj;
        }
    }

    /**
     *
     * TODO 5
     *
     * Input: an n x n 2d array.
     * Output: a 1d array of length n^2 that contains the order of elements visited in a snail traversal of the 2d array.
     *
     * Example:
     *
     * Input:
     *
     * {
     *     {1,2,3},
     *     {4,5,6},
     *     {7,8,9}
     * }
     *
     * Output:
     *
     * {1,2,3,6,9,8,7,4,5}
     *
     * @param array2d
     * @return a 1d array containing the order of elements visited in a snail traversal of the 2d array.
     *         returns an empty array if array2d is not square.
     */
    public static int[] flattenSnail(int[][] array2d) {
        if (array2d==null || !isPerfectSquare(array2d))
            return new int[0];

        return flattenHelper(array2d, new int[array2d.length * array2d.length]);
    }


    /**
     *
     * TODO 6
     *
     * Input: a 1d array of length n, where n is a perfect square.
     * Output: a 2d array of width == height == sqrt(n) that represents a snail.
     *
     * Example:
     *
     * Input:
     *
     * {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
     *
     * Output:
     *
     * {
     *     {1,2,3,4,5},
     *     {16,17,18,19,6},
     *     {15,24,25,20,7},
     *     {14,23,22,21,8},
     *     {13,12,11,10,9},
     * }
     *
     *
     * @param array1d
     * @return a 2d array of width == height == sqrt(n), where n is the length of the inputted array, that represents a snail.
     *         returns an empty 2d array if the length of array1d is not a perfect square.
     */
    public static int[][] makeSnail(int[] array1d) {
        if (array1d==null || !isPerfectSquare(array1d))
            return new int[0][0];

        int n = (int) Math.sqrt(array1d.length);
        return makeHelper(array1d, new int[n][n]);
    }

    /**
     *
     * TODO 1
     *
     * Private helper method that prints the contents of a 1d array.
     * Used mainly for debugging purposes.
     *
     * @param array1d
     */
    private static void print1dArray(int[] array1d) {
        System.out.print("[");
        for (int x : array1d)
            System.out.print(x + " ");
        System.out.println("]");
    }

    /**
     *
     * TODO 2
     *
     * Private helper method that prints the contents of a 2d array.
     * Used mainly for debugging purposes.
     *
     * @param array2d
     */
    private static void print2dArray(int[][] array2d) {
        System.out.println("[");
        for (int[] row : array2d)
            print1dArray(row);
        System.out.println("]");
    }

    /**
     *
     * TODO 3
     *
     * Private helper method that checks to see if a 1d array is of a length that is a perfect square.
     *
     * @param array1d
     * @return
     */
    private static boolean isPerfectSquare(int[] array1d) {
        return Math.pow(Math.sqrt(array1d.length), 2) == Math.pow((int) Math.sqrt(array1d.length), 2);
    }


    /**
     *
     * TODO 4
     *
     * Private helper method that checks to see if a 2d array is a square.
     *
     * @param array2d
     * @return
     */
    private static boolean isPerfectSquare(int[][] array2d) {
        for (int[] row : array2d)
            if (row.length != array2d.length)
                return false;
        return true;
    }
}
