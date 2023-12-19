/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.service.EditorialService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form.html";
    }
    
     @PostMapping("/registro")
     public String registro(@RequestParam String nombre){
        try {
            System.out.println("Se cargo correctamente la editorial " + nombre);
            editorialService.crearEditorial(nombre);
        } catch (MyException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
         return "editorial_form.html";
     }
    
}
