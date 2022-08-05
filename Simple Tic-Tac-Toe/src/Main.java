import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] array = new char[3][3];
        int xMoves = 0;
        int oMoves = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = '_';
            }
        }

        printTable(array);

        while (true) {
            String moves = scanner.nextLine();

            try {
                String[] tempArr = moves.split(" ");

                int nextMoveRow = Integer.parseInt(tempArr[0]);
                int nextMoveCell = Integer.parseInt(tempArr[1]);

                if (nextMoveCell > 3 || nextMoveRow > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (array[nextMoveRow - 1][nextMoveCell - 1] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        if (xMoves == 0 || xMoves == oMoves) {
                            array[nextMoveRow - 1][nextMoveCell - 1] = 'X';
                            xMoves++;
                        } else if (xMoves > oMoves) {
                            array[nextMoveRow - 1][nextMoveCell - 1] = 'O';
                            oMoves++;
                        }
                        printTable(array);

                        int blankChars = getChars(array, '_');

                        boolean xWins = whoWins(array, 'X');
                        boolean oWins = whoWins(array, 'O');

                        if (xWins) {
                            System.out.println("X wins");
                            break;
                        } else if (oWins) {
                            System.out.println("O wins");
                            break;
                        }

                        if (blankChars <= 0) {
                            System.out.println("Draw");
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static void printTable(char[][] array) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static int getChars(char[][] array, char ch) {
        int chars = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == ch) {
                    chars++;
                }
            }
        }
        return chars;
    }

    public static boolean whoWins(char[][] array, char ch) {
        int chCount = 0;
        boolean centerPiece = false;
        for (char[] chars : array) {
            for (int j = 0; j < array.length; j++) {
                if (chars[j] == ch) {
                    chCount++;
                    if (chars[j] == array[1][1]) {
                        centerPiece = true;
                    }
                } else {
                    chCount = 0;
                }
            }
            if (chCount == 3) {
                return true;
            }
        }

        if (centerPiece) {
            if (array[0][0] == ch && array[2][2] == ch) {
                return true;
            } else if (array[0][2] == ch && array[2][0] == ch) {
                return true;
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (array[0][i] == ch && array[1][i] == ch && array[2][i] == ch) {
                return true;
            }
        }

        return false;
    }
}
