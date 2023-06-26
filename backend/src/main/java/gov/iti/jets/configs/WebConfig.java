package gov.iti.jets.configs;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@MultipartConfig
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET","POST","PUT","DELETE", "PATCH")
                .allowedHeaders("*");
    }
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSize(10485760); // Set your desired maximum file size (in bytes)
//        return resolver;
//    }
//@Bean
//public StandardServletMultipartResolver multipartResolver() {
//    return new StandardServletMultipartResolver();
//}
}
