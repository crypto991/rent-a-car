package org.crypto.support;

import org.crypto.model.Najam;
import org.crypto.web.dto.NajamDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NajamToNajamDTO implements Converter<Najam, NajamDTO> {

	@Override
	public NajamDTO convert(Najam arg0) {

		NajamDTO dto = new NajamDTO();
		dto.setId(arg0.getId());

		return dto;
	}

}
