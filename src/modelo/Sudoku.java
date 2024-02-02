package modelo;

import Tablero.Tablero;
import java.util.Random;

public final class Sudoku extends Tablero{

    public Sudoku() {
        super.limpiarSudoku();
    }

    public boolean resolverSudoku() {
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                if (Map[i][j] == 0) {
                    for (int valor = 1; valor <= 9; valor++) {
                        if (validarFila(i, valor) && validarColumna(j, valor) && validarCuadrante(i, j, valor)) {
                            Map[i][j] = valor;
                            if (resolverSudoku()) {
                                return true;
                            }
                            Map[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean comprobarSudoku() {
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                int aux = Map[i][j];
                Map[i][j] = 0;
                if (!validarFila(i, aux) || !validarColumna(j, aux) || !validarCuadrante(i, j, aux)) {
                    Map[i][j]=aux;
                    return false;

                }
                Map[i][j]=aux;
            }
        }
        return true;
    }

    public boolean validarCuadrante(int i, int j, int valor) {
        int posI = subCuadranteActual(i);
        int posJ = subCuadranteActual(j);

        for (int k = posI - 3; k < posI; k++) {
            for (int l = posJ - 3; l < posJ; l++) {
                if (Map[k][l] == valor) {
                    return false;
                }
            }
        }
        return true;
    }

    public int subCuadranteActual(int pos) {
        if (pos <= 2) {
            return 3;
        } else if (pos <= 5) {
            return 6;
        } else {
            return 9;
        }
    }

    public boolean validarFila(int i, int valor) {
        for (int j = 0; j < Map[i].length; j++) {
            if (Map[i][j] == valor) {
                return false;
            }
        }
        return true;
    }

    public boolean validarColumna(int j, int valor) {
        for (int i = 0; i < Map.length; i++) {
            if (Map[i][j] == valor) {
                return false;
            }
        }
        return true;
    }

    public void mostrarSudoku() {
        resolverSudoku();
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                System.out.print(Map[i][j]);
                if (!(j == Map[0].length - 1)) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public void generarSudoku(int nivel) {
        limpiarSudoku();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = random.nextInt(9) + 1;
                if (validarCuadrante(i, j, num)) {
                    Map[i][j] = num;
                } else {
                    j--;
                }
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                int num = random.nextInt(9) + 1;
                if (validarCuadrante(i, j, num)) {
                    Map[i][j] = num;
                } else {
                    j--;
                }
            }
        }

        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                int num = random.nextInt(9) + 1;
                if (validarCuadrante(i, j, num)) {
                    Map[i][j] = num;
                } else {
                    j--;
                }
            }
        }
        resolverSudoku();

        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                int aux = j;
                int rand = random.nextInt(nivel + 1);
                j += rand;
                for (int k = aux; k < j && k < Map.length; k++) {
                    Map[i][k] = 0;
                }
            }
        }

    }

    public int[][] getSudoku() {
        return Map;
    }

    public void setSudoku(int[][] Map) {
        this.Map = Map;
    }

}
