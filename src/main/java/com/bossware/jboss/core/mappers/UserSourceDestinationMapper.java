package com.bossware.jboss.core.mappers;

import com.bossware.jboss.application.features.user.dtos.UserRequestDto;
import com.bossware.jboss.application.features.user.dtos.UserResponseDto;
import com.bossware.jboss.domain.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract  class UserSourceDestinationMapper {

    public abstract User reqToEntity(UserRequestDto dto);

    public abstract UserResponseDto entityToResp(User entity);

    public abstract List<UserResponseDto> entityToRespList(List<User> listEntity);

}
