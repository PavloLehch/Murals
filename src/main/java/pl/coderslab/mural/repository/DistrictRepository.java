package pl.coderslab.mural.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.mural.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
	
	List<District> findAllByOrderByNameDistrictAsc();
	List<District> findByNameDistrictLikeOrderByNameDistrictAsc (String nameDistrict);
}
