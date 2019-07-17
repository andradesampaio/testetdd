package br.org.testetdd.leilao;

public class TesteDoAvaliador {

    public static void main(String[] args) {

        Usuario joao = Usuario.builder().id(1).nome("joao").build();
        Usuario jose = Usuario.builder().id(2).nome("jose").build();
        Usuario maria = Usuario.builder().id(3).nome("maria").build();

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(Lance.builder().usuario(joao).valor(800.00).build());
        leilao.propoe(Lance.builder().usuario(jose).valor(500.0).build());
        leilao.propoe(Lance.builder().usuario(maria).valor(400.0).build());

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        System.out.println(leiloeiro.getMaiorLance()); // imprime 400.0

    }
}
