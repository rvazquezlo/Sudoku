/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import conjuntos.ConjuntoA;
import conjuntos.ConjuntoADT;
import java.util.ArrayList;

/**
 *
 * @author Regina Vazquez
 */
public class SudokuSolver {
    
    private int[][] cuadricula, posicionesUsuario;
    private final int MAXIMO = 9;//MAXIMO DE RENGLONES, FILAS Y COLUMNAS
    
    /**
     * Constructor normal. Se instancian los atributos de la clase como matrices
     * de 9 x 9.
     */
    public SudokuSolver(){
        cuadricula = new int[MAXIMO][MAXIMO];
        posicionesUsuario = new int[MAXIMO][MAXIMO];
    }

    /**
     * Constructor para pruebas de cuadricula
     * @param cuadricula: arreglo de 9 x 9 con el problema del sudoku. Todas las
     * casillas vacias deben estar llenas con ceros.
     */
    public SudokuSolver(int[][] cuadricula) {
        this.cuadricula = cuadricula;
        posicionesUsuario = new int[MAXIMO][MAXIMO];
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
     * @return <ul>
     * <li>true: Si todos los renglones tienen formato valido</li>
     * <li>false: Si algun renglon esta sobre-restringido</li>
     * <li>llamada recursiva: si aun no se acaban de verificar todos los 
     * renglones</li>
     * </ul> 
     */
    private boolean verificaRenglones(int row, int column, ConjuntoA<Integer> conjunto){
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
                return verificaRenglones(row + 1, 0, conjunto);
            }
        }
        else
            return verificaRenglones(row, column + 1, conjunto); 
    }

    /**
     * Metodo recursivo que verifica que todas las columnas de cuadricula esten
     * en un formato valido para resolver el sudoku
     * @param row: el renglon de cuadricula que se esta analizando
     * @param column: la columna de cuadricula que se esta analizando
     * @param conjunto: el conjunto en el que se meten los digitos de la columna 
     * que se esta analizando
     * @return <ul>
     * <li>true: Si todos las columnas tienen formato valido</li>
     * <li>false: Si alguna columna esta sobre-restringida</li>
     * <li>llamada recursiva: si aun no se acaban de verificar todas las 
     * columnas</li>
     * </ul>  
     */
    private boolean verificaColumnas(int row, int column, ConjuntoA<Integer> conjunto){
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
                return verificaColumnas(0, column + 1, conjunto);
            }
        }
        else
            return verificaColumnas(row + 1, column, conjunto);
    }
    
    /**
     * Metodo recursivo que verifica que todos los cuadrados de cuadricula esten
     * en un formato valido para resolver el sudoku
     * @param row: el renglon de cuadricula que se esta analizando
     * @param column: la columna de cuadricula que se esta analizando
     * @param conjunto: el conjunto en el que se meten los digitos del cuadrado 
     * que se esta analizando
     * @return <ul>
     * <li>true: Si todos los cuadrados tienen formato valido</li>
     * <li>false: Si algun cuadrado esta sobre-restringido</li>
     * <li>llamada recursiva: si aun no se acaban de verificar todos los 
     * cuadrados</li>
     * </ul>  
     */
    private boolean verificaCuadrados(int row, int column, ConjuntoA<Integer> conjunto){
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
                    return verificaCuadrados(row + 1, 0, conjunto);  
                }
                else
                    return verificaCuadrados(row - 2, column + 1, conjunto);  
            }
            else//acabe las 3 columnas de una fila de un cuadrado
                return verificaCuadrados(row + 1, column - 2, conjunto);       
        }
        else{//sigo en el mismo renglon del mismo cuadrado
            return verificaCuadrados(row, column + 1, conjunto);
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
        return verificaRenglones(0, 0, new ConjuntoA<>()) && verificaColumnas(0, 0, new ConjuntoA<>()) && verificaCuadrados(0, 0, new ConjuntoA<>());
    }
    
    /**
     * Metodo auxiliar, recursivo cuyo proposito es regresar a la casilla 
     * anterior mas proxima para volver a intentar mas numeros que resuelvan el
     * sudoku.
     * Cuando encuentra una casilla que se puede modificar y se manda la 
     * posicion y el siguiente numero a probar al metodo privado resuleve.
     * @param renglon: renglon al que se quiere regresar
     * @param columna: columna a la que se quiere regresar 
     * @see resuelve
     */
    private void regresa(int renglon, int columna){
        int numeroAnterior;
        
        if(numerosUsuario(renglon, columna)){//No lo dio el usuario
            numeroAnterior = cuadricula[renglon][columna];
            cuadricula[renglon][columna] = 0;
            if(numeroAnterior < MAXIMO){
                resuelve(renglon, columna, numeroAnterior + 1);
            }
            else if(columna == 0){
                regresa(renglon - 1, MAXIMO);
            }
            else{
                regresa(renglon, columna - 1);
            }
        }
        else if(columna == 0){
                regresa(renglon - 1, MAXIMO);
            }
        else{
                regresa(renglon, columna - 1);
            }
    }
    
    /**
     * Metodo auxiliar que se encarga de mandar la posicion de la siguiente 
     * casilla a ser examinada, junto con el valor que se va a probar al
     * metodo privado resuelve.
     * @param renglon: de la casilla que se acaba de examinar
     * @param columna: de la casilla que se acaba de examinar
     * @see resuelve
     */
    private void mueveAlSiguiente(int renglon, int columna){
        if(columna == MAXIMO - 1){//ESTAMOS EN LA ULTIMA COLUMNA
            if(renglon > MAXIMO - 1)//NO ESTEMOS EN EL ULTIMO RENGLON
                resuelve(renglon + 1, 0, 1);
            //si estamos en el ultimo renglon, entonces ya quedo el sudoku
        }
        else{//Me voy al siguiente cuadrado y empiezo a intentar
            resuelve(renglon, columna + 1, 1);
        }
    }
    
    /**
     * Metodo recursivo, auxiliar de verificaCuadrado. Agrega todos los 
     * elementos de un cuadrado de cuadricula a un conjunto para verificar que
     * el numero que se esta probando no este repetido.
     * @param renglon: del elemento de cuadricula que se va a agregar al 
     * conjunto
     * @param columna: del elemento de cuadricula que se va a agregar al 
     * conjunto 
     * @param renglonMax: que se encuentra dento del cuadro de 3 x 3 analizado
     * @param columnaMax: que se encuentra dento del cuadro de 3 x 3 analizado
     * @param cuadro: conjunto en el que se agregan los numeros del cuadrado de
     * 3 x 3
     * @return: <ul>
     * <li>true: Si el numero que se esta probando es valido en el cuadrado
     * de 3 x 3</li>
     * <li>false: Si el numero que esta probando no es valido en el cuadrado de
     * 3 x 3</li>
     * </ul>   
     */
    private boolean agregaCuadrado(int renglon, int columna, int renglonMax, int columnaMax, ConjuntoA<Integer> cuadro){
        int numero;
        
        numero = cuadricula[renglon][columna];
        if(numero != 0){
            if(!cuadro.add(numero)){
                return false;
            }
        }
        if(columna == columnaMax - 1){
            if(renglon == renglonMax - 1)
               return true;
            else
                return agregaCuadrado(renglon + 1, columnaMax - 3, renglonMax, columnaMax, cuadro);
        }
        else
            return agregaCuadrado(renglon, columna + 1, renglonMax, columnaMax, cuadro);  
    }
    
    /**
     * Metodo auxiliar que verifica que numero no este repetido dentro del 
     * cuadrado perteneciente a la posicion indicada por renglon y columna. Para 
     * hacerlo, determina el cuadrado de 3 x 3 perteneciente a la posicion
     * marcada por renglon y columna y utiliza el metodo privado agregaCuadrado.
     * @param renglon: de la casilla en donde se intentara agregar a numero
     * @param columna: de la casilla en donde se intentara agregar a numero 
     * @param numero: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en el cuadrado
     * de 3 x 3</li>
     * <li>false: Si el numero que esta probando no es valido en el cuadrado de
     * 3 x 3</li>
     * </ul>  
     * @see agregaCuadrado
     */
    private boolean verificaCuadrado(int renglon, int columna, int numero){
        int renglonMax, columnaMax;
        ConjuntoA<Integer> cuadro;
        
        cuadro = new ConjuntoA<>();
        cuadro.add(numero);
        
        //Encontrar maximo renglon
        if(renglon < 3){
            renglonMax = 3;
        }
        else if(renglon < 6){
            renglonMax = 6;
        }
        else{
            renglonMax = 9;
        }
        
        //Encontrar maxima columna
        if(columna < 3){
            columnaMax = 3;
        }
        else if(columna < 6){
            columnaMax = 6;
        }
        else{
            columnaMax = 9;
        }
        return agregaCuadrado(renglonMax - 3, columnaMax - 3, renglonMax, columnaMax, cuadro);    
    }
    
    private  boolean creaConjuntoRenglon(int renglon, ConjuntoADT<Integer> conjuntoRenglon, int columna){
        if(!conjuntoRenglon.add(cuadricula[renglon][columna]))
            return false;
        else if(columna == MAXIMO - 1)
            return true;
        else
            return creaConjuntoRenglon(renglon, conjuntoRenglon, columna + 1);
    }
    
    private  boolean verificaRenglon(int renglon, int numero){
        ConjuntoA<Integer> conjuntoRenglon;
        
        conjuntoRenglon = new ConjuntoA();
        conjuntoRenglon.add(numero);
        return creaConjuntoRenglon(renglon, conjuntoRenglon, 0);
    }
    
    private  boolean creaConjuntoColumna(int columna, ConjuntoADT<Integer> conjuntoColumna, int renglon){
        if(!conjuntoColumna.add(cuadricula[renglon][columna]))
            return false;
        else if(renglon == MAXIMO - 1)
            return true;
        else
            return creaConjuntoColumna(columna, conjuntoColumna, renglon + 1);       
    }
    
    public  boolean verificaColumna(int columna, int numero){
        ConjuntoA<Integer> conjuntoColumna;
        
        conjuntoColumna = new ConjuntoA();
        conjuntoColumna.add(numero);
        return creaConjuntoColumna(columna, conjuntoColumna, 0);
    }
    
    public  boolean numerosUsuario(int renglon, int columna){
        return posicionesUsuario[renglon][columna] != 1;            
    }
    
    /**
     * Metodo recursivo, auxiliar del metodo publico resuelve. Encuentra una
     * solucion para el sudoku utilizando un algoritmo de fuerza bruta. Cuando
     * se encuentra, la agrega a cuadricula y pasa a la siguiente casilla. 
     * Si despues se encuentra que la solucion es invalida, se borra de 
     * cuadricula y se vuelve a intentar, regresando cuantas casillas sean 
     * necesarias. 
     * @param renglon: de la casilla para la cual se busca una solucion
     * @param columna: de la casilla para cual se busca una solucion
     * @param numero: entero del 1-9 que se va a intentar poner en la casilla
     * @see posicionVacia, verificaRenglon, verificaColumna, verificaCuadrado, 
     * regresa, mueveAlSiguiente
     */
    private void resuelve(int renglon, int columna, int numero){
        if(numerosUsuario(renglon, columna)){
            if(verificaRenglon(renglon, numero) && verificaColumna(columna, numero) && verificaCuadrado(renglon, columna, numero)){
                cuadricula[renglon][columna] = numero;
                mueveAlSiguiente(renglon, columna);
            }
            else if(numero < MAXIMO){
                resuelve(renglon, columna, numero + 1);
            }
            else{
                if(columna == 0)
                    regresa(renglon - 1, MAXIMO);//, cuadricula[renglon - 1][MAXIMO - 1]);
                else
                    regresa(renglon, columna - 1);//, cuadricula[renglon][columna - 1]);
            }        
        }
        else{//En esa casilla hay un numero que dio el usuario
            mueveAlSiguiente(renglon, columna);
        }  
    }
    
    /**
     * Metodo que resuelve el sudoku dado por el usuario con el apoyo del
     * metodo privado y recursivo, resuelve.
     * @return: una matriz de 9 x 9 con el sudoku resuelto.
     * @see resuelve
     */
    public int[][] resuelve(){
        resuelve(0, 0, 1);
        return cuadricula;
    }
}

