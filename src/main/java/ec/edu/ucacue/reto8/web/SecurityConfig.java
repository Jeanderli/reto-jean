package ec.edu.ucacue.reto8.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
               .authorizeHttpRequests(authorizeRequests -> authorizeRequests
    .requestMatchers("/login", "/img/**", "/resources/**", "/css/**", "/js/**").permitAll()

    // RUTAS PARA USER y ADMIN
    .requestMatchers(
        "/",
        "/combustible",
        "/conductores",
        "/informacion",
        "/mantenimiento",
        "/reporteVehiculo"
    ).hasAnyRole("USER", "ADMIN")

    // SOLO ADMIN (ajusté tus patrones para que sí agarren)
    .requestMatchers(
        "/registroUsuario",
        "/editar/**",
        "/agregar/**",
        "/eliminar/**"
    ).hasRole("ADMIN")

    .anyRequest().authenticated()
)

                .formLogin(formLogin -> formLogin
                .loginPage("/login") // Página de login personalizada
                .defaultSuccessUrl("/", true) // Redirigir después del login exitoso
                .permitAll() // Permitir acceso al formulario de login
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/errores/403")
                );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("admin")
//                        .password("{noop}123")
//                        .roles("ADMIN", "USER")
//                        .build(),
//                User.withUsername("user")
//                        .password("{noop}123")
//                        .roles("USER")
//                        .build()
//        );
//    }
    
    
}