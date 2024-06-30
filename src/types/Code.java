package types;

import java.util.Random;

public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};

        System.out.println(sum(numbers)); // 11
    }

    public static int sum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }

    public static double average(int[] numbers) {
        Double sum = 0.0;
        for (int number : numbers) {
            sum += number;
        }

        return sum / numbers.length;
    }

    public static Integer minimumElement(int[] integers) {
        if (integers.length < 1) {
            return null;
        } else {
            int minElement = integers[0];
            for (int number : integers) {
                if (number < minElement) {
                    minElement = number;
                }
            }

            return minElement;
        }
    }

//    public static String asString(int[] elements) {
//        String words = "";
//        for (int element : elements) {
//            if (words.isEmpty()) {
//                words = String.valueOf(element);
//            } else {
//                words = words +  ", " + element;
//            }
//        }
//
//        return words;
//    }

    public static String asString(int[] elements) {
        StringBuilder words = new StringBuilder();
        for (int element : elements) {
            if (words.isEmpty()) {
                words = new StringBuilder(String.valueOf(element));
            } else {
                words.append(", ").append(element);
            }
        }

        return words.toString();
    }

    public static Character mode(String input) {
        if (input.isEmpty()) {
            return null;
        } else {
            int count = 0;
            int currentCount = 1;
            char letter = 0;
            char currentLetter;
            for (int i = 0; i < input.length(); i++) {
                currentLetter = input.charAt(i);
                if (currentCount > count) {
                    count = currentCount;
                    letter = currentLetter;
                }
                for (int j = 1; j < input.length(); j++) {
                    if (input.charAt(i) == input.charAt(j)) {
                        currentCount += 1;
                    }
                }
            }

            return letter;
        }
    }

//    public static String squareDigits(String s) {
//        String newString = "";
//        for (int i = 0; i < s.length(); i++) {
//            if (Character.isDigit(s.charAt(i))) {
//                int number = Integer.parseInt(String.valueOf(s.charAt(i)));
//                int square = number * number;
//                newString += square;
//            } else {
//                newString += s.charAt(i);
//            }
//        }
//
//        return newString;
//    }

    public static String squareDigits(String s) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int number = Integer.parseInt(String.valueOf(s.charAt(i)));
                int square = number * number;
                newString.append(square);
            } else {
                newString.append(s.charAt(i));
            }
        }

        return newString.toString();
    }

    public static String addWord(String s) {
        StringBuilder newString = new StringBuilder("Hello");
        for (char c : s.toCharArray()) {
            newString.append(c);
        }

        return newString.toString();
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();

        int isolatedCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] && isIsolated(i, j)) {
                    isolatedCount += 1;
                }
            }
        }

        return isolatedCount;
    }

    public static boolean isIsolated(int row, int col) {
        boolean[][] matrix = getSampleMatrix();
//        printMatrix(matrix);

        if (row == 0) {
            return firstRow(matrix, col);
        } else if (row == 9) {
            return lastRow(matrix, col);
        } else if (col == 0) {
            return !matrix[row - 1][0] && !matrix[row - 1][1] && !matrix[row][1] &&
                    !matrix[row + 1][0] && !matrix[row + 1][1];
        } else if (col == 9) {
            return !matrix[row - 1][9] && !matrix[row - 1][8] && !matrix[row][8] &&
                    !matrix[row + 1][8] && !matrix[row + 1][9];
        } else {
            return !matrix[row][col + 1] && !matrix[row + 1][col + 1] && !matrix[row + 1][col] &&
                    !matrix[row + 1][col - 1] && !matrix[row][col - 1] && !matrix[row - 1][col - 1] &&
                    !matrix[row - 1][col] && !matrix[row - 1][col + 1];
        }
    }

    private static boolean firstRow(boolean[][] matrix, int col) {
        if (col == 0) {
            return !matrix[0][1] && !matrix[1][0] && !matrix[1][1];
        } else if (col == 9) {
            return !matrix[0][8] && !matrix[1][8] && !matrix[1][9];
        } else {
            return !matrix[0][col - 1] && !matrix[0][col + 1] &&
                    !matrix[1][col - 1] && !matrix[1][col] && !matrix[1][col + 1];
        }
    }

    private static boolean lastRow(boolean[][] matrix, int col) {
        if (col == 0) {
            return !matrix[8][0] && !matrix[8][1] && !matrix[9][1];
        } else if (col == 9) {
            return !matrix[9][8] && !matrix[8][8] && !matrix[8][9];
        } else {
            return !matrix[9][col - 1] && !matrix[9][col + 1] &&
                    !matrix[8][col - 1] && !matrix[8][col] && !matrix[8][col + 1];
        }
    }

    /*private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }*/

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
