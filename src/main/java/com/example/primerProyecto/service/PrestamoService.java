/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Cliente;
import com.example.primerProyecto.entity.Libro;
import com.example.primerProyecto.entity.Prestamo;
import com.example.primerProyecto.repository.ClienteRepository;
import com.example.primerProyecto.repository.LibroRepository;
import com.example.primerProyecto.repository.PrestamoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class PrestamoService {
    
    @Autowired
    LibroRepository libroRepository;
    
    @Autowired
    PrestamoRepository prestamoRepository;
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Transactional
    public void generarPrestamo(Long isbn, Long documento){
        
        Prestamo prestamo = new Prestamo();
        
        Libro libro = libroRepository.findById(isbn).get();
        Cliente cliente = clienteRepository.findById(documento).get();
        
        prestamo.setFechaPrestamo(new Date());
        
       LocalDate fechaActual = LocalDate.now();
       LocalDate fechaDevolucion = fechaActual.plusDays(7);

        prestamo.setFechaDevolucion(java.sql.Date.valueOf(fechaDevolucion));
        
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
