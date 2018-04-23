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
     * @param args the command line arguments
     */
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
        
        //8) Prueba la creacion de conjuntos para columnas y renglones.
        sudoku = new SudokuSolver(sudoku6);
        //imprime el conjunto
        System.out.println("8) " + sudoku.crearconjuntoColumna(3).toString());
    }
    
}
