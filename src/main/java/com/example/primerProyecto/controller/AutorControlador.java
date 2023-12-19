/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.service.AutorService;
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
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorService autorService;
    
    @GetMapping("/registrar")
    public String registrar(){
        return "autor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        try {
            autorService.crearAutor(nombre);
        } catch (MyException ex) {
             modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
  
        return "autor_form.html";
    }
}
