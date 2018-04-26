/*
 * Clase para resolver problemas de sudoku utilizando búsqueda exhaustiva con 
 * retroceso. 
 * La clase lleva el proceso desde verificar que la cuadricula sea valida hasta
 * regresar una posible solucion.
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
    private long incioDeSolucion;//Tiempo
    private long tiempoMaximoSolucion;//Tiempo
    
    /**
     * Constructor normal. Se instancian una matriz de 9 x 9.
     */
    public SudokuSolver(){
        cuadricula = new int[MAXIMO][MAXIMO];
    }

    /**
     * Constructor para pruebas de cuadricula.
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
     * Metodo auxiliar de resuelve que se encarga de encontrar la siguiente 
     * casilla en cuadricula basandose en la posicion marcada por renglon y 
     * columna.
     * @param renglon: de la casilla que se acaba de examinar
     * @param columna: de la casilla que se acaba de examinar
     * @return: un arreglo de longitud dos. <ul>
     * <li>En la posicion 0 se encuentra el renglon de la siguiente casilla. Si 
     * en la posicion 0 hay un 9, ya no hay siguiente casilla. </li>
     * <li>en la posicion 1 se encuentra la columna de la siguiente casilla</li>
     * </ul> 
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
     * cuadrado perteneciente a la posicion indicada por renglon y columna de
     * cuadricula. Para hacerlo, determina el cuadrado de 3 x 3 perteneciente 
     * a la posicion marcada por renglon y columna y agrega en un conjunto los 
     * numeros de ese cuadrado y numero.
     * @param renglon: de la casilla en donde se intentara agregar a numero
     * @param columna: de la casilla en donde se intentara agregar a numero 
     * @param numero: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en el cuadrado
     * de 3 x 3</li>
     * <li>false: Si el numero que se esta probando no es valido en el cuadrado 
     * de 3 x 3</li>
     * </ul>  
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
        
        //agregar numeros de cuadrado determinado
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

    /**
     * Metodo auxiliar que verifica que numero no este repetido dentro de 
     * columna en cuadricula. Para  agrega en un conjunto los numeros de esa 
     * columna y numero.
     * @param columna: de la casilla en donde se intentara agregar a numero 
     * @param numero: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en la columna</li>
     * <li>false: Si el numero que se esta probando no es valido en la columna
     * </li>
     * </ul> 
     */
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
    
    /**
     * Metodo auxiliar que verifica que numero no este repetido dentro de 
     * renglon en cuadricula. Para  agrega en un conjunto los numeros de esa 
     * renglon y numero.
     * @param renglon: de la casilla en donde se intentara agregar a numero 
     * @param numero: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en el renglon</li>
     * <li>false: Si el numero que se esta probando no es valido en el renglon
     * </li>
     * </ul> 
     */
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
     * Metodo auxiliar de resuelve() que utiliza búsqueda exhaustiva con 
     * retroceso para encontrar una solucion para el sudoku.
     * @param renglon: de la casilla de cuadricula para la cual se esta buscando 
     * una solucion
     * @param columna: de la casilla de cuadricula para la cual se esta buscando 
     * una solucion
     * @return <ul>
     * <li>true: Si el sudoku tuvo solucion</li>
     * <li>false: Si no se puso encontrar una solucion despues de 3 segundos
     * </li>
     * <li>Llamada recursiva: Cuando no se pudo hacer nada en esa casilla y se
     * pasa a la siguiente</li>
     * </ul> 
     * @see: mueveAlSiguiente, verificaRenglon, verificaColumna, 
     * verificaCuadrado 
     */
    private boolean resuelve(int renglon, int columna){
        int numero, posiciones[];
        
        if(System.currentTimeMillis() > tiempoMaximoSolucion)//tiempo maximo para resolver
                return false;
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
                        return true;
                    cuadricula[renglon][columna] = 0;//La solucion que estaba no es valida. Se pone cero para evitar interferencia en los conjuntos
                }
            }
        return false;//Ningun numero del 1-9 sirve. Entonces regresa cambiar los de antes y vuelve a intentar   
        }
    }

    /**
     * Metodo que resuelve el sudoku dado por el usuario con el apoyo del
     * metodo privado y recursivo, resuelve.
     * @return: una matriz de 9 x 9 con el sudoku resuelto.
     * @see resuelve
     */
    public int[][] resuelve(){
        incioDeSolucion = System.currentTimeMillis();
        tiempoMaximoSolucion = incioDeSolucion + 3 * 1000;
        if(!resuelve(0, 0))
            cuadricula = null;
        return cuadricula;
    }
}

