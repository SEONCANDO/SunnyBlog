package com.sunny.blog.user;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=50, unique = true)
    private String username; // 아이디

    @Column(nullable = false,  length=100)
    private String nickname;

    @Column(nullable = false, length=100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public static User createUser(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername(user.getUsername());
        user.setNickname(user.getNickname());
        String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.USER);
        return user;
    }
}
