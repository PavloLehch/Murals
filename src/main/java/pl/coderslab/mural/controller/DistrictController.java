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

import pl.coderslab.mural.entity.District;
import pl.coderslab.mural.repository.DistrictRepository;

@Controller
@RequestMapping("/admin/district")
public class DistrictController {

	@Autowired
	DistrictRepository districtRepo;

	@GetMapping("/add_district")
	public String addDistrict(Model model) {
		District district = new District();
		model.addAttribute("district", district);
		return "addDistrict";
	}

	@PostMapping("/add_district")
	public String addDistrictPost(District district) {
		this.districtRepo.save(district);
		return "redirect:/admin/district/allDistricts";
	}

	@GetMapping("/add_district_from_mural")
	public String addDistrictFromMural(Model model) {
		District district = new District();
		model.addAttribute("district", district);
		return "addDistrict";
	}

	@PostMapping("/add_district_from_mural")
	public String addDistrictFromMuralPost(District district) {
		this.districtRepo.save(district);
		return "close";
	}

	@GetMapping("/edit_district/{id}")
	public String editDistrict(@PathVariable long id, Model model) {
		District district = districtRepo.findOne(id);
		model.addAttribute("district", district);
		return "editDistrict";
	}

	@PostMapping("/edit_district/{id}")
	public String editDistrictPost(District district) {
		this.districtRepo.save(district);
		return "redirect:/admin/district/allDistricts";
	}

	@GetMapping("del_district/{id}")
	public String delDistrict(@PathVariable long id) {
		District district = districtRepo.findOne(id);
		districtRepo.delete(district);
		return "redirect:/admin/district/allDistricts";
	}

	// show all districts
	@GetMapping("/allDistricts")
	public String showAllDistricts() {
		return "adminAllDistricts";
	}

	// list districts
	@ModelAttribute("districts")
	public List<District> getDistricts() {
		return this.districtRepo.findAllByOrderByNameDistrictAsc();
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

	// find district
	@PostMapping("/allDistricts")
	@ResponseBody
	public List<District> findDistrictByName(@RequestParam String district) {
			List<District> districts = this.districtRepo.findByNameDistrictLikeOrderByNameDistrictAsc("%"+district+"%");
			return districts;
	}

}
