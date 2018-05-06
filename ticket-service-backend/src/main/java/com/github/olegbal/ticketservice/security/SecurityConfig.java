package com.github.olegbal.ticketservice.security;

import com.github.olegbal.ticketservice.filters.JWTAuthenticationFilter;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserInfoService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //permitting access to all resources
                .antMatchers("/favicon.ico", "/**.html", "/**.css", "/**.js").permitAll()
                //Registration and authorization mapping mapping security
                .antMatchers(V1 + "/register", V1 + "/login").permitAll()
                //                EVENTS SECURITY
                .antMatchers(HttpMethod.GET, V1 + "/events/{id}", V1 + "/events").permitAll()
                .antMatchers(HttpMethod.DELETE, V1 + "/events/{id}").permitAll() //.hasAnyRole("ADMIN", "ORGANIZER")
                .antMatchers(HttpMethod.POST, V1 + "/events").permitAll()//.hasAnyRole("ADMIN", "ORGANIZER")
                .antMatchers(HttpMethod.PUT, V1 + "/events").permitAll()//.hasAnyRole("ADMIN", "ORGANIZER")
                //                END OF EVENTS SECURITY
                //                EVENT PLACES
                .antMatchers(HttpMethod.GET, V1 + "/event-places").permitAll()
                .antMatchers(HttpMethod.POST, V1 + "/event-places").permitAll()      //.hasAnyRole("ADMIN", "ORGANIZER")
                .antMatchers(HttpMethod.PUT, V1 + "/event-places").permitAll()      //.hasAnyRole("ADMIN", "ORGANIZER")
                .antMatchers(HttpMethod.DELETE, V1 + "/event-places").permitAll()  //.hasAnyRole("ADMIN", "ORGANIZER")
                //                END OF EVENT PLACES SECURITY
                //                ORDERS
                .antMatchers(V1 + "/orders**").permitAll()
                //                END OF ORDERS
                .antMatchers(V1 + "/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //Disabling session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}