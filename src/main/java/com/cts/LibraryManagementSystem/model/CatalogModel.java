package com.cts.LibraryManagementSystem.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Book_Catalog")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogModel  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalog_seq")
	@SequenceGenerator(name = "catalog_seq", sequenceName = "catalog_seq", allocationSize = 1)
    private int bookId;                     
    private String bookName;
    private String bookAuthor;
    private String bookGenre;
    private char availabilityStatus;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    
    @JsonBackReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowRecordModel> borrowRecords;
 

}