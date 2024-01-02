/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Cliente;
import com.example.primerProyecto.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Transactional
    public void crearCliente(Long documento, String nombre, String apellido, String telefono){
        
        Cliente cliente = new Cliente();
        
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        
        clienteRepository.save(cliente);
        
    }
    
    
}
