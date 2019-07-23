package br.org.testetdd;

import br.org.testetdd.leilao.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestetddApplicationTests {

	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	private Avaliador leiloeiro;

	@Before
	public void setUu(){

	    joao = Usuario.builder().id(1).nome("joao").build();
		jose = Usuario.builder().id(2).nome("jose").build();
		maria = Usuario.builder().id(3).nome("maria").build();
		this.leiloeiro = new Avaliador();
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 800.00)
				.lance(jose, 500.00)
				.lance(maria, 10.00)
				.constroi();


		leiloeiro.avalia(leilao);

		double maiorEsperado = 800.00;
		double menorEsperado = 10.00;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("PS3 Novo")
				.lance(joao, 800.00)
				.constroi();

		leiloeiro.avalia(leilao);
		//assertEquals(800.00, leiloeiro.getMaiorLance(), 0.00001);
		//assertEquals(800.00, leiloeiro.getMenorLance(), 0.00001);
		assertThat(leiloeiro.getMenorLance(), equalTo(800.00));
		assertThat(leiloeiro.getMaiorLance(), equalTo(800.00));
	}

	@Test
	public void deveEncontrarOsTresMaioresLance() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 800.00)
				.lance(jose, 500.00)
				.lance(maria, 10.00)
				.lance(maria, 1000.00)
				.constroi();

		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getListMaiores();

		assertEquals(3, maiores.size());
	//	assertEquals(1000.00, maiores.get(0).getValor(), 0.000001);
	//	assertEquals(800.00, maiores.get(1).getValor(), 0.000001);
	//	assertEquals(500.00, maiores.get(2).getValor(), 0.000001);
		assertThat(maiores, hasItems(
				Lance.builder().usuario(maria).valor(1000.00).build(),
				Lance.builder().usuario(joao).valor(800.00).build(),
				Lance.builder().usuario(jose).valor(500.00).build())
				);

	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemNenhumLanceDado(){
		Leilao leilao = new CriadorDeLeilao().para("Iphone 4").constroi();
		leiloeiro.avalia(leilao);
	}

}
