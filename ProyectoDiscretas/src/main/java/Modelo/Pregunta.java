/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author jexa1
 */
public class Pregunta {
    private String id;
    private String enunciado;
    private Categorias categoria;
    private final ArrayList<String> OPCIONES;
    private int respuesta;

    public Pregunta(String id, String enunciado, Categorias categoria, ArrayList<String> OPCIONES, int respuesta) {
        this.id = id;
        this.enunciado = enunciado;
        this.categoria = categoria;
        this.OPCIONES = OPCIONES;
        this.respuesta = respuesta;
    }
    public Pregunta(){
        this.OPCIONES=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public ArrayList<String> getOPCIONES() {
        return OPCIONES;
    }
    
    
    
}
