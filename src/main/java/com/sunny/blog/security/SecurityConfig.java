package com.sunny.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        //h2-console 사용에 대한 허용
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //회원 관리 처리 API(POST/user/**)에 대해 CSRF 무시
        http.csrf()
                        .ignoringAntMatchers("/user/**");
        http
                .authorizeHttpRequests((authz) -> authz
                        // ~ 폴더를 로그인 없이 허용
                        .antMatchers("/images/**").permitAll()
                        .antMatchers("/css/**").permitAll()
                       // 회원관리 처리 전부를 로그인 없이 허용
                        .antMatchers("/user/**").permitAll()
                        //어떤 요청이든 인증
                        .anyRequest().authenticated()
                )
                //로그인 기능 허용
                .formLogin()
                //로그인 뷰 제공(Post/user/login)
                .loginPage("/user/login")
                //로그인 처리(POST/user/login)
                .loginProcessingUrl("/user/login")
                //로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
                //로그인 처리 후 실패 시 URL
                .failureUrl("/user/login?error")
                .permitAll()
                .and()
                //로그아웃 기능 허용
                .logout()
                .permitAll();
        return http.build();
    }

    @Bean
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admintest").password(passwordEncoder().encode("admintest")).roles("ADMIN")
                .and()
                .withUser("usertest").password(passwordEncoder().encode("usertest")).roles("USER");

    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // antMatchers 부분도 deprecated 되어 requestMatchers로 대체
//        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
//    }

    }

