package com.sunny.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

//    @Transactional
//    public Long join(UserDto dto){
//        dto.setPassword(encoder.encode(dto.getPassword()));
//        return userRepository.save(dto.toEntity().getUsername());
//    }

    public User saveUser(User user){
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByUsername(user.getUsername());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}

