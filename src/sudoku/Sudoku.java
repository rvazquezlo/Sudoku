/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Carlos
 */
public class Sudoku {
    
    /**
     * Froma una cadena con todos los elementos de arreglo en orden
     * @param arreglo: del que se quiere formar la cadena
     * @return <ul>
     * <li> null: si arreglo es null o si tiene 0 columnas y/o renglones </li>
     * <li> De otra forma, regresa la cadena en forma de matriz, poniendo 
     * horizontalmente las columnas y verticalmente los renglones </li>
     * </ul>
    */
    public static String imprimeMatriz(int[][] arreglo){
        StringBuilder sb;
        String contenido;
        int rows, columns, i, j;
        
        try{
            sb = new StringBuilder();
            rows = arreglo.length;
            columns = arreglo[0].length;
            for(i = 0; i < rows; i++){
                for(j = 0; j < columns; j++)
                    sb.append(arreglo[i][j]);
                if(i < rows - 1)
                    sb.append("\n");
                if(j < columns - 1)
                    sb.append("," + " ");
            }
            contenido = sb.toString();
        }catch(NullPointerException | ArrayIndexOutOfBoundsException e){
            contenido = null;
        }
        return contenido;
    }

    public static void main(String[] args) {
        SudokuSolver sudoku;
        int[][] sudoku1 = new int[][]{
          { 1, 0, 4, 0, 4, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] sudoku2 = new int[][]{
          { 1, 0, 4, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 4, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] sudoku3 = new int[][]{
          { 1, 0, 4, 0, 0, 0, 0, 0, 0},
          { 0, 4, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 1, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 1, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] sudoku4 = new int[][]{
          { 1, 4, 4, 0, 0, 0, 0, 0, 0},
          { 0, 4, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 1, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 1, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] sudoku5 = new int[][]{
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] sudoku6 = new int[][]{
          { 1, 0, 0, 4, 0, 0, 0, 0, 0},
          { 0, 4, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 6, 0, 0, 0, 0, 7, 0},
          { 0, 0, 0, 9, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 1, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 2, 0, 0, 0},
          { 0, 2, 3, 0, 0, 0, 5, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 6, 0},
          { 0, 0, 0, 7, 0, 0, 0, 0, 9}
        };
        int[][] sudoku7 = new int[9][9];
        int[][] sudoku8 = new int[][]{
          { 8, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 3, 6, 0, 0, 0, 0, 0},
          { 0, 7, 0, 0, 9, 0, 2, 0, 0},
          { 0, 5, 0, 0, 0, 7, 0, 0, 0},
          { 0, 0, 0, 0, 4, 5, 7, 0, 0},
          { 0, 0, 0, 1, 0, 0, 0, 3, 0},
          { 0, 0, 1, 0, 0, 0, 0, 6, 8},
          { 0, 0, 8, 5, 0, 0, 0, 1, 0},
          { 0, 9, 0, 0, 0, 0, 4, 0, 0}
        };
        int[][] sudoku9 = new int[][]{
          { 0, 0, 0, 2, 6, 0, 7, 0, 1},
          { 6, 8, 0, 0, 7, 0, 0, 9, 0},
          { 1, 9, 0, 0, 0, 4, 5, 0, 0},
          { 8, 2, 0, 1, 0, 0, 0, 4, 0},
          { 0, 0, 4, 6, 0, 2, 9, 0, 0},
          { 0, 5, 0, 0, 0, 3, 0, 2, 8},
          { 0, 0, 9, 3, 0, 0, 0, 7, 4},
          { 0, 4, 0, 0, 5, 0, 0, 3, 6},
          { 7, 0, 3, 0, 1, 8, 0, 0, 0}
        };
        int[][] sudoku10 = new int[][]{
          { 1, 0, 0, 4, 8, 9, 0, 0, 6},
          { 7, 3, 0, 0, 0, 0, 0, 4, 0},
          { 0, 0, 0, 0, 0, 1, 2, 9, 5},
          { 0, 0, 7, 1, 2, 0, 6, 0, 0},
          { 5, 0, 0, 7, 0, 3, 0, 0, 8},
          { 0, 0, 6, 0, 9, 5, 7, 0, 0},
          { 9, 1, 4, 6, 0, 0, 0, 0, 0},
          { 0, 2, 0, 0, 0, 0, 0, 3, 7},
          { 8, 0, 0, 5, 1, 2, 0, 0, 4}
        };
        int[][] sudoku11 = new int[][]{
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        //1) Prueba filas
        sudoku = new SudokuSolver(sudoku1);
        //imprime false
        System.out.println("1) " + sudoku.verificaCuadricula());
        
        //2) Prueba columnas
        sudoku = new SudokuSolver(sudoku2);
        //imprime false
        System.out.println("2) " + sudoku.verificaCuadricula());
        
        //3) Prueba cuadrados
        sudoku = new SudokuSolver(sudoku3);
        //imprime false
        System.out.println("3) " + sudoku.verificaCuadricula());
        
        //4) Prueba cuadrados, filas y columnas
        sudoku = new SudokuSolver(sudoku4);
        //imprime false
        System.out.println("4) " + sudoku.verificaCuadricula());
        
        //5) Prueba cuadricula de puros ceros
        sudoku = new SudokuSolver(sudoku5);
        //imprime true
        System.out.println("5) " + sudoku.verificaCuadricula());
        
        //6) Prueba cuadricula valida
        sudoku = new SudokuSolver(sudoku6);
        //imprime true
        System.out.println("6) " + sudoku.verificaCuadricula());
        
        //7) Prueba sudoku vacio
        sudoku = new SudokuSolver(sudoku7);
        //imprime true
        System.out.println("7) " + sudoku.verificaCuadricula());
        
        //8) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku8);
        /*imprime:
         *  8, 1, 2, 7, 5, 3, 6, 4, 9
         *  9, 4, 3, 6, 8, 2, 1, 7, 5
         *  6, 7, 5, 4, 9, 1, 2, 8, 3
         *  1, 5, 4, 2, 3, 7, 8, 9, 6
         *  3, 6, 9, 8, 4, 5, 7, 2, 1
         *  2, 8, 7, 1, 6, 9, 5, 3, 4
         *  5, 2, 1, 9, 7, 4, 3, 6, 8
         *  4, 3, 8, 5, 2, 6, 9, 1, 7
         *  7, 9, 6, 3, 1, 8, 4, 5, 2
         */
        System.out.println("8) " + imprimeMatriz(sudoku.resuelve()));
        
        //9) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku9);
        /*imprime:
         *  4, 3, 5, 2, 6, 9, 7, 8, 1
         *  6, 8, 2, 5, 7, 1, 4, 9, 3
         *  1, 9, 7, 8, 3, 4, 5, 6, 2
         *  8, 2, 6, 1, 9, 5, 3, 4, 7
         *  3, 7, 4, 6, 8, 2, 9, 1, 5
         *  9, 5, 1, 7, 4, 3, 6, 2, 8
         *  5, 1, 9, 3, 2, 6, 8, 7, 4
         *  2, 4, 8, 9, 5, 7, 1, 3, 6
         *  7, 6, 3, 4, 1, 8, 2, 5, 9
         */
        System.out.println("9) " + imprimeMatriz(sudoku.resuelve()));
        
        //10) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku10);
        /*imprime:
         *  1, 5, 2, 2, 6, 9, 7, 8, 1
         *  7, 3, 9, 5, 7, 1, 4, 9, 3
         *  4, 6, 8, 8, 3, 4, 5, 6, 2
         *  3, 8, 7, 1, 9, 5, 3, 4, 7
         *  5, 9, 1, 6, 8, 2, 9, 1, 5
         *  2, 4, 6, 7, 4, 3, 6, 2, 8
         *  9, 1, 4, 3, 2, 6, 8, 7, 4
         *  6, 2, 5, 9, 5, 7, 1, 3, 6
         *  8, 7, 3, 4, 1, 8, 2, 5, 9
         */
        System.out.println("10) " + imprimeMatriz(sudoku.resuelve()));
        
    }
    
}
