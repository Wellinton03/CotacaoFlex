/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.config;

/**
 *
 * @author welli
 */
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.faces.webapp.FacesServlet;

@Configuration
public class WebConfig {

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServlet() {
        ServletRegistrationBean<FacesServlet> servlet = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        servlet.setLoadOnStartup(1);
        return servlet;
    }
}
