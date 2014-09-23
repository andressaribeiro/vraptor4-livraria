package br.com.casadocodigo.livraria.converter;

import java.math.BigDecimal;

import br.com.caelum.iogi.exceptions.ConversionException;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.casadocodigo.livraria.modelo.Dinheiro;
import br.com.casadocodigo.livraria.modelo.Moeda;

import com.google.common.base.Strings;

@Convert(Dinheiro.class)
public class DinheiroConverter implements Converter<Dinheiro> {

	@Override
	public Dinheiro convert(String value, Class<? extends Dinheiro> type) {
		if (Strings.isNullOrEmpty(value)) {
			return null;
		}
		for (Moeda moeda : Moeda.values()) {
			if (value.startsWith(moeda.getSimbolo())) {
				return new Dinheiro(moeda, criaMontante(value, moeda));
			}
		}
		throw new ConversionException("O {0} não é um dinheiro válido", value);
	}

	private BigDecimal criaMontante(String value, Moeda moeda) {
		try {
			return new BigDecimal(value.replace(moeda.getSimbolo(), "").replace(',', '.').trim());
		} catch (NumberFormatException e) {
			throw new ConversionException("O {0} não é um dinheiro válido", value);
		}
	}

}
