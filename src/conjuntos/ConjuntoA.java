/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntos;

import java.util.Iterator;

/**
 *
 * @author Regina Vazquez
 */
public class ConjuntoA<T> implements ConjuntoADT<T>{
    private T[] conjunto;
    private int cardinalidad; //Cuantos elementos tengo en el conjunto
    private final int MAXIMO = 50;
    
    public ConjuntoA(){
        conjunto = (T[])new Object[MAXIMO];
        cardinalidad = 0;
    }
    
    public ConjuntoA(int maximo){
        conjunto = (T[])new Object[maximo];
        cardinalidad = 0;
    }

    public int getCardinalidad() {
        return cardinalidad;
    }

    public int getMAXIMO() {
        return MAXIMO;
    }
    
    public boolean isEmpty(){
        return cardinalidad == 0;
    }

    @Override
    public boolean equals(ConjuntoADT<T> otro) {
        boolean iguales;
       
        iguales = false;
        if(this == otro){
            iguales = true;
        }
        else if(otro != null)
            if(cardinalidad == this.getCardinalidad())
                if(interseccion(otro).getCardinalidad() == cardinalidad)
                    iguales = true;
        return iguales;
    }
    
    public T quita(T dato){
        T resultado;
        int i;
        
        resultado = null;
        i = 0;
        while(i < cardinalidad && !dato.equals(conjunto[i]))
            i++;
        if(i < cardinalidad){
           resultado = conjunto[i];
           conjunto[i] = conjunto[cardinalidad -1];
           conjunto[cardinalidad -1] = null;
           cardinalidad--;
        }
        return resultado;
    }
    
    public Iterator<T> iterator(){
        return new IteradorArreglo(conjunto, cardinalidad);
    }
    
    public boolean contains(T obj){
        boolean contiene;
        Iterator<T> iterador;
        
        iterador = this.iterator();
        contiene = false;
        while(iterador.hasNext() && !contiene)
            contiene = iterador.next().equals(obj);
        return contiene;
    } 
    
    private void extiendeArreglo(){
        T[] nuevoConjunto;
        int i, longitud;
        
        longitud = conjunto.length;
        nuevoConjunto =  (T[]) new Object[longitud * 20];
        for(i = 0; i < longitud; i++)
            nuevoConjunto[i] = conjunto[i];
        conjunto = nuevoConjunto;
    }
    
    public boolean add(T obj){
        boolean agrega;
        
        if(contains(obj))
            agrega =  false;
        else{
            if(conjunto.length == cardinalidad)
                extiendeArreglo();
            conjunto[cardinalidad] = obj;
            cardinalidad++;
            agrega = true;
        }
        return agrega;    
    }
    
    public ConjuntoADT<T> union(ConjuntoADT<T> otro){
        ConjuntoADT<T> union;
        Iterator<T> iteradorOtro, iterador;
        
        try{
            iteradorOtro = otro.iterator();
            iterador = this.iterator();
            union = new ConjuntoA<T>();
            while(iterador.hasNext())
                union.add(iterador.next());
            while(iteradorOtro.hasNext())
                union.add(iteradorOtro.next());
        }catch(NullPointerException e){
            union = null;
        }
        return union;
    }
    
    public ConjuntoADT<T> unionRecursiva(ConjuntoADT<T> otro){
        ConjuntoADT<T> union;
        Iterator<T> iteradorOtro, iterador;
        
        try{
            union = new ConjuntoA<T>();
            iterador = this.iterator();
            unionRecursiva(iterador, union); 
            iteradorOtro = otro.iterator();
            unionRecursiva(iteradorOtro, union);
        }catch(NullPointerException e){
            union = null;
        }
        return union;
    }
    
    private void unionRecursiva(Iterator<T> iterador, ConjuntoADT<T> union){
        if(iterador.hasNext()){
            union.add(iterador.next());
            unionRecursiva(iterador, union);
        }
    }
    
    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro){
        ConjuntoADT<T> interseccion;
        Iterator<T> iteradorOtro;
        T elemento;
        
        try{
            iteradorOtro = otro.iterator();
            interseccion = new ConjuntoA<T>();
            while(iteradorOtro.hasNext()){
                elemento = iteradorOtro.next();
                if(contains(elemento))
                    interseccion.add(elemento);
            }// end while
        }catch(NullPointerException e){
            interseccion = null;
        }
        return interseccion;
    }

    public ConjuntoADT<T> interseccionRecursiva(ConjuntoADT<T> otro){
        ConjuntoADT<T> interseccion;
        Iterator<T> iteradorOtro;
        
        try{
            interseccion = new ConjuntoA<T>();
            iteradorOtro = otro.iterator();
            interseccionRecursiva(iteradorOtro, interseccion);
        }catch(NullPointerException e){
            interseccion = null;
        }
        return interseccion;
    }
    
    private void interseccionRecursiva(Iterator<T> iterador, ConjuntoADT<T> interseccion){
        T elemento;
        
        if(iterador.hasNext()){
            elemento = iterador.next();
            if(contains(elemento))
                interseccion.add(elemento);
            interseccionRecursiva(iterador, interseccion);
        }
    }
    /**
     * Elementos del calling object que no estén en otro
     * @param otro
     * @return 
     */
    @Override
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro) {
        ConjuntoADT<T> diferencia;
        Iterator<T> iterador;
        T elemento;
        
        try{
            diferencia = new ConjuntoA();
            iterador = this.iterator();
            while(iterador.hasNext()){
                elemento = iterador.next();
                if(!otro.contains(elemento))
                    diferencia.add(elemento);
            }      
        }catch(NullPointerException e){
            diferencia = null;
        }
        return diferencia;
    }
    
    public ConjuntoADT<T> diferenciaRecursiva(ConjuntoADT<T> otro){
        ConjuntoADT<T> diferencia;
        Iterator<T> iterador;
        
        
        iterador = this.iterator();
        diferencia = new ConjuntoA<T>();
        diferenciaRecursiva(iterador, diferencia, otro);
        return diferencia;
    }
    
    private void diferenciaRecursiva(Iterator<T> iterador, ConjuntoADT<T> diferencia, ConjuntoADT<T> otro){
        T elemento;
        
        try{
            if(iterador.hasNext()){
                elemento = iterador.next();
                if(!otro.contains(elemento))
                    diferencia.add(elemento);
                diferenciaRecursiva(iterador, diferencia, otro);
            }
        }catch(NullPointerException e){
            diferencia = null;
        }       
    }

    @Override
    public String toString() {
        Iterator<T> iterador;
        StringBuilder sb;
        
        sb = new StringBuilder();
        iterador = this.iterator();
//        sb.append("\nConjunto de " + conjunto.getClass().getSimpleName() + "\n");
//        sb.append("\n\t Cardinalidad: " + cardinalidad);
//        sb.append("\n\t Elementos: \n\t");
        while(iterador.hasNext())
            sb.append("\t" + iterador.next());
        return sb.toString();
    }
    
    
}
