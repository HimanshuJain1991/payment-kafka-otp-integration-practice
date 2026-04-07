package com.map.struct.exampl;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE=Mappers.getMapper(UserMapper.class);

    UserDTO entityToDTO(UserEntity userEntity);

    UserEntity dtoToEntity(UserDTO userDTO);

}
