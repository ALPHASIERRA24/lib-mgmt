package com.cts.LibraryManagementSystem.ThyemleafController;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.LibraryManagementSystem.dto.BorrowRecordDTO;
import com.cts.LibraryManagementSystem.dto.CatalogDTO;
import com.cts.LibraryManagementSystem.model.CatalogModel;
import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.service.BorrowService;
import com.cts.LibraryManagementSystem.service.CatalogService;
import com.cts.LibraryManagementSystem.service.UsersService;


@Controller
@RequestMapping("/books")
public class ThyCatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BorrowService borrowService;

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
	
	@GetMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable int bookId,RedirectAttributes redirectAttributes) {
		boolean isDeleted = catalogService.deleteBookById(bookId);

		if(isDeleted){
			redirectAttributes.addFlashAttribute("successMessage","Book deleted successfully.");
		}else {
			redirectAttributes.addFlashAttribute("errorMessage","Book not found or cannot be deleted");	
		}
		return "redirect:/books";
	}
	
	@GetMapping("/request/{bookId}")

	public String requestBook(@PathVariable int bookId, Principal principal, Model model) {

	    UsersModel user = usersService.getUserByName(principal.getName());

	    CatalogModel book = catalogService.getBookById(bookId)

	                                      .orElseThrow(() -> new RuntimeException("Book not found"));

	    BorrowRecordDTO borrowRecordDTO = new BorrowRecordDTO();
	    borrowRecordDTO.setBookId(book.getBookId());
	    borrowRecordDTO.setUserId(user.getUserId());
	    model.addAttribute("borrowRecord", borrowRecordDTO);
	    model.addAttribute("book", book);  // Send book details for display
	    return "borrow-request";

	}

	@PostMapping("/request")

	public String submitBorrowRequest(@ModelAttribute BorrowRecordDTO borrowRecordDTO) {
	    borrowRecordDTO.setReturnStatus(false);  
	    borrowService.addBorrowRecord(borrowRecordDTO);  
	    return "redirect:/borrow-records/borrow-history";

	}
	
	@GetMapping
	public String getAllBooks(@RequestParam(value = "query", required = false) String query, Model model) {
	  List<CatalogModel> books;
	  if (query != null && !query.isEmpty()) {
	    books = catalogService.getBooksByName(query); 
	  } else {
	    books = catalogService.getAllBooks();
	  }	  
	  model.addAttribute("books", books);
	  model.addAttribute("query", query);
	  return "book-list";
	}
}
