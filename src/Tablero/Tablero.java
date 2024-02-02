package Tablero;

public abstract class Tablero {
    protected int[][] Map;
    
    public Tablero() {
        Map = new int[9][9];
    }
    
    public final void limpiarSudoku() {
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                Map[i][j] = 0;
            }
        }
    }
    
}
