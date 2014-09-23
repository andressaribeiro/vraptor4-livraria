package br.com.casadocodigo.livraria.converter;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.iogi.exceptions.ConversionException;
import br.com.caelum.vraptor.converter.Converter;
import br.com.casadocodigo.livraria.modelo.Dinheiro;
import br.com.casadocodigo.livraria.modelo.Moeda;

public class DinheiroConverterTest {

	@Test
	public void converteUmValorEmReais() {
		Converter<Dinheiro> converter = new DinheiroConverter();
		Assert.assertThat(converter.convert("R$ 1,00", null), Matchers.is(new Dinheiro(Moeda.REAL, new BigDecimal("1.00"))));
	}

	@Test(expected = ConversionException.class)
	public void lancaErroDeConversaoQuandoOValorEhInvalido() {
		Converter<Dinheiro> converter = new DinheiroConverter();
		converter.convert("noventa pratas!", null);
	}
}
