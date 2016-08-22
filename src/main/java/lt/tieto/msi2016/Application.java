package lt.tieto.msi2016;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JodaModule jodaModule = new JodaModule();
        mapper.registerModule(jodaModule);
        return mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
