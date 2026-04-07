package com.map.struct.exampl;

public class MapStructMainClass {
    public static void main(String[] args) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Himanshu Jain");
        userEntity.setEmail("Himanshujain119@yahoo.com");

        UserDTO userDTO =UserMapper.INSTANCE.entityToDTO(userEntity);
        System.out.println(userDTO.getName()+" "+userDTO.getEmail());

        com.map.struct.exampl.UserEntity userEntity1 =UserMapper.INSTANCE.dtoToEntity(userDTO);
        System.out.println(userEntity1.getName());
        System.out.println(userEntity1.getEmail());
        System.out.println(userEntity1.getId());

    }
}
