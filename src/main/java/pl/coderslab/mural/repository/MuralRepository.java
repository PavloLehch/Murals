package pl.coderslab.mural.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.mural.entity.Author;
import pl.coderslab.mural.entity.District;
import pl.coderslab.mural.entity.Mural;
import pl.coderslab.mural.entity.Street;

public interface MuralRepository extends JpaRepository<Mural, Long> {
	
	List<Mural> findAllByOrderByNameMuralAsc();
	
	List<Mural> findByNameMuralLikeOrderByNameMuralAsc(String nameMural);
	List<Mural> findByAuthorsMuralLikeOrderByNameMuralAsc(Author author);
	List<Mural> findByDistrictMuralLikeOrderByNameMuralAsc(District district);
	List<Mural> findByStreetMuralLikeOrderByNameMuralAsc(Street streetMural);
	
	
}
