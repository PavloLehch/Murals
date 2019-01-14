package pl.coderslab.mural.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.mural.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	List<Author> findAllByOrderByNameAuthorAsc();
	List<Author> findByNameAuthorLikeOrderByNameAuthorAsc(String nameAuthor);
}
