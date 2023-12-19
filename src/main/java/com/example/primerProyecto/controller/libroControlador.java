/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.entity.Autor;
import com.example.primerProyecto.entity.Editorial;
import com.example.primerProyecto.entity.Libro;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.service.AutorService;
import com.example.primerProyecto.service.EditorialService;
import com.example.primerProyecto.service.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Pc
 */
@Controller
@RequestMapping("/libro") //localhost:8080/autor
public class libroControlador {

    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;
    
    @GetMapping("/lista")
    public String listarLibros(ModelMap modelo){
        
        List<Libro> libros = libroService.listarLibros();
        modelo.addAttribute("libros" , libros);
        
        return "listaLibros.html";
        
    }

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam Integer idAutor,
            @RequestParam Integer idEditorial, ModelMap modelo) {

        try {
            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado exitosamente");
        } catch (MyException ex) {
            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }

}
