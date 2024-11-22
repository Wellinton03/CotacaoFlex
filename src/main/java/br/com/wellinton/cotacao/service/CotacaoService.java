package br.com.wellinton.cotacao.service;

import br.com.wellinton.cotacao.entity.cotacao.Cotacao;
import br.com.wellinton.cotacao.entity.cotacao.CotacaoDTO;
import br.com.wellinton.cotacao.entity.indicador.Indicador;
import br.com.wellinton.cotacao.repository.CotacaoRepository;
import br.com.wellinton.cotacao.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CotacaoService{

    @Autowired
    private CotacaoRepository cotacaoRepository;
    
    @Autowired
    private IndicadorRepository indicadorRepository;
    
    @Transactional
    public Cotacao salvar(CotacaoDTO data) {
       Indicador indicador = indicadorRepository.findByDescription(data.description())
            .orElseThrow(() -> new IllegalArgumentException("Indicador não encontrado"));

    Cotacao cotacao;

    if (data.id() != null) { 
        cotacao = cotacaoRepository.findById(data.id())
                .orElseThrow(() -> new IllegalArgumentException("Cotação não encontrada"));

        cotacao.setIndicador(indicador);
        cotacao.setDataHora(data.dataHora());
        cotacao.setValor(data.valor());
    } else { 
        cotacao = new Cotacao(indicador, data.dataHora(), data.valor());
    }
        return cotacaoRepository.save(cotacao);
    }
    
    
    @Transactional
    public void excluir(CotacaoDTO data) {
         Cotacao cotacao = this.cotacaoRepository.findById(data.id())
                .orElseThrow(() -> new IllegalArgumentException("Cotaão não encontrada"));
                
        cotacaoRepository.delete(cotacao);
    }
    
}