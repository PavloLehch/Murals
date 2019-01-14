package pl.coderslab.mural.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.mural.entity.Author;
import pl.coderslab.mural.entity.District;
import pl.coderslab.mural.entity.Mural;
import pl.coderslab.mural.entity.Street;
import pl.coderslab.mural.repository.AuthorRepository;
import pl.coderslab.mural.repository.DistrictRepository;
import pl.coderslab.mural.repository.MuralRepository;
import pl.coderslab.mural.repository.StreetRepository;

@Controller
@RequestMapping("/admin/mural")
public class MuralController {

	@Autowired
	MuralRepository muralRepo;

	@Autowired
	DistrictRepository districtRepo;

	@Autowired
	StreetRepository streetRepo;

	@Autowired
	AuthorRepository authorRepo;

	@GetMapping("/add_mural")
	public String addMural(Model model) {
		Mural mural = new Mural();
		model.addAttribute("mural", mural);
		return "addMural";
	}

	@PostMapping("/add_mural")
	public String addMuralPost(Mural mural) {
		this.muralRepo.save(mural);
		return "redirect:/admin/mural/allMurals";
	}

	@GetMapping("/edit_mural/{id}")
	public String editMural(@PathVariable long id, Model model) {
		Mural mural = muralRepo.findOne(id);
		model.addAttribute("mural", mural);
		return "editMural";
	}

	@PostMapping("/edit_mural/{id}")
	public String editMuralPost(Mural mural) {
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		mural.setDateUpdateMural(sqlDate);
		this.muralRepo.save(mural);
		return "redirect:/admin/mural/allMurals";
	}

	@GetMapping("/del_mural/{id}")
	public String delMural(@PathVariable long id, Model model) {
		Mural mural = muralRepo.findOne(id);
		muralRepo.delete(mural);
		return "redirect:/admin/mural/allMurals";
	}

	// show all murals
	@GetMapping("/allMurals")
	public String showAllMurals() {
		return "adminAllMurals";
	}

	// list murals
	@ModelAttribute("murals")
	public List<Mural> getMurals() {
		return this.muralRepo.findAllByOrderByNameMuralAsc();
	}

	// list streets
	@ModelAttribute("streets")
	public List<Street> getStreets() {
		return this.streetRepo.findAllByOrderByNameStreetAsc();
	}

	// list authors
	@ModelAttribute("authors")
	public List<Author> getAuthors() {
		return this.authorRepo.findAllByOrderByNameAuthorAsc();
	}

	// list districts
	@ModelAttribute("districts")
	public List<District> getDistricts() {
		return this.districtRepo.findAllByOrderByNameDistrictAsc();
	}

	@PostMapping("/search_mural")
	@ResponseBody
	public List<Mural> searchMural(@RequestParam String searchElement, @RequestParam String type, Model model) {
		List<Mural> murals = new ArrayList<Mural>();
		if (type.equals("name")) {
			murals = this.muralRepo.findByNameMuralLikeOrderByNameMuralAsc("%" + searchElement + "%");
		} else if (type.equals("author")) {
			List<Author> authors = this.authorRepo.findByNameAuthorLikeOrderByNameAuthorAsc("%" + searchElement + "%");
			for (Author author : authors) {
				for (Mural mural : this.muralRepo.findByAuthorsMuralLikeOrderByNameMuralAsc(author)) {
					boolean check = true;
					for (Mural muralHave : murals) {
						if (muralHave.getId() == mural.getId()) {
							check = false;
							break;
						}
					}
					if (check) {
					murals.add(mural);
					}
				}
			}
		} else if (type.equals("district")) {
			List<District> districts = this.districtRepo.findByNameDistrictLikeOrderByNameDistrictAsc("%" + searchElement + "%");
			for (District district : districts) {
				for (Mural mural : this.muralRepo.findByDistrictMuralLikeOrderByNameMuralAsc(district)) {
					murals.add(mural);
				}
			}
		} else if (type.equals("street")) {
			List<Street> streets = this.streetRepo.findByNameStreetLikeOrderByNameStreetAsc("%" + searchElement + "%");
			for (Street street : streets) {
				for (Mural mural : this.muralRepo.findByStreetMuralLikeOrderByNameMuralAsc(street)) {
					murals.add(mural);
				}
			}
		}
		
		Collections.sort(murals, new NameComparator());
		return murals;
	}
	
	public class NameComparator implements Comparator<Mural> {
	    public int compare(Mural o1, Mural o2) {
	       return o1.getNameMural().compareTo(o2.getNameMural());
	   }
	}

	// list muralsOnly
	@ModelAttribute("availableMurals")
	public List<String> getMuralsOnly() {
		List<String> muralNameOnly = new ArrayList<String>();
		for (Mural mural : this.muralRepo.findAllByOrderByNameMuralAsc()) {
			muralNameOnly.add(mural.getNameMural());
		}
		return muralNameOnly;
	}

	// list authorsOnly
	@ModelAttribute("availableAuthors")
	public List<String> getAuthorsOnly() {
		List<String> authorNameOnly = new ArrayList<String>();
		for (Author author : this.authorRepo.findAllByOrderByNameAuthorAsc()) {
			authorNameOnly.add(author.getNameAuthor());
		}
		return authorNameOnly;
	}

	// list districtsOnly
	@ModelAttribute("availableDistricts")
	public List<String> getDistrictsOnly() {
		List<String> districtNameOnly = new ArrayList<String>();
		for (District district : this.districtRepo.findAllByOrderByNameDistrictAsc()) {
			districtNameOnly.add(district.getNameDistrict());
		}
		return districtNameOnly;
	}

	// list streetsOnly
	@ModelAttribute("availableStreets")
	public List<String> getStreetsOnly() {
		List<String> streetNameOnly = new ArrayList<String>();
		for (Street street : this.streetRepo.findAll()) {
			streetNameOnly.add(street.getNameStreet());
		}
		return streetNameOnly;
	}

}
