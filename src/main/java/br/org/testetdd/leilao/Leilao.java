package br.org.testetdd.leilao;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class Leilao {

    private String descricao;
    private List<Lance> lances = new ArrayList<Lance>();

    public Leilao(String descricao){
        this.descricao = descricao;
    }


    public void propoe(Lance lance){
        lances.add(lance);
    }

    public List<Lance> getLances(){
        return Collections.unmodifiableList(lances);
    }
}
