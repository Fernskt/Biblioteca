/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.entity.Autor;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.service.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Pc
 */
@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorService autorService;
    
    @GetMapping("/lista")
    public String listarAutores(ModelMap modelo){
        
        List<Autor> autores = autorService.listarAutores();
        modelo.addAttribute("autores", autores);
        
        return "list_autores.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "autor_form.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, ModelMap modelo){
        modelo.put("autor", autorService.getOne(id));
        return "modificar_autor.html";
    }
    
    @GetMapping("/eliminado/{id}")
    public String eliminar(@PathVariable Integer id, ModelMap modelo){
        modelo.put("autor", autorService.getOne(id));
        return "eliminar_autor.html";
    }
    
    @PostMapping("/eliminado/{id}")
    public String eliminar(@PathVariable Integer id){
        
        autorService.eliminarAutor(id);
        return "redirect:../lista";
        
    }
    
    @PostMapping("/modificar/{id}")
public String modificar(@PathVariable Integer id, @RequestParam String nombre, ModelMap modelo) {
    try {
        autorService.modificarAutor(nombre, id);
        Autor autorModificado = autorService.getOne(id);
        modelo.put("autor", autorModificado); // Agrega el autor al modelo
        modelo.put("exito", "El autor se ha modificado correctamente");
    } catch (MyException ex) {
        modelo.put("error", ex.getMessage());
        return "redirect:../modificar/{id}";
    }
    return "redirect:../lista";
}
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        try {
            autorService.crearAutor(nombre);
             modelo.put("exito", "El autor se ha creado correctamente");
        } catch (MyException ex) {
             modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
  
        return "redirect:../autor/lista";
    }
    
    
    
}
