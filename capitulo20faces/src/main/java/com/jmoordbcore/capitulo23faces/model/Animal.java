/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcore.capitulo23faces.model;

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
@Entity(jakartaSource = JakartaSource.JAKARTA)
public class Animal {
 @Id(autogeneratedActive = AutogeneratedActive.ON)
 private Long idanimal;
 @Column
 private String animal;
 
 
 @Embedded
 private Especie especie;
 
 @Embedded
 private List<Plaga> plaga;
 
 @Referenced(from = "grupo",localField = "idgrupo",typeReferenced = TypeReferenced.REFERENCED)
 private Grupo grupo;

 @Referenced(from="zoo",localField = "idzoo",typeReferenced = TypeReferenced.REFERENCED)
 private List<Zoo> zoo;
 
 
 
    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Animal() {
    }

    public Animal(Long idanimal, String animal, Especie especie, List<Plaga> plaga, Grupo grupo, List<Zoo> zoo) {
        this.idanimal = idanimal;
        this.animal = animal;
        this.especie = especie;
        this.plaga = plaga;
        this.grupo = grupo;
        this.zoo = zoo;
    }


    public List<Zoo> getZoo() {
        return zoo;
    }

    public void setZoo(List<Zoo> zoo) {
        this.zoo = zoo;
    }

   
    
    
    
    
    public Animal(Long idanimal, String animal) {
        this.idanimal = idanimal;
        this.animal = animal;
    }

    public Long getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(Long idanimal) {
        this.idanimal = idanimal;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public List<Plaga> getPlaga() {
        return plaga;
    }

    public void setPlaga(List<Plaga> plaga) {
        this.plaga = plaga;
    }
 
 
 
}
