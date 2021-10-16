package com.josephrodriguez.learning.springboot.mapping;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.dto.csv.CsvDocumentDto;
import com.josephrodriguez.learning.springboot.dto.http.DocumentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DocumentDto fromDocumentEntity2Dto(Document document) {
        return modelMapper.map(document, DocumentDto.class);
    }

    public Document fromRestDocument2Entity(DocumentDto restDocumentDto) {
        return modelMapper.map(restDocumentDto, Document.class);
    }

    public DocumentDto fromCsv2RestDocument(CsvDocumentDto csvDocumentDto) {
        return modelMapper.map(csvDocumentDto, DocumentDto.class);
    }

    public CsvDocumentDto fromRest2CsvDocument(DocumentDto restDocumentDto) {
        return modelMapper.map(restDocumentDto, CsvDocumentDto.class);
    }
}
