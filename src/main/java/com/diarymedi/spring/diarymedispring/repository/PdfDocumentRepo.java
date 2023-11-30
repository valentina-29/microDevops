package com.diarymedi.spring.diarymedispring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diarymedi.spring.diarymedispring.model.PdfDocument;

public interface PdfDocumentRepo extends JpaRepository<PdfDocument, Long>{
    
}
