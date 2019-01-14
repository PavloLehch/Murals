package pl.coderslab.mural.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.mural.entity.Author;
import pl.coderslab.mural.repository.AuthorRepository;


public class AuthorConverter implements Converter<String, Author> {

	@Autowired
	private AuthorRepository authorRepository;
	
	public Author convert(String id) {
		return this.authorRepository.findOne(Long.parseLong(id));
	}

}
