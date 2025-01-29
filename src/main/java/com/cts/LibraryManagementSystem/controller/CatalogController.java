package com.cts.LibraryManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.LibraryManagementSystem.dto.CatalogDTO;
import com.cts.LibraryManagementSystem.model.CatalogModel;
import com.cts.LibraryManagementSystem.service.CatalogService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/books")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	@GetMapping
	public ResponseEntity<List<CatalogModel>> getAllBooks(){
		List<CatalogModel> books = catalogService.getAllBooks();
		return ResponseEntity.ok(books);
	}
	
	@PostMapping
	public ResponseEntity<CatalogModel> addBook(@RequestBody CatalogDTO catalogDto){
		CatalogModel addedBook=catalogService.addBook(catalogDto);
		return ResponseEntity.ok(addedBook);
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBookById(@PathVariable Integer bookId){
		boolean isDeleted =catalogService.deleteBookById(bookId);
		if(isDeleted) {
			return ResponseEntity.ok("Book Deleted SuccessFully.");
		}else {
			return ResponseEntity.status(404).body("Book not found.");
		}
			
	}
	

	@GetMapping("/name/{bookName}")
	public List<CatalogModel> getBookByName(@PathVariable String bookName){
		return catalogService.getBooksByName(bookName);
	}
	
	@GetMapping("/id/{bookId}")
	public Optional<CatalogModel> getBookById(@PathVariable int bookId) {
		return catalogService.getBookById(bookId);
	}
	
	
	@GetMapping("/genre/{bookGenre}")
	public List<CatalogModel> getBookByGenre(@PathVariable String bookGenre){
		return catalogService.getBooksByGenre(bookGenre);
	}
	
	@PutMapping("/update/{bookId}")
	public CatalogModel updateBookById(@PathVariable("bookId") int bookId,@RequestBody CatalogDTO catalogDTO) {
		return catalogService.updateBookById(bookId, catalogDTO);
	}
	
}
