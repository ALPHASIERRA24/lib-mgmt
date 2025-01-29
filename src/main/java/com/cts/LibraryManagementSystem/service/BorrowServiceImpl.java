package com.cts.LibraryManagementSystem.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.dto.BorrowRecordDTO;
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
				.orElseThrow(()-> new RuntimeException("Book not foubnd with ID: "+ borrowRecordDTO.getBookId()));
		
		if(book.getAvailabilityStatus() == 'N') {
			throw new RuntimeException("Book is not available for borrowing.");
		}
		
		BorrowRecordModel borrowRecord = new BorrowRecordModel();
		borrowRecord.setUser(user);
		borrowRecord.setBook(book);
		borrowRecord.setBorrowDate(borrowRecordDTO.getBorrowDate());
		borrowRecord.setDueDate(borrowRecordDTO.getDueDate());
		borrowRecord.setReturnStatus(false);
		
		book.setAvailabilityStatus('N');
		catalogService.updateAvailabilityStatus(book);
		
		return borrowRecordRepository.save(borrowRecord);
	}

	
	@Override
	public BorrowRecordModel updateReturnStatus(int borrowId,BorrowRecordDTO borrowDTO) {
		BorrowRecordModel borrowRecord =borrowRecordRepository.findById(borrowId)
				.orElseThrow(() -> new RuntimeException("Borrow Record not Found with ID: "+ borrowId));
		
//		System.out.println(borrowRecord);
		borrowRecord.setReturnStatus(borrowDTO.isReturnStatus());
		
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
