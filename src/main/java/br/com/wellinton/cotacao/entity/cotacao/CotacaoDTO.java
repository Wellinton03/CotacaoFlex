/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.wellinton.cotacao.entity.cotacao;
import java.time.LocalDateTime;

/**
 *
 * @author welli
 */
public record CotacaoDTO(Long id, String description, LocalDateTime dataHora, Double valor) {

}
