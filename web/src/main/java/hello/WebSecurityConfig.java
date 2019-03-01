package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers(
            PageDefine.WELCOME.getViewController(), 
            PageDefine.INDEX.getViewController()
          ).permitAll().anyRequest().authenticated()
        .and().formLogin().loginPage(
            PageDefine.LOGIN.getViewController()
          ).permitAll()
        .and().logout().permitAll();
        //.and().requiresChannel().anyRequest().requiresSecure();
    }

    @Bean
    @Override
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
          .username("yoko")
          .password("lovelove")
          .roles("USER")
        .build();
        return new InMemoryUserDetailsManager(user);
    }

}