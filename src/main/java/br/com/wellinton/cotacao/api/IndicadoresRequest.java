/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.api;

import br.com.wellinton.cotacao.entity.indicador.IndicadorDTO;
import br.com.wellinton.cotacao.entity.indicador.IndicadorResponseDTO;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author welli
 */

@Component
public class IndicadoresRequest {
    
    
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/CotacaoFlex/api/indicador";
    
    public IndicadoresRequest() {
        this.restTemplate = new RestTemplate();
    }
    
    
    public List<IndicadorResponseDTO> listarIndicadores() {
        String url = baseUrl + "/listar";
        
        return List.of(restTemplate.getForObject(url, IndicadorResponseDTO[].class));
    }
    
    public void salvar(IndicadorDTO data) {
        String url = baseUrl + "/cadastrar";
        
        restTemplate.postForObject(url, data, Void.class);
    }
    
    public void excluir(Long id) {
        String url = baseUrl + "/excluir/" + id;
        
        restTemplate.delete(url);
    }
    
}
