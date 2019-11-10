package Lesson_7;

public class Matrix {
    public static void main(String[] args) {
        int countStкrings = 5;
        int countColumns = 5;
        int [][] matrix = new int[countStкrings][countColumns];
        int iterator = 1;

        int minString = 0;
        int minColumn = 0;
        int maxString = countStкrings - 1;
        int maxColumn = countColumns - 1;
        int countElements = countStкrings * countColumns;
        int j = 1;

        while (iterator <= countElements) {
            for (int i = minString; i <= maxString; i++) {
                j--;
                while (j <= maxColumn) {
                    matrix[i][j] = iterator;
                    iterator++;
                    j++;
                }
            }
            minString++;
            maxColumn--;
            j = maxColumn - 1;
            for (int i = maxString; i >= minString; i--) {
                j++;
                while (j >= minColumn) {
                    matrix[i][j] = iterator;
                    iterator++;
                    j--;
                }
            }
            maxString--;
            minColumn++;
            j = minColumn + 1;
        }
        for (int[] elem : matrix) {
            for (int elemin : elem) {
                System.out.print(elemin);
            }
            System.out.println();
        }
    }
}
