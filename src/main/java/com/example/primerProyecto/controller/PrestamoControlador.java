/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.entity.Cliente;
import com.example.primerProyecto.service.ClienteService;
import com.example.primerProyecto.service.PrestamoService;
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
@RequestMapping("/prestamo")
public class PrestamoControlador {
    
    @Autowired
    ClienteService clienteService;
    
    @Autowired
    PrestamoService prestamoService;
    
    @GetMapping("/registrarPrestamo/{isbn}")
    public String registrarPrestamo(@PathVariable Long isbn, ModelMap model){
        List<Cliente> clientes = clienteService.listarClientes();
        
        model.addAttribute("clientes",clientes);
        
        return "prestar_libro";
    }
    
    @PostMapping("/registroPrestamo/{isbn}")
    public String registroPrestamo(@PathVariable Long isbn, @RequestParam Long documento){
        
      prestamoService.generarPrestamo(isbn, documento);
        
        return "redirect:../../libro/lista";
    }
    
    
    
}
