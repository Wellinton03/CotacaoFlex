/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.repository;

import br.com.wellinton.cotacao.entity.cotacao.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author welli
 */

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    

    
}
