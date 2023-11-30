package com.diarymedi.spring.diarymedispring.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.diarymedi.spring.diarymedispring.model.PdfDocument;
import com.diarymedi.spring.diarymedispring.repository.PdfDocumentRepo;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/pdf")
public class PdfController {

    @Autowired
    PdfDocumentRepo pdfDocumentRepo;
    
    @PostMapping("/upload")
    String uploadPdf(@RequestPart("pdf_data") MultipartFile file, @RequestParam("userId") String userId, @RequestParam("medicoId") String medicoId){
        try {
            PdfDocument pdf = new PdfDocument();
            pdf.setPdf_data(file.getBytes());
            pdf.setNombre_Archivo(file.getOriginalFilename());
            pdf.setIdPaciente(userId);
            pdf.setIdMedico(medicoId);
            pdfDocumentRepo.save(pdf);
            return "Guardado correctamente";
        } catch (Exception e) {
            return "Error al guardar el pdf: "+ e.getMessage();
        }
    }

    @GetMapping("/list")
    public List<PdfDocument> listPdfs() {
        return pdfDocumentRepo.findAll();
    }

    @GetMapping("/download/{pdfId}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long pdfId) {
        Optional<PdfDocument> pdfDocumentOptional = pdfDocumentRepo.findById(pdfId);

        if (pdfDocumentOptional.isPresent()) {
            PdfDocument pdfDocument = pdfDocumentOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "document.pdf");
            headers.setCacheControl(CacheControl.noCache());

            return new ResponseEntity<>(pdfDocument.getPdf_data(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*ALTER TABLE `user_login`.`pdf_document`
MODIFY pdf_data LONGBLOB; para que permita agregar datos grandes a la bd*/ 

}