/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Editorial;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.repository.EditorialRepository;
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
public class EditorialService{
    @Autowired
    EditorialRepository editorialRepository;        
    
    @Transactional
    public void crearEditorial(String nombre) throws MyException{
        
        validar(nombre);
         Editorial editorial = new Editorial();
         editorial.setNombre(nombre);
         
         editorialRepository.save(editorial);
         
    }
       public List<Editorial> listarEditoriales(){
        
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepository.findAll();
        return editoriales;
        
         
    }
       
       public void modificarEditorial(String nombre, Integer id) throws MyException{
           validar(nombre);
           Optional<Editorial> respuesta = editorialRepository.findById(id);
           
           if(respuesta.isPresent()){
               Editorial editorial = respuesta.get();
               editorial.setNombre(nombre);
               
               editorialRepository.save(editorial);
           }
       }
       private void validar(String nombre) throws MyException{
             if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }
    }
    
}
