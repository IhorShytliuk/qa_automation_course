package hw;

import java.util.Scanner;

public class TicTacToe {

    static int boardSize = 3;

    public static void main(String[] args) {

        char[][] board = new char[boardSize][boardSize];
        initBoard(board);

        String[] players = initPlayers();
        int stepCnt = 0;
        boolean endGame = false;
        boolean winGame = false;

        while (!endGame && !winGame) {
            String player = players[stepCnt % 2];
            char ch = stepCnt % 2 == 0 ? 'X' : 'O';

            System.out.println("Step " + player + " turn");

            board = step(ch, board, player);
            printBoard(board);

            winGame = checkWin(ch, board);
            endGame = !checkOnEmptyCells(board);

            if (winGame) {
                System.out.println("Winner is: " + player);
            } else if (endGame) {
                System.out.println("No winner. Try again");
            }
            stepCnt++;
        }
    }

    static String[] initPlayers() {
        int humansNumber;
        do {
            System.out.println("Please type number of humanoids (0-2): ");
            humansNumber = new Scanner(System.in).nextInt();
        } while (!(humansNumber >= 0 && humansNumber <= 2));

        String[] players = new String[2];
        switch (humansNumber) {
            case 0: {
                players[0] = "PC1";
                players[1] = "PC2";
                break;
            }
            case 1: {
                players[0] = "PC1";
                players[1] = "Human1";
                break;
            }
            case 2: {
                players[0] = "Human1";
                players[1] = "Human2";
                break;
            }
        }
        return players;
    }

    private static void initBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '_';
            }
        }
    }

    private static void printBoard(char[][] board) {
        for (char[] aBoard : board) {
            for (char anABoard : aBoard) {
                System.out.print(anABoard + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean checkOnEmptyCells(char[][] board) {
        int emptyCellsCnt = 0;
        for (char[] aBoard : board) {
            for (char anABoard : aBoard) {
                if (anABoard == '_') {
                    emptyCellsCnt++;
                }
            }
        }
        return emptyCellsCnt > 0;
    }

    private static char[][] step(char ch, char[][] board, String player) {
        boolean successStep = false;
        boolean isPc = player.contains("PC");

        while (!successStep) {
            int x, y;
            if (isPc) {
                x = (int) (Math.random() * boardSize);
                y = (int) (Math.random() * boardSize);
            } else {
                x = readCoordsFromConsole('x');
                y = readCoordsFromConsole('y');
            }

            if (board[x][y] == '_') {
                board[x][y] = ch;
                successStep = true;
            }
        }
        return board;
    }

    private static int readCoordsFromConsole(char coordCh) {
        int coord;
        do {
            System.out.println("Please type " + coordCh + " coord (0-2): ");
            coord = new Scanner(System.in).nextInt();
        } while (!(coord >= 0 && coord <= 2));
        return coord;
    }

    private static boolean checkLine(char ch, char[] line) {
        for (int j = 0; j < boardSize; j++) {
            if (ch != line[j]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonals(char ch, char[][] board) {
        boolean main = true;
        boolean reverse = true;

        for (int i = 0; i < board.length; i++) {
            if (ch != board[i][i]) {
                main = false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (ch != board[i][boardSize-1 - i]) {
                reverse = false;
            }
        }
        return main || reverse;
    }

    private static boolean checkWin(char ch, char[][] board) {

        //check lines
        for (int i = 0; i < board.length; i++) {
            if (checkLine(ch, board[i])) {
                return true;
            }
        }

        //check cols
        for (int i = 0; i < board.length; i++) {
            char[] cols = new char[board[0].length];

            for (int j = 0; j < board.length; j++) {
                cols[j] = board[j][i];
            }
//            {board[0][i], board[1][i], board[2][i]};
            if (checkLine(ch, cols)) {
                return true;
            }
        }

        //check diagonals
        return checkDiagonals(ch, board);
    }
}


