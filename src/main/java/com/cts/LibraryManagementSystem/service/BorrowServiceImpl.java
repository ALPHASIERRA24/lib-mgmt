package com.cts.LibraryManagementSystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.dto.BorrowRecordDTO;
import com.cts.LibraryManagementSystem.dto.CatalogDTO;
import com.cts.LibraryManagementSystem.model.BorrowRecordModel;
import com.cts.LibraryManagementSystem.model.CatalogModel;
import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.repository.BorrowRecordRepository;
import com.cts.LibraryManagementSystem.repository.CatalogRepository;
import com.cts.LibraryManagementSystem.repository.UsersRepository;

@Service
public class BorrowServiceImpl implements BorrowService {
	
	@Autowired
	private BorrowRecordRepository borrowRecordRepository;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CatalogService catalogService;
	
	
	@Override
	public List<BorrowRecordModel> getAllBorrowRecord() {
		return borrowRecordRepository.findAll();
	}

	@Override
	public BorrowRecordModel addBorrowRecord(BorrowRecordDTO borrowRecordDTO) {
		UsersModel user = usersService.getUserById(borrowRecordDTO.getUserId())
				.orElseThrow(()-> new RuntimeException("User not found with ID: "+ borrowRecordDTO.getUserId()));
	
		CatalogModel book= catalogService.getBookById(borrowRecordDTO.getBookId())
				.orElseThrow(()-> new RuntimeException("Book not foubnd with ID: "+ borrowRecordDTO.getBookId()));  //when borrowing the book
		
		if(book.getStock() <= 0) {
			throw new RuntimeException("Book is out of Stock");
		}
		
		book.setStock(book.getStock() - 1);
		if(book.getStock() == 0){
			book.setAvailabilityStatus('N');
		}

		
		BorrowRecordModel borrowRecord = new BorrowRecordModel();
		borrowRecord.setUser(user);
		borrowRecord.setBook(book);
		borrowRecord.setBorrowDate(new Date(System.currentTimeMillis()));
		borrowRecord.setDueDate(borrowRecordDTO.getDueDate());
		borrowRecord.setReturnStatus(false);
		
		catalogService.updateAvailabilityStatus(book);
		
		return borrowRecordRepository.save(borrowRecord);
	}

	
	@Override
	public BorrowRecordModel returnBook(int borrowId,BorrowRecordDTO borrowDTO) {
		BorrowRecordModel borrowRecord =borrowRecordRepository.findById(borrowId)
				.orElseThrow(() -> new RuntimeException("Borrow Record not Found with ID: "+ borrowId));
		borrowRecord.setReturnStatus(borrowDTO.isReturnStatus());
		CatalogModel book = borrowRecord.getBook();
		book.setStock(book.getStock() + 1);
		
		if(book.getStock() > 0) {
			book.setAvailabilityStatus('Y');
		}
		catalogService.updateAvailabilityStatus(book);

		return borrowRecordRepository.save(borrowRecord);		
	}

	@Override
	public Optional<BorrowRecordModel> getBorrowRecordById(int borrowId) {
		return borrowRecordRepository.findById(borrowId);
	}

	@Override
	public List<BorrowRecordModel> getBorrowRecordByUserId(int userId) {
		return borrowRecordRepository.findByUser_UserId(userId);
	}

	@Override
	public List<BorrowRecordModel> getBorrowRecordByBookd(int bookId) {
		return borrowRecordRepository.findByBook_BookId(bookId);
	}

	@Override
	public List<BorrowRecordModel> getOverdueBorrowRecord(Date currectDate) {
		return borrowRecordRepository.findByDueDateBeforeAndReturnStatus(currectDate, false);
	}

	@Override
	public boolean deleteBorrowrecordById(int borrowId) {
		Optional<BorrowRecordModel> borrowRecord = borrowRecordRepository.findById(borrowId);
		if(borrowRecord.isPresent()) {
			CatalogModel book= borrowRecord.get().getBook();
			book.setAvailabilityStatus('Y');
			catalogService.updateAvailabilityStatus(book);
			
			borrowRecordRepository.deleteById(borrowId);
			return true;
		}
		return false;
	}
	
		
	
}
