package com.tim26.AuthenticationService.config;

import com.tim26.AuthenticationService.security.TokenUtils;
import com.tim26.AuthenticationService.security.auth.RestAuthenticationEntryPoint;
import com.tim26.AuthenticationService.security.auth.TokenAuthenticationFilter;
import com.tim26.AuthenticationService.service.UServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private UServiceImpl jwtUserDetailsService;

    // Neautorizovani pristup zastcenim resursima
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Definisemo nacin autentifikacije
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // komunikacija izmedju klijenta i servera je stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // za neautorizovane zahteve posalji 401 gresku
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                // svim korisnicima dopusti da pristupe putanjama /auth/login, /auth/register, /activate
                .authorizeRequests()
                .antMatchers("/api/users/confirm-account/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/users/get-id").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/verify/**").permitAll()
                .antMatchers("/api/ads/all").permitAll()
                .antMatchers("/api/ads/one/{id}").permitAll()
                .antMatchers("/api/ads/car/{id}").permitAll()
                .antMatchers("/api/ads/filter").permitAll()
                .antMatchers("/api/review/by-ad-approved/{id}").permitAll()
                .antMatchers("/api/users/check**").permitAll()


                // svaki zahtev mora biti autorizovan
                .anyRequest().authenticated().and()

                .cors().and()


                // presretni svaki zahtev filterom
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
                        BasicAuthenticationFilter.class)

                .headers().frameOptions().disable();

        http.csrf().disable();

    }

    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers(HttpMethod.POST, "/api/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");
        web.ignoring().antMatchers(HttpMethod.POST,"/api/auth/login");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/ads/all");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/ads/filter");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/ads/one/{id}");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/ads/car/{id}");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/review/by-ad-approved/{id}");
        web.ignoring().antMatchers(HttpMethod.GET,"/api/users/check**");

    }

}
