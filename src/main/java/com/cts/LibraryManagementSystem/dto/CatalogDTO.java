package com.cts.LibraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDTO {                 
    private String bookName;
    private String bookAuthor;
    private String bookGenre;
    private char availabilityStatus;
}
