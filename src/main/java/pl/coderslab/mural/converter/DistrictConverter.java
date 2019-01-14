package pl.coderslab.mural.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.mural.entity.District;
import pl.coderslab.mural.repository.DistrictRepository;


public class DistrictConverter implements Converter<String, District> {

	@Autowired
	private DistrictRepository districtRepository;
	
	public District convert(String id) {
		return this.districtRepository.findOne(Long.parseLong(id));
	}

}
