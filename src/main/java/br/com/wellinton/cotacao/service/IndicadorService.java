package br.com.wellinton.cotacao.service;


import br.com.wellinton.cotacao.entity.indicador.Indicador;
import br.com.wellinton.cotacao.repository.IndicadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndicadorService {

    @Autowired
    private IndicadorRepository indicadorRepository;
    
    
    public Indicador porId(Long id) {
        return indicadorRepository.findById(id)
                .orElse(null);
    }
    
    @Transactional
    public Indicador salvar(Indicador indicador){
        return indicadorRepository.save(indicador);
    }
    
    @Transactional
    public void excluir(Indicador indicador) {
        indicadorRepository.delete(indicador);
    }
    
}