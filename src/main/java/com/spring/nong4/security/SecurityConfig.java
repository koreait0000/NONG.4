package com.spring.nong4.security;

import com.spring.nong4.security.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetails;
    @Autowired
    private CustomOAuth2UserService customOauth2UserService;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/res/**" , "/pic/**", "/error", "favicon.ico");
      
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/login", "/user/join", "/user/auth","/index","/chat","/user/chkOverlap","/user/findUser","/user/findEmail","/user/findPw","/openapi/apiTest").permitAll()
                .anyRequest().authenticated();
        //.authenticated();

        http.formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("pw")
//                .successHandler(authenticationSuccessHandler)
                .defaultSuccessUrl("/board/home");

        http.oauth2Login()
                .loginPage("/user/login")
                .defaultSuccessUrl("/board/home")
                .failureUrl("/user/login")
                .userInfoEndpoint() //OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                .userService(customOauth2UserService);

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}
