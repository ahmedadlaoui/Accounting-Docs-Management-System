package com.example.demo.mapper;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {

    CompanyDTO toDTO(Company company);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "documents", ignore = true)
    Company toEntity(CompanyDTO dto);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "documents", ignore = true)
    void updateEntityFromDTO(CompanyDTO dto, @MappingTarget Company company);
}
