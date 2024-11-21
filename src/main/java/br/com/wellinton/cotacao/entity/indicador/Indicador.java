package br.com.wellinton.cotacao.entity.indicador;

import br.com.wellinton.cotacao.entity.cotacao.Cotacao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@Entity(name = "Indicadores")
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "indicador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cotacao> cotacoes;

    @Column(name = "description")
    private String description;

    public Indicador(String description) {
        this.description = description;
    }
}
