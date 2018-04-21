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
    
    private int[][] cuadricula, posicionesUsuario;
    private final int MAXIMO = 9;//MAXIMO DE RENGLONES, FILAS Y COLUMNAS
    
    public SudokuSolver(){
        cuadricula = new int[MAXIMO][MAXIMO];
    }

    /**
     * Constructor para pruebas de cuadricula
     * @param cuadricula: arreglo de 9 x 9 con el problema del sudoku. Todas las
     * casillas vacias deben estar llenas con ceros.
     */
    public SudokuSolver(int[][] cuadricula) {
        this.cuadricula = cuadricula;
        
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
            posicionesUsuario[row][column] = 1;
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
        return verificaRenglones(0, 0, new ConjuntoA<>(), MAXIMO) && verificaColumnas(0, 0, new ConjuntoA<>(), MAXIMO) && verificaCuadrados(0, 0, new ConjuntoA<>(), MAXIMO);
    }
    
    public int[][] resuelve(){
        resuelve(0, 0, 0, 1);
    }
    
    private void numeroIgualAMaximo(int renglon, int columna, int numero){
        if(columna == 0){
            if(cuadricula[renglon - 1][MAXIMO - 1] != 9){
                if(posicionValida(renglon - 1, MAXIMO - 1)){
                    
                }
            }
        }
    }
    
    private void hola(int renglon, int columna, int numero){
        int numeroAnterior;
        
        if(posicionVacia()){
            if(verificaRenglon() && verificaColumna() && verificaCuadrado()){
                cuadricula[renglon][columna] = numero;
                if(columna == MAXIMO - 1){//ESTAMOS EN LA ULTIMA COLUMNA
                    if(renglon > MAXIMO - 1){//NO ESTEMOS EN EL ULTIMO RENGLON
                        resuelve(renglon + 1, 0, 1);
                    }
                    //si estamos en el ultimo renglon, entonces ya quedo el sudoku
                }
                else{//Me voy al siguiente cuadrado y empiezo a intentar
                    resuelve(renglon, columna + 1, 1);
                }
            }
            else{
                if(numero == MAXIMO){//entonces te regresas al cuadrado de antes a intentar mas numeros
                    if(columna == 0){//El cuadro de antes esta en el renglon anterior
                        numeroAnterior = cuadricula[renglon - 1][8];
                        if(posicionVacia(renglon - 1, 8)){//El usuario no dio nada ahi
                            cuadricula[renglon - 1][8] = 0;
                        }
                        if(numeroAnterior < 9){
                            resuelve(renglon - 1, 8, numeroAnterior + 1);
                        }
                        else{
                            numeroAnterior = cuadricula[renglon - 1][7];
                        }
                            
                        
                    }
                    else{
                        resuelve(renglon, columna - 1, cuadricula[renglon][columna] + 1);
                    }
                }
                else{//en el mismo cuadrado checas mas numeros 
                    resuelve(renglon, columna, numero + 1);
                }
                
            }        
        }
        else{//En esa casilla hay un numero que dio el usuario
            if(columna == MAXIMO - 1){//ESTAMOS EN LA ULTIMA COLUMNA
                if(renglon > MAXIMO - 1){//NO ESTEMOS EN EL ULTIMO RENGLON
                    resuelve(renglon + 1, 0, 1);
                }
                //si estamos en el ultimo renglon, entonces ya quedo el sudoku
            }
            else{
                resuelve(renglon, columna + 1, 1);
            }
        }
        
    }
    
    private void resuelve(int renglon, int columna,  int numero){
        
        if(numero == 10){
            if(columna == 0){
                resuelve(renglon - 1, 8, cuadrado, cuadricula[renglon - 1][8] + 1);
            }
            else
                resuelve(renglon, columna - 1, cuadrado, cuadricula[renglon][columna] + 1);  
        }
        if(posicionVacia()){//en la casilla no hay un numero que dio el usuario
            if(verificaRenglon() && verificaColumna() && verificaCuadrado()){
                if(verificaColumna()){
                    if(verificaCuadrado()){//si todos son validos y el numero es valido, se guarda en cuadricula y se pasa al siguiente
                        cuadricula[renglon][columna] = numero;
                        if(columna == MAXIMO - 1){//ESTAMOS EN LA ULTIMA COLUMNA
                            if(renglon > MAXIMO - 1){//NO ESTEMOS EN EL ULTIMO RENGLON
                                resuelve(renglon + 1, 0, cuadrado, 1);
                            }
                            //si estamos en el ultimo renglon, entonces ya quedo el sudoku
                        }
                        else{
                            resuelve(renglon, columna + 1, cuadrado, 1);
                        }
                    }
                    else{
                        if(numero == MAXIMO){//entonces te regresas al cuadrado de antes a intentar mas numeros
                            if(columna == 0){//CHECAR QUE NO ESTEMOS EN EL PRIMER RENGLON
                                resuelve(renglon - 1, 8, cuadrado, cuadricula[renglon - 1][8] + 1);
                            }
                            else{
                                resuelve(renglon, columna - 1, cuadrado, cuadricula[renglon][columna] + 1);
                            }
                        } 
                        else{//en el mismo cuadrado checas mas numeros 
                            resuelve(renglon, columna, cuadrado, numero + 1);
                        }
                    }
                }
                else{//columna no valida
                    if(numero == MAXIMO){//entonces te regresas al cuadrado de antes a intentar mas numeros
                        if(columna == 0){//CHECAR QUE NO ESTEMOS EN EL PRIMER RENGLON
                            resuelve(renglon - 1, 8, cuadrado, cuadricula[renglon - 1][8] + 1);
                        }
                        else{
                            resuelve(renglon, columna - 1, cuadrado, cuadricula[renglon][columna] + 1);
                        }
                    }
                    else{//en el mismo cuadrado checas mas numeros 
                        resuelve(renglon, columna, cuadrado, numero + 1);
                    }
                }
            }
            else{
                if(numero == MAXIMO){//entonces te regresas al cuadrado de antes a intentar mas numeros
                    if(columna == 0){//CHECAR QUE NO ESTEMOS EN EL PRIMER RENGLON
                        resuelve(renglon - 1, 8, cuadrado, cuadricula[renglon - 1][8] + 1);
                    }
                    else{
                        resuelve(renglon, columna - 1, cuadrado, cuadricula[renglon][columna] + 1);
                    }
                }
                else{//en el mismo cuadrado checas mas numeros 
                    resuelve(renglon, columna, cuadrado, numero + 1);
                }
                
            }        
        }
        else{//En esa casilla hay un numero que dio el usuario
            if(columna == MAXIMO - 1){//ESTAMOS EN LA ULTIMA COLUMNA
                if(renglon > MAXIMO - 1){//NO ESTEMOS EN EL ULTIMO RENGLON
                    resuelve(renglon + 1, 0, cuadrado, 1);
                }
                //si estamos en el ultimo renglon, entonces ya quedo el sudoku
            }
            else{
                resuelve(renglon, columna + 1, cuadrado, 1);
            }
        }
    }
    
    
}
