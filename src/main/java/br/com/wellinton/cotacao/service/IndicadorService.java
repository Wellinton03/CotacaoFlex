package br.com.wellinton.cotacao.service;

import br.com.wellinton.cotacao.entity.indicador.Indicador;
import br.com.wellinton.cotacao.entity.indicador.IndicadorDTO;
import br.com.wellinton.cotacao.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndicadorService {

    @Autowired
    private IndicadorRepository indicadorRepository;
    
    @Transactional
public Indicador salvar(IndicadorDTO data) {
    if (data.description() == null || data.description().isBlank()) {
        throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
    }

    Indicador indicador = null;
    if (data.id() != null) {
        indicador = this.indicadorRepository.findById(data.id()).orElse(null);
    }

    if (indicador == null) {
        indicador = this.indicadorRepository.findByDescription(data.description()).orElse(null);
    }

    if (indicador == null) {
        indicador = new Indicador(data.description());
    } else {
        indicador.setDescription(data.description());
    }

    return indicadorRepository.save(indicador);
}
    
    @Transactional
    public void excluir(IndicadorDTO data) {
        Indicador indicador  = this.indicadorRepository.findById(data.id())
                  .orElseThrow(()->  new IllegalArgumentException("Indicador não encontrado"));
            
        indicadorRepository.delete(indicador);
    }
    
}