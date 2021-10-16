package com.josephrodriguez.learning.springboot.mapping;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.dto.csv.CsvDocumentDto;
import com.josephrodriguez.learning.springboot.dto.http.RestDocumentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RestDocumentDto fromDocumentEntity2Dto(Document document) {
        return modelMapper.map(document, RestDocumentDto.class);
    }

    public Document fromRestDocument2Entity(RestDocumentDto restDocumentDto) {
        return modelMapper.map(restDocumentDto, Document.class);
    }

    public RestDocumentDto fromCsv2RestDocument(CsvDocumentDto csvDocumentDto) {
        return modelMapper.map(csvDocumentDto, RestDocumentDto.class);
    }

    public CsvDocumentDto fromRest2CsvDocument(RestDocumentDto restDocumentDto) {
        return modelMapper.map(restDocumentDto, CsvDocumentDto.class);
    }
}
