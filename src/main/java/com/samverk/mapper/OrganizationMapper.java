
package com.samverk.mapper;

import com.samverk.domain.entity.Organization;
import com.samverk.dto.OrganizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface OrganizationMapper {

    @Mapping(target = "address", source = "address")
    @Mapping(target = "users", ignore = true)
    Organization toEntity(OrganizationDTO dto);
    
    @Mapping(target = "address", source = "address")
    OrganizationDTO toDto(Organization entity);
}
