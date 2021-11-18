package com.josephrodriguez.learning.springboot.services.mapping;

import com.josephrodriguez.learning.springboot.data.entity.DocumentEntity;
import com.josephrodriguez.learning.springboot.dto.csv.DocumentCsvDto;
import com.josephrodriguez.learning.springboot.dto.http.DocumentRestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DocumentRestDto fromEntity2Dto(DocumentEntity document) {
        return modelMapper.map(document, DocumentRestDto.class);
    }

    public DocumentEntity fromRest2Entity(DocumentRestDto document) {
        return modelMapper.map(document, DocumentEntity.class);
    }

    public DocumentRestDto fromCsv2Rest(DocumentCsvDto document) {
        return modelMapper.map(document, DocumentRestDto.class);
    }

    public DocumentCsvDto fromRest2Csv(DocumentRestDto restDto) {
        return modelMapper.map(restDto, DocumentCsvDto.class);
    }
}
