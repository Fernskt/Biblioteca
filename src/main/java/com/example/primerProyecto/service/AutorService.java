/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Autor;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.repository.AutorRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class AutorService {
    
    @Autowired
    AutorRepository autorRepository;
    
    @Transactional
    public void crearAutor(String nombre) throws MyException{
        
        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepository.save(autor);
    }
    
        public List<Autor> listarAutores(){
        
        List<Autor> autores = new ArrayList();
        
        autores = autorRepository.findAll();
        
        return autores;
        
    }
        
        public void modificarAutor(String nombre, Integer id) throws MyException{
            
            validar(nombre);
            
            Optional<Autor> respuesta = autorRepository.findById(id);
            
            if (respuesta.isPresent()){
                Autor autor = respuesta.get();
                autor.setNombre(nombre);
                autorRepository.save(autor);
            }
            
        }
        
        public void eliminarAutor(Integer id){
            
            Optional<Autor> respuesta = autorRepository.findById(id);
            
            if(respuesta.isPresent()){
                Autor autor = respuesta.get();
                autorRepository.delete(autor);
            }
            
        }
        
        public Autor getOne(Integer id){
            return autorRepository.getOne(id);
        }
        
        private void validar(String nombre) throws MyException{
             if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }
    }
    
}
