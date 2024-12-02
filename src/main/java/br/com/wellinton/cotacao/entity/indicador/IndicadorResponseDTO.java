/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.wellinton.cotacao.entity.indicador;

/**
 *
 * @author welli
 */
public record IndicadorResponseDTO(String description, Long id) {
    
    public IndicadorResponseDTO(Indicador indicador){
        this(indicador.getDescription(),
        indicador.getId());
        
    }
    
     public String getDescription() {
        return description;
    }

     
     public Long getId() {
         return id;
     }
}
