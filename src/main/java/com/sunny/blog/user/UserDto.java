package com.sunny.blog.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String nickname;

    private String password;

    private Role role;

    // DTO -> Entity
    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .role(role.USER).build();
    }
}
