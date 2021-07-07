package lesson_8;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePlay {

    private final int SIZE;
    private final int DOTS_TO_WIN;
    private final int DOT_EMPTY = 0;
    private int DOT_X = 1;
    private final int DOT_O = 2;
    private int[][] map;
    private JButton[][] gameButton;
    private JFrame gamePlay;
    private final Random rand = new Random();
    private int round;
    private final int firstPlayer;
    public static final int MODE_VS_AI = 0;
    public static final int MODE_VS_HUMAN = 1;
    private String buttonDot = "X";
    private final int gameMode;

    public static void main(String[] args) {
        new GameWindow();
    }

    public GamePlay(int gameMode, int SIZE, int DOTS_TO_WIN) {
        this.gameMode = gameMode;
        this.SIZE = SIZE;
        this.DOTS_TO_WIN = DOTS_TO_WIN;
        initMap();
        firstPlayer = fistMove();
        createGameBoard(SIZE);
        if (firstPlayer == 1) {
            play();
        }
    }

    private void createGameBoard(int SIZE) {        //Создается двумерный массив кнопок, они размещаются в рамке и для каждой кнопки интерфейс слушателя
        JFrame gamePlay = new JFrame("Крестики нолики");
        int height = 660;
        int width = 630;
        gamePlay.setSize(width, height);
        gamePlay.setLocationRelativeTo(null);
        gamePlay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton[][] gameButton = new JButton[SIZE][SIZE];
        Font bigFontTR = new Font("TimesRoman", Font.BOLD, 40);
        gamePlay.setLayout((new GridLayout(SIZE, SIZE)));
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                gameButton[y][x] = new JButton();
                gamePlay.add(gameButton[y][x]);
                gameButton[y][x].setFont(bigFontTR);
                int finalY = y;
                int finalX = x;
                gameButton[y][x].addActionListener(e -> {
                    gameButton[finalY][finalX].setText(buttonDot);
                    gameButton[finalY][finalX].setEnabled(false);
                    map[finalY][finalX] = DOT_X;
                    round++;
                    System.out.println(round);
                    play();
                });
            }
        }
        gamePlay.setVisible(true);
        this.gameButton = gameButton;
        this.gamePlay = gamePlay;
    }

    private void play() {
        if (gameMode == 0) {
            if (gameOver()) return;
            aiTurn();
            gameOver();
        } else {
            gameOver();
            if (round % 2 == 0) {
                buttonDot = "X";
                DOT_X = 1;
            } else {
                buttonDot = "O";
                DOT_X = 2;
            }
        }
    }

    private boolean gameOver() {
        int stateGameOver;
        if (checkWin(DOT_O, map, DOTS_TO_WIN)) {
            stateGameOver = 2;
            showGameOver(stateGameOver);
            gamePlay.setEnabled(false);
            return true;
        }
        if (checkWin(DOT_X, map, DOTS_TO_WIN)) {
            stateGameOver = 1;
            showGameOver(stateGameOver);
            gamePlay.setEnabled(false);
            return true;
        }
        if (isMapFull()) {
            stateGameOver = 0;
            showGameOver(stateGameOver);
            gamePlay.setEnabled(false);
            return true;
        }
        return false;
    }

    private void showGameOver(int stateGameOver) {
        String winner;
        JFrame gameOver = new JFrame("Игра окончена");
        gameOver.setSize(510, 70);
        gameOver.setLocationRelativeTo(null);
        gameOver.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        switch (stateGameOver) {
            case 0: {
                winner = "Ничья";
                break;
            }
            case 1: {
                winner = "Вы победили";
                break;
            }
            case 2: {
                winner = "Победил Искуственный интеллект";
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + stateGameOver);
        }
        JLabel over = new JLabel();
        Font gameOverLabel = new Font("Arial", Font.BOLD, 28);
        over.setFont(gameOverLabel);
        over.setText(winner);
        gameOver.add(over, BorderLayout.CENTER);
        gameOver.setVisible(true);
    }

    private int fistMove() {
        return (int) (Math.random() * 2);
    }

    public boolean checkWin(int symbol, int[][] map, int DOTS_TO_WIN) {       // Метод для проверки есть ли победная линия, перебирает клетки поля по очереди.
        int count;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if ((SIZE - y) >= DOTS_TO_WIN || (SIZE - x) >= DOTS_TO_WIN) {   // Условие при не исполнении которого, при заданной победной длине дальше проверять не имеет смысла.
                    if (map[y][x] == symbol) {
                        count = 1;
                        for (int i = 1; i < DOTS_TO_WIN; i++) {         // Проверка первого вектора, есть ли в нем победная комбинация символов. Длина вектора есть победная длина, в случае если вектор выходит за границы поля обрабатывается исключение, всего четыре вектора.
                            try {
                                if (map[y - i][x + i] == symbol) {
                                    count++;
                                    if (count == DOTS_TO_WIN) {
                                        return true;
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                        }
                        count = 1;
                        for (int i = 1; i < DOTS_TO_WIN; i++) {
                            try {
                                if (map[y][x + i] == symbol) {
                                    count++;
                                    if (count == DOTS_TO_WIN) {
                                        return true;
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                        }
                        count = 1;
                        for (int i = 1; i < DOTS_TO_WIN; i++) {
                            try {
                                if (map[y + i][x + i] == symbol) {
                                    count++;
                                    if (count == DOTS_TO_WIN) {
                                        return true;
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                        }
                        count = 1;
                        for (int i = 1; i < DOTS_TO_WIN; i++) {
                            try {
                                if (map[y + i][x] == symbol) {
                                    count++;
                                    if (count == DOTS_TO_WIN) {
                                        return true;
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkWin(int symbol, int[][] map, int DOTS_TO_WIN, int m, int n) {  // Переопределенный метод проверки победы, берется конкретное поле и проверяеся 8 векторов. Победную длину можно изменить. Метод используется чтобы блокировать игроку вектора длиной три клетки.
        int count;
        if (map[m][n] == symbol) {
            count = 1;
            if (checkWinVector(m, n, map, symbol, DOTS_TO_WIN))
                return true; // Четыре вектора проверяются в этом методе.
            for (int i = 1; i < DOTS_TO_WIN; i++) {
                try {
                    if (map[m - i][n - i] == symbol) {
                        count++;
                        if (count == DOTS_TO_WIN) {
                            return true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            count = 1;
            for (int i = 1; i < DOTS_TO_WIN; i++) {
                try {
                    if (map[m][n - i] == symbol) {
                        count++;
                        if (count == DOTS_TO_WIN) {
                            return true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            count = 1;
            for (int i = 1; i < DOTS_TO_WIN; i++) {
                try {
                    if (map[m - i][n] == symbol) {
                        count++;
                        if (count == DOTS_TO_WIN) {
                            return true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            count = 1;
            for (int i = 1; i < DOTS_TO_WIN; i++) {
                try {
                    if (map[m + i][n - i] == symbol) {
                        count++;
                        if (count == DOTS_TO_WIN) {
                            return true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal(int y, int x, int[][] map) {   // Метод для проверки возможности у игрока собрать линию из трех клеток, но если между двумя уже поставленными токенами игрока пока пустая клетка.
        try {
            if (map[y - 1][x - 1] == DOT_X && map[y + 1][x + 1] == DOT_X) return true;
            if (map[y - 1][x] == DOT_X && map[y + 1][x] == DOT_X) return true;
            if (map[y - 1][x + 1] == DOT_X && map[y + 1][x - 1] == DOT_X) return true;
            if (map[y][x - 1] == DOT_X && map[y][x + 1] == DOT_X) return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return false;
    }

    public boolean checkWinVector(int y, int x, int[][] map, int symbol, int DOTS_TO_WIN) {  // Метод сос строки 165, проверяет четыре базовых вектора.
        int count = 1;
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            try {
                if (map[y - i][x + i] == symbol) {
                    count++;
                    if (count == DOTS_TO_WIN) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        count = 1;
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            try {
                if (map[y][x + i] == symbol) {
                    count++;
                    if (count == DOTS_TO_WIN) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        count = 1;
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            try {
                if (map[y + i][x + i] == symbol) {
                    count++;
                    if (count == DOTS_TO_WIN) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        count = 1;
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            try {
                if (map[y + i][x] == symbol) {
                    count++;
                    if (count == DOTS_TO_WIN) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return false;
    }

    public boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public void aiTurn() {
        round++;
        int[][] mapTemp = new int[SIZE][SIZE];            // Создается копия карты, для всевозможных дополнительных проверок.
        int x, y;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mapTemp[j][i] = map[j][i];
            }
        }
        if (round == 1 || round == 2) {               // В первом раунде ИИ пытается занять клетку ближе к центру поля
            int center = SIZE / 2;
            if (SIZE % 2 == 0) {
                if (isCellValid((center), (center), map)) {
                    map[center][center] = DOT_O;
                    aiPressButton(center, center);
                } else {
                    map[center + 1][center] = DOT_O;
                    aiPressButton(center + 1, center);
                }
                return;
            } else {
                if (isCellValid(center, center, map)) {
                    map[center][center] = DOT_O;
                    aiPressButton(center, center);
                } else {
                    map[center + 1][center + 1] = DOT_O;
                    aiPressButton(center + 1, center + 1);
                }
            }
            return;
        }

        if ((firstPlayer == 1) && (DOTS_TO_WIN == 3)) {     // Если первый ходит ИИ и поле 3 на 3, он сам пытается в первую очередь сделать победный вектор.
            if (canWinAi(map)) return;
            if (canWinHuman(map)) return;
            if (searchThreeInRowAi(mapTemp)) return;
            if (searchFourInRowAi(mapTemp)) return;
            if (checkThreeInRowHuman(map)) return;
        } else {            // В других случаях, первостепенно мешать игроку собирать 3 в ряд, и если такую возможность ИИ не видит, то сам собирает четыре в ряд.
            if (canWinAi(map)) return;
            if (canWinHuman(map)) return;
            if (searchFourInRowAi(mapTemp)) return;
            if (checkThreeInRowHuman(map)) return;
            if (searchFourInRowAi(mapTemp)) return;
        }
        do {                                // Если все предыдущие проверки неудачны, то ИИ рэндомно сьавит токен.
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y, map));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
        aiPressButton(y, x);
    }

    public boolean canWinAi(int[][] map) {
        int temp;
        for (int j = 0; j < SIZE; j++) {               //Проверка, нет ли хода чтобы выйграть игру
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_O;
                    if (checkWin(DOT_O, map, DOTS_TO_WIN)) {
                        aiPressButton(j, i);
                        return true;
                    }
                    map[j][i] = temp;
                }
            }
        }
        return false;
    }

    public boolean canWinHuman(int[][] map) {
        int temp;
        for (int j = 0; j < SIZE; j++) {            //Проверка, нет ли хода чтобы выйграть игру у игрока
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_X;
                    if (checkWin(DOT_X, map, DOTS_TO_WIN)) {
                        map[j][i] = DOT_O;
                        aiPressButton(j, i);
                        return true;
                    }
                    map[j][i] = temp;
                }
            }
        }
        return false;
    }

    public boolean checkThreeInRowHuman(int[][] map) {         // ИИ проверяет есть ли у игрока возможность поставить 3 ряд, приоритет заполнение клеток через одну.
        int temp;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_X;
                    if (checkDiagonal(j, i, map)) {
                        map[j][i] = DOT_O;
                        aiPressButton(j, i);
                        return true;
                    }
                    map[j][i] = temp;
                }
            }
        }
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_X;
                    if (checkWin(DOT_X, map, 3, j, i)) {
                        map[j][i] = DOT_O;
                        aiPressButton(j, i);
                        return true;
                    }
                    map[j][i] = temp;
                }
            }
        }
        return false;
    }

    public boolean searchThreeInRowAi(int[][] mapTemp) {  // ИИ ищет возможность поставить свои токены 3 в ряд
        if (DOTS_TO_WIN == 3) {
            int mapTempJ, mapTempL;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    mapTempJ = mapTemp[i][j];
                    if (isCellValid(j, i, mapTemp)) {
                        mapTemp[i][j] = DOT_O;
                        if (checkWin(DOT_O, mapTemp, DOTS_TO_WIN)) {
                            map[i][j] = DOT_O;
                            aiPressButton(i, j);
                            return true;
                        }
                        for (int k = 0; k < SIZE; k++) {
                            for (int l = 0; l < SIZE; l++) {
                                mapTempL = mapTemp[k][l];
                                if (isCellValid(l, k, mapTemp)) {
                                    mapTemp[k][l] = DOT_O;
                                    if (checkWin(DOT_O, mapTemp, DOTS_TO_WIN)) {
                                        map[k][l] = DOT_O;
                                        aiPressButton(k, l);
                                        return true;
                                    }
                                }
                                mapTemp[k][l] = mapTempL;
                            }
                        }
                    }
                    mapTemp[i][j] = mapTempJ;
                }
            }
        }
        return false;
    }

    public boolean searchFourInRowAi(int[][] mapTemp) { // ИИ ищет возможность поставить свои токены 4 в ряд. Для 5 и более в ряд циклы будут еще глубже, пробовал сделать с помощью рекурсии, но времени не хватило.
        if (DOTS_TO_WIN >= 4) {
            int mapTempJ, mapTempL, mapTempN;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    mapTempJ = mapTemp[i][j];
                    if (isCellValid(j, i, mapTemp)) {
                        mapTemp[i][j] = DOT_O;
                        for (int k = 0; k < SIZE; k++) {
                            for (int l = 0; l < SIZE; l++) {
                                mapTempL = mapTemp[k][l];
                                if (isCellValid(l, k, mapTemp)) {
                                    mapTemp[k][l] = DOT_O;
                                    if (checkWin(DOT_O, mapTemp, 4)) {
                                        map[k][l] = DOT_O;
                                        aiPressButton(k, l);
                                        return true;
                                    }
                                    for (int m = 0; m < SIZE; m++) {
                                        for (int n = 0; n < SIZE; n++) {
                                            mapTempN = mapTemp[m][n];
                                            if (isCellValid(n, m, mapTemp)) {
                                                mapTemp[m][n] = DOT_O;
                                                if (checkWin(DOT_O, mapTemp, 4)) {
                                                    map[m][n] = DOT_O;
                                                    aiPressButton(m, n);
                                                    return true;
                                                }
                                            }
                                            mapTemp[m][n] = mapTempN;
                                        }
                                    }
                                }
                                mapTemp[k][l] = mapTempL;
                            }
                        }
                    }
                    mapTemp[i][j] = mapTempJ;
                }
            }
        }
        return false;
    }

    public void aiPressButton(int y, int x) {
        gameButton[y][x].setText("O");
        gameButton[y][x].setEnabled(false);
    }

    public boolean isCellValid(int x, int y, int[][] map) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public void initMap() {
        map = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

}


