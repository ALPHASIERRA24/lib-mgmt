package com.cts.LibraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.dto.CatalogDTO;
import com.cts.LibraryManagementSystem.model.CatalogModel;

@Service
public interface CatalogService {

	public List<CatalogModel> getAllBooks();
	
	CatalogModel addBook(CatalogDTO catalogDto);
	
	boolean deleteBookById(Integer bookId);
	
	public List<CatalogModel> getBooksByName(String bookName);
	
	public Optional<CatalogModel> getBookById(int bookId);
	
	public List<CatalogModel> getBooksByGenre(String bookGenre);

	CatalogModel updateBookById(int bookId, CatalogDTO catalogDTO);
	
	public void updateAvailabilityStatus(CatalogModel catalogModel);
	
	
}
