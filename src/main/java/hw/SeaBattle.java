package hw;

import java.util.Scanner;

import static hw.TicTacToe.initPlayers;

public class SeaBattle {

    public static void main(String[] args) {

        String[] playersStrArr = initPlayers();
        Player[] players = new Player[2];

        for (int i = 0; i < playersStrArr.length; i++) {
            players[i] = new Player(playersStrArr[i]);
        }

        boolean isGameFinished = false;
        int stepCnt = 0;

        while (!isGameFinished) {
            int playerNumber = stepCnt % 2;
            Player player = players[playerNumber];
            Player rival = players[(playerNumber + 1) % 2];
            Step step = new Step(player, rival);
            step.showShipMaps(isGameFinished);
            step.shot();

            isGameFinished = step.isGameFinished();
            if(isGameFinished) {
                step.showShipMaps(isGameFinished);
            }
            stepCnt++;
        }
    }
}

class Field {
    private static final char SHIP = 'S';
    private static final char EMPTY = ' ';

    private int size;
    private int shipCount;
    private char[][] shipsMap;

    public Field(int size, int ships) {
        this.size = size;
        this.shipCount = ships;

        if (Math.pow(getSize(), 2) < shipCount) {
            System.out.println("Num of ships (" + shipCount + ") more then num of cells (" + getSize() * getSize() + ")");
            System.exit(0);
        }
        shipsMap = new char[getSize()][getSize()];
        shipsMap = initShips();
    }

    private char[][] initShips() {
        char[][] battlefield = new char[getSize()][getSize()];

        for (int i = 0; i < battlefield.length; i++) {
            for (int j = 0; j < battlefield[i].length; j++) {
                battlefield[i][j] = EMPTY;
            }
        }

        for (int s = 0; s < shipCount; s++) {
            boolean empty_cell = false;
            while (!empty_cell) {
                int i = (int) (Math.random() * getSize());
                int j = (int) (Math.random() * getSize());
                if (battlefield[i][j] != SHIP) {
                    battlefield[i][j] = SHIP;
                    empty_cell = true;
                }
            }
        }
        return battlefield;
    }

    public char[][] getShipsMap() {
        return shipsMap;
    }

    public int getSize() {
        return size;
    }
}

class Player {
    private String name;
    private boolean isHuman;
    private Field field;

    public Player(String playerName) {
        this.name = playerName;
        this.isHuman = !(playerName.contains("PC"));
        field = new Field(4, 3);
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public boolean isHuman() {
        return isHuman;
    }
}

class Step {
    private static final String DELIMITER = "|";
    private static final char SHIP = 'S';
    private static final char DAMAGED = 'X';
    private static final char MISSED = '.';

    private Player player;
    private Player rival;

    public Step(Player player, Player rival) {
        this.player = player;
        this.rival = rival;
    }

    public void showShipMaps(boolean isGameFinished) {
        System.out.println("---------- " + (isGameFinished ? "Result" :  player.getName() + " turn") + " ----------");
        showShipMap(player, false);
        showShipMap(rival, true);
        System.out.println("--------------------------------");
    }

    public void showShipMap(Player player, boolean isRival) {
        Field field = player.getField();
        System.out.println("Player " + player.getName() + " map:");

        String[] formattedArr = new String[field.getSize() + 1];

        formattedArr[0] = "//" + DELIMITER;
        for (int i = 0; i < field.getShipsMap().length; i++)
            formattedArr[0] += ((i + 1) + DELIMITER);
        for (int i = 0; i < field.getShipsMap().length; i++) {
            formattedArr[i + 1] = DELIMITER + (i + 1) + DELIMITER;
            for (int j = 0; j < field.getShipsMap().length; j++) {
                formattedArr[i + 1] += field.getShipsMap()[i][j] + DELIMITER;
            }
        }
        for (int i = 0; i < formattedArr.length; i++) {
            System.out.println(isRival ? formattedArr[i].replace("S", " ") : formattedArr[i]);
        }
        System.out.println();
    }

    private int shipsLeft() {
        int shipsCnt = 0;
        int fieldSize = rival.getField().getSize();

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (rival.getField().getShipsMap()[i][j] == SHIP)
                    shipsCnt++;
            }
        }
        return shipsCnt;
    }

    public boolean isGameFinished() {
        return shipsLeft() == 0;
    }

    public void shot() {
        int si;
        int sj;
        boolean correct_coords = false;
        String ij = "";
        String playerName = player.getName();
        Field rivalField = rival.getField();
        int fieldSize = rivalField.getSize();


        while (!correct_coords) {
            if (player.isHuman()) {
                System.out.print("Please type x,y coords, like '11', '31', etc: ");
                Scanner in = new Scanner(System.in);
                ij = in.next();
            } else {
                ij = pcTurn(player);
                System.out.println(playerName + " type: " + ij);
            }

            sj = Integer.parseInt(ij.substring(0, 1));
            si = Integer.parseInt(ij.substring(1));

            if (si > fieldSize || sj > fieldSize) {
                System.out.println("One of coords more than field size (" + fieldSize + ")");
                continue;
            } else if (si < 1 || sj < 1 || Integer.parseInt(ij) < 10) {
                System.out.println("One of coords less than 1 (" + fieldSize + ")");
                continue;
            } else {
                si--;
                sj--;
            }

            if (player.isHuman() && rivalField.getShipsMap()[si][sj] == MISSED) {
                System.out.println("Missed yet!");
            } else if (player.isHuman() && rivalField.getShipsMap()[si][sj] == DAMAGED) {
                System.out.println("Damaged yet!");
            } else if (rivalField.getShipsMap()[si][sj] != MISSED && rivalField.getShipsMap()[si][sj] != DAMAGED) {
                if (rivalField.getShipsMap()[si][sj] == SHIP) {
                    rivalField.getShipsMap()[si][sj] = DAMAGED;
                    if (isGameFinished())
                        System.out.println(player.getName() + " Win!");
                    else
                        System.out.println(player.getName() + " kill the ship. " + shipsLeft() + " left.\n");
                } else {
                    rivalField.getShipsMap()[si][sj] = '.';
                    System.out.println(player.getName() + " missed. " + shipsLeft() + " ships left.\n");
                }
                correct_coords = true;
            }
        }
    }

    private String pcTurn(Player rival) {
        int si = 0;
        int sj = 0;
        boolean correct_coords = false;

        while (!correct_coords) {
            sj = (int) (Math.random() * (rival.getField().getSize()));
            si = (int) (Math.random() * (rival.getField().getSize()));

            if (rival.getField().getShipsMap()[si][sj] != MISSED && rival.getField().getShipsMap()[si][sj] != DAMAGED) {
                correct_coords = true;
            } else if (rival.getField().getShipsMap()[si][sj] == MISSED) {
                System.out.println("Missed yet!");
            } else if (rival.getField().getShipsMap()[si][sj] == DAMAGED) {
                System.out.println("Damaged yet!");
            }
        }
        si++;
        sj++;
        return "" + si + sj;
    }
}