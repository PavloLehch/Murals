package pl.coderslab.mural.controller;

import java.util.ArrayList;
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

import pl.coderslab.mural.entity.Street;
import pl.coderslab.mural.repository.StreetRepository;

@Controller
@RequestMapping("/admin/street")
public class StreetController {

	@Autowired
	StreetRepository streetRepo;

	@GetMapping("/add_street")
	public String addStreet(Model model) {
		Street street = new Street();
		model.addAttribute("street", street);
		return "addStreet";
	}

	@PostMapping("/add_street")
	public String addStreetPost(Street street) {
		this.streetRepo.save(street);
		return "redirect:/admin/street/allStreets";
	}

	@GetMapping("/add_street_from_mural")
	public String addStreetFromMural(Model model) {
		Street street = new Street();
		model.addAttribute("street", street);
		return "addStreet";
	}

	@PostMapping("/add_street_from_mural")
	public String addStreetFromMuralPost(Street street) {
		this.streetRepo.save(street);
		return "close";
	}

	@GetMapping("/edit_street/{id}")
	public String editStreet(@PathVariable long id, Model model) {
		Street street = streetRepo.findOne(id);
		model.addAttribute("street", street);
		return "editStreet";
	}

	@PostMapping("/edit_street/{id}")
	public String editStreetPost(Street street) {
		this.streetRepo.save(street);
		return "redirect:/admin/street/allStreets";
	}

	@GetMapping("del_street/{id}")
	public String delStreet(@PathVariable long id) {
		Street street = streetRepo.findOne(id);
		streetRepo.delete(street);
		return "redirect:/admin/street/allStreets";
	}

	// show all streets
	@GetMapping("/allStreets")
	public String showAllStreets() {
		return "adminAllStreets";
	}

	// list streets
	@ModelAttribute("streets")
	public List<Street> getStreets() {
		return this.streetRepo.findAllByOrderByNameStreetAsc();
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

	// find street
	@PostMapping("/allStreets")
	@ResponseBody
	public List<Street> findStreetByName(@RequestParam String street) {
			List<Street> streets = this.streetRepo.findByNameStreetLikeOrderByNameStreetAsc("%"+street+"%");
			return streets;
	}

}
