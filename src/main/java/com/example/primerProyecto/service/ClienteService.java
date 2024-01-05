/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Cliente;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    public void crearCliente(Long documento, String nombre, String apellido, String telefono) throws MyException{
        
        validar(documento, nombre, apellido, telefono);
        
        Cliente cliente = new Cliente();
        
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        
        clienteRepository.save(cliente);
        
    }
    
    public List<Cliente> listarClientes(){
        
        List<Cliente> clientes = new ArrayList();
        clientes = clienteRepository.findAll();
        
        return clientes;
        
    }
    
    private void validar(Long documento, String nombre, String apellido, String telefono) throws MyException {
        
          if (documento == null) {
            throw new MyException("El n de documento no puede ser nulo o estar vacío");
        }
          
          List<Cliente> clientes = listarClientes();
          
          for (Cliente cliente : clientes) {
            
              if(cliente.getDocumento().equals(documento)){
                  throw  new MyException("Ya existe un usuario con ese documento");
              }
              
        }
        
           if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }
           if (apellido.isEmpty() || apellido == null) {
            throw new MyException("El apellido no puede ser nulo o estar vacío");
        }
          if (telefono.isEmpty() || telefono == null) {
            throw new MyException("El numero de telefono no puede ser nulo o estar vacío");
        }
        
    }
    
    
}
