/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.beans;

import br.com.wellinton.cotacao.api.IndicadoresRequest;
import br.com.wellinton.cotacao.entity.indicador.IndicadorResponseDTO;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author welli
 */

@Component
@Scope("request")
public class IndicadoresBean {
    
    @Autowired
    private IndicadoresRequest indicadoresRequest;
    
    private List<IndicadorResponseDTO> indicadores;
    
    
    @PostConstruct
    public void Init() {
        carregarIndicadores(); 
    }
    
    public void carregarIndicadores() {
        this.indicadores = indicadoresRequest.listarIndicadores();
    }
    
    public List<IndicadorResponseDTO> getIndicadores() {
        System.out.println(indicadores);
        return indicadores;
    }
}
