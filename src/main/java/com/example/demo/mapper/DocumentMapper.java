package com.example.demo.mapper;

import com.example.demo.dto.DocumentCreateDTO;
import com.example.demo.dto.DocumentDTO;
import com.example.demo.model.entity.Document;
import com.example.demo.model.enums.DocumentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DocumentMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "validatedBy.id", target = "validatedById")
    @Mapping(source = "validatedBy.fullName", target = "validatedByName")
    DocumentDTO toDTO(Document document);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "filePath", ignore = true)
    @Mapping(target = "fileName", ignore = true)
    @Mapping(target = "fileType", ignore = true)
    @Mapping(target = "fileSize", ignore = true)
    @Mapping(target = "status", constant = "EN_ATTENTE")
    @Mapping(target = "validationDate", ignore = true)
    @Mapping(target = "accountantComment", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "validatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Document toEntity(DocumentCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "filePath", ignore = true)
    @Mapping(target = "fileName", ignore = true)
    @Mapping(target = "fileType", ignore = true)
    @Mapping(target = "fileSize", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "validationDate", ignore = true)
    @Mapping(target = "accountantComment", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "validatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(DocumentCreateDTO dto, @MappingTarget Document document);
}
