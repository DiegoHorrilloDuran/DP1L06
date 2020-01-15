package acme.components;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class FormatterRegister implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        PhoneFormatter formater = new PhoneFormatter();

        registry.addFormatter(formater);
    }
}
