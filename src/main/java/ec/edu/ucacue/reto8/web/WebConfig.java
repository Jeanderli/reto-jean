package ec.edu.ucacue.reto8.web;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Indicamos que esta clase es de configuraciÃ³n
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        // Establecemos el idioma por defecto (EspaÃ±ol)
        slr.setDefaultLocale(Locale.forLanguageTag("es"));
        return slr;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        // Indicamos que el parÃ¡metro para cambiar de idioma serÃ¡ "lang"
        lci.setParamName("lang");
        return lci;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }
     @Override
     public void addViewControllers(ViewControllerRegistry registro){
         registro.addViewController("/").setViewName("index");
         registro.addViewController("/login");
         registro.addViewController("/errores/403").setViewName ("/errores/403/");
     }
}