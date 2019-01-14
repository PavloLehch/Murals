package pl.coderslab.mural.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.coderslab.mural.entity.Author;
import pl.coderslab.mural.entity.District;
import pl.coderslab.mural.entity.Mural;
import pl.coderslab.mural.entity.Street;
import pl.coderslab.mural.repository.AuthorRepository;
import pl.coderslab.mural.repository.DistrictRepository;
import pl.coderslab.mural.repository.MuralRepository;
import pl.coderslab.mural.repository.StreetRepository;

@Controller
public class StartController {

	@GetMapping("/")
	public String home() {
		return "start";
	}

}
