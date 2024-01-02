/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Cliente;
import com.example.primerProyecto.entity.Libro;
import com.example.primerProyecto.entity.Prestamo;
import com.example.primerProyecto.repository.LibroRepository;
import com.example.primerProyecto.repository.PrestamoRepository;
import jakarta.transaction.Transactional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Pc
 */
public class PrestamoService {
    
    @Autowired
    LibroRepository libroRepository;
    
    @Autowired
    PrestamoRepository prestamoRepository;
    
    @Transactional
    public void generarPrestamo( Date fechaDevolucion, Libro libro, Cliente cliente){
        
        Prestamo prestamo = new Prestamo();
        
        prestamo.setFechaPrestamo(new Date());
        
        prestamo.setFechaDevolucion(fechaDevolucion);
        
        prestamo.setCliente(cliente);
        
        prestamo.setLibro(libro);
        
       int ejemplaresRestantes = libro.getEjemplaresRestantes() -1;
       int ejemplaresPrestados = libro.getEjemplaresPrestados() +1;
       
       libro.setEjemplaresPrestados(ejemplaresPrestados);
       libro.setEjemplaresRestantes(ejemplaresRestantes);
       
       libroRepository.save(libro);
        
        prestamoRepository.save(prestamo);
        
        
    }
    
    
}
