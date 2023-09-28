package user.userauthtoken.usermapper;

import org.springframework.stereotype.Component;
import user.userauthtoken.dto.SignUpDto;
import user.userauthtoken.dto.UserDto;
import user.userauthtoken.model.UserData;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserData user);

    @Mapping(target = "password", ignore = true)
    UserData signUpToUser(SignUpDto signUpDto);

}
