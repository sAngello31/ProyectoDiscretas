/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author CltControl
 */
public class ArbolBinario<T> {

    public T[] arr;
    int ultimo;
    int capacidad;
    Comparator<T> comp;
    Class<T> clazz;

    public ArbolBinario(Comparator<T> comp,Class<T> clazz) {
        this.comp = comp;
        ultimo = -1;
        arr = (T[]) new Object[10];
        capacidad = 10;
        this.clazz=clazz;
    }

    public ArbolBinario(Comparator<T> comp, int capacidad,Class<T> clazz) {
        this.comp = comp;
        ultimo = -1;
        arr = (T[]) Array.newInstance(clazz, capacidad);
        this.capacidad = capacidad;
    }

    private void crecerArreglo() {
        T[] arregloTemp = (T[]) Array.newInstance(clazz, capacidad+5);
        for (int i = 0; i < capacidad; i++) {
            arregloTemp[i] = arr[i];
        }
        arr = arregloTemp;
        capacidad += 5;
    }

    private int getPadre(int i) {
        return (i - 1) / 2;
    }

    private int getHijoI(int i) {
        return (2 * i) + 1;
    }

    private int getHijoD(int i) {
        return 2 * (i + 1);
    }

    private int conocerNumeroHijos(int i) {
        if (getHijoI(i) + 1 <= ultimo) {
            return 2;
        } else if (getHijoI(i) <= ultimo) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isEmpty() {
        return (ultimo == -1);
    }

    public void encolar(T contenido) {
        if (capacidad == ultimo + 1) {
            crecerArreglo();
        }
        ultimo++;
        arr[ultimo] = contenido;
        reajustarEncolamiento(ultimo);
    }

    public T desencolar() {
        if (!isEmpty()) {
            T retorno = arr[0];
            arr[0] = arr[ultimo];
            ultimo--;
            reajustarDesencolamiento();
            return retorno;
        } else {
            throw new RuntimeException();
        }
    }

    private void reajustarEncolamiento(int i) {
        int referencia = i;
        while (referencia > 0) {
            int esMayorQuePadre = (comp.compare(arr[referencia], arr[getPadre(referencia)]));
            if (esMayorQuePadre > 0) {
                cambiarValores(getPadre(referencia), referencia);
                referencia = getPadre(referencia);
            } else {
                referencia = 0;
            }
        }
    }

    private void reajustarDesencolamiento() {
        int referencia = 0;
        while (referencia <= ultimo) {
            if (conocerNumeroHijos(referencia) == 2) {
                int comparePadreHI = comp.compare(arr[referencia], arr[getHijoI(referencia)]);
                int comparePadreHD = comp.compare(arr[referencia], arr[getHijoD(referencia)]);
                int compareHIHD = comp.compare(arr[getHijoI(referencia)], arr[getHijoD(referencia)]);

                if (comparePadreHI >= 0 && comparePadreHD >= 0) {
                    referencia = ultimo + 1;
                } else {
                    if (comparePadreHI < 0) {
                        if (compareHIHD < 0) {
                            cambiarValores(referencia, getHijoD(referencia));
                            referencia = getHijoD(referencia);
                        } else {
                            cambiarValores(referencia, getHijoI(referencia));
                            referencia = getHijoI(referencia);
                        }
                    } else if (comparePadreHD < 0) {
                        cambiarValores(referencia, getHijoD(referencia));
                        referencia = getHijoD(referencia);
                    }
                }
            } else if (conocerNumeroHijos(referencia) == 1) {
                if (comp.compare(arr[referencia], arr[getHijoI(referencia)]) < 0) {
                    cambiarValores(referencia, getHijoI(referencia));
                    referencia = getHijoI(referencia);
                } else {
                    referencia = ultimo + 1;
                }
            } else {
                referencia = ultimo + 1;
            }
        }
    }
    private void cambiarValores(int padre, int hijo) {
        T tmp = arr[padre];
        arr[padre] = arr[hijo];
        arr[hijo] = tmp;
    }
}
