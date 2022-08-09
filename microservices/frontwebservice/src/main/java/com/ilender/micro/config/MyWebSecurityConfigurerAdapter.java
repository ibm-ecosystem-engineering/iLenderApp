package com.ilender.micro.config;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
//@EnableWebSecurity
//@EnableOAuth2Sso
@Order(99)
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http
//                .cors().and().authorizeRequests().antMatchers("/**").permitAll()
//                    .antMatchers("/", "/index.html","/user", "/userInfo", "/userName", "/login", "/error**")
//                    .permitAll()
//                    .antMatchers("**/api/**").permitAll()
////                    .authenticated()
//                    .and().logout().logoutSuccessUrl("/").permitAll()
//                    .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            ;
//            ;
//
//
//
//        }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////            http
////                .cors().and().authorizeRequests().antMatchers("/**").permitAll()
////                    .antMatchers("/", "/index.html","/user", "/userInfo", "/userName", "/login", "/error**")
////                    .permitAll()
////                    .antMatchers("**/api/**").permitAll()
//////                    .authenticated()
////                    .and().logout().logoutSuccessUrl("/").permitAll()
////                    .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////            ;
////            ;
////
//
//        http.csrf().disable().
//
//                authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/", "/index.html","/user", "/userInfo", "/userName", "/login", "/error**")
//                .permitAll()
//                .antMatchers("**/api/**").authenticated()
//                .and().httpBasic();
//
//
//        }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    //BASIC AUTH WORKS Well
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().
//
//                authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
//                .and().httpBasic();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().
//                authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
////                .antMatchers("/", "/**", "/index.html","/user", "/userInfo", "/userName", "/login", "/error**").permitAll()
//                .antMatchers("**/api/**").authenticated()
//                .anyRequest().permitAll()
//                .and().httpBasic();
//    }


        @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
            authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().permitAll()
            .and().httpBasic();
    }
}