package pl.coderslab.mural.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.mural.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{
	
	List<Street> findAllByOrderByNameStreetAsc();
	List<Street> findByNameStreetLikeOrderByNameStreetAsc (String street);
}
