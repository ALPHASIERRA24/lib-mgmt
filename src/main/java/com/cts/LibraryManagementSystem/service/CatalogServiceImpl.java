package com.cts.LibraryManagementSystem.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.dto.CatalogDTO;
import com.cts.LibraryManagementSystem.model.CatalogModel;
import com.cts.LibraryManagementSystem.repository.CatalogRepository;

@Service
public class CatalogServiceImpl implements CatalogService {
	
	@Autowired
	private CatalogRepository catalogRepo;

	@Override
	public List<CatalogModel> getAllBooks() {
		return catalogRepo.findAll();
	}

	@Override
	public List<CatalogModel> addBook(List<CatalogDTO> catalogDtoList) {
		List<CatalogModel> savedBooks = new ArrayList<>();
		
		for(CatalogDTO catalog : catalogDtoList) {
			CatalogModel catalogModel = CatalogModel.builder()
					.bookName(catalog.getBookName())
					.bookAuthor(catalog.getBookAuthor())
					.bookGenre(catalog.getBookGenre())
					.availabilityStatus(catalog.getAvailabilityStatus())
					.createdAt(new Timestamp(new Date(System.currentTimeMillis()).getTime()))
					.updatedAt(new Timestamp(new Date(System.currentTimeMillis()).getTime()))
					.build();
			savedBooks.add(catalogModel);
		}
		
		return catalogRepo.saveAll(savedBooks);
	}

	@Override
	public boolean deleteBookById(Integer bookId) {
	       if (!catalogRepo.existsById(bookId)) {
	            return false;
	        }
	        catalogRepo.deleteById(bookId);
	        return true;
	}

	@Override
	public CatalogModel updateBookById(int bookId, CatalogDTO catalogDTO) {
		CatalogModel catalogModel=catalogRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
		
		catalogModel.setBookName(catalogDTO.getBookName() != null ? catalogDTO.getBookName() : catalogModel.getBookName());
	    catalogModel.setBookAuthor(catalogDTO.getBookAuthor() != null ? catalogDTO.getBookAuthor() : catalogModel.getBookAuthor());
	    catalogModel.setBookGenre(catalogDTO.getBookGenre() != null ? catalogDTO.getBookGenre() : catalogModel.getBookGenre());
	    catalogModel.setAvailabilityStatus(catalogDTO.getAvailabilityStatus());
	    catalogModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		    
		return catalogRepo.save(catalogModel);
	}

	@Override
	public List<CatalogModel> getBooksByName(String bookName) {
		return catalogRepo.findByBookName(bookName);
	}

	@Override
	public Optional<CatalogModel>getBookById(int bookId) {
		return catalogRepo.findById(bookId);
	}

	@Override
	public List<CatalogModel> getBooksByGenre(String bookGenre) {
		return catalogRepo.findByBookGenre(bookGenre);
	}
	
	public void updateAvailabilityStatus(CatalogModel catalogModel) {
		catalogRepo.save(catalogModel);
	}

}
