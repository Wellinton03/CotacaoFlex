/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.wellinton.cotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.wellinton.cotacao.entity.indicador.Indicador;
import java.util.Optional;

/**
 *
 * @author welli
 */
public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
    
        Optional<Indicador> findByDescription(String description);
}
