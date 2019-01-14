package pl.coderslab.mural.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.mural.entity.Street;
import pl.coderslab.mural.repository.StreetRepository;


public class StreetConverter implements Converter<String, Street> {

	@Autowired
	private StreetRepository streetRepository;
	
	public Street convert(String id) {
		return this.streetRepository.findOne(Long.parseLong(id));
	}

}
