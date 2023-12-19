/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.repository;

import com.example.primerProyecto.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pc
 */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
    
}
