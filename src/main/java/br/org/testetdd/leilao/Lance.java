package br.org.testetdd.leilao;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@EqualsAndHashCode
public class Lance {

    private Usuario usuario;
    private double valor;


}
