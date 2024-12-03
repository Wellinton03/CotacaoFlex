package br.com.wellinton.cotacao.service;

import br.com.wellinton.cotacao.entity.indicador.Indicador;
import br.com.wellinton.cotacao.entity.indicador.IndicadorRequestDTO;
import br.com.wellinton.cotacao.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndicadorService {

    @Autowired
    private IndicadorRepository indicadorRepository;
    
    @Transactional
    public Indicador salvar(IndicadorRequestDTO data) {
        if (data.getDescription() == null || data.getDescription().isBlank()) {
        throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
    }

    Indicador indicador = null;
    if (data.getId() != null) {
        indicador = this.indicadorRepository.findById(data.getId()).orElse(null);
    }

    if (indicador == null) {
        indicador = this.indicadorRepository.findByDescription(data.getDescription()).orElse(null);
    }

    if (indicador == null) {
        indicador = new Indicador(data.getDescription());
    } else {
        indicador.setDescription(data.getDescription());
    }

    return indicadorRepository.save(indicador);
}
    
    @Transactional
    public void excluir(Long id) {
        Indicador indicador  = this.indicadorRepository.findById(id)
                  .orElseThrow(()->  new IllegalArgumentException("Indicador não encontrado"));
            System.out.println(indicador);
        indicadorRepository.delete(indicador);
    }
    
}