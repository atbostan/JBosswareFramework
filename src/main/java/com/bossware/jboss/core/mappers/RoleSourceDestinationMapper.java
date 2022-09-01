package com.bossware.jboss.core.mappers;


import com.bossware.jboss.application.features.role.dtos.RoleRequestDto;
import com.bossware.jboss.application.features.role.dtos.RoleResponseDto;
import com.bossware.jboss.domain.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoleSourceDestinationMapper {

    @Mapping(target="user.id", source="userId")
    public abstract Role reqToEntity(RoleRequestDto dto);

    @Mapping(target="userId", source="entity.user.id")
    public abstract RoleResponseDto entityToResp(Role entity);

    @Mapping(target="userId", source="entity.user.id")
    public abstract List<RoleResponseDto> entityToRespList(List<Role> listEntity);

}
