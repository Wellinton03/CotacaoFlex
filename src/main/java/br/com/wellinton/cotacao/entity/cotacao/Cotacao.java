package br.com.wellinton.cotacao.entity.cotacao;


import br.com.wellinton.cotacao.entity.indicador.Indicador;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity(name = "Cotacoes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_Hora")
    private LocalDateTime dataHora;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_indicador")
    private Indicador indicador;

}
