/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.beans;

import br.com.wellinton.cotacao.api.IndicadoresRequest;
import br.com.wellinton.cotacao.entity.indicador.IndicadorRequestDTO;
import br.com.wellinton.cotacao.entity.indicador.IndicadorResponseDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author welli
 */

@Component
@Scope("view")
public class IndicadoresBean implements Serializable {
    
    @Autowired
    private IndicadoresRequest indicadoresRequest;
    
    private IndicadorRequestDTO indicadorSelecionado;
    
    private List<IndicadorResponseDTO> indicadores;
    private String description;
    private Long id;
    
    
    @PostConstruct
    public void Init() {
         System.out.println("Init chamado. IndicadorSelecionado: " + indicadorSelecionado);
        carregarIndicadores(); 
    }
    
    public String editarIndicador(IndicadorResponseDTO indicadorResponse) {
         if(indicadorResponse != null) {
             if (indicadorSelecionado == null) {
            indicadorSelecionado = new IndicadorRequestDTO();
        }
            indicadorSelecionado.setId(indicadorResponse.getId());
            indicadorSelecionado.setDescription(indicadorResponse.getDescription());
            System.out.println("ID: " + indicadorSelecionado.getId() + ", Descrição: " + indicadorSelecionado.getDescription());
        }
        return "CadastroDeIndicadores?faces-redirect=true";
    }
    
    public void prepararExclusão(IndicadorResponseDTO indicadorResponse){
        if(indicadorResponse != null) {
             if (indicadorSelecionado == null) {
            indicadorSelecionado = new IndicadorRequestDTO();
        }
            indicadorSelecionado.setId(indicadorResponse.getId());
            indicadorSelecionado.setDescription(indicadorResponse.getDescription());
        }
    }
    
    public void carregarIndicadores() {
        this.indicadores = indicadoresRequest.listarIndicadores();
    }
    
    public List<IndicadorResponseDTO> getIndicadores() {
        System.out.println(indicadores);
        return indicadores;
    }
    
    public String salvar() {
        if(indicadorSelecionado.getDescription() != null) {
            
        try {
                indicadoresRequest.salvar(indicadorSelecionado);
        } catch (Exception e ) {
            e.printStackTrace();
            
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES,
            "Erro ao salvar o Indicador, Por favor tente novamente"));
            
            return null;
        }
        
    } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES,
                    "Por favor, adicione um Indicador válido e tente novamente"));
        }
        return "ListaDeIndicadores?faces-redirect=true";
    }
    
    public void excluir() {
        if(indicadorSelecionado.getId() != null) {
            System.out.println(indicadorSelecionado.getId());
        indicadoresRequest.excluir(indicadorSelecionado.getId());
        carregarIndicadores();
}
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IndicadorRequestDTO getIndicadorSelecionado() {
         System.out.println("Indicador selecionado acessado: " + indicadorSelecionado);
        return indicadorSelecionado;
    }

    public void setIndicadorSelecionado(IndicadorRequestDTO indicadorSelecionado) {
        this.indicadorSelecionado = indicadorSelecionado;
    }
    
    
    
    
}
