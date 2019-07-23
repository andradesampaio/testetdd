package br.org.testetdd.leilao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Avaliador {

    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;
    private List<Lance> maioresLances;
    private List<Lance> listMaioresLances;

    public void avalia(Leilao leilao) {
        if(leilao.getLances().size() == 0){
            throw new RuntimeException("Nao e possivel avaliar sem lances");
        }

        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorLance) maiorLance = lance.getValor();
            if (lance.getValor() < menorLance) menorLance = lance.getValor();
        }


      maioresLances = new ArrayList<Lance>(leilao.getLances());

        listMaioresLances = maioresLances.stream()
                .sorted(Comparator.comparingDouble(Lance::getValor)
                .reversed())
                .limit(3)
                .collect(Collectors.toList());

    }

    public List<Lance> getListMaiores(){
        return listMaioresLances;
    }
        public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

}

