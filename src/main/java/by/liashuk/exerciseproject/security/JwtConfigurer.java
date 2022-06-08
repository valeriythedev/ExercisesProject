package by.liashuk.exerciseproject.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider provider;

    public JwtConfigurer(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtFilter filter = new JwtFilter(provider);
        httpSecurity.addFilterBefore(filter, AnonymousAuthenticationFilter.class);
    }
}
