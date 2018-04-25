/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import conjuntos.ConjuntoA;

/**
 * @author Shanti Sanchez Barbero
 * @author Regina Vazquez Santa María López
 * @author Regina Canalizo 
 * @author Jerusa Chavero Gonzalez
 * @author Damian Perez Landeros
 */
public class SudokuSolver {
    
    private int[][] cuadricula;
    private final int MAXIMO = 9;//MAXIMO DE RENGLONES, FILAS Y COLUMNAS
    
    /**
     * Constructor normal. Se instancian los atributos de la clase como matrices
     * de 9 x 9.
     */
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
     * Metodo auxiliar que se encarga de mandar la posicion de la siguiente 
     * casilla a ser examinada, junto con el valor que se va a probar al
     * metodo privado resuelve.
     * @param renglon: de la casilla que se acaba de examinar
     * @param columna: de la casilla que se acaba de examinar
     * @see resuelve
     */    
    private int[] mueveAlSiguiente(int renglon, int columna){
        int[] posicion;
        
        posicion = new int[2];
        if(columna == MAXIMO - 1){//ESTAMOS EN LA ULTIMA COLUMNA
            if(renglon < MAXIMO - 1){//NO ESTEMOS EN EL ULTIMO RENGLON
                posicion[0] = renglon + 1;
                posicion[1] = 0;  
            }
            else{//si estamos en el ultimo renglon, entonces ya quedo el sudoku
                posicion[0] = 9;
            }    
        }
        else{//Me voy al siguiente cuadrado y empiezo a intentar
            posicion[0] = renglon;
            posicion[1] = columna + 1;
        }
        return posicion;
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
        int renglonMax, columnaMax, numeroCuadricula;
        ConjuntoA<Integer> cuadro;
        boolean respuesta;
        
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
        renglon = renglonMax - 3;
        respuesta = true;
        while(respuesta && renglon < renglonMax){
            columna = columnaMax - 3;
            while(respuesta && columna < columnaMax){
                numeroCuadricula = cuadricula[renglon][columna];
                if(numeroCuadricula != 0){
                    respuesta = cuadro.add(numeroCuadricula);
                }
                columna = columna + 1;
            }
            renglon = renglon + 1;
        }//end-while
        return respuesta;  
    }

    public boolean verificaColumna(int columna, int numero){
        ConjuntoA<Integer> conjuntoColumna;
        boolean respuesta;
        int renglon, numeroCuadricula;
        
        conjuntoColumna = new ConjuntoA();
        conjuntoColumna.add(numero);
        respuesta = true;
        renglon = 0;
        while(respuesta && renglon < MAXIMO){
            numeroCuadricula = cuadricula[renglon][columna];
            if(numeroCuadricula != 0){
                respuesta = conjuntoColumna.add(numeroCuadricula);
            }
            renglon++;
        }
        return respuesta;    
    }
    
    public boolean verificaRenglon(int renglon, int numero){
        ConjuntoA<Integer> conjuntoRenglon;
        boolean respuesta;
        int columna, numeroCuadricula;
        
        conjuntoRenglon = new ConjuntoA();
        conjuntoRenglon.add(numero);
        respuesta = true;
        columna = 0;
        while(respuesta && columna < MAXIMO){
            numeroCuadricula = cuadricula[renglon][columna];
            if(numeroCuadricula != 0){
                respuesta = conjuntoRenglon.add(numeroCuadricula);
            }
            columna++;
        }
        return respuesta;    
    }
   
    /**
     * 
     * @param renglon
     * @param columna
     * @return 
     */
    private boolean resuelve(int renglon, int columna){
        int numero, posiciones[];
        
        if(renglon == MAXIMO)//Estado base
            return true;
        if(cuadricula[renglon][columna] != 0){
            posiciones = mueveAlSiguiente(renglon, columna);
            return resuelve(posiciones[0], posiciones[1]);
        }
        else{    
            for(numero = 1; numero < MAXIMO + 1; numero++){
                if(verificaRenglon(renglon, numero) && verificaColumna(columna, numero) && verificaCuadrado(renglon, columna, numero)){
                    cuadricula[renglon][columna] = numero;//Guardar valor
                    posiciones = mueveAlSiguiente(renglon, columna);
                    if(resuelve(posiciones[0], posiciones[1]))//Mover a la siguiente casilla
                        return true;//Se interrumpe el ciclo si queda
                    cuadricula[renglon][columna] = 0;//La solucion que estaba no es valida. Se pone cero
                }
            }
        return false;//Ningun numero del 1-9 sirve. Entonces regresa cambiar los de antes y vuelve a intentar   
        }
    }

//    /**
//     * Metodo recursivo, auxiliar del metodo publico resuelve. Encuentra una
//     * solucion para el sudoku utilizando un algoritmo de fuerza bruta. Cuando
//     * se encuentra, la agrega a cuadricula y pasa a la siguiente casilla. 
//     * Si despues se encuentra que la solucion es invalida, se borra de 
//     * cuadricula y se vuelve a intentar con los numeros que aun no se
//     * han intentado, regresando cuantas casillas sea  necesario. 
//     * @param renglon: de la casilla para la cual se busca una solucion
//     * @param columna: de la casilla para cual se busca una solucion
//     * @param numero: entero del 1-9 que se va a intentar poner en la casilla
//     * @see posicionVacia, verificaRenglon, verificaColumna, verificaCuadrado, 
//     * regresa, mueveAlSiguiente
//     */

    /**
     * Metodo que resuelve el sudoku dado por el usuario con el apoyo del
     * metodo privado y recursivo, resuelve.
     * @return: una matriz de 9 x 9 con el sudoku resuelto.
     * @see resuelve
     */
    public int[][] resuelve(){
        resuelve(0, 0);
        return cuadricula;
    }
}

