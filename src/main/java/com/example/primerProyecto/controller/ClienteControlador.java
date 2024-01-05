/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.primerProyecto.controller;

import com.example.primerProyecto.entity.Cliente;
import com.example.primerProyecto.exceptions.MyException;
import com.example.primerProyecto.service.ClienteService;
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
@RequestMapping("/cliente")//localhost:8080/cliente
public class ClienteControlador {
    
    @Autowired
    ClienteService clienteService;
    
    @GetMapping("/registrar")
    public String registrarCliente(){
        return "cliente_form";
    }
    
    @GetMapping("/listar")
    public String listarClientes(ModelMap model){
        
        List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("clientes",clientes);
        
        return "lista_clientes";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) Long documento, @RequestParam String nombre, @RequestParam String apellido , @RequestParam String telefono, ModelMap model){
        
        try {
            clienteService.crearCliente(documento, nombre, apellido, telefono);
            model.put("exito", "El cliente se agrego correctamente");
            
        } catch (MyException ex) {
            model.put("error", ex.getMessage());
            return "cliente_form";
        }
        
        return "index.html";
    }
    
}
