package com.bossware.jboss.core.mappers;

import com.bossware.jboss.application.features.authority.dtos.AuthRequestDto;
import com.bossware.jboss.application.features.authority.dtos.AuthResponseDto;
import com.bossware.jboss.domain.entities.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuthSourceDestinationMapper {
    @Mapping(target="role.id", source="roleId")
    public abstract Auth reqToEntity(AuthRequestDto dto);

    @Mapping(target="roleId", source="entity.role.id")
    public abstract AuthResponseDto entityToResp(Auth entity);

    @Mapping(target="roleId", source="entity.role.id")
    public abstract List<AuthResponseDto> entityToRespList(List<Auth> listEntity);

}
