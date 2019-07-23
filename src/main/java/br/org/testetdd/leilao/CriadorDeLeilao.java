package br.org.testetdd.leilao;

public class CriadorDeLeilao {


    private Leilao leilao;

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, Double valor) {
        leilao.propoe(new Lance(usuario,valor));
        return this;
    }

