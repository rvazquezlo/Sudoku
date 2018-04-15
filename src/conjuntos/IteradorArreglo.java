/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntos;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Regina Vazquez
 */
public class IteradorArreglo<T> implements Iterator<T>{
    private T[] coleccion;
    private int elementosGuardados; //en la coleccion
    private int actual;
    
    public IteradorArreglo(T[] coleccion, int elementosGuardados){
        this.coleccion = coleccion;
        this.elementosGuardados = elementosGuardados;
        actual = 0; //el iterador se va a colocar en el elemento 0 de la coleccion
    }
    
    public boolean hasNext(){
        return actual < elementosGuardados;
    }
    
    /**
     * Si tengo algo me regresa el elemento en el que estoy y me muevo al siguiente.
     *  Si no tengo nada, lanza una exception
     * @return 
     */
    public T next(){
        if(hasNext()){
           actual++; 
           return coleccion[actual - 1];
        }
        throw new NoSuchElementException();   
    }
    
    /**
     * Este metodo no se implementa porque el remove corresponde a la 
     * coleccion directamente, no al iterator
     */
    public void remove(){
        throw new UnsupportedOperationException();
    }
    
}
