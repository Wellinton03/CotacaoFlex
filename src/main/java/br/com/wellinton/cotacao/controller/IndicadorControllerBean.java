package br.com.wellinton.cotacao.controller;

import br.com.wellinton.cotacao.entity.indicador.IndicadorDTO;
import br.com.wellinton.cotacao.repository.IndicadorRepository;
import br.com.wellinton.cotacao.entity.indicador.Indicador;
import br.com.wellinton.cotacao.entity.indicador.IndicadorResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/indicador")
public class IndicadorControllerBean  {

	@Autowired
        private IndicadorRepository repository;
	
        @GetMapping("/listarIndicadores")
        public ResponseEntity listarIndicadores(){
            List<IndicadorResponseDTO> todosIndicadores = this.repository.findAll().stream().map(IndicadorResponseDTO::new).toList();
            return ResponseEntity.ok(todosIndicadores);
        }
        
        @PostMapping("/cadastrar")
        public ResponseEntity salvar(@RequestBody @Valid IndicadorDTO data) {
            if(this.repository.findByDescription(data.description()).isPresent()) {
                
                return ResponseEntity.badRequest().build();
            }
            
            Indicador indicador = new Indicador(data.description());
            
            this.repository.save(indicador);
            
            return ResponseEntity.ok().build();
        }
        
        @PutMapping("/editar")
        public ResponseEntity editar(@RequestBody @Valid IndicadorDTO data) {
            Indicador indicador = this.repository.findById(data.id())
                    .orElse(null);
                    
                    if(indicador == null) {
                return ResponseEntity.notFound().build();
                    }
                indicador.setDescription(data.description());
                
                this.repository.save(indicador);
                
                return ResponseEntity.ok().build();
        }
        
        @DeleteMapping("/excluir")
        public ResponseEntity excluir(@RequestBody @Valid IndicadorDTO data) {
          Indicador indicador = this.repository.findById(data.id())
                  .orElse(null);
            
                  if(indicador == null) {
                      return ResponseEntity.notFound().build();
                  }
                  
            this.repository.delete(indicador);
            
            return ResponseEntity.ok().build();
        }
}
