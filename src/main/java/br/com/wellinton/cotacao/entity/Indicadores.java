package br.com.wellinton.cotacao.entity;

import br.com.wellinton.cotacao.entity.Cotacoes;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity(name = "Indicadores")
public class Indicadores implements Serializable {

    public Indicadores() {
    }
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "indicadores", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cotacoes> cotacoes;

    @Column(name = "description")
    private String description;

    public Indicadores(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Indicador [ID: " + id + ", Descrição: " + description + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Indicadores other = (Indicadores) obj;
        return Objects.equals(this.id, other.id);
    }

}
