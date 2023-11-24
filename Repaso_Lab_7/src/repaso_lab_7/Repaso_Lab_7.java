package repaso_lab_7;

import java.util.Scanner;
import java.util.Random;

public class Repaso_Lab_7 {

    public static void main(String[] args) {
        Scanner puchamon = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Menu");
            System.out.println("1. Matriz");
            System.out.println("2. UWU");
            System.out.println("3. Salir");
            System.out.print("Elige una opción (1-3): ");

            opcion = puchamon.nextInt();
            puchamon.nextLine(); // Da el salto de línea

            switch (opcion) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Introduce el tamaño de la matriz: ");
                    int n = scanner.nextInt();

                    // Generar la matriz
                    int[][] matriz = new int[n][n];
                    Random rand = new Random();
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            matriz[i][j] = rand.nextInt(10) + 1;
                        }
                    }

                    System.out.println("Matriz original:");
                    imprimirMatriz(matriz, n);

                    System.out.println("¿Quieres rotar la matriz en sentido horario o antihorario? (h/a): ");
                    String opcionRotacion = scanner.next();

                    // Rotar la matriz
                    if (opcionRotacion.toLowerCase().equals("h")) {
                        matriz = rotarHorario(matriz, n);
                    } else if (opcionRotacion.toLowerCase().equals("a")) {
                        matriz = rotarAntihorario(matriz, n);
                    }

                    System.out.println("Matriz rotada:");
                    imprimirMatriz(matriz, n);
                    break;
                // Aquí puedes agregar el código para las otras opciones

                case 2:
                    char[][] tablero = new char[10][10];
                    llenarTablero(tablero);
                    while (hayEnemigos(tablero)) {
                        imprimirTablero(tablero);
                        movimientoJugador(tablero);
                        if (enemigosCerca(tablero)) {
                            ataqueJugador(tablero);
                        }
                    }
                    System.out.println("¡Has ganado la batalla!");

                    break;
            }
        } while (opcion != 3);
    }

    public static void imprimirMatriz(int[][] matriz, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    public static int[][] rotarHorario(int[][] matriz, int n) {
        int[][] nuevaMatriz = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                nuevaMatriz[j][n - 1 - i] = matriz[i][j];
            }
        }
        return nuevaMatriz;
    }

    public static int[][] rotarAntihorario(int[][] matriz, int n) {
        int[][] nuevaMatriz = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                nuevaMatriz[n - 1 - j][i] = matriz[i][j];
            }
        }
        return nuevaMatriz;
    }

    public static void llenarTablero(char[][] tablero) {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int x, y;
            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            } while (x == 0 && y == 0);
            tablero[x][y] = 'E';
        }
        tablero[0][0] = 'Y';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 0) {
                    tablero[i][j] = ' ';
                }
            }
        }
    }

    public static boolean hayEnemigos(char[][] tablero) {
        for (char[] fila : tablero) {
            for (char c : fila) {
                if (c == 'E') {
                    return true;
                }
            }
        }
        return false;
    }

    public static void movimientoJugador(char[][] tablero) {
        Scanner scanner = new Scanner(System.in);
        int x = 0, y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 'Y') {
                    x = i;
                    y = j;
                }
            }
        }
        System.out.println("¿A qué dirección quieres moverte? (U/D/L/R): ");
        String opcion = scanner.next();
        switch (opcion.toUpperCase()) {
            case "U":
                if (x > 0) {
                    tablero[x][y] = ' ';
                    tablero[x - 1][y] = 'Y';
                }
                break;
            case "D":
                if (x < 9) {
                    tablero[x][y] = ' ';
                    tablero[x + 1][y] = 'Y';
                }
                break;
            case "L":
                if (y > 0) {
                    tablero[x][y] = ' ';
                    tablero[x][y - 1] = 'Y';
                }
                break;
            case "R":
                if (y < 9) {
                    tablero[x][y] = ' ';
                    tablero[x][y + 1] = 'Y';
                }
                break;
        }
    }

    public static boolean enemigosCerca(char[][] tablero) {
        int x = 0, y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 'Y') {
                    x = i;
                    y = j;
                }
            }
        }
        return (x > 0 && tablero[x - 1][y] == 'E')
                || (x < 9 && tablero[x + 1][y] == 'E')
                || (y > 0 && tablero[x][y - 1] == 'E')
                || (y < 9 && tablero[x][y + 1] == 'E');
    }

    public static void ataqueJugador(char[][] tablero) {
        int x = 0, y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 'Y') {
                    x = i;
                    y = j;
                }
            }
        }
        if (x > 0 && tablero[x - 1][y] == 'E') {
            tablero[x - 1][y] = ' ';
        }
        if (x < 9 && tablero[x + 1][y] == 'E') {
            tablero[x + 1][y] = ' ';
        }
        if (y > 0 && tablero[x][y - 1] == 'E') {
            tablero[x][y - 1] = ' ';
        }
        if (y < 9 && tablero[x][y + 1] == 'E') {
            tablero[x][y + 1] = ' ';
        }
    }

    public static void imprimirTablero(char[][] tablero) {
        for (char[] fila : tablero) {
            for (char c : fila) {
                System.out.print("[" + c + "]");
            }
            System.out.println();
        }
    }
}
