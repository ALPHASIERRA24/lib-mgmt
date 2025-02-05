package com.cts.LibraryManagementSystem.ThyemleafController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.LibraryManagementSystem.dto.CatalogDTO;
import com.cts.LibraryManagementSystem.model.CatalogModel;
import com.cts.LibraryManagementSystem.service.CatalogService;


@Controller
@RequestMapping("/books")
public class ThyCatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	@GetMapping
	public String getAllBooks(Model model) {
		List<CatalogModel> books = catalogService.getAllBooks();
		System.out.println(books);
		model.addAttribute("books",books);
		return "book-list";	
	}
	
	@GetMapping("/add")
	public String showAddBookForm(Model model) {
		model.addAttribute("book",new CatalogDTO());
		return "book-form";
	}
	
	@GetMapping("/edit/{bookId}")
	public String showEditBookForm(@PathVariable int bookId,Model model) {
		Optional<CatalogModel> optionalBook = catalogService.getBookById(bookId);
		if (optionalBook.isPresent()) {
	        CatalogModel book = optionalBook.get();
	        
	        model.addAttribute("book", book);
	        return "book-form";
	    } else {
	        throw new RuntimeException("Book not found");
	    }
	}
	

	@PostMapping("/addpost")
	public String addBook(@ModelAttribute CatalogDTO catalogDTO) {
		catalogService.addBook(List.of(catalogDTO));
		return "redirect:/books";
	}
	
	

	
	@PostMapping("/update/{bookId}")
	public String updateBook(@PathVariable int bookId,@ModelAttribute CatalogDTO catalogDTO) {
		catalogService.updateBookById(bookId, catalogDTO);
		return "redirect:/books";
	}

}
