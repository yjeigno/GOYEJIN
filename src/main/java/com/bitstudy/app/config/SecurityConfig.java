package com.bitstudy.app.config;

/* spring security 를 설치하고 실행하면 어떤 url 로 접근을 하던 무조건 로그인 화면으로 넘어간다.
* "DefaultLoginPageGeneratingFilter" 검색해 보면 해당 파일에 맨 위에 "/login 이라고 있음
*  아직은 로그인 기능이 없으니까 요청하는 url 대로 화면이 나타나게 할 거
*
* 그래도 login 페이지가 없어지는 건 아니다. 주소 넣으면 나오긴 나온다.
* http://localhost:8080/articles => http://localhost:8080/login */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// @EnableWebSecurity // 이제 안 넣어도 됨(auto-configuration 에 들어가 있다.)
@Configuration // 설정파일 등록하는 애너테이션
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* HttpSecurity: 세부적인 보안 기능을 설정할 수 있는  api 를 제공
                        - URL 접근 권한 설정
                        - 커스텀 로그인 페이지 지원
                        - 인증 후 성공/실패 핸들링
                        - 사용자 로그인/로그아웃
                        - CSRF 공격으로부터 보호 (악성 웹사이트 공격??????)
        * */
        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // HttpSecurity 의 authorizeHttpRequests 에서 모든 요청(auth.anyRequest() 부분) 이 인증을 허용(permitAll() 부분) 하겠다.
                .formLogin() // 로그인 페이지를 만들고
                .and().build(); // 빌드 해라

        /* 여기 하고 다시 브라우저 가서 localhost:8080/articles 넣으면 해당 페이지로 바로 이동함 */


        /** formLogin() 이후 사용할 수 있는 메서드
         * http.formLogin()
                   .loginPage("/login.html")               // 커스텀 로그인 페이지 보여줄 때
                   . defaultSuccessUrl("/index")           // 로그인 성공 후 이동할 페이지 경로
                   . failureUrl("/login/html?error=true")  // 로그인 실패 후 이동할 페이지
                   . usernameParameter("아이디/유저네임")    // 아이디 파라미터명 설정
         */
    }
}
