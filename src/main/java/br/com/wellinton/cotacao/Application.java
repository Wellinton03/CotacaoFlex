package br.com.wellinton.cotacao;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author welli
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    } 
    
}
