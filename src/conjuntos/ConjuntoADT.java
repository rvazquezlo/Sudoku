package conjuntos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Iterator;

/**
 *
 * @author Regina Vazquez
 */
public interface ConjuntoADT <T> extends Iterable<T>{
    boolean contains(T dato);
    boolean isEmpty();
    boolean add(T dato);
    T quita(T dato);
    ConjuntoADT<T> union(ConjuntoADT<T> conjunto);
    ConjuntoADT<T> interseccion(ConjuntoADT<T> conjunto);
    ConjuntoADT<T> diferencia(ConjuntoADT<T> conjunto);
    Iterator<T> iterator();
    int getCardinalidad();
    String toString();
    boolean equals(ConjuntoADT<T> otro);
    
}
