package ec.edu.ucacue.reto8.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            // 🔴 IMPORTANTE: sin esto, Railway suele dar comportamientos raros
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // recursos públicos
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/resources/**",
                    "/errores/**"
                ).permitAll()

                // vistas principales (USER y ADMIN)
                .requestMatchers(
                    "/",
                    "/combustible",
                    "/conductores",
                    "/informacion",
                    "/mantenimiento",
                    "/reporteVehiculo"
                ).hasAnyRole("USER", "ADMIN")

                // acciones CRUD → SOLO ADMIN
                .requestMatchers(
                    "/registroUsuario",
                    "/nuevo**",
                    "/guardar**",
                    "/editar**/**",
                    "/eliminar**/**"
                ).hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )

            .exceptionHandling(ex -> ex
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
}
