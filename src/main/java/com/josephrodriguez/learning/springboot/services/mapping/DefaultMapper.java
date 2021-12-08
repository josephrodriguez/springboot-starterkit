package com.josephrodriguez.learning.springboot.services.mapping;

import com.josephrodriguez.learning.springboot.data.entity.DocumentEntity;
import com.josephrodriguez.learning.springboot.dto.csv.DocumentCsvDto;
import com.josephrodriguez.learning.springboot.dto.http.DocumentRestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMapper {

    private final ModelMapper mapper;

    public DocumentRestDto fromEntity2Dto(DocumentEntity document) {
        return mapper.map(document, DocumentRestDto.class);
    }

    public DocumentEntity fromRest2Entity(DocumentRestDto document) {
        return mapper.map(document, DocumentEntity.class);
    }

    public DocumentRestDto fromCsv2Rest(DocumentCsvDto document) {
        return mapper.map(document, DocumentRestDto.class);
    }

    public DocumentCsvDto fromRest2Csv(DocumentRestDto restDto) {
        return mapper.map(restDto, DocumentCsvDto.class);
    }
}
