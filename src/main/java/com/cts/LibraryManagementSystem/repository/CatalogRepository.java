package com.cts.LibraryManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.LibraryManagementSystem.model.CatalogModel;


@Repository

public interface CatalogRepository extends JpaRepository<CatalogModel, Integer>{

	List<CatalogModel> findByBookName(String bookName);

    List<CatalogModel> findByBookGenre(String bookGenre);
    
    Optional<CatalogModel> findById(int bookId);
    
    
    
}
