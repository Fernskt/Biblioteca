/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.entity.Editorial;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.service.EditorialService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialService editorialService;
    
    @GetMapping("/lista")
    public String listarEditoriales(ModelMap modelo){
        
        List<Editorial> editoriales = editorialService.listarEditoriales();
        
        modelo.addAttribute("editoriales", editoriales);
        
        return "list_editoriales.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form.html";
    }
    
     @PostMapping("/registro")
     public String registro(@RequestParam String nombre, ModelMap modelo){
        try {
            System.out.println("Se cargo correctamente la editorial " + nombre);
            editorialService.crearEditorial(nombre);
            modelo.put("exito", "Se cargo la editorial correctamente");
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
         return "redirect:../editorial/lista";
     }
    
}
