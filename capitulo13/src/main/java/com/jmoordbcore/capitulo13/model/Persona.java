/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcore.capitulo13.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import com.jmoordb.core.annotation.enumerations.JakartaSource;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Entity(jakartaSource = JakartaSource.JAVAEE_LEGACY)
public class Persona {

    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idpersona;
    @Column
    private String nombre;

    @Embedded
    private Deporte deporte;
    
    @Embedded
    private List<Musica> musica;

    @Referenced(from = "pais", localField = "idpais", typeReferenced = TypeReferenced.REFERENCED)
    private Pais pais;

    @Referenced(from = "oceano", localField = "idoceano", typeReferenced = TypeReferenced.REFERENCED)
    private List<Oceano> oceano;
    
    
    
    
    

    public Persona() {
    }

    public Persona(Long idpersona, String nombre, Deporte deporte, List<Musica> musica, Pais pais, List<Oceano> oceano) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.deporte = deporte;
        this.musica = musica;
        this.pais = pais;
        this.oceano = oceano;
    }

    public List<Musica> getMusica() {
        return musica;
    }

    public void setMusica(List<Musica> musica) {
        this.musica = musica;
    }
    
    
    
    

    public List<Oceano> getOceano() {
        return oceano;
    }

    public void setOceano(List<Oceano> oceano) {
        this.oceano = oceano;
    }

   
    public Long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Long idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona{");
        sb.append("idpersona=").append(idpersona);
        sb.append(", nombre=").append(nombre);
        sb.append(", deporte=").append(deporte);
        sb.append(", musica=").append(musica);
        sb.append(", pais=").append(pais);
        sb.append(", oceano=").append(oceano);
        sb.append('}');
        return sb.toString();
    }

   

}
