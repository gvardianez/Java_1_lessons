package lesson_4;

import lesson_2.GetInput;
import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {
    private final int SIZE;
    private final int DOTS_TO_WIN;
    private final char DOT_EMPTY = '•';
    private final char DOT_X = 'X';
    private final char DOT_O = 'O';
    private char[][] map;
    private final Scanner sc = new Scanner(System.in);
    private final Random rand = new Random();
    private int round;
    private int firstPlayer;

    public static void main(String[] args) {
        HomeWork4 game = new HomeWork4();
        game.go();
    }

    public HomeWork4() {
        SIZE = GetInput.getUserInputPositive("Введите размер игрового поля (X или Y)");
        DOTS_TO_WIN = GetInput.getUserInputPositive("Введите победную длину");
    }

    private void go() {
        initMap();
        printMap();
        firstPlayer = fistMove();                    // Случайно выбирается кто будет начинать игру
        if (fistMove() == 1) {
            startAi();
        } else {
            startHuman();
        }
        System.out.println("Игра закончена");
    }

    private void startHuman() {                             //Если первый начинает человек
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X, map, DOTS_TO_WIN)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O, map, DOTS_TO_WIN)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private void startAi() {                        //Если первый начинает ИИ
        while (true) {
            aiTurn();
            printMap();
            if (checkWin(DOT_O, map, DOTS_TO_WIN)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            humanTurn();
            printMap();
            if (checkWin(DOT_X, map, DOTS_TO_WIN)) {
                System.out.println("Победил Человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private int fistMove() {
        return (int) (Math.random() * 2);
    }

    public boolean checkWin(char symbol, char[][] map, int DOTS_TO_WIN) {       // Метод для проверки есть ли победная линия, перебирает клетки поля по очереди.
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

    public boolean checkWin(char symbol, char[][] map, int DOTS_TO_WIN, int m, int n) {  // Переопределенный метод проверки победы, берется конкретное поле и проверяеся 8 векторов. Победную длину можно изменить. Метод используется чтобы блокировать игроку вектора длиной три клетки.
        int count;
        if (map[m][n] == symbol) {
            count = 1;
            if (checkWinVector(m, n, map, symbol,DOTS_TO_WIN)) return true; // Четыре вектора проверяются в этом методе.
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
                    if (map[m-i][n] == symbol) {
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
                    if (map[m+i][n -i] == symbol) {
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

    public boolean checkDiagonal(int y, int x, char[][] map) {   // Метод для проверки возможности у игрока собрать линию из трех клеток, но если между двумя уже поставленными токенами игрока пока пустая клетка.
            try {
                if (map[y - 1][x - 1] == DOT_X && map[y + 1][x + 1] == DOT_X) return true;
                if (map[y - 1][x] == DOT_X && map[y + 1][x] == DOT_X) return true;
                if (map[y - 1][x + 1] == DOT_X && map[y + 1][x - 1] == DOT_X) return true;
                if (map[y][x - 1] == DOT_X && map[y][x + 1] == DOT_X) return true;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        return false;
    }

    public boolean checkWinVector(int y, int x, char[][] map, char symbol, int DOTS_TO_WIN) {  // Метод сос строки 165, проверяет четыре базовых вектора.
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
        char[][] mapTemp = new char[SIZE][SIZE];            // Создается копия карты, для всевозможных дополнительных проверок.
        int x, y;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mapTemp[j][i] = map[j][i];
            }
        }
        if (round == 1) {               // В первом раунде ИИ пыатеся занять клетку ближе к центру поля
            int center = SIZE / 2;
            if (SIZE % 2 == 0) {
                if (isCellValid((center), (center), map)) {
                    map[center][center] = DOT_O;
                } else {
                    map[center + 1][center] = DOT_O;
                }
                return;
            } else {
                if (isCellValid(center, center, map)) {
                    map[center][center] = DOT_O;
                } else map[center + 1][center + 1] = DOT_O;
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
    }

    public boolean canWinAi(char[][] map) {
        char temp;
        for (int j = 0; j < SIZE; j++) {               //Проверка, нет ли хода чтобы выйграть игру
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_O;
                    if (checkWin(DOT_O, map, DOTS_TO_WIN)) return true;
                    map[j][i] = temp;
                }
            }
        }
        return false;
    }

    public boolean canWinHuman(char[][] map) {
        char temp;
        for (int j = 0; j < SIZE; j++) {            //Проверка, нет ли хода чтобы выйграть игру у игрока
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_X;
                    if (checkWin(DOT_X, map, DOTS_TO_WIN)) {
                        map[j][i] = DOT_O;
                        return true;
                    }
                    map[j][i] = temp;
                }
            }
        }
        return false;
    }

    public boolean checkThreeInRowHuman(char[][] map) {         // ИИ проверяет есть ли у игрока возможность поставить 3 ряд, приоритет заполнение клеток через одну.
        char temp;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                if (isCellValid(i, j, map)) {
                    temp = map[j][i];
                    map[j][i] = DOT_X;
                    if (checkDiagonal(j, i, map)) {
                        map[j][i] = DOT_O;
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
                        return true;
                    }
                    map[j][i] = temp;
                }
            }
        }
        return false;
    }

    public boolean searchThreeInRowAi(char[][] mapTemp) {  // ИИ ищет возможность поставить свои токены 3 в ряд
        if (DOTS_TO_WIN == 3) {
            char mapTempJ, mapTempL;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    mapTempJ = mapTemp[i][j];
                    if (isCellValid(j, i, mapTemp)) {
                        mapTemp[i][j] = DOT_O;
                        if (checkWin(DOT_O, mapTemp, DOTS_TO_WIN)) {
                            map[i][j] = DOT_O;
                            return true;
                        }
                        for (int k = 0; k < SIZE; k++) {
                            for (int l = 0; l < SIZE; l++) {
                                mapTempL = mapTemp[k][l];
                                if (isCellValid(l, k, mapTemp)) {
                                    mapTemp[k][l] = DOT_O;
                                    if (checkWin(DOT_O, mapTemp, DOTS_TO_WIN)) {
                                        map[k][l] = DOT_O;
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

    public boolean searchFourInRowAi(char[][] mapTemp) { // ИИ ищет возможность поставить свои токены 4 в ряд. Для 5 и более в ряд циклы будут еще глубже, пробовал сделать с помощью рекурсии, но времени не хватило.
        if (DOTS_TO_WIN >= 4) {
            char mapTempJ, mapTempL, mapTempN;
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
                                        return true;
                                    }
                                    for (int m = 0; m < SIZE; m++) {
                                        for (int n = 0; n < SIZE; n++) {
                                            mapTempN = mapTemp[m][n];
                                            if (isCellValid(n, m, mapTemp)) {
                                                mapTemp[m][n] = DOT_O;
                                                if (checkWin(DOT_O, mapTemp, 4)) {
                                                    map[m][n] = DOT_O;
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

//    public static void nextCell(int DOTS_TO_WIN, char[][] mapTemp) {  // Попытка сделать поиск 5 и более в ряд с помощью рекурсии.
//        char mapTempJ;
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                mapTempJ = mapTemp[i][j];
//                if (isCellValid(j, i, mapTemp)) {
//                    mapTemp[i][j] = DOT_O;
//                    if (checkWin(DOT_O, mapTemp, DOTS_TO_WIN)) {
//                        map[i][j] = DOT_O;
//                        return;
//                    }
//                    if (counter<DOTS_TO_WIN-3) {
//                        counter++;
//                        nextCell(DOTS_TO_WIN, mapTemp);
//                    }
//                    mapTemp[i][j]= mapTempJ;
//                }
//            }
//        }
//        return;
//    }

    public void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y, map)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    public boolean isCellValid(int x, int y, char[][] map) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}









