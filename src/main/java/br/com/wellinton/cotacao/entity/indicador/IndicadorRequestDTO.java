/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.wellinton.cotacao.entity.indicador;

/**
 *
 * @author welli
 */
public class IndicadorRequestDTO {

    private String description;
    private Long id;
    
    public IndicadorRequestDTO() {
        this.description = description;
}
    
     public String getDescription() {
        return description;
    }

     
     public Long getId() {
         return id;
     }
     
     public void setDescription(String description) {
         if (description != null) {
            this.description = description.toUpperCase();
        }
     }
     
     public void setId(Long id) {
         this.id = id;
     }

}
