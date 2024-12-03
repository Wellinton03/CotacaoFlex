package br.com.wellinton.cotacao.controller;

import br.com.wellinton.cotacao.entity.indicador.IndicadorRequestDTO;
import br.com.wellinton.cotacao.repository.IndicadorRepository;
import br.com.wellinton.cotacao.entity.indicador.IndicadorResponseDTO;
import br.com.wellinton.cotacao.service.IndicadorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/indicador")
public class IndicadorController  {

	@Autowired
        private IndicadorRepository indicadorRepository;
        
        @Autowired
        private IndicadorService indicadorService;
	
        @GetMapping("/listar")
        public ResponseEntity listarIndicadores(){
            List<IndicadorResponseDTO> todosIndicadores = this.indicadorRepository.findAll().stream().map(IndicadorResponseDTO::new).toList();
            return ResponseEntity.ok(todosIndicadores);
        }
        
        @PostMapping("/cadastrar")
        public ResponseEntity salvar(@RequestBody @Valid IndicadorRequestDTO data) {
                try{
                    this.indicadorService.salvar(data);
                    return ResponseEntity.ok().build();
                
                } catch (IllegalArgumentException e){
                    return ResponseEntity.badRequest().build();
                }
            
        }
        
        @PutMapping("/editar")
        public ResponseEntity editar(@RequestBody @Valid IndicadorRequestDTO data) {
              try{
                    this.indicadorService.salvar(data);
                    return ResponseEntity.ok().build();
                
                } catch (IllegalArgumentException e){
                    return ResponseEntity.badRequest().build();
                }
        }
        
        @DeleteMapping("/excluir/{id}")
        public ResponseEntity excluir(@PathVariable Long id) {
                try{
                    System.out.println(id);
                    this.indicadorService.excluir(id);
                    return ResponseEntity.ok().build();
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            
        }
}
