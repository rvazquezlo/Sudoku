/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import conjuntos.ConjuntoA;

/**
 *
 * @author Regina Vazquez
 */
public class SudokuSolver {
    
    private int[][] cuadricula;
    private final int MAXIMO = 9;//MAXIMO DE RENGLONES, FILAR Y COLUMNAS
    
    public SudokuSolver(){
        cuadricula = new int[MAXIMO][MAXIMO];
    }
    
    /**
     * Metodo para agregar los valores a cuadricula para los cuales se quiere
     * buscar una solucion
     * @param renglon: al que se quiere agregar el valor, empezando desde 0
     * @param columna: a la que se quiere agregar el valor, empezando desde 0
     * @param valor: que se quiere agregar
     * @throws ArrayIndexOutOfBoundsException: si se da un renglon o columna 
     * mayor a 8 (solo hay nueve renglones y columnas)
     */
    public void add(int renglon, int columna, int valor){
        try{
            cuadricula[renglon][columna] = valor;
        }catch(ArrayIndexOutOfBoundsException e){
            throw e;
        }
    }
    
    /**
     * Metodo recursivo que verifica que todos los renglones de cuadricula esten
     * en un formato valido para resolver el sudoku
     * @param row: el renglon de cuadricula que se esta analizando
     * @param column: la columna de cuadricula que se esta analizando
     * @param conjunto: el conjunto en el que se meten los digitos del renglon 
     * que se esta analizando
     * @param MAXIMO: el maximo numero de renglones y columnas 
     * @return <ul>
     * <li>true: Si todos los renglones tienen formato valido</li>
     * <li>false: Si algun renglon esta sobre-restringido</li>
     * <li>llamada recursiva: si aun no se acaban de verificar todos los 
     * renglones</li>
     * </ul> 
     */
    private boolean verificaRenglones(int row, int column, ConjuntoA<Integer> conjunto,  int MAXIMO){
        int numero;
        
        numero = cuadricula[row][column];
        if(numero != 0){
            if(!conjunto.add(numero))
                return false; 
        }
        if(column == MAXIMO - 1){
            if(row == MAXIMO - 1)
                return true;
            else{
                conjunto = new ConjuntoA<>();
                return verificaRenglones(row + 1, 0, conjunto, MAXIMO);
            }
        }
        else
            return verificaRenglones(row, column + 1, conjunto, MAXIMO);            
    }
    
    /**
     * Metodo recursivo que verifica que todas las columnas de cuadricula esten
     * en un formato valido para resolver el sudoku
     * @param row: el renglon de cuadricula que se esta analizando
     * @param column: la columna de cuadricula que se esta analizando
     * @param conjunto: el conjunto en el que se meten los digitos de la columna 
     * que se esta analizando
     * @param MAXIMO: el maximo numero de renglones y columnas
     * @return <ul>
     * <li>true: Si todos las columnas tienen formato valido</li>
     * <li>false: Si alguna columna esta sobre-restringida</li>
     * <li>llamada recursiva: si aun no se acaban de verificar todas las 
     * columnas</li>
     * </ul>  
     */
    private boolean verificaColumnas(int row, int column, ConjuntoA<Integer> conjunto,  int MAXIMO){
        int numero;
        
        numero = cuadricula[row][column];
        if(numero != 0){
            if(!conjunto.add(numero))
                return false; 
        }
        if(row == MAXIMO - 1){
            if(column == MAXIMO - 1)
                return true;
            else{
                conjunto = new ConjuntoA<>();
                return verificaColumnas(0, column + 1, conjunto, MAXIMO);
            }
        }
        else
            return verificaColumnas(row + 1, column, conjunto, MAXIMO);    
    }
    
    /**
     * Metodo recursivo que verifica que todos los cuadrados de cuadricula esten
     * en un formato valido para resolver el sudoku
     * @param row: el renglon de cuadricula que se esta analizando
     * @param column: la columna de cuadricula que se esta analizando
     * @param conjunto: el conjunto en el que se meten los digitos del cuadrado 
     * que se esta analizando
     * @param MAXIMO: el maximo numero de renglones y columnas
     * @return <ul>
     * <li>true: Si todos los cuadrados tienen formato valido</li>
     * <li>false: Si algun cuadrado esta sobre-restringido</li>
     * <li>llamada recursiva: si aun no se acaban de verificar todos los 
     * cuadrados</li>
     * </ul>  
     */
    private boolean verificaCuadrados(int row, int column, ConjuntoA<Integer> conjunto,  int MAXIMO){
        int numero;
        
        numero = cuadricula[row][column];
        if(numero != 0){
            if(!conjunto.add(numero))
                return false; 
        }
        if(row == MAXIMO - 1 && column == MAXIMO - 1)//acabe el ultimo cuadrado
                return true;
        else if((column + 1) % 3 == 0){
            if((row + 1) % 3 == 0){//acabe un cuadrado
                conjunto = new ConjuntoA<>();
                if(column == MAXIMO - 1){//acabe los 3 cuadrados que estan en los mismos 3 renglones
                    return verificaCuadrados(row + 1, 0, conjunto, MAXIMO);  
                }
                else
                    return verificaCuadrados(row - 2, column + 1, conjunto, MAXIMO);  
            }
            else//acabe las 3 columnas de una fila de un cuadrado
                return verificaCuadrados(row + 1, column - 2, conjunto, MAXIMO);       
        }
        else{//sigo en el mismo renglon del mismo cuadrado
            return verificaCuadrados(row, column + 1, conjunto, MAXIMO);
        }
    }
    
    /**
     * Metodo que verifica que no hayan numeros repetidos en los renglones, 
     * columnas y cuadros de 3 x 3.
     * @return <ul>
     * <li>true: Si el sudoku tiene un formato valido</li>
     * <li>false: Si el sudoku esta sobre-restringido</li>
     * </ul>  
     * @see verificaRenglones, verificaColumnas, verificaCuadrados
     */
    public boolean verificaCuadricula(){
        ConjuntoA<Integer> conjunto;
        
        conjunto = new ConjuntoA<>();
        return verificaRenglones(0, 0, conjunto, MAXIMO) && verificaColumnas(0, 0, conjunto, MAXIMO) && verificaCuadrados(0, 0, conjunto, MAXIMO);
    }
    
    
}
