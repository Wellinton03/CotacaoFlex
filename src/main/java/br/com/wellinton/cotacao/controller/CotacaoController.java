package br.com.wellinton.cotacao.controller;

import br.com.wellinton.cotacao.entity.cotacao.CotacaoDTO;
import br.com.wellinton.cotacao.entity.cotacao.CotacaoResponseDTO;
import br.com.wellinton.cotacao.repository.CotacaoRepository;
import br.com.wellinton.cotacao.service.CotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoRepository cotacaoRepository;
    
    @Autowired
    private CotacaoService cotacaoService;
    
    @GetMapping("/listar")
    public ResponseEntity listarCotacoes() {
        List<CotacaoResponseDTO> todasCotacoes = this.cotacaoRepository.findAll().stream().map(CotacaoResponseDTO::new).toList();
        return ResponseEntity.ok(todasCotacoes);
        
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity salvar(@RequestBody @Valid CotacaoDTO data) {
        try {
            this.cotacaoService.salvar(data);
            return ResponseEntity.ok().build();
            
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/editar")
    public ResponseEntity editar(@RequestBody @Valid CotacaoDTO data) {
        try {
            this.cotacaoService.salvar(data);
            return ResponseEntity.ok().build();
            
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/excluir")
    public ResponseEntity excluir(@RequestBody @Valid CotacaoDTO data) {
       try{
        this.cotacaoService.excluir(data);
            return ResponseEntity.ok().build();
            
       }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
       }
        
        
    }
	
}
    








