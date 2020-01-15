package acme.components;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FormatterRegister implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        PhoneFormatter formater = new PhoneFormatter();

        registry.addFormatter(formater);
    }
}
