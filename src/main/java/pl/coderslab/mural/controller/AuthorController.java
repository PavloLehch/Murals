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

import pl.coderslab.mural.entity.Author;
import pl.coderslab.mural.repository.AuthorRepository;

@Controller
@RequestMapping("/admin/author")
public class AuthorController {

	@Autowired
	AuthorRepository authorRepo;

	@GetMapping("/add_author")
	public String addAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "addAuthor";
	}

	@PostMapping("/add_author")
	public String addAuthorPost(Author author) {
		this.authorRepo.save(author);
		return "redirect:/admin/author/allAuthors";
	}

	@GetMapping("/add_author_from_mural")
	public String addAuthorFromMural(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "addAuthor";
	}

	@PostMapping("/add_author_from_mural")
	public String addAuthorFromMuralPost(Author author) {
		this.authorRepo.save(author);
		return "close";
	}
	
	@GetMapping("/edit_author/{id}")
	public String editAuthor(@PathVariable long id, Model model) {
		Author author = authorRepo.findOne(id);
		model.addAttribute("author", author);
		return "editAuthor";
	}

	@PostMapping("/edit_author/{id}")
	public String editAuthorPost(Author author) {
		this.authorRepo.save(author);
		return "redirect:/admin/author/allAuthors";
	}
	
	@GetMapping("del_author/{id}")
	public String delAuthor(@PathVariable long id) {
		Author author = authorRepo.findOne(id);
		authorRepo.delete(author);
		return "redirect:/admin/author/allAuthors";
	}

	// show all authors
	@GetMapping("/allAuthors")
	public String showAllAuthors() {
		return "adminAllAuthors";
	}

	// list authors
	@ModelAttribute("authors")
	public List<Author> getAuthors() {
		return this.authorRepo.findAllByOrderByNameAuthorAsc();
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

		// find author
		@PostMapping("/allAuthors")
		@ResponseBody
		public List<Author> findAuthorByName(@RequestParam String author) {
				List<Author> authors = this.authorRepo.findByNameAuthorLikeOrderByNameAuthorAsc("%"+author+"%");
				return authors;
		}

	}

