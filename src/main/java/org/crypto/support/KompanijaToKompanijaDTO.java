package org.crypto.support;

import org.crypto.model.Kompanija;
import org.crypto.web.dto.KompanijaDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KompanijaToKompanijaDTO
	implements Converter<Kompanija, KompanijaDTO> {

	@Override
	public KompanijaDTO convert(Kompanija kompanija) {
		KompanijaDTO kompanijaDTO = new KompanijaDTO();
		kompanijaDTO.setId(kompanija.getId());
		kompanijaDTO.setNaziv(kompanija.getNaziv());
		kompanijaDTO.setAdresa(kompanija.getAdresa());
		kompanijaDTO.setTelefon(kompanija.getTelefon());
		return kompanijaDTO;
	}

	public List<KompanijaDTO> convert(List<Kompanija> kompanije) {
		List<KompanijaDTO> ret = new ArrayList<>();

		for(Kompanija k: kompanije){
			ret.add(convert(k));
		}

		return ret;
	}
}
