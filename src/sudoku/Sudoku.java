/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Shanti Sanchez Barbero
 * @author Regina Vazquez Santa María López
 * @author Regina Canalizo 
 * @author Jerusa Chavero Gonzalez
 * @author Damian Perez Landeros
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
                for(j = 0; j < columns; j++){
                    sb.append(arreglo[i][j]);
                    if(j < columns - 1)
                        sb.append("," + " ");
                }
                if(i < rows - 1)
                    sb.append("\n");
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
          { 0, 2, 0, 6, 0, 8, 0, 0, 0},
          { 5, 8, 0, 0, 0, 9, 7, 0, 0},
          { 0, 0, 0, 0, 4, 0, 0, 0, 0},
          { 3, 7, 0, 0, 0, 0, 5, 0, 0},
          { 6, 0, 0, 0, 0, 0, 0, 0, 4},
          { 0, 0, 8, 0, 0, 0, 0, 1, 3},
          { 0, 0, 0, 0, 2, 0, 0, 0, 0},
          { 0, 0, 9, 8, 0, 0, 0, 3, 6},
          { 0, 0, 0, 3, 0, 6, 0, 9, 0}
        };
        int[][] sudoku12 = new int[][]{
          { 0, 0, 0, 6, 0, 0, 4, 0, 0},
          { 7, 0, 0, 0, 0, 3, 6, 0, 0},
          { 0, 0, 0, 0, 9, 1, 0, 8, 0},
          { 0, 0, 0, 0, 0, 0, 0, 0, 0},
          { 0, 5, 0, 1, 8, 0, 0, 0, 3},
          { 0, 0, 0, 3, 0, 6, 0, 4, 5},
          { 0, 4, 0, 2, 0, 0, 0, 6, 0},
          { 9, 0, 3, 0, 0, 0, 0, 0, 0},
          { 0, 2, 0, 0, 0, 0, 1, 0, 0}
        };
        int[][] sudoku13 = new int[][]{
          { 2, 0, 0, 3, 0, 0, 0, 0, 0},
          { 8, 0, 4, 0, 6, 2, 0, 0, 3},
          { 0, 1, 3, 8, 0, 0, 2, 0, 0},
          { 0, 0, 0, 0, 2, 0, 3, 9, 0},
          { 5, 0, 7, 0, 0, 0, 6, 2, 1},
          { 0, 3, 2, 0, 0, 6, 0, 0, 0},
          { 0, 2, 0, 0, 0, 9, 1, 4, 0},
          { 6, 0, 1, 2, 5, 0, 8, 0, 9},
          { 0, 0, 0, 0, 0, 1, 0, 0, 2}
        };
        int[][] sudoku14 = new int[][]{
          { 0, 2, 0, 0, 0, 0, 0, 0, 0},
          { 0, 0, 0, 6, 0, 0, 0, 0, 3},
          { 0, 7, 4, 0, 8, 0, 0, 0, 0},
          { 0, 0, 0, 0, 0, 3, 0, 0, 2},
          { 0, 8, 0, 0, 4, 0, 0, 1, 0},
          { 6, 0, 0, 5, 0, 0, 0, 0, 0},
          { 0, 0, 0, 0, 1, 0, 7, 8, 0},
          { 5, 0, 0, 0, 0, 9, 0, 0, 0},
          { 0, 0, 0, 0, 0, 0, 0, 4, 0}
        };
        int[][] sudoku15 = new int[][]{
          { 3, 0, 0, 0, 0, 8, 0, 0, 0},
          { 7, 0, 8, 3, 2, 0, 0, 0, 5},
          { 0, 0, 0, 9, 0, 0, 0, 1, 0},
          { 9, 0, 0, 0, 0, 4, 0, 2, 0},
          { 0, 0, 0, 0, 1, 0, 0, 0, 0},
          { 0, 7, 0, 8, 0, 0, 0, 0, 9},
          { 0, 5, 0, 0, 0, 3, 0, 0, 0},
          { 8, 0, 0, 0, 4, 7, 5, 0, 3},
          { 0, 0, 0, 5, 0, 0, 0, 0, 6}
        };
        int[][] sudoku16 = new int[][]{
          { 1, 2, 3, 0, 0, 0, 0, 0, 0},
          { 4, 5, 6, 0, 0, 0, 0, 0, 0},
          { 7, 8, 0, 9, 0, 0, 0, 0, 0},
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
        System.out.println("8)\n" + imprimeMatriz(sudoku.resuelve()));
        
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
        System.out.println("9)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //10) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku10);
        /*imprime:
         *  1, 5, 2, 4, 8, 9, 3, 7, 6
         *  7, 3, 9, 2, 5, 6, 8, 4, 1
         *  4, 6, 8, 3, 7, 1, 2, 8, 5
         *  3, 8, 7, 1, 2, 4, 6, 5, 9
         *  5, 9, 1, 7, 6, 3, 4, 2, 8
         *  2, 4, 6, 8, 9, 5, 7, 1, 3
         *  9, 1, 4, 6, 3, 7, 5, 8, 2
         *  6, 2, 5, 9, 4, 8, 1, 3, 7
         *  8, 7, 3, 5, 1, 2, 9, 6, 4
         */
        System.out.println("10)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //11) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku11);
        /*imprime:
         *  1, 2, 3, 6, 7, 8, 9, 4, 5
         *  5, 8, 4, 2, 3, 9, 7, 6, 1
         *  9, 6, 7, 1, 4, 5, 3, 2, 8
         *  3, 7, 2, 4, 6, 1, 5, 8, 9
         *  6, 9, 1, 5, 8, 3, 2, 7, 4
         *  4, 5, 8, 7, 9, 2, 6, 1, 3
         *  8, 3, 6, 9, 2, 4, 1, 5, 7
         *  2, 1, 9, 8, 5, 7, 4, 3, 6
         *  7, 4, 5, 3, 1, 6, 8, 9, 2
         */
        System.out.println("11)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //12) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku12);
        /*imprime:
         *  5, 8, 1, 6, 7, 2, 4, 3, 9
         *  7, 9, 2, 8, 4, 3, 6, 5, 1
         *  3, 6, 4, 5, 9, 1, 7, 8, 2
         *  4, 3, 8, 9, 5, 7, 2, 1, 6
         *  2, 5, 6, 1, 8, 4, 9, 7, 3
         *  1, 7, 9, 3, 2, 6, 8, 4, 5
         *  8, 4, 5, 2, 1, 9, 3, 6, 7
         *  9, 1, 3, 7, 6, 8, 5, 2, 4
         *  6, 2, 7, 4, 3, 5, 1, 9, 8
         */
        System.out.println("12)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //13) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku13);
        /*imprime:
         *  2, 7, 6, 3, 1, 4, 9, 5, 8
         *  8, 5, 4, 9, 6, 2, 7, 1, 3
         *  9, 1, 3, 8, 7, 5, 2, 6, 4
         *  4, 6, 8, 1, 2, 7, 3, 9, 5
         *  5, 9, 7, 4, 3, 8, 6, 2, 1
         *  1, 3, 2, 5, 9, 6, 4, 8, 7
         *  3, 2, 5, 7, 8, 9, 1, 4, 6
         *  6, 4, 1, 2, 5, 3, 8, 7, 9
         *  7, 8, 9, 6, 4, 1, 5, 3, 2
         */
        System.out.println("13)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //14) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku14);
        /*imprime:
         *  1, 2, 6, 4, 3, 7, 9, 5, 8
         *  8, 9, 5, 6, 2, 1, 4, 7, 3
         *  3, 7, 4, 9, 8, 5, 1, 2, 6
         *  4, 5, 7, 1, 9, 3, 8, 6, 2
         *  9, 8, 3, 2, 4, 6, 5, 1, 7
         *  6, 1, 2, 5, 7, 8, 3, 9, 4
         *  2, 6, 9, 3, 1, 4, 7, 8, 5
         *  5, 4, 8, 7, 6, 9, 2, 3, 1
         *  7, 3, 1, 8, 5, 2, 6, 4, 9
         */
        System.out.println("14)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //15) Prueba resolver un sudoku
        sudoku = new SudokuSolver(sudoku15);
        /*imprime:
         *  3, 1, 9, 4, 5, 8, 7, 6, 2
         *  7, 6, 8, 3, 2, 1, 9, 4, 5
         *  5, 4, 2, 9, 7, 6, 3, 1, 8
         *  9, 8, 5, 7, 3, 4, 6, 2, 1
         *  6, 3, 4, 2, 1, 9, 8, 5, 7
         *  2, 7, 1, 8, 6, 5, 4, 3, 9
         *  1, 5, 7, 6, 9, 3, 2, 8, 4
         *  8, 2, 6, 1, 4, 7, 5, 9, 3
         *  4, 9, 3, 5, 8, 2, 1, 7, 6
         */
        System.out.println("15)\n" + imprimeMatriz(sudoku.resuelve()));
        
        //16) Prueba resolver un sudoku sin solucion
        sudoku = new SudokuSolver(sudoku16);
        //imprime: null
        System.out.println("16)" + imprimeMatriz(sudoku.resuelve()));
    }  
}
