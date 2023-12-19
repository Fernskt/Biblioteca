/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.service;

import com.example.primerProyecto.entity.Autor;
import com.example.primerProyecto.entity.Editorial;
import com.example.primerProyecto.entity.Libro;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.repository.AutorRepository;
import com.example.primerProyecto.repository.EditorialRepository;
import com.example.primerProyecto.repository.LibroRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pc
 */
@Service
public class LibroService {
    
    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private EditorialRepository editorialRepository;
    
    
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws MyException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();
        
        
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(true);
        libro.setFechaAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepository.save(libro);
        
    }
    
    public List<Libro> listarLibros(){
        
        List<Libro> libros = new ArrayList();
        
        libros = libroRepository.findAll();
        
        return libros;
        
    }
    
    public void modificarLibro(Long isbn, String titulo, Integer idAutor, Integer idEditorial, int ejemplares) throws MyException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepository.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepository.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepository.findById(idAutor);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
          if(respuestaAutor.isPresent()){
            autor = respuestaAutor.get();
          }
            if(respuestaEditorial.isPresent()){
            editorial = respuestaEditorial.get();
            }
        
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libroRepository.save(libro);
        }
        
    }
    
    
    
    private void validar(Long isbn, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws MyException {
        
        
        List<Libro> libros = listarLibros();
        
        if (isbn == null) {
            throw new MyException("El isbn no puede ser nulo o estar vacío");
        }
        
        for (Libro libro : libros) {
              if(isbn == libro.getIsbn()){
            throw new MyException("El isbn ya existe");
        }
        }
     
        if (titulo.isEmpty() || titulo == null) {
            throw new MyException("El título no puede ser nulo o estar vacío");
        }
        if (ejemplares == null) {
            throw new MyException("la Cantidad de ejemplares no puede ser nula");
        }
        if (idAutor == 0) {
            throw new MyException("Debe seleccionar el Autor");
        }
        if (idEditorial == 0) {
            throw new MyException("Debe seleccionar la editorial");
        }
    }
    
}
