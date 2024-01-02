/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Libro {
    
    @Id
    private Long isbn;
    
    private String titulo;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    
    private boolean alta;
    
    @ManyToOne
    private Autor autor;
    
    @ManyToOne
    private Editorial editorial;

    public Libro() {       
    }

    public Libro(Long isbn, String titulo, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Date fechaAlta, boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.fechaAlta = fechaAlta;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

   

    
}