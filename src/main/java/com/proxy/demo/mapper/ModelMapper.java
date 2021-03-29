package com.proxy.demo.mapper;

import com.proxy.demo.domain.User;
import com.proxy.demo.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapper {
    public List<UserDto> convertUserList(List<User> userList) {
        if(userList == null) {
            return new ArrayList<UserDto>();
        }
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        for(User u: userList) {
            userDtoList.add(
                    UserDto.builder()
                            .id(u.getId())
                            .username(u.getUsername())
                            .realName(u.getRealName())
                            .createdDate(u.getCreatedDate())
                            .modifiedDate(u.getModifiedDate())
                            .build()
            );
        }
        return userDtoList;
    }
}
